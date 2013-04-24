/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.chargingcontroller;

/**
 *
 * @author This PC
 */


import java.util.*;
import java.io.*;
import gnu.io.*;

public class testPort
{
    public testPort(){}
    
    void connect ( String portName ) throws Exception
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
                //I2CPort i2cPort = (I2CPort) commPort;
                //i2cPort.setI2CPortParams(576000, I2CPort.DATABITS_8, I2CPort.STOPBITS_1, I2CPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                
                (new Thread(new SerialReader(in))).start();
                (new Thread(new SerialWriter(out))).start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    
    /** */
    public static class SerialReader implements Runnable 
    {
        InputStream in;
        
        public SerialReader ( InputStream in )
        {
            this.in = in;
        }
        
        public void run ()
        {
            byte[] buffer = new byte[1024];
            int len = -1;
            String temp = null;
            String newString = "";
            int tempLength = 0;
            try
            {
                while ( ( len = this.in.read(buffer)) > -1 )
                {
                    //System.out.println("-----"+len+"-----");
                    temp = new String(buffer,0,len);
                    if(!temp.equals(""))
                        System.out.println(temp);
                    if(!temp.equals(""))
                    {
                        tempLength += len;
                        newString += temp;
                        if(tempLength > 4)
                        {
                            tempLength = 0;
                            newString = newString.substring(0, newString.length()-1);
                            System.out.println(newString);
                            System.out.println(System.currentTimeMillis());
                            newString = "";
                        }
                        //System.out.println("String:"+temp+". Length: "+len);
                    }
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }

    /** */
    public static class SerialWriter implements Runnable 
    {
        OutputStream out;
        
        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }
        
        public void run ()
        {
            try
            {                
                int c = 0;
                while ( ( c = System.in.read()) > -1 )
                {
                    if(c == 49 | c== 10)
                    {
                        //this.out.write(0xFE);
                        c = 0xFFFF;
                        this.out.write('d');

                        this.out.write('G');
                        this.out.write('3');
                        this.out.write('S');
                        this.out.write('0');
                        this.out.write('4');
                        this.out.write('1');
                        this.out.write('7');
                        this.out.write('P');
                        this.out.write('c');

                        //System.out.println("\r\nwrote "+c+ " to the serial port.\r\n");
                    }else if(c == 50)
                    {
                       
                        this.out.write('S');
                        this.out.write('0');
                        this.out.write('5');
                        this.out.write('0');
                        this.out.write('2');
                        this.out.write('P');
                        System.out.println(System.currentTimeMillis());

                        //System.out.println("\r\nwrote "+c+ " to the serial port.\r\n");
                    }else if(c == 51)
                    {
                        this.out.write('a');
                    }else if(c == 52)
                    {
                        this.out.write('b');
                    }else
                    {
                        //this.out.write(c);
                        //System.out.println("Wrote something random "+c);
                    }
                }                
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }
    
    public static HashSet lookForPorts() {
        HashSet h = new HashSet();
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
            case CommPortIdentifier.PORT_SERIAL:
                try {
                    CommPort thePort = com.open("CommUtil", 50);
                    thePort.close();
                    h.add(com);
                    System.out.println(com.getName());
                } catch (PortInUseException e) {
                    //System.out.println("Port, "  + com.getName() +  ", is in use.");
                } catch (Exception e) {
                    System.err.println("Failed to open port " + com.getName());
                    e.printStackTrace();
                }
            }
        }
        return h;
    }
    
    public static void main ( String[] args )
    {
        try
        {
            //(new testPort()).connect("COM1");
            testPort myTestPort = new testPort();
            HashSet allAvailablePorts = myTestPort.lookForPorts();
            if(allAvailablePorts.isEmpty())
            {
                System.out.println("\nThere is no available ports\n");
            }else
            {
                //System.out.println("Port Founded:");
            }
            myTestPort.connect("COM21");
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
