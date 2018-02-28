

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Math.sqrt;
import java.net.Socket;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Interface extends javax.swing.JFrame {
   
    private Thread t;
    
    Double D,HPZ;
    int V,DP;
    static String DX, DY, DZ;
    //String PX, PY, PZ;
    static Double dx = 0.0, dy = 0.0, dz = 0.0;
    Double px = 0.0, py = 0.0, pz = 0.0;
    Thread m;

    DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String dat = dateformat.format(date);

    public Interface() {
        initComponents();
        m = new Thread(new Heure());// Heure c'est une classe pour recupérer l'heure 
        m.start();
        TextD.setText(" " + dat);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SliderVitesse = new javax.swing.JSlider();
        SliderAltitude = new javax.swing.JSlider();
        LabAl = new javax.swing.JLabel();
        LabV = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TextD = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        XDEST = new javax.swing.JTextField();
        YDEST = new javax.swing.JTextField();
        ZDEST = new javax.swing.JTextField();
        Dest = new javax.swing.JButton();
        cordonnées = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        heure = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        XPOSITION = new javax.swing.JTextField();
        YPOSITION = new javax.swing.JTextField();
        ZPOSITION = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Dist = new javax.swing.JLabel();
        Res = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Reste = new javax.swing.JTextField();
        Bar = new javax.swing.JProgressBar();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interface GCS");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        SliderVitesse.setMajorTickSpacing(50);
        SliderVitesse.setMaximum(300);
        SliderVitesse.setMinorTickSpacing(10);
        SliderVitesse.setPaintLabels(true);
        SliderVitesse.setPaintTicks(true);
        SliderVitesse.setSnapToTicks(true);
        SliderVitesse.setValue(0);
        SliderVitesse.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SliderVitesse.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SliderVitesseStateChanged(evt);
            }
        });

        SliderAltitude.setMajorTickSpacing(50);
        SliderAltitude.setMaximum(500);
        SliderAltitude.setMinorTickSpacing(50);
        SliderAltitude.setPaintLabels(true);
        SliderAltitude.setPaintTicks(true);
        SliderAltitude.setValue(0);
        SliderAltitude.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SliderAltitude.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SliderAltitudeStateChanged(evt);
            }
        });

        LabAl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabAl.setText("0");

        LabV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabV.setText("0");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Gmaps");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText(" Vitesse :");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Altitude : ");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Date :");

        TextD.setBackground(new java.awt.Color(51, 255, 51));
        TextD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TextD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextDActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Destination (X, Y, Z)");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("X :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Y :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Z :");

        XDEST.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        XDEST.setText("100");
        XDEST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XDESTActionPerformed(evt);
            }
        });

        YDEST.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        YDEST.setText("0");

        ZDEST.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ZDEST.setText("0");

        Dest.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Dest.setText("Démarrer");
        Dest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DestActionPerformed(evt);
            }
        });

        cordonnées.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cordonnées.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cordonnéesActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Heure :");

        heure.setBackground(new java.awt.Color(51, 255, 51));
        heure.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        heure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heureActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Afficher la position initial de drone (X, Y, Z) :");

        XPOSITION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        XPOSITION.setText("0");
        XPOSITION.setToolTipText("");
        XPOSITION.setBorder(null);
        XPOSITION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XPOSITIONActionPerformed(evt);
            }
        });

        YPOSITION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        YPOSITION.setText("0");
        YPOSITION.setBorder(null);
        YPOSITION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YPOSITIONActionPerformed(evt);
            }
        });

        ZPOSITION.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ZPOSITION.setText("0");
        ZPOSITION.setBorder(null);
        ZPOSITION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZPOSITIONActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("X :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Y :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Z :");

        Dist.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Dist.setText("Distance :");

        Res.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Res.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Drone");

        jLabel15.setText("Position initial");

        jLabel16.setText("Destination");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Reste :");

        Reste.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Reste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResteActionPerformed(evt);
            }
        });

        Bar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Bar.setForeground(new java.awt.Color(0, 255, 0));
        Bar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                BarStateChanged(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Users\\BOUYA\\Documents\\NetBeansProjects\\Simulateur\\src\\simulateur\\iconImages\\d5.jpg")); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(XDEST, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(YDEST, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(XPOSITION, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ZDEST, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(YPOSITION, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ZPOSITION, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1194, 1194, 1194))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Dest)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cordonnées)
                                .addGap(18, 18, 18)
                                .addComponent(Dist, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(227, 227, 227))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(478, 478, 478)
                                .addComponent(Res, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)))
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(Reste, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(880, 880, 880))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SliderAltitude, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SliderVitesse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabAl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(heure, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel1)
                                .addGap(36, 36, 36)
                                .addComponent(TextD, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel16))
                                    .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8)
                            .addComponent(heure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(SliderVitesse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LabV))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SliderAltitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(LabAl))
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(6, 6, 6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(XPOSITION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(YPOSITION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ZPOSITION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(XDEST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(YDEST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ZDEST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cordonnées, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Dest)
                    .addComponent(Dist, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Res, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(45, 45, 45)
                .addComponent(jButton1)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SliderVitesseStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SliderVitesseStateChanged
        LabV.setText(Integer.toString(SliderVitesse.getValue()));
        V=SliderVitesse.getValue();
        //Res.setText(Integer.toString(SliderV.getValue()));

    }//GEN-LAST:event_SliderVitesseStateChanged

    private void SliderAltitudeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SliderAltitudeStateChanged
        LabAl.setText(Integer.toString(SliderAltitude.getValue()));
        /******* Récuperer la valeur de l'altitude à chaque changement ********/
       HPZ=SliderAltitude.getValue()*1.0;
       ZPOSITION.setText(""+ HPZ);
       Distance(dx, dy, dz, px, py, HPZ);
        //System.out.println(HPZ);
    }//GEN-LAST:event_SliderAltitudeStateChanged
      
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GM g = new GM();
        g.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

    }//GEN-LAST:event_jButton1MouseClicked

    private void XDESTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XDESTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_XDESTActionPerformed

    private void DestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DestActionPerformed

   /****************** DESTINATION ***************************/        
        DX = XDEST.getText();
        DY = YDEST.getText();
        DZ = ZDEST.getText();
        System.out.println("voila la valeur "+DX);
        /************* genérer automatic les cordonnées de position******/
        px=Math.random()*1000;
        py=Math.random()*1000;
        pz=Math.random()*500;     
  /********** Envoyer la  valeur z initial au slider de l'Altitude **********/
        int z = pz.intValue();
        SliderAltitude.setValue(z);
 /***********************************************************************/
        XPOSITION.setText(""+px);
        YPOSITION.setText(""+py);         
 /*********************Teste pour les valeurs saisis  ***************/
        try {
           
            int d = (int) Double.parseDouble(DX);
            int dd = (int) Double.parseDouble(DY);
            int ddd = (int) Double.parseDouble(DZ);
            //********convert les cordonnées de destiantion string vers Double****/
            dx = Double.parseDouble(DX);
            // System.out.println(dx);
            dy = Double.parseDouble(DY);
            dz = Double.parseDouble(DZ);
          
            cordonnées.setText("X: " + dx + " - " + px + " Y: " + dy + " - " + py + " Z: " + dz + " - " + HPZ);
            //Distance(dx, dy, dz, px, py, HPZ);
            /****************** Parcour de drone ******************************/
            t = new Thread(new Interface.Traitement());
            t.start();            
      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Entrer des chiffres pas de caractères");
        }
        
    }//GEN-LAST:event_DestActionPerformed

    private void TextDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextDActionPerformed
        // champ pour date
    }//GEN-LAST:event_TextDActionPerformed

    private void YPOSITIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YPOSITIONActionPerformed
        // position de drone sur Y
    }//GEN-LAST:event_YPOSITIONActionPerformed

    private void XPOSITIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XPOSITIONActionPerformed
        // position de drone sur X
    }//GEN-LAST:event_XPOSITIONActionPerformed

    private void ZPOSITIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZPOSITIONActionPerformed
        // position de drone sur Z
    }//GEN-LAST:event_ZPOSITIONActionPerformed

    private void cordonnéesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cordonnéesActionPerformed
        // cordonnées sz destination
    }//GEN-LAST:event_cordonnéesActionPerformed

    private void heureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heureActionPerformed
        // heure
    }//GEN-LAST:event_heureActionPerformed

    private void ResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ResActionPerformed

    private void ResteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ResteActionPerformed

    private void BarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_BarStateChanged
        int DI = D.intValue();
        Bar.setMaximum(DI);
        Bar.setMinimum(0);
        //Bar.setStringPainted(true);// afficher % de parcours
        DP=Bar.getValue();
        int DD = D.intValue();
        Reste.setText(" "+ (DD-DP));
        //System.out.println(DP);
        
    }//GEN-LAST:event_BarStateChanged
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface().setVisible(true);
            }
        });
        /**************** Connexion ********************/
       final Socket clientSocket;
       final BufferedReader in;
       final PrintWriter out;
       final Scanner sc = new Scanner(System.in);//pour lire à partir du clavier
      
            try {
           clientSocket = new Socket("127.0.0.1",5000);
           //flux pour envoyer
           out = new PrintWriter(clientSocket.getOutputStream());
           //flux pour recevoir
           in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           //****************************************************************************************
            //System.out.println(" "+ dx+dy+dz);
           Thread envoyer = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while(msg!=null){                        
                        msg= sc.nextLine();
                        out.println(msg);
                        out.flush();  
                    }
                    }
            });
            envoyer.start();
            
           Thread recevoir = new Thread(new Runnable() {
               String msg;
               @Override
               public void run() {
                   try {
                       msg = in.readLine();
                       String tab=msg;
                       System.out.println("le voila la table "+ tab);

                       while(msg!=null){
                           System.out.println("Serveur : "+msg);
                           msg = in.readLine();
                       }
                       System.out.println("Serveur déconecté");
                       out.close();
                       clientSocket.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           });
           recevoir.start();
       }
       catch (IOException e) {
           e.printStackTrace();
       }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar Bar;
    private javax.swing.JButton Dest;
    private javax.swing.JLabel Dist;
    private javax.swing.JLabel LabAl;
    private javax.swing.JLabel LabV;
    private javax.swing.JTextField Res;
    private javax.swing.JTextField Reste;
    private javax.swing.JSlider SliderAltitude;
    private javax.swing.JSlider SliderVitesse;
    private javax.swing.JTextField TextD;
    private javax.swing.JTextField XDEST;
    private javax.swing.JTextField XPOSITION;
    private javax.swing.JTextField YDEST;
    private javax.swing.JTextField YPOSITION;
    private javax.swing.JTextField ZDEST;
    private javax.swing.JTextField ZPOSITION;
    private javax.swing.JTextField cordonnées;
    private javax.swing.JTextField heure;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
 //************************************ HEURE ********************//   

    class Heure implements Runnable {
        public void run() {
            Boolean bool = true;
            while (bool) {
                Time heur;
                heur = new Time(0);
                heur.setTime(System.currentTimeMillis());
                heure.setText(" " + heur);
                try {
                    m.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    }
/*********************** CALCULER LA DISTANCE ********************************/   
    public void Distance(Double dx, Double dy, Double dz, Double px, Double py, Double pz) {
        Double rx = dx - px;
        Double ry = dy - py;
        Double rz = dz - pz;
        Double dist = sqrt(Math.pow(rx, 2) + Math.pow(ry, 2) + Math.pow(rz, 2));
        D=dist;
        Res.setText(" " + dist);
    }
    /************************ bouger le drone ***************/
    class Traitement implements Runnable{   
    public void run(){
      Dest.setEnabled(false);   
      for( Double val = 0.0; val <= D ; val+= V*0.0001){
        int d = val.intValue();
        Bar.setValue(d);
        try {
          t.sleep(10);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
        e.printStackTrace();
        }
      }
      Dest.setEnabled(true);
    }   
  }
}
   


  

