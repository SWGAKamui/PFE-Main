
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class Drone {
    static Double vitesse,y,p,r;
    static Double px,py,pz;
    private static Double dx = 0.0, dy = 0.0, dz = 0.0;
    static String vpx,vpy,vpz;
    public static String position;
    static Container contenu =new Container();

    public static JFrame f =new JFrame();
    private static JButton envoyer =new JButton("Envoyer");
    private static JLabel pi=new JLabel("Position initial");
    private static JTextField tpx = new JTextField();
    private static JTextField tpy = new JTextField();
    private static JTextField tpz = new JTextField();

    private static JLabel dest=new JLabel("Déstination de drone");
    private static JTextField tdx = new JTextField();
    private static JTextField tdy = new JTextField();
    private static JTextField tdz = new JTextField();
    //***********************************************
    public static void Fenetre(){
//******************* GUI ********************************
    f.setResizable(false);
    f.setBounds(100,50,500,300);
    f.setTitle("Cordonnées de Drone");
    f.setDefaultCloseOperation(EXIT_ON_CLOSE);
//*************** Position ************************
    contenu.add(pi);
        pi.setBounds(10,20,100,25);
    contenu.add(tpx);
        tpx.setBounds(10,55,40,25);
    contenu.add(tpy);
        tpy.setBounds(60, 55,40,25);
    contenu.add(tpz);
        tpz.setBounds(110,55,40,25);
    contenu.add(envoyer);
        envoyer.setBounds(10,100,90,25);
        contenu.add(pi);
//************************Destination **************************
    contenu.add(dest);
        dest.setBounds(10,160,120,25);
    contenu.add(tdx);
        tdx.setBounds(10,195,40,25);
    contenu.add(tdy);
        tdy.setBounds(60, 195,40,25);
    contenu.add(tdz);
        tdz.setBounds(110,195,40,25);

        f.getContentPane().add(contenu);
        f.setVisible(true);

}
//************************************************
    public static void main(String[] test) {
        Fenetre();
        /****************************************************************/
        vitesse =  (Math.random()*200);
        y=  (Math.random()*100);
        p=  (Math.random()*100);
        r=  (Math.random()*100);
       //String position="{" +"\"X\""+":"+"\""+px+"\""+","+"\"Y\""+":"+"\""+py+"\""+","+"\"Z\""+":"+"\""+pz+"\""+"}";
        //************************************



        /******************************************************************/
        final ServerSocket serveurSocket  ;
        final Socket clientSocket ;
        final BufferedReader in;
        final PrintWriter out;
        //final Scanner sc=new Scanner(System.in);

        try {
            serveurSocket = new ServerSocket(5000);
            System.out.println("le serveur est connecté");
            clientSocket = serveurSocket.accept();
            System.out.println("le client est connecté");
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));

            Thread envoi= new Thread(new Runnable() {

                String  msg;
                @Override
                public void run() {
                    //************************************
                    envoyer.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            vpx = tpx.getText();
                            vpy = tpy.getText();
                            vpz = tpz.getText();
                            //********convert les cordonnées de destiantion string vers Double****/
                            px = Double.parseDouble(vpx);
                            py = Double.parseDouble(vpy);
                            pz = Double.parseDouble(vpz);
                            position="{" +"\"X\""+":"+"\""+px+"\""+","+"\"Y\""+":"+"\""+py+"\""+","+"\"Z\""+":"+"\""+pz+"\""+

                                         ","+"\"V\""+":"+"\""+vitesse+"\""+","+"\"Yaw\""+":"+"\""+y+"\""+","+"\"P\""+":"+"\""+p+"\""+

                                         ","+"\"R\""+":"+"\""+r+"\""+"}";
                            msg=position;
                            while(msg!=null){
                                //msg = sc.nextLine();
                                out.println(msg);
                                out.flush();
                                msg=null;
                            }
                        }
                    });
                }
            });
            envoi.start();
            //***********************************************************************
            Thread recevoir= new Thread(new Runnable() {
                String msg ;
                @Override
                public void run() {
                    try {
                        //*************************************************************
                        try {
                            JSONObject obj;
                            String message;
                            while((message = in.readLine())!=null){
                                System.out.println("poistion de Drone :"+message);
                                try {
                                    obj=new JSONObject(message);
                                    dx=obj.getDouble("X");
                                    tdx.setText(""+dx);
                                    dy =obj.getDouble("Y");
                                    tdy.setText(""+dy);
                                    dz=obj.getDouble("Z");
                                    tdz.setText(""+dz);
                        //***************************************************************
                                } catch (JSONException e) {
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Station_Sol.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        //***************************************************************

                        //sortir de la boucle si le client a déconecté
                        System.out.println("Client déconecté");
                        //fermer le flux et la session socket
                        out.close();
                        clientSocket.close();
                        serveurSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            recevoir.start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/*
*  msg = in.readLine();
                        //tant que le client est connecté
                        while(msg!=null){
                            System.out.println("la table de déstination est arrivée au Drone : "+msg);
                            msg = in.readLine();
                        }*/