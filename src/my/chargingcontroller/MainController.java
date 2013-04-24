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
    
    /**
     * Constructor of MainController
     */
    public MainController() {
        
        /* generate a set of default charging parameters */
        int numOfCells = 8;
        double vUpper = 4;
        double iUpper = 500;
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
            
            /**
             * stay in this while loop if the user wants to charge and hasn't decide
             * to stop yet
             */
            while(this.realTimeData.getIsCharging())
            {
                
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
                        this.dataCollector.setChargingRelay(true);
                    }
                }
                
                /**
                 * If the current state is already charging state
                 */
                if(this.realTimeData.getState().equals(RealTimeData.State.CHARGING))
                {
                    if(this.checkForCellOvercharge())
                    {
                        //tell the user at least one of the cells is overcharged
                        //charging was finished
                        this.realTimeData.setIsCharging(false);
                        this.realTimeData.setState(RealTimeData.State.IDLE);
                        this.dataCollector.setChargingRelay(false);
                        this.guiController.createdPopupDialog("Message", "Charging is Done!");
                        for(int j = 1; j <= this.chargingParameters.getNumOfCells(); j++)
                        {
                            this.dataCollector.setBypassSwitch(j, false);
                        }

                    }else if(this.checkForCellOverheating())
                    {
                        //tell the user at least one of the cells is overheating
                        //charging was stopped
                        this.dataCollector.setChargingRelay(false);
                        this.guiController.createdPopupDialog("Warning", "Charging is stopped because at least one of the cells is overheating.");
                        this.realTimeData.setIsCharging(false);
                        this.realTimeData.setState(RealTimeData.State.IDLE);
                    }else if(this.checkForCurrentOverLimit())
                    {
                        //tell the user the current is over the limit
                        //charging was stopped
                        this.dataCollector.setChargingRelay(false);
                        this.guiController.createdPopupDialog("Warning", "Charging is stopped because the current is already over the upper limit.");
                        this.realTimeData.setIsCharging(false);
                        this.realTimeData.setState(RealTimeData.State.IDLE);
                    }else
                    {
                        for (int i = 0; i < this.chargingParameters.getNumOfCells(); i++)
                        {
                            if(this.checkForBypass(i))
                            {
                                this.dataCollector.setBypassSwitch(i+1, true);
                            }
                        }
                    }
                }
            }
            
            this.dataCollector.setChargingRelay(false);
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


