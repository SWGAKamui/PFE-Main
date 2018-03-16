import Processing.TBord;
import org.json.JSONException;
import org.json.JSONObject;
import processing.core.PApplet;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static oracle.jrockit.jfr.events.Bits.floatValue;

public class Station_Sol extends JFrame implements Runnable{
//********************* Socket et flux ************************************************
    private static Socket clientSocket;
    private static BufferedReader in;
    static PrintWriter out;

//************************************* classe ****************************************
    static DistGps distance = new DistGps();
    private static Component rootPane;
    Thread t,d;
    private static Container contenu;
//******************** Variable *****************************
    private static String corD;
//**************************************** variables pour recevoir les valeurs envoyées par drone**************
    private static Double vitesse,y,p,r;
    private static Double px = 0.0, py = 0.0, pz = 0.0;
    static int V;
//**************************************** variables pour les envoyer au drone**************
    private static String VDX, VDY;
    private static Double dx = 0.0, dy = 0.0, dz = 0.0;
    private static int DP;// pour calculer le reste de la distance
//******************************************************* Variables de GUI ****************************
    private static JLabel Heure=new JLabel("Heure :");
    private static JLabel Date=new JLabel("Date :");
    private static JLabel Vitesse=new JLabel("Vitesse");
    private static JLabel ValeurV=new JLabel("0");
    private static JLabel Altitude=new JLabel("Altitude");
    private static JLabel ValeurAlt=new JLabel("0");
    private static JLabel CPosition=new JLabel("position initial de drone (X,Y) :");
    private static JLabel PX=new JLabel("X");
    private static JLabel PY=new JLabel("Y");
    private static JLabel CDestination =new JLabel("Destination (X,Y):");
    private static JLabel DX=new JLabel("X");
    private static JLabel DY=new JLabel("Y");
    private static JLabel Distance=new JLabel("Distance");
    private static JLabel Reste=new JLabel("Reste");
    private static JLabel Drone=new JLabel("Drone");
    private static JLabel PI=new JLabel("Position de Drone");
    private static JLabel Dest=new JLabel("Arrivée");
    private static JLabel Photo=new JLabel("");

    //************************************** JTextField *****************
    static JTextField THeure= new JTextField();
    static JTextField TDate= new JTextField();
    private static JTextField TPX= new JTextField();
    private static JTextField TPY= new JTextField();
    private static JTextField TDX= new JTextField();
    private static JTextField TDY= new JTextField();
    private static JTextField TPI_DEST= new JTextField();
    private static JTextField TDistance= new JTextField();
    private static JTextField TReste= new JTextField();

    //***************************** BOUTTON ***************************
    private static JButton Envoyer =new JButton("Envoyer");
    static JButton Demarrer=new JButton("Démarrer");
    private static JButton Pr=new JButton("Tableau de Bord");

    //************************* SLIDER ************************************
    private static JSlider SVitesse= new JSlider(JSlider.HORIZONTAL);
    private static JSlider SAltitude= new JSlider(JSlider.HORIZONTAL);

    //**************************** BarProgress ***************************
    static JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL);

    //**************************** Frame *************************************
    public Station_Sol(){
        this.setResizable(false);
        this.setBounds(100,50,1100,500);
        this.setTitle("Station de Sol");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//**********************Heure et Date****************************************
        d = new Thread(new Temps());// Heure c'est une classe pour recupérer l'heure
        d.start();

//********************** Contener/ Jpanel ***********************************
        contenu=this.getContentPane();
        contenu.setLayout(null);

        contenu.add(Heure);
        Heure.setBounds(05,05,50,15);

        contenu.add(Date);
        Date.setBounds(135,05,40,15);

        contenu.add(TDate);
        TDate.setBounds(175,05,90,20);
        TDate.setFont(new Font("Tahoma", 1, 12));
        TDate.setBackground(new Color(51, 255, 51));

        contenu.add(THeure);
        THeure.setBounds(55,05,65,20);
        THeure.setFont(new Font("Tahoma", 1, 12));
        THeure.setBackground(new Color(51, 255, 51));

        contenu.add(CPosition);
        CPosition.setBounds(05,180,250,15);

        contenu.add(PX);
        PX.setBounds(05,205,10,15);

        contenu.add(PY);
        PY.setBounds(140,205,10,20);

        contenu.add(TPX);
        TPX.setBounds(20,205,110,20);

        contenu.add(TPY);
        TPY.setBounds(155,205,110,20);

        contenu.add(CDestination);
        CDestination.setBounds(05,245,250,15);

        contenu.add(DX);
        DX.setBounds(05,270,10,15);

        contenu.add(DY);
        DY.setBounds(140,270,10,20);

        contenu.add(Distance);
        Distance.setBounds(230,313,50,20);

        contenu.add(Reste);
        Reste.setBounds(495,313,40,20);

        contenu.add(Drone);
        Drone.setBounds(10,370,50,15);

        contenu.add(TDX);
        TDX.setBounds(20,270,110,20);

        contenu.add(TDY);
        TDY.setBounds(155,270,110,20);

        contenu.add(TPI_DEST);
        TPI_DEST.setBounds(10,313,200,20);

        contenu.add(TDistance);
        TDistance.setBounds(290,313,190,20);

        contenu.add(TReste);
        TReste.setBounds(535,313,60,20);

        //********************** Slider vitesse et Altitude *************************
        contenu.add(Vitesse);
        Vitesse.setBounds(05,60,60,15);
        contenu.add(ValeurV);
        ValeurV.setBounds(585,60,40,15);
        ValeurV.setFont(new Font("Tahoma", 1, 12));
        ValeurV.setBackground(new Color(51, 255, 51));
        contenu.add(SVitesse);
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
            }
        });

//*****************************************************************************
        contenu.add(Altitude);
        contenu.add(ValeurAlt);
        ValeurAlt.setBounds(585,120,40,15);
        ValeurAlt.setFont(new Font("Tahoma", 1, 12));
        ValeurAlt.setBackground(new Color(51, 255, 51));
        Altitude.setBounds(05,120,60,15);
        contenu.add(SAltitude);
        SAltitude.setBounds(70,120,500,45);
        SAltitude.setMaximum(300);
        SAltitude.setMinimum(0);
        SAltitude.setValue(0);
        SAltitude.setMajorTickSpacing(50);
        SAltitude.setMinorTickSpacing(1);
        SAltitude.setPaintLabels(true);
        SAltitude.setPaintTicks(true);
        SAltitude.setSnapToTicks(true);

        SAltitude.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                ValeurAlt.setText(Integer.toString(SAltitude.getValue()));
            }
        });
//********************** PROGRESSBAR ******************************

        contenu.add(bar);
        bar.setBounds(65,370,1000,15);
        bar.setValue(0);
        bar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bar.setForeground(new java.awt.Color(0, 255, 0));
        bar.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {

                int DI = distance.D.intValue();
                bar.setMaximum(DI);
                bar.setMinimum(0);
                //Bar.setStringPainted(true);// afficher % de parcours
                DP =bar.getValue();
                int DD = distance.D.intValue();
                TReste.setText(" "+ (DD-DP));
            }
        });
        contenu.add(PI);
        PI.setBounds(70,400,90,15);
        contenu.add(Dest);
        Dest.setBounds(1020,400,60,15);
        contenu.add(Photo);
//********************** IMAGE DE DRONE ***************************************
        Photo.setBounds(635, 15, 350, 250);
        Photo.setIcon(new javax.swing.ImageIcon("C:\\Users\\BOUYA\\IdeaProjects\\PFE\\src\\iconImages\\d5.jpg")); // NOI18N
        Photo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Photo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
//********************************* Button Envoyer *********************************************
        contenu.add(Envoyer);
        Envoyer.setBounds(990,20,95,25);// son travail au niveau class TBord
 //******************************** Button Demmarrer ***********************************************
        contenu.add(Demarrer);
        Demarrer.setBounds(990,50,95,25);
        Demarrer.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    VDX = TDX.getText();
                    VDY = TDY.getText();
                    dx = Double.parseDouble(VDX);
                    dy = Double.parseDouble(VDY);
                    t = new Thread(new BarProgress());
                    t.start();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(rootPane, "Vérifier les cordonnées de la déstination ");
                }
            }
        });

        contenu.add(Pr);
        Pr.setBounds(990,200,95,25);
        Pr.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                new TBord();
                PApplet.main("Processing.TBord");

            }
        });
    }
//************************************************* Fin de construct *************
       public void run (){
        try {
            JSONObject obj;
            String message;
            while((message = in.readLine())!=null){
                System.out.println("poistion de Drone :"+message);
                try {
                    obj=new JSONObject(message);
                    px=obj.getDouble("X");
                    System.out.println(px);
                    TPX.setText(""+px);
                    py =obj.getDouble("Y");
                    TPY.setText(""+py);
                   //********************************************************** Altitude********
                    pz=obj.getDouble("Z");
                    //TPZ.setText(""+pz);
                    //System.out.println(pz);
                    float f = floatValue(pz);
                    Processing.pr.Altitude.setAlt(f);
                    //****************************************************************************************
                    int z = pz.intValue();//* convertir double à integer
                    SAltitude.setValue(z);
                    //************************************************************************ Vitesse *******
                    vitesse=obj.getDouble("V");
                    float v = floatValue(vitesse);
                    Processing.pr.Vitesse.setSpeed(v);
                    V = vitesse.intValue();//* convertir double à integer
                    SVitesse.setValue(V);
                    //****************************************************************** YawPitchRolls ***********
                    y=obj.getDouble("Yaw");
                    float yaw = floatValue(y);
                    Processing.pr.LineH.setYaw(yaw);

                    p=obj.getDouble("P");
                    float vp = floatValue(p);
                    Processing.pr.LineH.setPitch(vp);

                    r=obj.getDouble("R");
                    float vr = floatValue(r);
                    Processing.pr.LineH.setRoll(vr);
                    System.out.println(r);

                } catch (JSONException e) {
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Station_Sol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String [] args) {
        try {
            clientSocket = new Socket("127.0.0.1",5000);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException ex) {
            Logger.getLogger(Station_Sol.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread(new Station_Sol()).start();
        Envoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                   VDX = TDX.getText();
                   VDY = TDY.getText();
                   dx = Double.parseDouble(VDX);
                   dy = Double.parseDouble(VDY);

                   TPI_DEST.setText("X: " + dx + " - " + px + " Y: " + dy + " - " + py );
                   TDistance.setText(" " + distance.Distance(dx, dy, px, py));
                   String corD ="{" +"\"X\""+":"+"\""+dx+"\""+","+"\"Y\""+":"+"\""+dy+"\""+","+"\"Z\""+":"+"\""+dz+"\""+"}";
                   out.println(corD);
                   out.flush();
               }catch (Exception evt){
                JOptionPane.showMessageDialog(rootPane, "Vérifier bien les cordonnées de la déstination et de les envoyer");
            }
            }
        });
    }
}
