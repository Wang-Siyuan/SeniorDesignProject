/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.chargingcontroller;

/**
 *
 * @author This PC
 */
public class GUIController extends Thread{
    
    private ChargingMonitor chargingMonitor = null;
    private ParameterSetup parameterSetup = null;
    private ParameterConfirm parameterConfirm = null;
    private PopupDialog popupDialog = null;
    private ManualConfigure man = null;
    
    private RealTimeData realTimeData;
    private ChargingParameters chargingParameters;
    private MainController mainController;
    private boolean userRequestedCharge;

    
    public GUIController(   MainController _mainController, 
                            ChargingParameters _chargingParameters,
                            RealTimeData _realTimeData)
    {
        
    
        this.mainController = _mainController;
        
        this.chargingParameters = _chargingParameters;

        this.realTimeData = _realTimeData;
        this.userRequestedCharge = false;
    }
    
    public void createChargingMonitorPage()
    {
        chargingMonitor = new ChargingMonitor(  this, realTimeData,
                                                chargingParameters);
        chargingMonitor.setVisible(true);
    }
    
    public void createParamSetupPage()
    {
        if(parameterSetup == null)
        {
            this.parameterSetup = new ParameterSetup(this,this.chargingParameters);
        }
        this.parameterSetup.setVisible(true);
    }
    
    public void createParamConfirmPage()
    {
        parameterConfirm = new ParameterConfirm(this,this.chargingParameters);
        this.parameterConfirm.setVisible(true);
    }
    
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
    
    public void createManualConfig()
    {
        man = new ManualConfigure(this.mainController);
        man.setVisible(true);
    }
    
    public void userRequestedCharge()
    {
        //this.mainController.userRequestedCharge();
        this.chargingMonitor.setVisible(false);
        this.userRequestedCharge = true;
        this.createParamConfirmPage();
    }
    
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
