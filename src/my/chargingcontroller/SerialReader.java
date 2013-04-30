/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.chargingcontroller;
import java.io.*;
/**
 *
 * @author This PC
 */
public class SerialReader extends Thread{
    InputStream in;
    DataCollector dataCollector;
    boolean stop;
    public int readThreshold = 4;

    public SerialReader ( DataCollector _dataCollector,  InputStream in )
    {
        this.dataCollector = _dataCollector;
        this.in = in;
        this.stop = true;
    }

    public SerialReader()
    {
        this.stop = true;
    }

    public void setInputStream(InputStream _in)
    {
        this.in = _in;
    }
    
    public void setDataCollector(DataCollector _dataCollector)
    {
        this.dataCollector = _dataCollector;
    }

    public void run ()
    {
        byte[] buffer = new byte[1024];
        int len = -1;
        
        try
        {
            while(true)
            {
                while(stop)
                {
                    try{
                        Thread.sleep(10);
                    }catch( Exception e)
                    {
                        
                    }
                }
                
                String temp = null;
                String newString = "";
                int tempLength = 0;
                    while (( len = this.in.read(buffer)) > -1)
                    {
                        temp = new String(buffer,0,len);
                        if(!temp.equals(""))
                        {
                            tempLength += len;
                            newString += temp;
                            if(tempLength > readThreshold)
                            {
                                tempLength = 0;
                                newString = newString.substring(0, newString.length()-1);
                                this.dataCollector.updateStringBuffer(newString);
                                newString = "";
                            }
                        }
                    }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }            
    }
    
    public void startThread()
    {
        this.stop = false;
    }
    
    public void stopThread()
    {
        this.stop = true;
    }
    
    public boolean isStopped()
    {
        return this.stop;
    }

}
