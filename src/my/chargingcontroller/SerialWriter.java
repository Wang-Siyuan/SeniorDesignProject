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
            System.out.println("set output stream");
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
                System.out.println("I'm about to write bytes to arduino*********************************");
                this.out.write(data);
                System.out.println("Finished writing******************************************************");
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
                
                System.out.println("I'm about to write bytes to serial");
                this.out.write('G');
                this.out.write('3');
                //System.out.println("Wrote two bytes to serial");
                this.out.write('S');
                for(int i = 0; i < data.length; i++)
                {
                    //System.out.println("Wrote one byte");
                    this.out.write(data[i]);
                }
                this.out.write('P');
                System.out.println("Finished writing ");
            }catch( InterruptedException e)
            {
                System.out.println("Interrupt occurred");
                return;
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        
        public void run()
        {
            System.out.println("SerialWriter Thread created");
            System.out.println("about to write");
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
                

                
                System.out.println("Finished Writing");
                } catch ( IOException e )
                {
                    return;
                }
                
         }

        
        public void stopThread()
        {
            System.out.println("Stop set to true");
            this.stop = true;
        }
        
        public void startThread()
        {
            System.out.println("stop set to false");
            this.stop = false;
        }
}
