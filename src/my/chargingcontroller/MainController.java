/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.chargingcontroller;
import java.util.*;
/**
 *
 * @author This PC
 */
public class MainController implements Runnable{
    /*I changed here */
    /*
     //parameters for charging
     public static int numOfCells = 0; // number of cells
     public static double vUpper = 3.8; // voltage upper limit
     public static int tUpper = 60; // temperature upper limit
     public static double iUpper = 0; // current upper limit
     public static double bypassDuration = 20; // bypass duration
     public static double bypassThreshold = 50; // bypass threshold
     
     //current variable readings
     public static double vCurr = 0; //current voltage
     public static double iCurr = 0; //current current
     public static int tCurr = 0; //current temperature
     
     //an array of booleans indicating whether or not the cell 
     //at the specified index is bypassed or not
     public static boolean isBypassed[]; 
     //an array of integers indicating how long the cell
     //at the specified index has been bypassed
     public static int bypassTime[];
     */
    private ChargingParameters defaultChargingParameters;
    private RealTimeData defaultRealTimeData;
    ChargingParameters chargingParameters;
    RealTimeData realTimeData;
    private GUIController guiController = null;
    
    
    /**
     * Creates new form ChargingControllerUI
     */
    public MainController() {
        
        int numOfCells = 8;
        double vUpper = 4;
        double iUpper = 20;
        int tUpper = 200;
        int bypassDuration = 20;
        int bypassThreshold = 50;
        double bypassCutoff = vUpper - 0.2;
        ArrayList<String> listOfPorts = new ArrayList<String>();
        String portToPC = "";
        String portToArduino = "";
        defaultChargingParameters = new ChargingParameters(     numOfCells, 
                                                                vUpper,
                                                                iUpper, 
                                                                tUpper, 
                                                                bypassDuration, 
                                                                bypassThreshold,
                                                                bypassCutoff,
                                                                listOfPorts,
                                                                portToPC,
                                                                portToArduino );
        
        double[] vCurr = new double[8];
        double iCurr = 1;
        int[] tCurr = new int[8];
        boolean[] isBypassed = new boolean[8];
        for(int i = 0; i < this.defaultChargingParameters.getNumOfCells(); i ++)
        {
            isBypassed[i] = false;
        }
        long[] bypassTime = new long[8];
        double[][] diff = new double[8][7];
        boolean isCharging = false;
        RealTimeData.State state = RealTimeData.State.IDLE;
        boolean done = false;
        boolean chargingRelayOpen = true;
        int cycleTime = 0;
        int tick = 0;
        boolean errorOccurred = false;
        String errorMessage = "";

        defaultRealTimeData= new RealTimeData(  vCurr, 
                                                iCurr, 
                                                tCurr, 
                                                isBypassed,
                                                bypassTime,
                                                diff,
                                                isCharging,
                                                state,
                                                done,
                                                chargingRelayOpen,
                                                cycleTime,
                                                tick,
                                                errorOccurred,
                                                errorMessage);
        
        this.chargingParameters = this.defaultChargingParameters;
        this.realTimeData = this.defaultRealTimeData;
        listOfPorts = new ArrayList<String>();
        DataCollector dataCollector = new DataCollector(this, realTimeData, chargingParameters);
        
        
        guiController = new GUIController(this, chargingParameters, realTimeData);
        guiController.createChargingMonitorPage();
        new Thread(guiController).start();
        new Thread(dataCollector).start();
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        MainController mainController = new MainController();
        new Thread(mainController).start();
    }   
    
    public void updateChargingParameters(ChargingParameters newChargingParameters)
    {
        this.chargingParameters = newChargingParameters;
    }

    public void userApprovedParameters()
    {
        this.realTimeData.setIsCharging(true);
    }
    
    public void userRequestedStop()
    {
        this.realTimeData.setIsCharging(false);
    }
    
    public RealTimeData getRealTimeData()
    {
        return this.realTimeData;
    }
    
    public ChargingParameters getChargingParameters()
    {
        return this.chargingParameters;
    }
    
    public void setRealTimeData(RealTimeData _realTimeData)
    {
        //System.out.println("Real Time Data Updated in main controller");
        //System.out.println(this.realTimeData.getVoltage(0));
        this.realTimeData = _realTimeData;
    }
    
    public void setChargingParameters(ChargingParameters _chargingParameters)
    {
        this.chargingParameters = _chargingParameters;
    }
    
    
    
    public void run()
    {   
        while(true)
        {
            if(this.realTimeData.getErrorOccurred() == true)
            {
                this.realTimeData.setIsCharging(false);
                this.realTimeData.setState(RealTimeData.State.IDLE);
                this.guiController.createdPopupDialog("Warning", this.realTimeData.getErrorMessage());
                this.realTimeData.setErrorOccurred(false);
                this.realTimeData.setErrorMessage("");
                continue;
            }
            
            //System.out.println("Controller Thread:"+this.realTimeData.getVoltage(0));
            while(this.realTimeData.getIsCharging())
            {
                if(this.realTimeData.getState().equals(RealTimeData.State.IDLE))
                {
                    if(this.checkForCellOvercharge())
                    {
                       //tell the user at least one of the cells is overcharged
                        this.guiController.createdPopupDialog("Warning", "Charging cannot start because at least one of the cells has voltage that is above the voltage upper limit.");
                    }else if(this.checkForCellOverheating())
                    {
                        //tell the user at least one of the cells is overheating
                        this.guiController.createdPopupDialog("Warning", "Charging cannot start because at least one of the cells is overheating.");
                    }else if(this.checkForCurrentOverLimit())
                    {
                        this.guiController.createdPopupDialog("Warning", "Charging cannot start because the current is already over the upper limit.");
                    }else
                    {
                        this.realTimeData.setState(RealTimeData.State.CHARGING);
                    }
                }

                if(this.realTimeData.getState().equals(RealTimeData.State.CHARGING))
                {
                    if(this.checkForCellOvercharge())
                    {
                       //tell the user at least one of the cells is overcharged
                        //charging was finished
                        this.realTimeData.setIsCharging(false);
                        this.realTimeData.setState(RealTimeData.State.IDLE);
                        this.guiController.createdPopupDialog("Warning", "Charging is Done!");

                    }else if(this.checkForCellOverheating())
                    {
                        //tell the user at least one of the cells is overheating
                        //charging was stopped
                        this.guiController.createdPopupDialog("Warning", "Charging is stopped because at least one of the cells is overheating.");
                        this.realTimeData.setIsCharging(false);
                        this.realTimeData.setState(RealTimeData.State.IDLE);
                    }else if(this.checkForCurrentOverLimit())
                    {
                        //tell the user the current is over the limit
                        //charging was stopped
                        this.guiController.createdPopupDialog("Warning", "Charging is stopped because the current is already over the upper limit.");
                        this.realTimeData.setIsCharging(false);
                        this.realTimeData.setState(RealTimeData.State.IDLE);
                    }else
                    {
                        for (int i = 0; i < this.chargingParameters.getNumOfCells(); i++)
                        {
                            if(this.checkForBypass(i))
                            {
                                this.realTimeData.setBypassInfo(i, true);
                            }
                        }
                    }
                }
            }
            this.realTimeData.setState(RealTimeData.State.IDLE);
            try{
                Thread.sleep(100);
            }catch(Exception e)
            {
                
            }
        }
    }
    
    public boolean checkForCellOverheating()
    {
        boolean someCellOverHeated = false;
        for(int i = 0; i < this.chargingParameters.getNumOfCells(); i ++)
        {
            if(this.realTimeData.getTemperature(i) >= this.chargingParameters.getTemperatureUpperLimit())
                someCellOverHeated = true;
        }
        return someCellOverHeated;
    }
    
    public boolean checkForCellOvercharge()
    {
        boolean someCellOverCharged = false;
        
        for(int i = 0; i < this.chargingParameters.getNumOfCells(); i ++)
        {
            if(this.realTimeData.getVoltage(i) >= this.chargingParameters.getVoltageUpperLimit())
                someCellOverCharged = true;
        }
        return someCellOverCharged;
    }
    
    public boolean checkForCurrentOverLimit()
    {
        return (this.realTimeData.getCurrent() >= this.chargingParameters.getCurrentUpperLimit());
    }
    
    public boolean checkForBypass(int index)
    {
        //System.out.println("checking for bypass"+cellNumber);
        //System.out.println(currVoltage[cellNumber]);
        boolean bypassNeeded = false;
        for(int i = 0; i < this.chargingParameters.getNumOfCells(); i++)
        {
            if((this.realTimeData.getDiff(index, i) > this.chargingParameters.getBypassThreshold()) && 
               (index != i) &&
               (this.realTimeData.getVoltage(index) < this.chargingParameters.getBypassCutoff()))
            {
                bypassNeeded = true;
            }
        }
        
        return bypassNeeded;
    }

}


