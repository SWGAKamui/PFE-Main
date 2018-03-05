
package rpas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.sqrt;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Station_Sol extends JFrame {
    public  String tabPosi, tabP;
    private static Connect connect= new Connect();
    Thread t,d;
    Container contenu;
    
///******************** Variable *****************************   
    Double D,HPZ;
    int V,DP;
    static String VDX, VDY, VDZ,VDX1, VDY1, VDZ1;
    static Double dx = 0.0, dy = 0.0, dz = 0.0;
    Double px = 0.0, py = 0.0, pz = 0.0;
//****************************************** JLABEL ****************    
    JLabel Heure=new JLabel("Heure :");
    JLabel Date=new JLabel("Date :");
    JLabel Vitesse=new JLabel("Vitesse");
    JLabel ValeurV=new JLabel("0");
    JLabel Altitude=new JLabel("Altitude");
    JLabel ValeurAlt=new JLabel("0");
    JLabel CPosition=new JLabel("Afficher la position initial de drone (X,Y,Z) :");
    JLabel PX=new JLabel("X");
    JLabel PY=new JLabel("Y");
    JLabel PZ=new JLabel("Z");
    JLabel CDestination =new JLabel("Destination (X,Y,Z):");
    JLabel DX=new JLabel("X");
    JLabel DY=new JLabel("Y");
    JLabel DZ=new JLabel("Z");
    JLabel Distance=new JLabel("Distance");
    JLabel Reste=new JLabel("Reste");
    JLabel Drone=new JLabel("Drone");
    JLabel PI=new JLabel("Position initial");
    JLabel Dest=new JLabel("Arrivée");
    JLabel Photo=new JLabel("");
//********************************************************************   
    
 //************************************** JTextField *****************
    JTextField THeure= new JTextField();
    JTextField TDate= new JTextField();
    JTextField TPX= new JTextField();
    JTextField TPY= new JTextField();
    JTextField TPZ= new JTextField();
    JTextField TDX= new JTextField();
    JTextField TDY= new JTextField();
    JTextField TDZ= new JTextField();
    JTextField TPI_DEST= new JTextField();
    JTextField TDistance= new JTextField();
    JTextField TReste= new JTextField();
//*****************************************************************
    
//***************************** BOUTTON ***************************
    JButton Connecter=new JButton("Connecter");
    JButton Envoyer =new JButton("Envoyer");
    JButton Demarrer=new JButton("Démarrer");  
//*****************************************************************
    
//************************* SLIDER ************************************
    JSlider SVitesse= new JSlider(JSlider.HORIZONTAL);
    JSlider SAltitude= new JSlider(JSlider.HORIZONTAL);
//********************************************************************

//**************************** BarProgress ***************************
    JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL);
//*********************************************************************
    
 //**************************** Frame *************************************    
    public Station_Sol(){
        
    this.setResizable(false);
    this.setBounds(100,50,1100,600);
    this.setTitle("Station de Sol");
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
  //**********************Heure et Date****************************************
    DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String dat = dateformat.format(date);
    d = new Thread(new Heure());// Heure c'est une classe pour recupérer l'heure 
        d.start();
        TDate.setText(" " + dat);

//********************** Contener/ Jpanel *********************************** 
    contenu=this.getContentPane();
    
    contenu.add(Heure);
    contenu.add(Date);
    contenu.add(Vitesse);
    contenu.add(ValeurV);
    contenu.add(Altitude);
    contenu.add(ValeurAlt);
    contenu.add(CPosition);
    contenu.add(PX);
    contenu.add(PY);
    contenu.add(PZ);
    contenu.add(CDestination);
    contenu.add(DX);
    contenu.add(DY);
    contenu.add(DZ); 
    contenu.add(Distance);
    contenu.add(Reste);
    contenu.add(Drone);
    contenu.add(PI);
    contenu.add(Dest);
    contenu.add(Photo);
    
 //************************* Contener/JText ***************************************  
    contenu.setLayout(null); 
    contenu.add(THeure);
    contenu.add(TDate);
    contenu.add(TPX);
    contenu.add(TPY);
    contenu.add(TPZ);
    contenu.add(TDX);
    contenu.add(TDY);
    contenu.add(TDZ);
    contenu.add(TPI_DEST);
    contenu.add(TDistance);
    contenu.add(TReste);
//**************************** Contener/ Button *****************************
   contenu.add(Connecter);   
   contenu.add(Envoyer);
//*************************** Contener/ Slider ************************
   contenu.add(SVitesse);
   contenu.add(SAltitude);
//*************************** Contener/ BarProgress *********************
   contenu.add(bar);
//*************************************************************************

//********************* créer les bouttons les champs de texte *********
   Heure.setBounds(05,05,50,15);
   THeure.setBounds(55,05,65,20);
   THeure.setFont(new Font("Tahoma", 1, 12));
   THeure.setBackground(new Color(51, 255, 51));
   
   Date.setBounds(135,05,40,15); 
   TDate.setBounds(175,05,90,20);
   TDate.setFont(new Font("Tahoma", 1, 12));
   TDate.setBackground(new Color(51, 255, 51));
  //********************** Slider vitesse et Altitude ************************* 
   Vitesse.setBounds(05,60,60,15); 
   SVitesse.setBounds(70,60,500,45);
   SVitesse.setMaximum(300);
   SVitesse.setMinimum(0);
   SVitesse.setValue(0);
   SVitesse.setMajorTickSpacing(50);  
   SVitesse.setMinorTickSpacing(10);
   SVitesse.setPaintLabels(true);
   SVitesse.setPaintTicks(true);
   SVitesse.setSnapToTicks(true);
   SVitesse.addChangeListener(new ChangeListener(){
        @Override
        public void stateChanged(ChangeEvent e) {
            ValeurV.setText(Integer.toString(SVitesse.getValue()));
             V=SVitesse.getValue();
        }
    });
   ValeurV.setBounds(585,60,40,15); 
   ValeurV.setFont(new Font("Tahoma", 1, 12));
   ValeurV.setBackground(new Color(51, 255, 51));
//*****************************************************************************   
   Altitude.setBounds(05,120,60,15); 
   SAltitude.setBounds(70,120,500,45);
   SAltitude.setMaximum(300);
   SAltitude.setMinimum(0);
   SAltitude.setValue(0);
   SAltitude.setMajorTickSpacing(50);  
   SAltitude.setMinorTickSpacing(10);
   SAltitude.setPaintLabels(true);
   SAltitude.setPaintTicks(true);
   SAltitude.setSnapToTicks(true);
   SAltitude.addChangeListener(new ChangeListener(){
        @Override
        public void stateChanged(ChangeEvent e) {
            ValeurAlt.setText(Integer.toString(SAltitude.getValue()));
             /******* Récuperer la valeur de l'altitude à chaque changement ********/
       HPZ=SAltitude.getValue()*1.0;
       TPZ.setText(""+ HPZ);
       Distance(dx, dy, dz, px, py, HPZ);
        //System.out.println(HPZ);
        }
    });
   ValeurAlt.setBounds(585,120,40,15); 
   ValeurAlt.setFont(new Font("Tahoma", 1, 12));
   ValeurAlt.setBackground(new Color(51, 255, 51));
//***************************************** position de drone *****************   
   CPosition.setBounds(05,180,250,15); 
   
   PX.setBounds(05,205,10,15); 
   TPX.setBounds(20,205,110,20);
   
   PY.setBounds(140,205,10,20);
   TPY.setBounds(155,205,110,20); 
   
   PZ.setBounds(275,205,10,20);   
   TPZ.setBounds(290,205,110,20);
//***************************************** Destination de drone *****************   
   CDestination.setBounds(05,245,250,15); 
   
   DX.setBounds(05,270,10,15); 
   TDX.setBounds(20,270,110,20);
   
   DY.setBounds(140,270,10,20);
   TDY.setBounds(155,270,110,20); 
   
   DZ.setBounds(275,270,10,20);   
   TDZ.setBounds(290,270,110,20);   
   
//************************** Cordonnées de position et de destination***********  
   
   TPI_DEST.setBounds(10,313,200,20); 
   Distance.setBounds(230,313,50,20); 
   TDistance.setBounds(290,313,190,20);
   Reste.setBounds(495,313,40,20); 
   TReste.setBounds(535,313,60,20);
 
//********************** PROGRESSBAR ******************************
    Drone.setBounds(10,370,50,15);
  
    bar.setBounds(65,370,1000,15);
    bar.setValue(0);
    bar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    bar.setForeground(new java.awt.Color(0, 255, 0));
    bar.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                
              int DI = D.intValue();
        bar.setMaximum(DI);
        bar.setMinimum(0);
        //Bar.setStringPainted(true);// afficher % de parcours
        DP =bar.getValue();
        int DD = D.intValue();
        TReste.setText(" "+ (DD-DP));
        //System.out.println(DP);
            }
        });
        
        PI.setBounds(70,400,90,15);
        Dest.setBounds(1020,400,60,15);
    
//********************** IMAGE DE DRONE ***************************************
        Photo.setBounds(635, 15, 350, 250);
        Photo.setIcon(new javax.swing.ImageIcon("C:\\Users\\BOUYA\\Documents\\NetBeansProjects\\rpas\\src\\rpas\\iconImages\\d5.jpg")); // NOI18N
        Photo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Photo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        
//*************************** Connecter au Drone *************************
        Connecter.setBounds(990,20,95,25);     
        Connecter.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt) {
        
        /************* genérer automatic les cordonnées de position******/
        px=Math.random()*1000;
        py=Math.random()*1000;
        pz=Math.random()*300;     
  /********** Envoyer la  valeur z initial au slider de l'Altitude **********/
        int z = pz.intValue();
        SAltitude.setValue(z);
 /***********************************************************************/
        TPX.setText(" "+px);
        TPY.setText(" "+py); 
        connect.Conn();   
        }             
    });
//******************************************************************************
        Envoyer.setBounds(990,50,95,25);     
        Envoyer.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt) {
            try{ 
            //****************** Afficher le boutton démarrer ***************
            contenu.add(Demarrer);
            
            //***************************************
              String corD=(VDX+","+VDY+","+VDZ);//*****
              connect.setTabD(corD);//**************
           //******************************************
            VDX = TDX.getText();
            VDY = TDY.getText();
            VDZ = TDZ.getText();
            //System.out.println("voila la valeur "+VDX);
                       
/*********************Teste pour les valeurs saisis  ***************/        
            int d = (int) Double.parseDouble(VDX);
            int dd = (int) Double.parseDouble(VDY);
            int ddd = (int) Double.parseDouble(VDZ);
                 
//********convert les cordonnées de destiantion string vers Double****/
            
            dx = Double.parseDouble(VDX);
            // System.out.println(dx);
            dy = Double.parseDouble(VDY);
            dz = Double.parseDouble(VDZ);
            //String corD=(VDX+","+VDY+","+VDZ);
            //connect.setTabDest(corD);
          
            TPI_DEST.setText("X: " + dx + " - " + px + " Y: " + dy + " - " + py + " Z: " + dz + " - " + HPZ);
           // Distance(dx, dy, dz, px, py, HPZ);
            
            } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Entrer les données correctement");
        }
        
    }
   });
            Demarrer.setBounds(990,80,95,25);     
            Demarrer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
            VDX1 = TDX.getText();
            VDY1 = TDY.getText();
            VDZ1 = TDZ.getText();
            if (VDX1.equals(VDX) && VDY1.equals(VDY) &&VDZ1.equals(VDZ)){
           
                t = new Thread(new Station_Sol.PDrone());
                t.start(); }
            
            else { 
            JOptionPane.showMessageDialog(rootPane, "Vérifier les entrées");
                    }
        }
   });
//********************** Fin de Déclaration ************************************
    }
//******************************* getter et setter *****************************    
    /*public String getTabPosi(){
        return tabPosi;    
    }
    public void setTabPosi (String tab){
    this.tabPosi=tab;
    tabP=tab;
    System.out.println("setter affiche la table position :"+ tab);
    }*/
    /*********************** CALCULER LA DISTANCE ********************************/   
    public void Distance(Double dx, Double dy, Double dz, Double px, Double py, Double pz) {
        Double rx = dx - px;
        Double ry = dy - py;
        Double rz = dz - pz;
        Double dist = sqrt(Math.pow(rx, 2) + Math.pow(ry, 2) + Math.pow(rz, 2));
        D=dist;
        TDistance.setText(" " + dist);
    }
      
//******************************************************************
    class PDrone implements Runnable{   
    public void run(){
      Demarrer.setEnabled(false);   
      for( Double val = 0.0; val <= D ; val+= V*0.0001){
        int d = val.intValue();
        bar.setValue(d);
        try {
          t.sleep(10);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
      }
      Demarrer.setEnabled(true);
    } 
  }
    //************************************ HEURE ********************//   

    class Heure implements Runnable {
        public void run() {
            Boolean bool = true;
            while (bool) {
                Time heur;
                heur = new Time(0);
                heur.setTime(System.currentTimeMillis());
                THeure.setText(" " + heur);
                try {
                    d.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    }
    
    public static void main(String [] args){
        //Station_Sol Int= new Station_Sol();     
        
    }
}
