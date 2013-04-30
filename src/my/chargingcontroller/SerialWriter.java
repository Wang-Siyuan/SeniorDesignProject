/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.chargingcontroller;
import java.io.*;
import java.lang.*;
/**
 *
 * @author This PC
 */
public class SerialWriter extends Thread{
        OutputStream out;
        boolean stop;
        
        public SerialWriter ( OutputStream out )
        {
            this.out = out;
            stop = true;
        }
        
        public SerialWriter()
        {
            stop = true;
        }
        
        public void setOutputStream(OutputStream _out)
        {
            this.out = _out;
        }
        
        public void setUp()
        {
            try
            {
                this.out.write('G');
                this.out.write('3');
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            } 
        }
        
        public void writeToSerialForArduino(byte data)
        {
            try
            {               
                this.out.write(data);
            }catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        
        public void writeToSerial(byte[] data)
        {
            try
            { 
                if(Thread.interrupted())
                {
                    throw new InterruptedException();
                }
                
                this.out.write('G');
                this.out.write('3');
                this.out.write('S');
                for(int i = 0; i < data.length; i++)
                {
                    this.out.write(data[i]);
                }
                this.out.write('P');
            }catch( InterruptedException e)
            {
                return;
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        
        public void run()
        {
            try
            {      
                this.out.write('d');

                this.out.write('G');
                this.out.write('3');
                this.out.write('S');
                this.out.write('0');
                this.out.write('2');
                this.out.write('1');
                this.out.write('7');
                this.out.write('R');
                this.out.write('0');
                this.out.write('2');
                this.out.write('P');

                } catch ( IOException e )
                {
                    return;
                }
                
         }

        
        public void stopThread()
        {
            this.stop = true;
        }
        
        public void startThread()
        {
            this.stop = false;
        }
}
