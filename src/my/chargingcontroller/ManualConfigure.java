/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.chargingcontroller;

/**
 *
 * @author This PC
 */
public class ManualConfigure extends javax.swing.JFrame {
    public MainController mainController;
    RealTimeData realTimeData;
    ChargingParameters chargingParameters;

    /**
     * Creates new form ManualConfigure
     */
    public ManualConfigure(MainController _mainController) {
        initComponents();
        this.mainController = _mainController;
        this.realTimeData = mainController.getRealTimeData();
        this.initializeValues();
        this.setLocation((int)(this.getGraphicsConfiguration().getBounds().getWidth()-this.getWidth())/2,(int)(this.getGraphicsConfiguration().getBounds().getHeight()-this.getHeight())/2); 
        this.updateGUIBasedOnNumOfCells(this.mainController.getChargingParameters().getNumOfCells());
        this.chargingParameters = this.mainController.getChargingParameters();
    }
    
    public void initializeValues()
    {
        this.jSpinner1.setValue(this.realTimeData.getVoltage(0));
        this.jSpinner2.setValue(this.realTimeData.getVoltage(1));
        this.jSpinner3.setValue(this.realTimeData.getVoltage(2));
        this.jSpinner4.setValue(this.realTimeData.getVoltage(3));
        this.jSpinner5.setValue(this.realTimeData.getVoltage(4));
        this.jSpinner6.setValue(this.realTimeData.getVoltage(5));
        this.jSpinner7.setValue(this.realTimeData.getVoltage(6));
        this.jSpinner9.setValue(this.realTimeData.getVoltage(7));
        this.jSpinner10.setValue(this.realTimeData.getTemperature(0));
        this.jSpinner11.setValue(this.realTimeData.getTemperature(1));
        this.jSpinner12.setValue(this.realTimeData.getTemperature(2));
        this.jSpinner13.setValue(this.realTimeData.getTemperature(3));
        this.jSpinner14.setValue(this.realTimeData.getTemperature(4));
        this.jSpinner15.setValue(this.realTimeData.getTemperature(5));
        this.jSpinner16.setValue(this.realTimeData.getTemperature(6));
        this.jSpinner17.setValue(this.realTimeData.getTemperature(7));
        this.jSpinner8.setValue(this.realTimeData.getCurrent());
    }
    
    public void updateGUIBasedOnNumOfCells(int numOfCells)
    {
        if(numOfCells < 8)
        {
            this.jSpinner9.setVisible(false);
            this.jSpinner17.setVisible(false);
            this.jLabel9.setVisible(false);
        }
        
        if(numOfCells <7)
        {
            this.jSpinner7.setVisible(false);
            this.jSpinner16.setVisible(false);
            this.jLabel8.setVisible(false);
        }
        if(numOfCells < 6)
        {
            this.jSpinner6.setVisible(false);
            this.jSpinner15.setVisible(false);
            this.jLabel7.setVisible(false);
        }
        if(numOfCells < 5)
        {
            this.jSpinner5.setVisible(false);
            this.jSpinner14.setVisible(false);
            this.jLabel6.setVisible(false);
        }
        if(numOfCells < 4)
        {
            this.jSpinner4.setVisible(false);
            this.jSpinner13.setVisible(false);
            this.jLabel5.setVisible(false);
        }
        if(numOfCells < 3)
        {
            this.jSpinner3.setVisible(false);
            this.jSpinner12.setVisible(false);
            this.jLabel4.setVisible(false);
        }
        if(numOfCells < 2)
        {
            this.jSpinner2.setVisible(false);
            this.jSpinner11.setVisible(false);
            this.jLabel3.setVisible(false);
        }
    }
    
    public void updateRealTimeData()
    {
        
        this.realTimeData.setVoltage(0,(double)this.jSpinner1.getValue());
        this.realTimeData.setVoltage(1,(double)this.jSpinner2.getValue());
        this.realTimeData.setVoltage(2,(double)this.jSpinner3.getValue());
        this.realTimeData.setVoltage(3,(double)this.jSpinner4.getValue());
        this.realTimeData.setVoltage(4,(double)this.jSpinner5.getValue());
        this.realTimeData.setVoltage(5,(double)this.jSpinner6.getValue());
        this.realTimeData.setVoltage(6,(double)this.jSpinner7.getValue());
        this.realTimeData.setVoltage(7,(double)this.jSpinner9.getValue());

        this.realTimeData.setTemperature(0,(int)this.jSpinner10.getValue());
        this.realTimeData.setTemperature(1,(int)this.jSpinner11.getValue());
        this.realTimeData.setTemperature(2,(int)this.jSpinner12.getValue());
        this.realTimeData.setTemperature(3,(int)this.jSpinner13.getValue());
        this.realTimeData.setTemperature(4,(int)this.jSpinner14.getValue());
        this.realTimeData.setTemperature(5,(int)this.jSpinner15.getValue());
        this.realTimeData.setTemperature(6,(int)this.jSpinner16.getValue());
        this.realTimeData.setTemperature(7,(int)this.jSpinner17.getValue());
        this.realTimeData.setCurrent((double)this.jSpinner8.getValue());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner10 = new javax.swing.JSpinner();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner6 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jSpinner5 = new javax.swing.JSpinner();
        jSpinner7 = new javax.swing.JSpinner();
        jSpinner9 = new javax.swing.JSpinner();
        jSpinner11 = new javax.swing.JSpinner();
        jSpinner12 = new javax.swing.JSpinner();
        jSpinner13 = new javax.swing.JSpinner();
        jSpinner14 = new javax.swing.JSpinner();
        jSpinner15 = new javax.swing.JSpinner();
        jSpinner16 = new javax.swing.JSpinner();
        jSpinner17 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jSpinner8 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSpinner10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner10.setModel(new javax.swing.SpinnerNumberModel(20, 0, 255, 1));
        jSpinner10.setDoubleBuffered(true);

        jSpinner1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(3.5d, 0.0d, 5.0d, 0.1d));

        jSpinner2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(3.5d, 0.0d, 5.0d, 0.1d));

        jSpinner3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(3.5d, 0.0d, 5.0d, 0.1d));

        jSpinner6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner6.setModel(new javax.swing.SpinnerNumberModel(3.5d, 0.0d, 5.0d, 0.1d));

        jSpinner4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(3.5d, 0.0d, 5.0d, 0.1d));

        jSpinner5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner5.setModel(new javax.swing.SpinnerNumberModel(3.5d, 0.0d, 5.0d, 0.1d));

        jSpinner7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner7.setModel(new javax.swing.SpinnerNumberModel(3.5d, 0.0d, 5.0d, 0.1d));

        jSpinner9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner9.setModel(new javax.swing.SpinnerNumberModel(3.5d, 0.0d, 5.0d, 0.1d));

        jSpinner11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner11.setModel(new javax.swing.SpinnerNumberModel(20, 0, 255, 1));

        jSpinner12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner12.setModel(new javax.swing.SpinnerNumberModel(20, 0, 255, 1));

        jSpinner13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner13.setModel(new javax.swing.SpinnerNumberModel(20, 0, 255, 1));

        jSpinner14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner14.setModel(new javax.swing.SpinnerNumberModel(20, 0, 255, 1));

        jSpinner15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner15.setModel(new javax.swing.SpinnerNumberModel(20, 0, 255, 1));

        jSpinner16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner16.setModel(new javax.swing.SpinnerNumberModel(20, 0, 255, 1));

        jSpinner17.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner17.setModel(new javax.swing.SpinnerNumberModel(20, 0, 255, 1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("cell1");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("cell2");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("cell3");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("cell4");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("cell5");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("cell6");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("cell7");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("cell8");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Manual Update");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("voltage");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setText("temp");

        jButton1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton1.setText("Quit Manual Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton2.setText("Click to Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setText("Current");

        jSpinner8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jSpinner8.setModel(new javax.swing.SpinnerNumberModel(10.0d, 0.0d, 100.0d, 0.1d));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinner9)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSpinner10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner16, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner15, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner14, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner11, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(53, 53, 53)
                                .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(72, 72, 72))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jSpinner10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jSpinner12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSpinner14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(19, 26, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        updateRealTimeData();
        this.mainController.setRealTimeData(this.realTimeData);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ManualConfigure.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManualConfigure.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManualConfigure.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManualConfigure.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManualConfigure(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner10;
    private javax.swing.JSpinner jSpinner11;
    private javax.swing.JSpinner jSpinner12;
    private javax.swing.JSpinner jSpinner13;
    private javax.swing.JSpinner jSpinner14;
    private javax.swing.JSpinner jSpinner15;
    private javax.swing.JSpinner jSpinner16;
    private javax.swing.JSpinner jSpinner17;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JSpinner jSpinner8;
    private javax.swing.JSpinner jSpinner9;
    // End of variables declaration//GEN-END:variables
}
