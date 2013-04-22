/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.chargingcontroller;
import java.util.*;
import java.io.*;
import gnu.io.*;
import java.lang.*;

/**
 *
 * @author This PC
 */
public class DataCollector implements Runnable{
    
    private MainController mainController = null;
    private RealTimeData realTimeData = null;
    private ChargingParameters chargingParameters = null;
    private byte[] boardAddresses = null;
    private SerialWriter[] sw = null;
    private SerialReader sr = null;
    private Thread readerThread = null;
    private Thread[] writerThreads = null;
    //private SerialWriter sw = null;
    private volatile Thread writerThread = null;
    
    
    private String stringBuffer = null;
    private String portnameToBMS = null;
    private String portnameToArduino = null;
    private SerialPort portToBMS = null;
    private SerialPort portToArduino = null;
    
    private int writerIndexForBMS = 0;
    private int writerIndexForArduino = 0;
    
    public DataCollector(MainController _mainController, RealTimeData _realTimeData, ChargingParameters _chargingParameters)
    {
        this.mainController = _mainController;
        this.chargingParameters = _chargingParameters;
        this.realTimeData = _realTimeData;
        this.lookForPorts();
        //this.mainController.setChargingParameters(this.chargingParameters);
        this.boardAddresses = new byte[8];
        
        this.sr = new SerialReader();
        readerThread = new Thread(this.sr);
        readerThread.start();
        
        
        this.sw = new SerialWriter[8];
        writerThreads = new Thread[8];
        
        //this.sw = new SerialWriter();
        //this.writerThread = new Thread(this.sw);
        

        for(int i = 0; i < 8; i++)
        {
            this.boardAddresses[i] = (byte)(i);
            sw[i] = new SerialWriter();
            writerThreads[i] = new Thread(sw[i]);
        }
        stringBuffer = "";
    }
    
    public void updateStringBuffer(String _stringBuffer)
    {
        this.stringBuffer = _stringBuffer;
    }
    
    public boolean getPortNames() throws Exception
    {
        SerialPort serialPort = null;
        boolean BMSfound = false;
        boolean ArduinoFound = false;
        
        //for (int i = 0; i < this.chargingParameters.getListOfPorts().size(); i++)
        for (int i = (this.chargingParameters.getListOfPorts().size()-1); i >=0; i--)
        {
            //System.out.println("---------Current port is: "+i+"/"+(this.chargingParameters.getListOfPorts().size()-1)+"-----");
            //System.out.println("try one port:" + this.chargingParameters.getListOfPorts().get(i));
            
            try{
                serialPort = this.connect(this.chargingParameters.getListOfPorts().get(i));
            }catch(PortInUseException e)
            {
                continue;
            }
            
            InputStream in = serialPort.getInputStream();
            OutputStream out = serialPort.getOutputStream();
            
            
            this.sr.setInputStream(in);
            this.sr.setDataCollector(this);
            this.sr.startThread();
                      
            this.sw[i].setOutputStream(out);
            if(!this.writerThreads[i].isAlive())
            {
                this.writerThreads[i].start();
            }
            Thread.sleep(1000);

            this.sw[i] = new SerialWriter();
            this.writerThreads[i] = new Thread(this.sw[i]);
            
            if(this.stringBuffer.equals("0042"))
            {
                System.out.println("BMS board found");
                //System.out.println(this.chargingParameters.getListOfPorts().get(i));
                this.portnameToBMS = this.chargingParameters.getListOfPorts().get(i);
                this.portToBMS = serialPort;
                this.writerIndexForBMS = i;
                BMSfound = true;
            }else if(this.stringBuffer.equals("Ardui")||this.stringBuffer.equals("Ardu"))
           // }else if(this.stringBuffer.charAt(0) == 'A')
            {
                System.out.println("Arduino Board found");
                //System.out.println(this.chargingParameters.getListOfPorts().get(i));
                this.portToArduino = serialPort;
                this.portnameToArduino = this.chargingParameters.getListOfPorts().get(i);
                this.writerIndexForArduino = i;
                ArduinoFound = true;
            }else
            {
                System.out.println("Incorrect port");
                Thread.sleep(1000);
                if(!this.stringBuffer.equals(""))
                {
                    System.out.println("haha"+this.stringBuffer+"haha");
                }
                
                /*  Dear future engineers, if you could make the following calls
                 *  non blocking, please enlighten me.
                 */
                //this.portToBMS.getInputStream().close();
                //this.portToBMS.getOutputStream().close();
                //this.portToBMS.removeEventListener();
                //this.portToBMS.close();
            }
            this.stringBuffer = "";
            this.sr.stopThread();
            if(BMSfound && ArduinoFound)
            {
                return true;
            }
        }
        return false;
    }
    
    public void writeToFile(String content)
    {
//        try {
//                File file = new File("C:\\Documents and Settings\\This PC\\My Documents\\ChargingCharacteristics.txt");
//
//                if (!file.exists()) {
//                        file.createNewFile();
//                }
//                
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Documents and Settings\\This PC\\My Documents\\ChargingCharacteristics.txt", true)));
                    out.println(content);
                    out.close();
                } catch (IOException e) {
                    //oh noes!
                }

                //file.setReadOnly();
                
//        } catch (IOException e) {
//                e.printStackTrace();
//        }
    }
    
    public double getCellVoltage(int index)
    {
        double v_ref = 2.048;
        int v_v = 0;
        double actual_voltage = 0;
        byte[] temp = {'0','0','1','0','R','0','2'};
        if(index <= 4)
        {
            temp[1] += 2*index;
        }else{
            temp[1] = 'A';
            temp[1] += (byte)(2*(index-5));
        }
        
        System.out.println("The offset this time is:" + temp[1]);
        try{
            
            System.out.println("About to read for testing cell");
            this.sr.startThread();

            
            this.sw[this.writerIndexForBMS].writeToSerial(temp);
            Thread.sleep(1000);
            if(!this.stringBuffer.equals("FFFF"))
            {
                System.out.println("I got a valid reading!");
                v_v = Integer.parseInt(this.stringBuffer, 16);
                actual_voltage = 4.096 - v_ref*(v_v/(Math.pow(2,10) - 1));
                int temp_result = (int)(actual_voltage*100);
                actual_voltage = ((double)(temp_result))/100;
                
                String stringToWrite = "";
                stringToWrite += System.currentTimeMillis();
                stringToWrite += "\t";
                stringToWrite += actual_voltage;
                stringToWrite += "\n";
                
                this.writeToFile(stringToWrite);
                
                return actual_voltage;
            }else{
               System.out.println("Couldn't get any reading");
            }
        
        }catch(Exception e)
        {
            
        }
        return 0;
    }
    
    public int getCellTemp(int index)
    {
        double v_ref = 2.048;
        int v_t = 0;
        int actual_temperature = 0;
        byte[] temp = {'0','0','1','1','R','0','2'};
        if(index <= 4)
        {
            temp[1] += 2*index;
        }else{
            temp[1] = 'A';
            temp[1] += (byte)(2*(index-5));
        }
        
        System.out.println("The offset this time is:" + temp[1]);
        try{
            
            System.out.println("About to read for testing cell");
            this.sr.startThread();

            
            this.sw[this.writerIndexForBMS].writeToSerial(temp);
            Thread.sleep(100);
            if(!this.stringBuffer.equals("FFFF"))
            {
                System.out.println("I got a valid reading!");
                v_t = Integer.parseInt(this.stringBuffer, 16);
                actual_temperature = (int)((v_ref*(v_t/(Math.pow(2,10) - 1)) - 0.5)/0.01);
                return actual_temperature;
            }else{
               System.out.println("Couldn't get any reading");
            }
        
        }catch(Exception e)
        {
            
        }
        return 0;
    }
    
    public double getPackCurrent()
    {
        double v_ref = 2.048;
        double gain = 305;
        double v_off = -0.0022;
//        double resistance_bar = 1;
        double resistance_bar = 0.000021;

        int v_ic = 0;
        double actual_current = 0;
        byte[] temp = {'0','2','1','2','R','0','2'};
        
        try{
            
            System.out.println("About to read for testing cell");
            this.sr.startThread();

            
            this.sw[this.writerIndexForBMS].writeToSerial(temp);
            Thread.sleep(100);
            if(!this.stringBuffer.equals("FFFF"))
            {
                System.out.println("I got a valid reading!");
                v_ic = Integer.parseInt(this.stringBuffer, 16);
                actual_current = ((v_ref/gain)*(v_ic/(Math.pow(2,10) - 1)) + v_off)/resistance_bar;
                int temp_result = (int)(actual_current*100);
                actual_current = ((double)(temp_result))/100;
                return actual_current;
            }else{
               System.out.println("Couldn't get any reading");
            }
        
        }catch(Exception e)
        {
            
        }
        return 0;
    }
    
    public long getBypassTime(int index)
    {
        String min = "";
        String sec = "";
        String millisec = "";
        long time_min = 0;
        long time_sec = 0;
        long time_millisec = 0;
        long timeToReturn = 0;
        
        byte[] temp = {'0','0','1','E','R','0','6'};
        if(index <= 4)
        {
            temp[1] += 2*index;
        }else{
            temp[1] = 'A';
            temp[1] += (byte)(2*(index-5));
        }
        
        System.out.println("The offset this time is:" + temp[1]);
        try{
            this.sr.readThreshold = 11;
            this.sr.startThread();
            
            this.sw[this.writerIndexForBMS].writeToSerial(temp);
            Thread.sleep(1000);
            if(!this.stringBuffer.equals("FFFF"))
            {
                min = this.stringBuffer.substring(0, 4);
                System.out.println("I got a valid reading: " + this.stringBuffer);
                System.out.println("This is the minutes: " + min);
                time_min = Long.parseLong(min, 16);
                System.out.println("This is the minutes in integer:" + time_min);
                sec = this.stringBuffer.substring(4, 8);
                System.out.println("This is the sec: " + sec);
                time_sec = Long.parseLong(min, 16);
                System.out.println("This is the secs in integer:" + time_sec);
                millisec = this.stringBuffer.substring(8, 12);
                System.out.println("This is the sec: " + millisec);
                time_millisec = Long.parseLong(min, 16);
                System.out.println("This is the millisecs in integer:" + time_millisec);
                timeToReturn = (time_min << 32) + (time_sec << 16) + time_millisec;
                this.sr.readThreshold = 4;
                System.out.println(timeToReturn);
                return timeToReturn;
            }else{
               System.out.println("Couldn't get any reading");
            }
        
        }catch(Exception e)
        {
            
        }
        return 0;
    }
    
    public void setBypassSwitch(boolean isOn)
    {
        //need to talk to BMS
    }
    
    public int getBypassState(int index)
    {
        byte[] temp = {'0','0','1','4','R','0','2'};
        if(index <= 4)
        {
            temp[1] += 2*index;
        }else{
            temp[1] = 'A';
            temp[1] += (byte)(2*(index-5));
        }
        
        System.out.println("The offset this time is:" + temp[1]);
        try{
            
            this.sr.startThread();

            
            this.sw[this.writerIndexForBMS].writeToSerial(temp);
            Thread.sleep(10);
            if(!this.stringBuffer.equals("FFFF"))
            {
                if(this.stringBuffer.equals("0000"))
                {
                    System.out.println("Bypass off");
                    return 0;
                }else if(this.stringBuffer.equals("0001"))
                {
                    System.out.println("Bypass on");
                    return 1;
                }else
                {
                    System.out.println("Read some garbage");
                    return -1;
                }
            }else{
               System.out.println("Couldn't get any reading");
            }
        
        }catch(Exception e)
        {
            
        }
        return -1;
    }
    
    public void setBoardAddress()
    {
        //need instruction
    }
    
    SerialPort connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(57600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_OUT);
                
                //InputStream in = serialPort.getInputStream();
                //OutputStream out = serialPort.getOutputStream();
                return serialPort;
            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
                return null;
            }
        }
        return null;
    }
    
    public void lookForPorts() {
        ArrayList<String> listOfPorts = this.chargingParameters.getListOfPorts();
        if(listOfPorts == null)
        {
            return;
        }
        
        if(!listOfPorts.isEmpty())
        {
            listOfPorts.clear();
        } 
        System.out.println("Starting to search for ports.  Available ports are:");
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
            case CommPortIdentifier.PORT_SERIAL:
                try {
                    CommPort thePort = com.open("CommUtil", 50);
                    thePort.close();
                    if(!listOfPorts.contains(com.getName()))
                    {
                        listOfPorts.add(com.getName());
                    }
                    System.out.print(com.getName()+"\r\n");
                } catch (PortInUseException e) {
                    //System.out.println("Port, "  + com.getName() +  ", is in use.");
                    System.out.println("Port"+ com.getName() + "already used found when looking for ports");
                } catch (Exception e) {
                    System.err.println("Failed to open port " + com.getName());
                    e.printStackTrace();
                }
            }
        }
        
        this.chargingParameters.setListOfPorts(listOfPorts);
    }
    
    
    public boolean testCell(int index)
    {       
        byte[] temp = {'0','0','1','7','R','0','2'};
        if(index <= 4)
        {
            temp[1] += 2*index;
        }else{
            temp[1] = 'A';
            temp[1] += (byte)(2*(index-5));
        }
        
        System.out.println("The offset this time is:" + temp[1]);
        try{
            
            System.out.println("About to read for testing cell");
            this.sr.startThread();

            
            this.sw[this.writerIndexForBMS].writeToSerial(temp);
            Thread.sleep(1000);
            if(this.stringBuffer.equals("0042"))
            {
                System.out.println("test passed!");
                return true;
            }else{
                System.out.println("This is what I got: " + this.stringBuffer);
            }
        
        }catch(Exception e)
        {
            
        }
        return false;
    }
    
    public void run()
    {
        boolean errorOccurred;
        String tempPortName = "";
        double currentReading = 0;
        double voltageReading = 0;
        int tempReading = 0;
        int bypassState = -1;
        this.lookForPorts();
        try{
            if(getPortNames())
            {
                InputStream in = this.portToBMS.getInputStream();
                OutputStream out = this.portToBMS.getOutputStream();

                this.sr.setInputStream(in);
                this.sr.setDataCollector(this);
                this.sw[this.writerIndexForBMS].setOutputStream(out);
                while(true)
                {
                    this.realTimeData = this.mainController.getRealTimeData();
                    this.chargingParameters = this.mainController.getChargingParameters();
                    this.chargingParameters.setPortToArduino(portnameToArduino);
                    this.chargingParameters.setPortToBMS(portnameToBMS);
                    this.realTimeData.setErrorMessage("");
                    this.realTimeData.setErrorOccurred(false);
                    
                    System.out.println("I'm here!");
                    errorOccurred = false;
                    currentReading = this.getPackCurrent();
                    if( currentReading != 0 )
                    {
                        this.realTimeData.setCurrent(currentReading);
                        System.out.println(currentReading);
                    }else{
                        //errorOccurred = true;
                        //this.realTimeData.setErrorMessage(this.realTimeData.getErrorMessage() + "Cannot Read Current Value \n");
                    }

                    for(int i = 1; i <= 8; i++)
                    {
                        System.out.println("About to test cell "+i);
                        if(this.testCell(i))
                        {
                            System.out.println("Cell "+i +" passed!");
                            voltageReading = this.getCellVoltage(i);
                            if(  voltageReading != 0 )
                            {
                                this.realTimeData.setVoltage(i-1, voltageReading);
                                System.out.println(voltageReading);
                            }else{
                                errorOccurred = true;
                                this.realTimeData.setErrorMessage(this.realTimeData.getErrorMessage() + "Cannot Read Voltage Value \n");
                            }
                            
                            tempReading = this.getCellTemp(i);
                            if(  tempReading != 0 )
                            {
                                this.realTimeData.setTemperature(i-1, tempReading);
                                System.out.println(tempReading);
                            }else{
                                errorOccurred = true;
                                this.realTimeData.setErrorMessage(this.realTimeData.getErrorMessage() + "Cannot Read Temperature Value \n");
                            }
                            
                            bypassState = this.getBypassState(i);
                            if( bypassState == 1)
                            {
                                this.realTimeData.setBypassInfo(i-1, true);
                                this.realTimeData.setBypassTime(i-1, this.getBypassTime(i));
                            }else if(bypassState == 0)
                            {
                                this.realTimeData.setBypassInfo(i-1, false);
                            }else if( bypassState == -1 )
                            {
                                errorOccurred = true;
                                this.realTimeData.setErrorMessage(this.realTimeData.getErrorMessage() + "Cannot Read Bypass State \n");
                            }
                            
                        }else if(i != 1)
                        {
                            this.chargingParameters.setNumOfCells(i-1);
                            System.out.println("The number of cells is: "+this.chargingParameters.getNumOfCells());
                            break;
                        }else
                        {
                            this.realTimeData.setErrorOccurred(true);
                            this.realTimeData.setErrorMessage(this.realTimeData.getErrorMessage()+"Cell"+i+"is not responding\r\n");
                            errorOccurred = true;
                        }
                    }
                    
                    if(!errorOccurred)
                    {
                        this.realTimeData.setErrorOccurred(false);
                    }
                    
                    this.mainController.setRealTimeData(this.realTimeData);
                    this.mainController.setChargingParameters(this.chargingParameters);
                    
//                    try{
//                        Thread.sleep(100);
//                    }catch(Exception e)
//                    {
//
//                    }
                }
            }else
            {
                //error handling
            }
        }catch(Exception e)
        {
            //error handling
        }
        
    }
    

}
