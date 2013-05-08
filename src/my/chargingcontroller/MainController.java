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
public class MainController extends Thread{

    private ChargingParameters defaultChargingParameters;
    private RealTimeData defaultRealTimeData;
    private ChargingParameters chargingParameters;
    private RealTimeData realTimeData;
    private GUIController guiController = null;
    private DataCollector dataCollector = null;
    private long chargingStartTime = 0;
    public static boolean[] mostRecentBypassInfo = null;
    
    /**
     * Constructor of MainController
     */
    public MainController() {
                
        mostRecentBypassInfo = new boolean[8];
        for(int i = 0; i < 8; i++)
        {
            this.mostRecentBypassInfo[i] = false;
        }
        
        /* generate a set of default charging parameters */
        int numOfCells = 3;
        double vUpper = 3.8;
        double iUpper = 1000;
        int tUpper = 50;
        int bypassDuration = 20;
        double bypassThreshold = 0.06;
        double bypassCutoff = 3.6;
        ArrayList<String> listOfPorts = new ArrayList<String>();
        String portToPC = "";
        String portToArduino = "";
        int ChargingTime = 180;
        float[] voltageOffsets = new float[8];
        voltageOffsets[0] = (float)(-0.037);
        voltageOffsets[1] = (float)(-0.01);
        voltageOffsets[2] = (float)(0.028);
        float[] temperatureOffsets = new float[8];
        float currentOffset = (float)(-0.0010);
        
        defaultChargingParameters = new ChargingParameters(     numOfCells, 
                                                                vUpper,
                                                                iUpper, 
                                                                tUpper, 
                                                                bypassDuration, 
                                                                bypassThreshold,
                                                                bypassCutoff,
                                                                listOfPorts,
                                                                portToPC,
                                                                portToArduino,
                                                                ChargingTime,
                                                                voltageOffsets,
                                                                temperatureOffsets,
                                                                currentOffset);
        
        /* generate a set of default real time data */
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
        int currentChargingTime = 0;

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
                                                errorMessage,
                                                currentChargingTime);
        
        /* initialize the charging parameter and real time data to be the default ones */
        this.chargingParameters = this.defaultChargingParameters;
        this.realTimeData = this.defaultRealTimeData;
        
        /* instantiate the data collector and the GUI controller */
        this.dataCollector = new DataCollector(this, realTimeData, chargingParameters);
        this.guiController = new GUIController(this, chargingParameters, realTimeData);
        
        /* create the charging monitor page */
        guiController.createChargingMonitorPage();
        
        /* start the GUI controller thread and also the data collector thread */
        guiController.start();
        dataCollector.start();
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* simply instantiate an object of the MainController class and start the thread */
        MainController mainController = new MainController();
        mainController.start();
    }
    
    /* 
     * The following two functions respond to user request that
     * involves the charging algorithm
     */
    public void userApprovedParameters()
    {
        this.realTimeData.setIsCharging(true);
    }
    
    public void userRequestedStop()
    {
        this.realTimeData.setIsCharging(false);
        this.dataCollector.requestToTurnOffAllBypass = true;
        this.dataCollector.requestToTurnOffRelay = true;
    }
    
    /*
     * The following four functions are getters and setters for the RealTimeData
     * And the Charging Parameters
     */
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
        this.realTimeData = _realTimeData;
    }
    
    public void setChargingParameters(ChargingParameters _chargingParameters)
    {
        this.chargingParameters = _chargingParameters;
    }
    
    public void setBypass(int index, boolean isOn)
    {
        if(isOn)
        {
            this.dataCollector.requestToTurnOnBypass[index-1] = true;
        }else
        {
            this.dataCollector.requestToTurnOffBypass[index-1] = true;
        }
    }
    
    
    public void run()
    {   
        boolean[] cellBypassCommandSent = new boolean[8];
        for(int i = 0; i < 8; i++)
        {
            cellBypassCommandSent[i] = false;
        }
        
        /* MainController thread will keep running until the program exits */
        while(true)
        {
            /**
             * At any point, if an error occurred, set the state to be IDLE
             * and show the user about the warning message
             */
            if(this.realTimeData.getErrorOccurred() == true)
            {
                this.realTimeData.setIsCharging(false);
                this.realTimeData.setState(RealTimeData.State.IDLE);
                this.guiController.createdPopupDialog("Warning", this.realTimeData.getErrorMessage());
                this.realTimeData.setErrorOccurred(false);
                this.realTimeData.setErrorMessage("");
                continue;
            }
            
            boolean prev = false;
            
            /**
             * stay in this while loop if the user wants to charge and hasn't decide
             * to stop yet
             */
            while(this.realTimeData.getIsCharging())
            {
                prev = true;
                /**
                 * If the system state is currently idle, only change the
                 * system state to charging only if the voltage, temperature,
                 * and current is not over the limit
                 */
                if(this.realTimeData.getState().equals(RealTimeData.State.IDLE))
                {
                    if(this.checkForCellOvercharge())
                    {
                       //tell the user at least one of the cells is overcharged
                        this.guiController.createdPopupDialog("Warning", "Charging cannot start because at least one of the cells has voltage that is above the voltage upper limit.");
                        try{
                            Thread.sleep(4000);
                        }catch(Exception e)
                        {
                            
                        }
                    }else if(this.checkForCellOverheating())
                    {
                        //tell the user at least one of the cells is overheating
                        this.guiController.createdPopupDialog("Warning", "Charging cannot start because at least one of the cells is overheating.");
                        try{
                            Thread.sleep(4000);
                        }catch(Exception e)
                        {
                            
                        }
                    }else if(this.checkForCurrentOverLimit())
                    {
                        this.guiController.createdPopupDialog("Warning", "Charging cannot start because the current is already over the upper limit.");
                        try{
                            Thread.sleep(4000);
                        }catch(Exception e)
                        {
                            
                        }
                    }else
                    {
                        this.realTimeData.setState(RealTimeData.State.CHARGING);
                        this.dataCollector.requestToTurnOnRelay = true;
                        this.chargingStartTime = System.currentTimeMillis();
                        this.dataCollector.requestToSetBypassTime = true;
                    }
                }
                
                /**
                 * If the current state is already charging state
                 */
                if(this.realTimeData.getState().equals(RealTimeData.State.CHARGING))
                {
                    int currentChargingTime = (int)((System.currentTimeMillis() - this.chargingStartTime)/60000);
                    this.realTimeData.setCurrentChargingTime(currentChargingTime);
                    if((this.chargingParameters.getChargingTime() - currentChargingTime) <= 0)
                    {
                        //tell the user at least one of the cells is overcharged
                        //charging was finished
                        this.realTimeData.setIsCharging(false);
                        this.realTimeData.setState(RealTimeData.State.IDLE);
                        this.dataCollector.requestToTurnOffRelay = true;
                        this.guiController.createdPopupDialog("Message", "Charging is not done, but charging timeout expired!");
                        for(int j = 1; j <= this.chargingParameters.getNumOfCells(); j++)
                        {
                            this.dataCollector.requestToTurnOffBypass[j-1] = true;
                        }
                    }
                    else if(this.checkForCellOvercharge())
                    {
                        //tell the user at least one of the cells is overcharged
                        //charging was finished
                        this.realTimeData.setIsCharging(false);
                        this.realTimeData.setState(RealTimeData.State.IDLE);
                        this.dataCollector.requestToTurnOffRelay = true;
                        this.guiController.createdPopupDialog("Message", "Charging is Done!");
                        for(int j = 1; j <= this.chargingParameters.getNumOfCells(); j++)
                        {
                            this.dataCollector.requestToTurnOffBypass[j-1] = true;
                        }
                    }else if(this.checkForCellOverheating())
                    {
                        //tell the user at least one of the cells is overheating
                        //charging was stopped
                        this.dataCollector.setChargingRelay(false);
                        this.guiController.createdPopupDialog("Warning", "Charging is stopped because at least one of the cells is overheating.");
                        this.dataCollector.requestToTurnOffRelay = true;
                        this.realTimeData.setState(RealTimeData.State.IDLE);
                        for(int j = 1; j <= this.chargingParameters.getNumOfCells(); j++)
                        {
                            this.dataCollector.requestToTurnOffBypass[j-1] = true;
                        }
                    }else if(this.checkForCurrentOverLimit())
                    {
                        //tell the user the current is over the limit
                        //charging was stopped
                        this.dataCollector.setChargingRelay(false);
                        this.guiController.createdPopupDialog("Warning", "Charging is stopped because the current is already over the upper limit.");
                        this.dataCollector.requestToTurnOffRelay = true;
                        this.realTimeData.setState(RealTimeData.State.IDLE);
                        for(int j = 1; j <= this.chargingParameters.getNumOfCells(); j++)
                        {
                            this.dataCollector.requestToTurnOffBypass[j-1] = true;
                        }
                    }else
                    {
                        for (int i = 0; i < this.chargingParameters.getNumOfCells(); i++)
                        {
                             //System.out.println("Checking bypass for "+ i);
                            boolean result = this.checkForBypass(i);
                            if( result && !this.realTimeData.getBypassInfo(i) & !cellBypassCommandSent[i])
                            {
                                this.dataCollector.requestToTurnOnBypass[i] = true;
                                this.mostRecentBypassInfo[i] = true;
                                System.out.println("most recent bypass info for index "+i+" set to true");
                                cellBypassCommandSent[i] = true;
                            }else if(this.realTimeData.getBypassInfo(i))
                            {
                                cellBypassCommandSent[i] = false;
                            }
                        }
                    }
                }
            }
            if(prev)
            {
                this.dataCollector.requestToTurnOffRelay = true;
                prev = false;
            }
            this.realTimeData.setState(RealTimeData.State.IDLE);
            try{
                Thread.sleep(10);
            }catch(Exception e)
            {
                
            }
        }
    }
    
    public void exitAndCleanUp()
    {
        this.dataCollector.requestToTurnOffAllBypass = true;
        this.dataCollector.requestToTurnOffRelay = true;
        //System.exit(0);
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
        boolean bypassNeeded = false;
        for(int i = 0; i < this.chargingParameters.getNumOfCells(); i++)
        {
            if((index != i) && (this.realTimeData.getDiff(index, i) > this.chargingParameters.getBypassThreshold()) && !this.mostRecentBypassInfo[i] &&
               (this.realTimeData.getVoltage(index) < this.chargingParameters.getBypassCutoff()))
            {
                if(index == 3)
                {
                    System.out.println("Diff between 3 and "+i+" is "+this.realTimeData.getDiff(index,i));
                }
                
                bypassNeeded = true;
            }else if (this.realTimeData.getDiff(index, i) < this.chargingParameters.getBypassThreshold())
            {
                //System.out.println("Diff " + this.realTimeData.getDiff(index, i) +" not large enough to be larger than "+ this.chargingParameters.getBypassThreshold());
            }else if(this.realTimeData.getVoltage(index) > this.chargingParameters.getBypassCutoff())
            {
                //System.out.println(this.realTimeData.getVoltage(index) + " larger than " + this.chargingParameters.getBypassCutoff()+ ", which is the bypass cuttoff");
            }
        }
        
        return bypassNeeded;
    }

}


