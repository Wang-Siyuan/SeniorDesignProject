package my.chargingcontroller;
import java.util.*;
/**
 *
 * @author This PC
 */
public class ChargingMonitor extends javax.swing.JFrame{
    
    /*
    //parameters set by the user
    public static int numOfCells = 1;
    public static double vUpper = 5;
    public static double iUpper = 600;
    public static int tUpper = 255;
    public static double bypassDuration = 0;
    public static double bypassThreshold = 0;         
    
    //real-time variables
    public static double[] currVoltage;
    public static int[] currTemp;
    public static boolean[] bypass;
    public static double[][] diff;
    public static int[] bypassTime;
    public static double currCurrent;
    public static boolean isCharging = false;
    public static int state = 0;
    public static boolean done = false;
    public static boolean chargingRelayOpen = true;
    public static int cycleTime = 0;
    public static int tick = 0;
    
    public static GUIController guiController;
    
    public static final int IDLE = 0;
    public static final int CHARGING = 1;

    //public static final double CURRENT_UPPER_LIMIT = 100;
    //public static final double CURRENT_LOWER_LIMIT = 0;
    
    public ManualConfigure manConfig = null;
    public static int maxTemp;
    */
    
    private ChargingParameters chargingParameters;
    private RealTimeData realTimeData;
    private GUIController guiController;

    /**
     * Creates new form ChargingUI
     */
    public ChargingMonitor( GUIController _guiController,
                            RealTimeData _realTimeData,
                            ChargingParameters _chargingParameters) 
    {
        initComponents();
        this.chargingParameters = _chargingParameters;
        this.realTimeData = _realTimeData;
        this.guiController = _guiController;
        this.setLocation((int)(this.getGraphicsConfiguration().getBounds().getWidth()-this.getWidth())/2,(int)(this.getGraphicsConfiguration().getBounds().getHeight()-this.getHeight())/2);
        /*
        this.myController = myController;
        this.numOfCells = myController.myController.numOfCells;
        this.currVoltage = new double[8];
        this.currTemp = new int[8];
        this.bypass = new boolean[8];
        this.diff = new double[8][8];
        this.bTime = new int[8];
        this.vUpper = myController.myController.vUpper;
        this.iUpper = myController.myController.iUpper;
        this.tempUpper = myController.myController.tempUpper;
        this.bypassDuration = myController.myController.bypassDuration;
        this.bypassThreshold = myController.myController.bypassThreshold;
        
        state = IDLE;
        tick = 0;
        isCharging = false;
        chargingRelayOpen = true;
        cycleTime = 0;
        done = false;
        
        this.currVoltage[0] = this.vLower + (this.vUpper-this.vLower)/2 - 0.5;
        this.currVoltage[1] = this.vLower + (this.vUpper-this.vLower)/2 - 0.2;
        this.currVoltage[2] = this.vLower + (this.vUpper-this.vLower)/2 - 0.3;
        this.currVoltage[3] = this.vLower + (this.vUpper-this.vLower)/2 - 0.4;
        this.currVoltage[4] = this.vLower + (this.vUpper-this.vLower)/2 - 0.8;
        this.currVoltage[5] = this.vLower + (this.vUpper-this.vLower)/2 - 0.7;
        this.currVoltage[6] = this.vLower + (this.vUpper-this.vLower)/2 - 0.6;
        this.currVoltage[7] = this.vLower + (this.vUpper-this.vLower)/2 - 0.5;
        
        this.currTemp[0] = (this.tempUpper)/2 - (this.tempUpper)/10;
        this.currTemp[1] = (this.tempUpper)/2 - (this.tempUpper)/5;
        this.currTemp[2] = (this.tempUpper)/2 - (this.tempUpper)/6;
        this.currTemp[3] = (this.tempUpper)/2 - (this.tempUpper)/8;
        this.currTemp[4] = (this.tempUpper)/2 - (this.tempUpper)/7;
        this.currTemp[5] = (this.tempUpper)/2 - (this.tempUpper)/8;
        this.currTemp[6] = (this.tempUpper)/2 - (this.tempUpper)/10;
        this.currTemp[7] = (this.tempUpper)/2 - (this.tempUpper)/9;
        
        this.currCurrent = (this.iUpper)/2;
        
        this.setLocation((int)(this.getGraphicsConfiguration().getBounds().getWidth()-this.getWidth())/2,(int)(this.getGraphicsConfiguration().getBounds().getHeight()-this.getHeight())/2); 
        
        updateGUIBasedOnCellNumber();
        updateGUIParameterRanges();
        this.jProgressBar1.setValue(50);
        this.jProgressBar2.setValue(50);
        this.jProgressBar3.setValue(50);
        this.jProgressBar4.setValue(50);
        this.jProgressBar5.setValue(50);
        this.jProgressBar6.setValue(50);
        this.jProgressBar7.setValue(50);
        this.jProgressBar8.setValue(50);
        this.jProgressBar9.setValue(50);
        this.jProgressBar10.setValue(50);
        this.jProgressBar11.setValue(50);
        this.jProgressBar12.setValue(50);
        this.jProgressBar13.setValue(50);
        this.jProgressBar14.setValue(50);
        this.jProgressBar15.setValue(50);
        this.jProgressBar16.setValue(50);
        this.jProgressBar17.setValue(50);
        this.jProgressBar18.setValue(50);
        */
        
        updateGUI();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar17 = new javax.swing.JProgressBar();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jProgressBar18 = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jProgressBar9 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar11 = new javax.swing.JProgressBar();
        jProgressBar10 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jProgressBar14 = new javax.swing.JProgressBar();
        jProgressBar15 = new javax.swing.JProgressBar();
        jProgressBar13 = new javax.swing.JProgressBar();
        jProgressBar12 = new javax.swing.JProgressBar();
        jProgressBar4 = new javax.swing.JProgressBar();
        jLabel18 = new javax.swing.JLabel();
        jProgressBar8 = new javax.swing.JProgressBar();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jProgressBar5 = new javax.swing.JProgressBar();
        jProgressBar6 = new javax.swing.JProgressBar();
        jProgressBar7 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jProgressBar16 = new javax.swing.JProgressBar();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Charging Monitor");
        setMaximumSize(new java.awt.Dimension(1600, 1000));
        setMinimumSize(new java.awt.Dimension(900, 800));
        setPreferredSize(new java.awt.Dimension(900, 800));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("Start Charging");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Charging Monitor");
        jLabel10.setMaximumSize(new java.awt.Dimension(800, 50));
        jLabel10.setMinimumSize(new java.awt.Dimension(600, 50));
        jLabel10.setPreferredSize(new java.awt.Dimension(600, 50));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton4.setText("Stop");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Overall Pack Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 36))); // NOI18N

        jProgressBar17.setString("");
        jProgressBar17.setStringPainted(true);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel22.setText("Cell Current");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("System State");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("IDLE");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setFont(jLabel22.getFont());
        jLabel24.setText("Max Cell Temperature");

        jProgressBar18.setString("");
        jProgressBar18.setStringPainted(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jProgressBar17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jProgressBar18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(4, 4, 4))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Individual Cell Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 36))); // NOI18N

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel11.setText("Cell1");
        jLabel11.setMaximumSize(new java.awt.Dimension(50, 30));
        jLabel11.setMinimumSize(new java.awt.Dimension(50, 30));
        jLabel11.setPreferredSize(new java.awt.Dimension(50, 30));

        jProgressBar1.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar1.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar1.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar1.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar1.setString("");
        jProgressBar1.setStringPainted(true);

        jLabel20.setFont(jLabel11.getFont());
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Voltage");
        jLabel20.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel20.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel20.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel21.setFont(jLabel11.getFont());
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Temp");
        jLabel21.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel21.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel21.setPreferredSize(new java.awt.Dimension(200, 30));

        jProgressBar9.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar9.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar9.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar9.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar9.setString("");
        jProgressBar9.setStringPainted(true);

        jLabel2.setFont(jLabel11.getFont());
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Not Bypassed");
        jLabel2.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel2.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel23.setFont(jLabel11.getFont());
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Status");
        jLabel23.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel23.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel23.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel12.setFont(jLabel11.getFont());
        jLabel12.setText("Cell2");
        jLabel12.setMaximumSize(new java.awt.Dimension(50, 30));
        jLabel12.setMinimumSize(new java.awt.Dimension(50, 30));
        jLabel12.setPreferredSize(new java.awt.Dimension(50, 30));

        jLabel13.setFont(jLabel11.getFont());
        jLabel13.setText("Cell3");
        jLabel13.setMaximumSize(new java.awt.Dimension(50, 30));
        jLabel13.setMinimumSize(new java.awt.Dimension(50, 30));
        jLabel13.setPreferredSize(new java.awt.Dimension(50, 30));

        jProgressBar2.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar2.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar2.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar2.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar2.setString("");
        jProgressBar2.setStringPainted(true);

        jProgressBar11.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar11.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar11.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar11.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar11.setString("");
        jProgressBar11.setStringPainted(true);

        jProgressBar10.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar10.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar10.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar10.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar10.setString("");
        jProgressBar10.setStringPainted(true);

        jProgressBar3.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar3.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar3.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar3.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar3.setString("");
        jProgressBar3.setStringPainted(true);

        jLabel3.setFont(jLabel11.getFont());
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Not Bypassed");
        jLabel3.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel3.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel4.setFont(jLabel11.getFont());
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Not Bypassed");
        jLabel4.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel4.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel4.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel7.setFont(jLabel11.getFont());
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Not Bypassed");
        jLabel7.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel7.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel7.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel6.setFont(jLabel11.getFont());
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Not Bypassed");
        jLabel6.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel6.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel6.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel5.setFont(jLabel11.getFont());
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Not Bypassed");
        jLabel5.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel5.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel5.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel8.setFont(jLabel11.getFont());
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Not Bypassed");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel8.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel8.setPreferredSize(new java.awt.Dimension(200, 30));

        jProgressBar14.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar14.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar14.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar14.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar14.setString("");
        jProgressBar14.setStringPainted(true);

        jProgressBar15.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar15.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar15.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar15.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar15.setString("");
        jProgressBar15.setStringPainted(true);

        jProgressBar13.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar13.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar13.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar13.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar13.setString("");
        jProgressBar13.setStringPainted(true);

        jProgressBar12.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar12.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar12.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar12.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar12.setString("");
        jProgressBar12.setStringPainted(true);

        jProgressBar4.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar4.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar4.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar4.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar4.setString("");
        jProgressBar4.setStringPainted(true);

        jLabel18.setFont(jLabel11.getFont());
        jLabel18.setText("Cell8");
        jLabel18.setMaximumSize(new java.awt.Dimension(50, 30));
        jLabel18.setMinimumSize(new java.awt.Dimension(50, 30));
        jLabel18.setPreferredSize(new java.awt.Dimension(50, 30));

        jProgressBar8.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar8.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar8.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar8.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar8.setString("");
        jProgressBar8.setStringPainted(true);

        jLabel17.setFont(jLabel11.getFont());
        jLabel17.setText("Cell7");
        jLabel17.setMaximumSize(new java.awt.Dimension(50, 30));
        jLabel17.setMinimumSize(new java.awt.Dimension(50, 30));
        jLabel17.setPreferredSize(new java.awt.Dimension(50, 30));

        jLabel16.setFont(jLabel11.getFont());
        jLabel16.setText("Cell6");
        jLabel16.setMaximumSize(new java.awt.Dimension(50, 30));
        jLabel16.setMinimumSize(new java.awt.Dimension(50, 30));
        jLabel16.setPreferredSize(new java.awt.Dimension(50, 30));

        jProgressBar5.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar5.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar5.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar5.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar5.setString("");
        jProgressBar5.setStringPainted(true);

        jProgressBar6.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar6.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar6.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar6.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar6.setString("");
        jProgressBar6.setStringPainted(true);

        jProgressBar7.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar7.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar7.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar7.setPreferredSize(new java.awt.Dimension(200, 30));
        jProgressBar7.setString("");
        jProgressBar7.setStringPainted(true);

        jLabel1.setFont(jLabel11.getFont());
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Not Bypassed");
        jLabel1.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel1.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel1.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel14.setFont(jLabel11.getFont());
        jLabel14.setText("Cell4");
        jLabel14.setMaximumSize(new java.awt.Dimension(50, 30));
        jLabel14.setMinimumSize(new java.awt.Dimension(50, 30));
        jLabel14.setPreferredSize(new java.awt.Dimension(50, 30));

        jLabel15.setFont(jLabel11.getFont());
        jLabel15.setText("Cell5");
        jLabel15.setMaximumSize(new java.awt.Dimension(50, 30));
        jLabel15.setMinimumSize(new java.awt.Dimension(50, 30));
        jLabel15.setPreferredSize(new java.awt.Dimension(50, 30));

        jProgressBar16.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar16.setMaximumSize(new java.awt.Dimension(200, 30));
        jProgressBar16.setMinimumSize(new java.awt.Dimension(200, 30));
        jProgressBar16.setString("");
        jProgressBar16.setStringPainted(true);

        jLabel26.setFont(jLabel11.getFont());
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Bypassed Time(sec)");
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel26.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel26.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel26.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel27.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("N/A");
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel27.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel27.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel27.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel28.setFont(jLabel27.getFont());
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("N/A");
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel28.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel28.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel28.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel29.setFont(jLabel27.getFont());
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("N/A");
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel29.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel29.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel29.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel30.setFont(jLabel27.getFont());
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("N/A");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel30.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel30.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel30.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel31.setFont(jLabel27.getFont());
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("N/A");
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel31.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel31.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel31.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel32.setFont(jLabel27.getFont());
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("N/A");
        jLabel32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel32.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel32.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel32.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel33.setFont(jLabel27.getFont());
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("N/A");
        jLabel33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel33.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel33.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel33.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel34.setFont(jLabel27.getFont());
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("N/A");
        jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel34.setMaximumSize(new java.awt.Dimension(200, 30));
        jLabel34.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel34.setPreferredSize(new java.awt.Dimension(200, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar13, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar14, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar15, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar16, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jProgressBar9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jProgressBar10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jProgressBar11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jProgressBar12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jProgressBar7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jProgressBar15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jProgressBar8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jProgressBar16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jMenu1.setText("File");

        jMenuItem8.setText("Open Manual Update Window");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Quit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu6.setText("Parameter");

        jMenuItem5.setText("Set Parameters");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem5);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Tools");

        jMenu8.setText("BMS");

        jMenuItem1.setText("Unknown Port");
        jMenu8.add(jMenuItem1);

        jMenu7.add(jMenu8);

        jMenu9.setText("Arduino");

        jMenuItem7.setText("Unknown Port");
        jMenu9.add(jMenuItem7);

        jMenu7.add(jMenu9);

        jMenuBar1.add(jMenu7);

        jMenu2.setText("About");

        jMenuItem3.setText("About this Software");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("About the Team");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Help");

        jMenuItem6.setText("Documentation");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.guiController.userRequestedCharge();
        /*
        this.realTimeData.setIsCharging(true);
        this.jButton1.setEnabled(false);
        if(this.realTimeData.getDone())
        {
            for(int i = 0; i < this.chargingParameters.getNumOfCells(); i++)
            {
                checkForBypass(i);
                if(!this.realTimeData.getBypassInfo(i))
                {
                    done = false;
                }
            }
            
            for(int i = 0; i < numOfCells; i++)
            {
                if(currTemp[i]>=tempUpper)
                {
                    done = true;
                }
            }
            if(!done && currCurrent >= iUpper)
            {
                done = true;
            }
        }
        */
        //charge();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.guiController.userRequestedStop();
        /*
        this.myController.setVisible(true);
        this.dispose();
        */
        /*
        if(state == CHARGING)
        {
           FreeWindow warningWindow = new FreeWindow(this, null, "WARNING!","Stop charging before you return to the previous window");
        }else
        {
            this.myController.setVisible(true);
            this.dispose();
        }
        */
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        //this.myController.setVisible(true);
        //this.dispose();
        this.guiController.exitAndCleanUp();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //FreeWindow aboutSoftwareWindow = new FreeWindow(this,null, "About this Software","Still under heavy development by Siyuan Wang.\r\n\r\nWe just hit Version 1.1");
        this.guiController.userRequestedAboutTheSoftware();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
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
        
        //FreeWindow AboutTeamWindow = new FreeWindow(this,null, "About the Team",contents);
         this.guiController.userRequestedAboutTheTeam();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        //FreeWindow helpWindow = new FreeWindow(this,null, "Help","Please don't hesitate to call 4845158114 for any additional assistance.");
        this.guiController.userRequestedDocumentation();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        this.guiController.createManualConfig();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        this.guiController.userRequestedSetParameters();
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    
    public void updateGUIParameterRanges()
    {
        this.jLabel22.setText("Cell Current(0-"+this.chargingParameters.getCurrentUpperLimit()+"A)");
        this.jLabel24.setText("Max Cell Temperature(0-"+this.chargingParameters.getTemperatureUpperLimit()+" Celsius)");
        this.jLabel20.setText("Voltage(0-"+this.chargingParameters.getVoltageUpperLimit()+"V)");
        this.jLabel21.setText("Temperature(0-"+this.chargingParameters.getTemperatureUpperLimit()+" Celsius)");
    }
    public void updateGUIBasedOnCellNumber()
    {
        int numOfCells = this.chargingParameters.getNumOfCells();
        if(numOfCells < 8)
        {
            this.jLabel18.setVisible(false);
            this.jProgressBar8.setVisible(false);
            this.jProgressBar16.setVisible(false);
            this.jLabel8.setVisible(false);
            this.jLabel34.setVisible(false);
        }
        
        if(numOfCells <7)
        {
            this.jLabel17.setVisible(false);
            this.jProgressBar7.setVisible(false);
            this.jProgressBar15.setVisible(false);
            this.jLabel7.setVisible(false);
            this.jLabel33.setVisible(false);
        }
        if(numOfCells < 6)
        {
            this.jLabel16.setVisible(false);
            this.jProgressBar6.setVisible(false);
            this.jProgressBar14.setVisible(false);
            this.jLabel6.setVisible(false);
            this.jLabel32.setVisible(false);
        }
        if(numOfCells < 5)
        {
            this.jLabel15.setVisible(false);
            this.jProgressBar5.setVisible(false);
            this.jProgressBar13.setVisible(false);
            this.jLabel5.setVisible(false);
            this.jLabel31.setVisible(false);
        }
        if(numOfCells < 4)
        {
            this.jLabel14.setVisible(false);
            this.jProgressBar4.setVisible(false);
            this.jProgressBar12.setVisible(false);
            this.jLabel4.setVisible(false);
            this.jLabel30.setVisible(false);
        }
        if(numOfCells < 3)
        {
            this.jLabel13.setVisible(false);
            this.jProgressBar3.setVisible(false);
            this.jProgressBar11.setVisible(false);
            this.jLabel3.setVisible(false);
            this.jLabel29.setVisible(false);
        }
        if(numOfCells < 2)
        {
            this.jLabel12.setVisible(false);
            this.jProgressBar2.setVisible(false);
            this.jProgressBar10.setVisible(false);
            this.jLabel2.setVisible(false);
            this.jLabel28.setVisible(false);
            //this.jLabel26.setVisible(false);
            //this.jLabel26.setLocation((int)(this.jLabel2.getLocation().getX()+206),(int)this.jLabel2.getLocation().getY());
        }
    }    
    
    public void updateGUI()
    {
        updatePorts();
        updateGUIParameterRanges();
        updateGUIBasedOnCellNumber();
        updateButtons();
        updateProgressBarProgress();
        updateState();     
        updateProgressBarValue();
        updateBypassStatusAndTime();
    }
    
    public void updatePorts()
    {
        this.jMenuItem1.setText(this.chargingParameters.getPortToBMS());
        this.jMenuItem7.setText(this.chargingParameters.getPortToArduino());
    }
    
    public void updateButtons()
    {
        //fade and enable appropriate button for different charging state
        if(realTimeData.getState() == realTimeData.state.CHARGING)
        {
            jButton1.setEnabled(false);
            jButton4.setEnabled(true);
        }else if(realTimeData.getState() == realTimeData.state.IDLE)
        {
            jButton1.setEnabled(true);
            jButton4.setEnabled(false);
        }
    }
    
    public void updateProgressBarProgress()
    {
       //update the progress bars for all the new voltage readings
        jProgressBar1.setValue((int)((realTimeData.getVoltage(0)/chargingParameters.getVoltageUpperLimit())*100));
        jProgressBar2.setValue((int)((realTimeData.getVoltage(1)/chargingParameters.getVoltageUpperLimit())*100));
        jProgressBar3.setValue((int)((realTimeData.getVoltage(2)/chargingParameters.getVoltageUpperLimit())*100));
        jProgressBar4.setValue((int)((realTimeData.getVoltage(3)/chargingParameters.getVoltageUpperLimit())*100));
        jProgressBar5.setValue((int)((realTimeData.getVoltage(4)/chargingParameters.getVoltageUpperLimit())*100));
        jProgressBar6.setValue((int)((realTimeData.getVoltage(5)/chargingParameters.getVoltageUpperLimit())*100));
        jProgressBar7.setValue((int)((realTimeData.getVoltage(6)/chargingParameters.getVoltageUpperLimit())*100));
        jProgressBar8.setValue((int)((realTimeData.getVoltage(7)/chargingParameters.getVoltageUpperLimit())*100));

        
        //update the temperature progress bars based on the new real time data
        jProgressBar9.setValue((int)(100*realTimeData.getTemperature(0)/chargingParameters.getTemperatureUpperLimit()));
        jProgressBar10.setValue((int)(100*realTimeData.getTemperature(1)/chargingParameters.getTemperatureUpperLimit()));
        jProgressBar11.setValue((int)(100*realTimeData.getTemperature(2)/chargingParameters.getTemperatureUpperLimit()));
        jProgressBar12.setValue((int)(100*realTimeData.getTemperature(3)/chargingParameters.getTemperatureUpperLimit()));
        jProgressBar13.setValue((int)(100*realTimeData.getTemperature(4)/chargingParameters.getTemperatureUpperLimit()));
        jProgressBar14.setValue((int)(100*realTimeData.getTemperature(5)/chargingParameters.getTemperatureUpperLimit()));
        jProgressBar15.setValue((int)(100*realTimeData.getTemperature(6)/chargingParameters.getTemperatureUpperLimit()));
        jProgressBar16.setValue((int)(100*realTimeData.getTemperature(7)/chargingParameters.getTemperatureUpperLimit()));
        jProgressBar17.setValue((int)(100*realTimeData.getCurrent()/chargingParameters.getCurrentUpperLimit()));      
        jProgressBar18.setValue((int)(100*realTimeData.getMaxTemp()/chargingParameters.getTemperatureUpperLimit())); 
    }
    
    public void updateState()
    {
       if(realTimeData.getDone())
        {
             jLabel9.setText("DONE");
        }else if(realTimeData.getState() == realTimeData.state.CHARGING)
        {
            jLabel9.setText("CHARGING");
        }else if(realTimeData.getState() == realTimeData.state.IDLE)
        {
            jLabel9.setText("IDLE");
        }else
        {
            //error handling
        } 
    }
    
    public void updateProgressBarValue()
    {
        this.jProgressBar1.setString(String.valueOf(this.realTimeData.getVoltage(0))+"V");
        this.jProgressBar2.setString(String.valueOf(this.realTimeData.getVoltage(1))+"V");
        this.jProgressBar3.setString(String.valueOf(this.realTimeData.getVoltage(2))+"V");
        this.jProgressBar4.setString(String.valueOf(this.realTimeData.getVoltage(3))+"V");
        this.jProgressBar5.setString(String.valueOf(this.realTimeData.getVoltage(4))+"V");
        this.jProgressBar6.setString(String.valueOf(this.realTimeData.getVoltage(5))+"V");
        this.jProgressBar7.setString(String.valueOf(this.realTimeData.getVoltage(6))+"V");
        this.jProgressBar8.setString(String.valueOf(this.realTimeData.getVoltage(7))+"V");
        this.jProgressBar9.setString(String.valueOf(this.realTimeData.getTemperature(0))+" Celsius");
        this.jProgressBar10.setString(String.valueOf(this.realTimeData.getTemperature(1))+" Celsius");
        this.jProgressBar11.setString(String.valueOf(this.realTimeData.getTemperature(2))+" Celsius");
        this.jProgressBar12.setString(String.valueOf(this.realTimeData.getTemperature(3))+" Celsius");
        this.jProgressBar13.setString(String.valueOf(this.realTimeData.getTemperature(4))+" Celsius");
        this.jProgressBar14.setString(String.valueOf(this.realTimeData.getTemperature(5))+" Celsius");
        this.jProgressBar15.setString(String.valueOf(this.realTimeData.getTemperature(6))+" Celsius");
        this.jProgressBar16.setString(String.valueOf(this.realTimeData.getTemperature(7))+" Celsius");
        this.jProgressBar17.setString(String.valueOf(this.realTimeData.getCurrent())+"A");
        this.jProgressBar18.setString(String.valueOf(this.realTimeData.getMaxTemp())+" Celsius");
    }
    
    public String getBypassTimeInString(int index)
    {
        String timeToReturn = "";
        long bypassTime = this.realTimeData.getBypassTime(index);
        timeToReturn = Long.toString((bypassTime >> 32) & 0x000000000000FFFF );
        timeToReturn += " m ";
        timeToReturn += Long.toString((bypassTime >> 16) & 0x000000000000FFFF );
        timeToReturn += " s ";
        timeToReturn += Long.toString(bypassTime & 0x000000000000FFFF );
        timeToReturn += " ms";
        return timeToReturn;
    }
    
    public void updateBypassStatusAndTime()
    {
        if(this.realTimeData.getBypassInfo(0))
        {
            this.jLabel1.setText("Bypassed");
            this.jLabel27.setText(""+this.getBypassTimeInString(0));
        }else
        {
            this.jLabel1.setText("Not Bypassed");
            this.jLabel27.setText("N/A");
        }
        
        if(this.realTimeData.getBypassInfo(1))
        {
            this.jLabel2.setText("Bypassed");
            this.jLabel28.setText(""+this.getBypassTimeInString(1));
        }else
        {
            this.jLabel2.setText("Not Bypassed");
            this.jLabel28.setText("N/A");
        }
        
        if(this.realTimeData.getBypassInfo(2))
        {
            this.jLabel3.setText("Bypassed");
            this.jLabel29.setText(""+this.getBypassTimeInString(2));
        }else
        {
            this.jLabel3.setText("Not Bypassed");
            this.jLabel29.setText("N/A");
        }
        
        if(this.realTimeData.getBypassInfo(3))
        {
            this.jLabel4.setText("Bypassed");
            this.jLabel30.setText(""+this.getBypassTimeInString(3));
        }else
        {
            this.jLabel4.setText("Not Bypassed");
            this.jLabel30.setText("N/A");
        }
        
        if(this.realTimeData.getBypassInfo(4))
        {
            this.jLabel5.setText("Bypassed");
            this.jLabel31.setText(""+this.getBypassTimeInString(4));
        }else
        {
            this.jLabel5.setText("Not Bypassed");
            this.jLabel31.setText("N/A");
        }
        if(this.realTimeData.getBypassInfo(5))
        {
            this.jLabel6.setText("Bypassed");
            this.jLabel32.setText(""+this.getBypassTimeInString(5));
        }else
        {
            this.jLabel6.setText("Not Bypassed");
            this.jLabel32.setText("N/A");
        }
        
        if(this.realTimeData.getBypassInfo(6))
        {
            this.jLabel7.setText("Bypassed");
            this.jLabel33.setText(""+this.getBypassTimeInString(6));
        }else
        {
            this.jLabel7.setText("Not Bypassed");
            this.jLabel33.setText("N/A");
        }
        
        if(this.realTimeData.getBypassInfo(7))
        {
            this.jLabel8.setText("Bypassed");
            this.jLabel34.setText(""+this.getBypassTimeInString(7));
        }else
        {
            this.jLabel8.setText("Not Bypassed");
            this.jLabel34.setText("N/A");
        }
    }
    
    public void setRealTimeData(RealTimeData _realTimeData)
    {
        this.realTimeData = _realTimeData;
    }
    
    public void setChargingParameters(ChargingParameters _chargingParameters)
    {
        this.chargingParameters = _chargingParameters;
    }
    public RealTimeData getRealTimeData()
    {
        return this.realTimeData;
    }
    
    public ChargingParameters getChargingParameters()
    {
        return this.chargingParameters;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChargingMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChargingMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChargingMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChargingMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChargingMonitor(null, null, null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar10;
    private javax.swing.JProgressBar jProgressBar11;
    private javax.swing.JProgressBar jProgressBar12;
    private javax.swing.JProgressBar jProgressBar13;
    private javax.swing.JProgressBar jProgressBar14;
    private javax.swing.JProgressBar jProgressBar15;
    private javax.swing.JProgressBar jProgressBar16;
    private javax.swing.JProgressBar jProgressBar17;
    private javax.swing.JProgressBar jProgressBar18;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    private javax.swing.JProgressBar jProgressBar7;
    private javax.swing.JProgressBar jProgressBar8;
    private javax.swing.JProgressBar jProgressBar9;
    // End of variables declaration//GEN-END:variables
}
