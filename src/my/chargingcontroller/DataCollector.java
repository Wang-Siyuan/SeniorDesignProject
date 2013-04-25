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
public class DataCollector extends Thread{
    
    private static final String BMS_CONFIRM = "0042";
    private static final String ARDUINO_CONFIRM_ONE = "Ardui";
    private static final String ARDUINO_CONFIRM_TWO = "Ardu";
    private static final byte ARDUINO_COMMAND_ON = 'a';
    private static final byte ARDUINO_COMMAND_OFF = 'b';
    private static final int SLEEP_TIME_FOR_READER = 1000;
    private static final int SLEEP_TIME_BTW_EACH_DATA_ACQUISITION = 100;
    private static final String BMS_INVALID_MSG = "FFFF";
    private static final String BYPASS_ON = "0001";
    private static final String BYPASS_OFF = "0000";
    private static final String BMS_VALID_TEST = "0042";
    private static final String PATH_FOR_DATA_RECORDING = "";
    private static final String PATH_FOR_ERROR_LOG = "";
    private static final String FILE_NAME_FOR_DATA_RECORDING = "ChargingCharacteristics.txt";
    private static final String FILE_NAME_FOR_ERROR_LOG = "ErrorLog.txt";
    
    
    // The MainController is the DataCollecotor's parent in the control hierarchy
    private MainController mainController = null;
    
    /**
     * DataCollector keeps a local copy of the RealTimeData and ChargingParameters,
     * but the MainController keeps the real copy
     */
    private RealTimeData realTimeData = null;
    private ChargingParameters chargingParameters = null;
    
    /**
     * A SerialWriter object for each port, since writing to a invalid port may
     * potentially block
     */
    private SerialWriter[] sw = null;
    
    //only one SerialReader is needed since it will never block
    private SerialReader sr = null;
    
    /* 
     * This is the buffer that the SerialReader will update when it receive new
     * bytes serially
     */
    private String stringBuffer = null;
    
    //The following two strings holds the Port names in String format
    private String portnameToBMS = null;
    private String portnameToArduino = null;
    
    //The following two strings holds the Port names in SerialPort format
    private SerialPort portToBMS = null;
    private SerialPort portToArduino = null;
    
    //The following indices indicate which element in the SerialWriter array 
    //corresponds to BMS/Arduino
    private int writerIndexForBMS = 0;
    private int writerIndexForArduino = 0;
    
    public DataCollector(MainController _mainController, RealTimeData _realTimeData, ChargingParameters _chargingParameters)
    {
        //assign data from parameters passed in
        this.mainController = _mainController;
        this.chargingParameters = _chargingParameters;
        this.realTimeData = _realTimeData;
        
        /**
         * first do a search on the ports, this will update the "listOfPorts" field
         * in the chargingParameters
         */
        this.lookForPorts();
        
        //initialize the SerialReader
        this.sr = new SerialReader();
        this.sr.start();
        
        //initialize the SerialWriters
        this.sw = new SerialWriter[8];        
        for(int i = 0; i < this.chargingParameters.getListOfPorts().size(); i++)
        {
            sw[i] = new SerialWriter();
        }
        
        //initialize the string buffer for the SerialReader
        stringBuffer = "";
    }
    
    /**
     * This is the function by the SerialReader to inform the DataCollector the
     * latest data read from the serial port that corresponds to the BMS boards
     * @param _stringBuffer 
     */
    public void updateStringBuffer(String _stringBuffer)
    {
        this.stringBuffer = _stringBuffer;
    }
    
    /**
     * This function will identify which port corresponds to Arduino and which
     * port corresponds to the BMS boards
     * 
     * @return true both ports are identified, false otherwise
     * @throws Exception 
     */
    public boolean getPortNames() throws Exception
    {
        SerialPort serialPort = null;
        boolean BMSfound = false;
        boolean ArduinoFound = false;
        
        //Going through all the ports discovered before
        //You can view all available ports under device manager in a windows machine
        for (int i = (this.chargingParameters.getListOfPorts().size()-1); i >=0; i--)
        {   
            //try connecting to one of the serial port
            try{
                serialPort = this.connect(this.chargingParameters.getListOfPorts().get(i));
            }catch(PortInUseException e)
            {
                this.writeToErrorFile("Port["+this.chargingParameters.getListOfPorts().get(i)+"] is already in use");
                continue;
            }
            
            //set the input and output stream for the SerialReader and SerialWriter
            InputStream in = serialPort.getInputStream();
            OutputStream out = serialPort.getOutputStream();

            this.sr.setInputStream(in);
            this.sr.setDataCollector(this);
            if(this.sr.isStopped())
            {
                this.sr.startThread();
            }          
            this.sw[i].setOutputStream(out);
            
            if(!this.sw[i].isAlive())
            {
                this.sw[i].start();
            }
            
            //wait until the SerialReader thread updates the buffer
            Thread.sleep(1000);
            
            if(this.stringBuffer.equals(BMS_CONFIRM))
            {
                /**
                 * update all the field in the DataCollector class that is related
                 * to the BMS board
                 */
                this.portnameToBMS = this.chargingParameters.getListOfPorts().get(i);
                this.portToBMS = serialPort;
                this.writerIndexForBMS = i;
                BMSfound = true;
            }else if(this.stringBuffer.equals(ARDUINO_CONFIRM_ONE)||this.stringBuffer.equals(ARDUINO_CONFIRM_TWO))
            {
                /**
                 * update all the field in the DataCollector class that is related
                 * to the Arduino board
                 */
                this.portToArduino = serialPort;
                this.portnameToArduino = this.chargingParameters.getListOfPorts().get(i);
                this.writerIndexForArduino = i;
                this.sw[this.writerIndexForArduino].writeToSerialForArduino(ARDUINO_COMMAND_OFF);
                ArduinoFound = true;
            }else
            {
                if(!this.stringBuffer.equals(""))
                {
                    this.writeToErrorFile("Received some data when trying to connect to Arduino and BMS, but it isn't the correct respond message");
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
            
            /* The program will only proceed if both ports are found */
            if(BMSfound && ArduinoFound)
            {
                return true;
            }
        }
        
        if(!BMSfound)
        {
            this.writeToErrorFile("BMS board not found");
        }else{
            this.writeToErrorFile("Arduino board not found");
        }
        return false;
    }
    
    /**
     * This is the function to write a string content to a local file
     * @param content 
     */
    public void writeToFile(String content)
    {
        try {
//            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Documents and Settings\\This PC\\My Documents\\ChargingCharacteristics.txt", true)));
              PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_FOR_DATA_RECORDING+FILE_NAME_FOR_DATA_RECORDING, true)));

            out.println(content);
            out.close();
        } catch (IOException e) {
            System.out.println("Invalid path name for Charging Characteristic file");
            System.exit(1);
        }
    }
    
    public void writeToErrorFile(String error)
    {
        try {
//            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Documents and Settings\\This PC\\My Documents\\ChargingCharacteristics.txt", true)));
              PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH_FOR_ERROR_LOG+FILE_NAME_FOR_ERROR_LOG, true)));

            out.println(error);
            out.close();
        } catch (IOException e) {
            System.out.println("Invalid path name for Error file");
            System.exit(1);
        }
    }
    
    /**
     * This function is to serve the I2C communication.  It update the available
     * bytes to write based on the cell index.  
     * 
     * @param bytesToWrite
     * @param index This should range from 1-8(inclusive on both sides)
     * @return 
     */
    public byte[] updateBytesBasedOnIndex(byte[] bytesToWrite, int index)
    {
       if(index <= 4)
        {
            bytesToWrite[1] += 2*index;
        }else if(index < 8){
            bytesToWrite[1] = 'A';
            bytesToWrite[1] += (byte)(2*(index-5));
        }else if(index == 8)
        {
            bytesToWrite[0] += 1;
        }else{
            this.writeToErrorFile("Invalid cell index found.");
            return null;
        } 
       return bytesToWrite;
    }
    
    /**
     * This function will retrieve the voltage of the cell that has the index
     * passed in
     * @param index range from 1 to 8(inclusive)
     * @return real voltage value in double
     */
    public double getCellVoltage(int index)
    {
        //parameters in the conversion equation
        double v_ref = 2.048;
        int v_v = 0;
        double actual_voltage = 0;
        
        /**
         * The basic command(without the index offset) that is used for
         * retrieving the voltage value
         */
        byte[] bytesToWrite = {'0','0','1','0','R','0','2'};
        
        /**
         * get the actual bytes to write with the adjustment based on the index
         * value
         */
        bytesToWrite = this.updateBytesBasedOnIndex(bytesToWrite, index);
        
        try{
            
            //write the bytes to the BMS board
            this.sw[this.writerIndexForBMS].writeToSerial(bytesToWrite);
            
            //give the Serial Reader a little time before reading it
            Thread.sleep(SLEEP_TIME_FOR_READER);
            
            if(!this.stringBuffer.equals(BMS_INVALID_MSG))
            {
                //conversion equations
                v_v = Integer.parseInt(this.stringBuffer, 16);
                actual_voltage = 4.096 - v_ref*(v_v/(Math.pow(2,10) - 1));
                int temp_result = (int)(actual_voltage*100);
                actual_voltage = ((double)(temp_result))/100;
                
                //format what to write to the local file and write it
                String stringToWrite = "";
                stringToWrite += (System.currentTimeMillis())/1000;
                stringToWrite += "\t";
                stringToWrite += actual_voltage;
                
                if(this.realTimeData.getIsCharging())
                {
                    this.writeToFile(stringToWrite);
                }
                
                //reset the string buffer to empty
                this.stringBuffer = "";
                return actual_voltage;
            }else{
                this.writeToErrorFile("Invalid data read from BMS when trying to get cell voltage");
            }
        }catch(Exception e)
        {
            this.writeToErrorFile("Exceptions occurred when trying to get cell voltage.");
        }
        return 0;
    }
    
    public int getCellTemp(int index)
    {
        //parameters in the conversion equation
        double v_ref = 2.048;
        int v_t = 0;
        int actual_temperature = 0;
        
        /**
         * The basic command(without the index offset) that is used for
         * retrieving the temperature value
         */
        byte[] bytesToWrite = {'0','0','1','1','R','0','2'};
        
        /**
         * get the actual bytes to write with the adjustment based on the index
         * value
         */
        this.updateBytesBasedOnIndex(bytesToWrite, index);
        
        try{
            
            this.sw[this.writerIndexForBMS].writeToSerial(bytesToWrite);
            Thread.sleep(SLEEP_TIME_FOR_READER);
            if(!this.stringBuffer.equals(BMS_INVALID_MSG))
            {
                //conversion equations
                v_t = Integer.parseInt(this.stringBuffer, 16);
                actual_temperature = (int)((v_ref*(v_t/(Math.pow(2,10) - 1)) - 0.5)/0.01);
                
                //reset the buffer
                this.stringBuffer = "";
                
                return actual_temperature;
            }else{
                this.writeToErrorFile("Invalid data read from BMS when trying to get cell temperature");
            }
        
        }catch(Exception e)
        {
            this.writeToErrorFile("Exceptions occurred when trying to get cell temperature.");
        }
        return 0;
    }
    
    public double getPackCurrent()
    {
        //The parameters used for conversion equation
        double v_ref = 2.048;
        double gain = 305;
//        double v_off = -0.0022;
//        double v_off = -0.0006;
        double v_off = 0;
        double resistance_bar = 0.000021;
        
        //initialize some local variables
        int v_ic = 0;
        double actual_current = 0;
        
        //The command used to get current in I2C
        byte[] bytesToWrite = {'0','2','1','2','R','0','2'};
        
        try{
            
            //write the command to the BMS board
            this.sw[this.writerIndexForBMS].writeToSerial(bytesToWrite);
            
            //wait for the SerialReader to update the buffer
            Thread.sleep(SLEEP_TIME_FOR_READER);
            
            
            if(!this.stringBuffer.equals(BMS_INVALID_MSG))
            {
                //conversion equation
                v_ic = Integer.parseInt(this.stringBuffer, 16);
                actual_current = ((v_ref/gain)*(v_ic/(Math.pow(2,10) - 1)) + v_off)/resistance_bar;
                int temp_result = (int)(actual_current*100);
                actual_current = ((double)(temp_result))/100;
                
                //reset the buffer
                this.stringBuffer = "";
                
                return actual_current;
            }else
            {
                this.writeToErrorFile("Invalid data read from BMS when trying to get pack current");
            }
        
        }catch(Exception e)
        {
            this.writeToErrorFile("Exceptions occurred when trying to get pack current.");
        }
        return 0;
    }
    
    public long getBypassTime(int index)
    {
        //local variables to store values
        String min = "";
        String sec = "";
        String millisec = "";
        long time_min = 0;
        long time_sec = 0;
        long time_millisec = 0;
        long timeToReturn = 0;
        
        //command to get bypass time
        byte[] bytesToWrite = {'0','0','1','E','R','0','6'};
        
        //update the bytes to write based on the cell index
        this.updateBytesBasedOnIndex(bytesToWrite, index);
        
        try{
            
            //reset the read threshold since 6 bytes will be returned
            this.sr.readThreshold = 11;
            
            //write the bytes to the BMS board
            this.sw[this.writerIndexForBMS].writeToSerial(bytesToWrite);
            
            //wait for the Serial Reader to update the string buffer
            Thread.sleep(SLEEP_TIME_FOR_READER);
            
            if(!this.stringBuffer.equals(BMS_INVALID_MSG))
            {
                //parse min, sec, millisec in different segments of the 6 bytes returned
                min = this.stringBuffer.substring(0, 4);
                time_min = Long.parseLong(min, 16);

                sec = this.stringBuffer.substring(4, 8);
                time_sec = Long.parseLong(sec, 16);

                millisec = this.stringBuffer.substring(8, 12);
                time_millisec = Long.parseLong(millisec, 16);

                timeToReturn = (time_min << 32) + (time_sec << 16) + time_millisec;
                
                //reset the SerialReader's threshold so that it can process other strings returned
                this.sr.readThreshold = 4;
                
                //reset the string buffer
                this.stringBuffer = "";
                return timeToReturn;
            }else
            {
                this.writeToErrorFile("Invalid data read from BMS when trying to get cell bypass time");
            }
        }catch(Exception e)
        {
                this.writeToErrorFile("Exceptions occurred when trying to get cell bypass time.");         }
        return 0;
    }
    
    public void setBypassSwitch(int index, boolean isOn)
    {
        //The command used to set bypass switch
        byte[] bytesToWrite = {'0','0','0','0','0','0','0','1'};
        
        /**
         * the boolean in the parameter determines whether or not the last bit
         * should be 1 or 0
         */
        if(!isOn)
        {
            bytesToWrite[7] = '0';
        }
        
        //update the bytes to write based on the index value
        this.updateBytesBasedOnIndex(bytesToWrite, index);
        
        //writing the bytes to BMS board to set the bypass switch
        try{
            this.sw[this.writerIndexForBMS].writeToSerial(bytesToWrite);
        }catch(Exception e)
        {
            this.writeToErrorFile("Exceptions occurred when trying to set bypass switch.");
        }
    }
    
    public int getBypassState(int index)
    {
        //command used to get bypass state
        byte[] bytesToWrite = {'0','0','1','4','R','0','2'};
        
        //update the bytes based on the cell index
        this.updateBytesBasedOnIndex(bytesToWrite, index);
        
        try{
            //write the bytes to BMS board
            this.sw[this.writerIndexForBMS].writeToSerial(bytesToWrite);
            
            //allow the SerialReader to update the string buffer
            Thread.sleep(SLEEP_TIME_FOR_READER);
            
            if(!this.stringBuffer.equals(BMS_INVALID_MSG))
            {
                if(this.stringBuffer.equals(BYPASS_OFF))
                {
                    return 0;
                }else if(this.stringBuffer.equals(BYPASS_ON))
                {
                    return 1;
                }else
                {
                    return -1;
                }
            }else
            {
                this.writeToErrorFile("Invalid data read from BMS when trying to get cell bypass state");
            }
        
        }catch(Exception e)
        {
            this.writeToErrorFile("Exceptions occurred when trying to get bypass state.");
        }
        return -1;
    }
    
    SerialPort connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        
        //handle the error if the port is already owned
        if ( portIdentifier.isCurrentlyOwned() )
        {
            this.writeToErrorFile("Port is already in use when trying to connect to a serial port.");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            /**
             * only use serial port and set the corresponding parameters as 
             * specified in the I2C protocol
             */
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(57600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_OUT);

                return serialPort;
            }
            
        }
        return null;
    }
    
    public void lookForPorts() {
        ArrayList<String> listOfPorts = new ArrayList<String>();
        
        //get the identifiers of all the ports
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        
        //go through all the port identifiers
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
                
                //only check the serial ports
                case CommPortIdentifier.PORT_SERIAL:
                    try {
                        CommPort thePort = com.open("CommUtil", 50);
                        thePort.close();
                        if(!listOfPorts.contains(com.getName()))
                        {
                            listOfPorts.add(com.getName());
                        }
                    } catch (PortInUseException e) {
                        this.writeToErrorFile(com.getName() + " is already in use when trying to look for ports");
                    } catch (Exception e) {
                        this.writeToErrorFile("Failed to open port " + com.getName());
                    }
            }
        }
        
        //set the listOfPorts field in the charging parameters
        this.chargingParameters.setListOfPorts(listOfPorts);
    }
    
    /**
     * Write 'a' or 'b' to the Arduino serial port to set the Charging Relay on
     * or off
     * @param isOn true if the relay needs to be on
     */
    public void setChargingRelay(boolean isOn)
    {
        
        byte byteToTurnOn = 'a';
        byte byteToTurnOff = 'b';
        try{           
            if(isOn)
            {
                this.sw[this.writerIndexForArduino].writeToSerialForArduino(byteToTurnOn);
            }else
            {
                this.sw[this.writerIndexForArduino].writeToSerialForArduino(byteToTurnOff);
            }
        }catch(Exception e)
        {
            this.writeToErrorFile("Exceptions occurred when trying to set charging relay.");
        }
    }
    
    
    public boolean testCell(int index)
    {       
        //the command to verify if the cell is there
        byte[] bytesToWrite = {'0','0','1','7','R','0','2'};
        
        //update the bytes to write based on the index
        this.updateBytesBasedOnIndex(bytesToWrite, index);
        
        try{
            
            //write the BMS board with the test command
            this.sw[this.writerIndexForBMS].writeToSerial(bytesToWrite);
            
            //allow the Serial Reader to write to the string buffer
            Thread.sleep(SLEEP_TIME_FOR_READER);
            
            //check if the string buffer holds the correct string returned by BMS
            if(this.stringBuffer.equals(BMS_VALID_TEST))
            {
                return true;
            }else{
                this.writeToErrorFile("Invalid data read from BMS when trying to test cell");
            }
        }catch(Exception e)
        {
            this.writeToErrorFile("Exceptions occurred when trying to test cell.");
        }
        return false;
    }
    
    public void turnOffAllBypass()
    {
        //go through all the cells and turn off bypass if they are on
        for(int i = 1; i <= 8; i++)
        {
            //test the cells one by one, only proceed to acquire data if the test passed
            if(this.testCell(i))
            {
                //set the bypass to off
                this.setBypassSwitch(i, false);

            }else if(i != 1)
            {
                this.chargingParameters.setNumOfCells(i-1);
                break;
            }
        }
    }
    
    /*
     * This is the function that will run once the DataCollector Thread starts
     */
    public void run()
    {
        //initialize a series of local variables
        boolean errorOccurred;
        double currentReading = 0;
        double voltageReading = 0;
        int tempReading = 0;
        int bypassState = -1;

        try{
            
            //only proceed if both ports are found
            if(getPortNames())
            {
                
                //make sure the Serial Reader and Serial Writer has the correct
                //input and output stream
                InputStream in = this.portToBMS.getInputStream();
                OutputStream out = this.portToBMS.getOutputStream();

                this.sr.setInputStream(in);
                this.sr.setDataCollector(this);
                this.sw[this.writerIndexForBMS].setOutputStream(out);
                
                //This will continuously run until the program exits
                while(true)
                {
                    //always work off the RealTimeData and ChargingParameters that the MainController has
                    this.realTimeData = this.mainController.getRealTimeData();
                    this.chargingParameters = this.mainController.getChargingParameters();
                    this.chargingParameters.setPortToArduino(portnameToArduino);
                    this.chargingParameters.setPortToBMS(portnameToBMS);
                    
                    //reset the Error Message fields
                    this.realTimeData.setErrorMessage("");
                    this.realTimeData.setErrorOccurred(false);
                    
                    //always initialize this boolean variable to false
                    errorOccurred = false;
                    
                    //read the current
                    currentReading = this.getPackCurrent();
                    
                    if( currentReading != 0 )
                    {
                        this.realTimeData.setCurrent(currentReading);
                    }
                    
                    this.turnOffAllBypass();
                    
                    //go through all possible cells
                    for(int i = 1; i <= 8; i++)
                    {
                        //test the cells one by one, only proceed to acquire data if the test passed
                        if(this.testCell(i))
                        {
                            //get the cell voltage
                            voltageReading = this.getCellVoltage(i);
                            if(  voltageReading != 0 )
                            {
                                this.realTimeData.setVoltage(i-1, voltageReading);
                            }else{
                                errorOccurred = true;
                                this.realTimeData.setErrorMessage(this.realTimeData.getErrorMessage() + "Cannot Read Voltage Value \n");
                            }
                            
                            //get the cell temperature
                            tempReading = this.getCellTemp(i);
                            if(  tempReading != 0 )
                            {
                                this.realTimeData.setTemperature(i-1, tempReading);
                                System.out.println(tempReading);
                            }else{
                                errorOccurred = true;
                                this.realTimeData.setErrorMessage(this.realTimeData.getErrorMessage() + "Cannot Read Temperature Value \n");
                            }
                            
                            //get the bypass state
                            bypassState = this.getBypassState(i);
                            
                            if( bypassState == 1)
                            {
                                this.realTimeData.setBypassInfo(i-1, true);
                                
                                //also retrieve the bypass time if it is bypassed
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
                            //update the number of cells
                            this.chargingParameters.setNumOfCells(i-1);
                            break;
                        }else
                        {
                            //error handling for no cells
                            this.realTimeData.setErrorOccurred(true);
                            this.realTimeData.setErrorMessage(this.realTimeData.getErrorMessage()+"No Cell Detected.  Please check if one of the cells have address 0x02\n");
                            errorOccurred = true;
                        }
                    }
                    
                    //set the errorOccurred field to be true if error did occur
                    if(!errorOccurred)
                    {
                        this.realTimeData.setErrorOccurred(false);
                    }
                    
                    //update the MainController's RealTimeData and ChargingParameters
                    this.mainController.setRealTimeData(this.realTimeData);
                    this.mainController.setChargingParameters(this.chargingParameters);
                    
                    try{
                        Thread.sleep(SLEEP_TIME_BTW_EACH_DATA_ACQUISITION);
                    }catch(Exception e)
                    {
                        //error handling
                    }
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
