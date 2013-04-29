/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.chargingcontroller;

/**
 * This the controller that controls all the GUI components with all the logic except
 * the charging logic
 * The actual charging logic is still held by the MainController
 * @author Siyuan Wang
 */
public class GUIController extends Thread{
    
    /**
     * GUIController should only hold one copy of ChargingMonitor, ParameterSetup,
     * ParameterConfirm, PopupDialog, and ManualConfigure
     */
    private ChargingMonitor chargingMonitor = null;
    private ParameterSetup parameterSetup = null;
    private ParameterConfirm parameterConfirm = null;
    private PopupDialog popupDialog = null;
    private ManualConfigure man = null;
    
    /**
     * The GUIController also hold a local copy of RealTimeData and ChargingParameters
     * These two data set should be in sync with the ones held by the MainController
     */
    private RealTimeData realTimeData;
    private ChargingParameters chargingParameters;
    
    //It holds a copy of the MainController.  This is its parent class
    private MainController mainController;
    
    //This is a boolean variable that will be used when a new set of parameter is submitted
    private boolean userRequestedCharge;

    
    /**
     * Default Constructor
     * 
     * @param _mainController
     * @param _chargingParameters
     * @param _realTimeData 
     */
    public GUIController(   MainController _mainController, 
                            ChargingParameters _chargingParameters,
                            RealTimeData _realTimeData)
    {
        
    
        this.mainController = _mainController;
        this.chargingParameters = _chargingParameters;
        this.realTimeData = _realTimeData;
        
        //user hasn't request charge yet
        this.userRequestedCharge = false;
    }
    
    /**
     * Create a ChargingMonitor Page
     */
    public void createChargingMonitorPage()
    {
        chargingMonitor = new ChargingMonitor(  this, realTimeData, chargingParameters);
        chargingMonitor.setVisible(true);
    }
    
    
    /**
     * Create a Parameter Setup Page
     */
    public void createParamSetupPage()
    {
        if(parameterSetup == null)
        {
            this.parameterSetup = new ParameterSetup(this,this.chargingParameters);
        }
        this.parameterSetup.setVisible(true);
    }
    
    /**
     * Create a Parameter Confirm Page
     */
    public void createParamConfirmPage()
    {
        parameterConfirm = new ParameterConfirm(this,this.chargingParameters);
        this.parameterConfirm.setVisible(true);
    }
    
    /**
     * Create a Popup Dialog with a specified title and contents
     * @param title The title that will be displayed in the Pop-up Dialog
     * @param contents The contents that will be displayed in the Pop-up Dialog
     */
    public void createdPopupDialog(String title, String contents)
    {
        if(popupDialog == null)
        {
            popupDialog = new PopupDialog(new javax.swing.JFrame(), true, this, title, contents);
        }else
        {
            popupDialog.setupPopupDialog(title, contents);
        }
        popupDialog.setVisible(true);
    }
    
    /**
     * Create the Manual Configuration Page
     */
    public void createManualConfig()
    {
        man = new ManualConfigure(this.mainController);
        man.setVisible(true);
    }
    
    /**
     * This is the function that will be called when user requests charge by clicking
     * on "Start Charging" button 
     * 
     * A Parameter Confirmation Page will be displayed
     */
    public void userRequestedCharge()
    {
        this.chargingMonitor.setVisible(false);
        this.userRequestedCharge = true;
        this.createParamConfirmPage();
    }
    
    /**
     * This is the function that will be called when user clicks on "stop" button
     */
    public void userRequestedStop()
    {
        this.mainController.userRequestedStop();
    }
    
    public void userRequestedAboutTheSoftware()
    {
        String title = "About the software";
        String contents = "Designed and Implemented by Siyuan Wang.  V1.1.3";
        this.createdPopupDialog(title, contents);
    }
    
    public void userRequestedAboutTheTeam()
    {
        String title = "About the team";
        String contents = "John Augelli";
        contents += "\r\n"+"Brendan Flood";
        contents += "\r\n"+"Brendan Flood";
        contents += "\r\n"+"Sabbir Siddiqui";
        contents += "\r\n"+"Jack Fedak";
        contents += "\r\n"+"Amira Ahsan";
        contents += "\r\n"+"Kai Wang";
        contents += "\r\n"+"Jacob Hartwig";
        contents += "\r\n"+"Ethan Swerdlow";
        contents += "\r\n"+"Gerald DeBrasi";
        contents += "\r\n"+"Siyuan Wang";
        this.createdPopupDialog(title, contents);
        
    }
    
    public void userRequestedDocumentation()
    {
        String title = "Documentations";
        String contents = "https://github.com/MrAlwaysRight/SeniorDesignProject";
        this.createdPopupDialog(title, contents);
    }
    
    public void userRequestedSetParameters()
    {
        this.createParamSetupPage();
        if(this.chargingMonitor != null)
        {
            this.chargingMonitor.setVisible(false);
        }
        
        if(this.parameterConfirm != null)
        {
            this.parameterConfirm.setVisible(false);
        }
    }
    
    public void userRequestedQuitParameterConfirm()
    {
        this.parameterConfirm.setVisible(false);
        this.chargingMonitor.setVisible(true);
    }
    
    public void userApprovedParameters()
    {
        this.mainController.userApprovedParameters();
        if(this.parameterConfirm != null)
        {
            this.parameterConfirm.dispose();
        }
        
        if(this.chargingMonitor != null)
        {
            this.chargingMonitor.setVisible(true);
        }else{
            this.createChargingMonitorPage();
        }
    }
    
    public void newParametersSubmitted(ChargingParameters newChargingParameters)
    {
        this.mainController.setChargingParameters(newChargingParameters);
        if(parameterSetup != null)
        {
            parameterSetup.dispose();
        }
        
        if(this.parameterConfirm != null)
        {
            this.parameterConfirm.dispose();
        }
        
        this.chargingMonitor.setVisible(true);
        
        if(this.userRequestedCharge)
        {
            this.userRequestedCharge = false;
            this.mainController.userApprovedParameters();
        }
    }
    
    public void userRequestedQuitParameterSetup()
    {
        this.parameterSetup.dispose();
        this.chargingMonitor.setVisible(true);
    }
    
    public void userRequestedViewingParameters()
    {
        if(this.parameterConfirm == null)
        {
            this.createParamConfirmPage();
        }else
        {
            this.parameterConfirm.setVisible(true);
        }
        if(this.chargingMonitor != null)
        {
            this.chargingMonitor.setVisible(false);
        }
        if(this.parameterSetup != null)
        {
            this.parameterSetup.setVisible(false);
        }
    }
    
    public void userRequestedClosingParameterConfirm()
    {
        if(this.parameterConfirm != null)
        {
            this.parameterConfirm.dispose();
            this.parameterConfirm = null;
        }
        this.chargingMonitor.setVisible(true);
    }
       
    public void userRequestedQuitPopupWindow()
    {
        popupDialog.setVisible(false);
    }
    
    
    //functions below are related to the menu bar
    
    public void exitAndCleanUp()
    {
        if(this.chargingMonitor!=null)
        {
            this.chargingMonitor.dispose();
        }
        if(this.parameterSetup != null)
        {
            this.parameterSetup.dispose();
        }
        if(this.parameterConfirm != null)
        {
            this.parameterConfirm.dispose();
        }
        if(this.popupDialog != null)
        {
            this.popupDialog.dispose();
        }
        this.mainController.exitAndCleanUp();
    }
    
    public void setRealTimeData(RealTimeData _realTimeData)
    {
        this.realTimeData = _realTimeData;
    }
    
    public void setChargingParameters(ChargingParameters _chargingParameters)
    {
        this.chargingParameters = _chargingParameters;
    }
    
    public void setBypassSwitch(int index, boolean isOn)
    {
        this.mainController.setBypass(index, isOn);
    }
    
    public void run()
    {
        while(true)
        {
            if(this.chargingMonitor != null)
            {
                this.chargingParameters = this.mainController.getChargingParameters();
                this.realTimeData = this.mainController.getRealTimeData();
                this.chargingMonitor.setChargingParameters(this.chargingParameters);
                this.chargingMonitor.setRealTimeData(this.realTimeData);
                this.chargingMonitor.updateGUI();
            }
            try{
                Thread.sleep(10);
            }catch(Exception e)
            {
                
            }
        }
    }
}
