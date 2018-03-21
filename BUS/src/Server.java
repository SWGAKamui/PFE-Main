import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
/**
 *@authors ZAMOUCHE & ZEROUALI
 */

public class Server {

    //Declaration des variables
    private static ServerSocket serverSocket=null;
    private static Socket socket=null;
    private static DataInputStream in = null;
    private static PrintStream out = null;
    private static int maxConnect=10;
    private static AcceptClient [] AClient =new AcceptClient[maxConnect];

    //Declaration des variables pour IHM
    private static JFrame Fentre=new JFrame();
    private static Container container=new Container();
    private static JTextPane text1=new JTextPane();
    private static JTextPane text2=new JTextPane();
    private static JScrollPane jspan1=new JScrollPane();
    private static JScrollPane jspan2=new JScrollPane();
    public static final Color VERY_DARK_GREEN = new Color(0,102,0);
    public static final Color VERY_DARK_BLUE = new Color(0,0,204);
    private static final JPanel panel_1 = new JPanel();
    private static final JPanel panel_2 = new JPanel();
    private static Border loweredetched;
    private static JLabel lblNewLabel_2;
    private static JLabel lblApiBus;

    //Creation de IHM
    public static void Fentre(){
        Fentre.setLocation(10,10);
        Fentre.setTitle("API BUS");
        Fentre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Fentre.setSize(1004,466);
        Fentre.setResizable(false);

        Fentre.getContentPane().add(container);
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(loweredetched, "Composants connect\u00E9s", TitledBorder.LEFT, TitledBorder.TOP, null, null));
        panel.setBounds(23, 32, 216, 173);
        container.add(panel);
        panel.setLayout(null);
        jspan1.setBounds(10, 25, 196, 137);
        panel.add(jspan1);
        jspan1.setViewportView(text1);
        
        text1.setEditable(false);
        panel_1.setBorder(new TitledBorder(loweredetched, "Debug Log", TitledBorder.LEFT, TitledBorder.TOP, null, null));
        panel_1.setBounds(249, 32, 711, 342);
        
        container.add(panel_1);
        panel_1.setLayout(null);
        jspan2.setBounds(20, 23, 669, 300);
        panel_1.add(jspan2);
        jspan2.setViewportView(text2);
        text2.setEditable(false);
        panel_2.setBorder(new TitledBorder(loweredetched, "Clefs", TitledBorder.LEFT, TitledBorder.TOP, null, null));
        panel_2.setBounds(23, 227, 216, 145);
        
        container.add(panel_2);
        panel_2.setLayout(null);
        
        JLabel lblErreur = new JLabel("ERREUR");
        lblErreur.setForeground(Color.RED);
        lblErreur.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblErreur.setBounds(41, 49, 65, 14);
        panel_2.add(lblErreur);
        
        JLabel lblNewLabel = new JLabel("TRACE");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(41, 30, 46, 14);
        panel_2.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("SUCCÈS \"PUT\"");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setForeground(Color.BLUE);
        lblNewLabel_1.setBounds(41, 69, 86, 14);
        panel_2.add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("SUCCÈS \"GET\" \"INFO\"");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2.setForeground(new Color(34, 139, 34));
        lblNewLabel_2.setBounds(41, 87, 145, 14);
        panel_2.add(lblNewLabel_2);
        
        lblApiBus = new JLabel("API BUS\u2502V1.0\u2502\u00A9 2018 \u2502Imane ZEROUALI & Ali  ZAMOUCHE \u2502Univ Bordeaux.  ");
        lblApiBus.setBounds(526, 403, 575, 14);
        
        container.add(lblApiBus);
        Fentre.setVisible(true);

    }
    
    //append du text de Debug Log selon couleur 
    private static void appendJTextWithColor(JTextPane textPane, String msg, Color color)
    {
        StyleContext style = StyleContext.getDefaultStyleContext();
        AttributeSet attr = style.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        attr = style.addAttribute(attr, StyleConstants.FontFamily, "Lucida Console");
   
        textPane.setCharacterAttributes(attr, false);
        textPane.replaceSelection(msg);
    }
    
    //Setters & getters pour text1 & text2 selon couleur de Log
    public static void settext1(String s){  
        text1.setEditable(true);
        appendJTextWithColor(text1, s+"\n", Color.BLACK);
        text1.setEditable(false);
    	  }
    public String gettext1(){
    	return text1.getText();
    	}
    public static void settext2(String s){
        text2.setEditable(true);
    	appendJTextWithColor(text2, s+"\n", Color.BLACK); 
    	text2.setEditable(false);
    	}
    public String gettext2(){
    	return text2.getText(); 
    	}
    public static void text2EROR(String s){  
        text2.setEditable(true);
        appendJTextWithColor(text2, s+"\n", Color.RED);
        text2.setEditable(false);
    	 }
    public static void text2INFO_Send(String s){ 
        text2.setEditable(true);
        appendJTextWithColor(text2, s+"\n", VERY_DARK_GREEN);
        text2.setEditable(false);
    	 }
    public static void text2INFO_Insered(String s){
        text2.setEditable(true);
        appendJTextWithColor(text2, s+"\n", VERY_DARK_BLUE);
        text2.setEditable(false);
    }

    /*
    * creation de server socket qui ecoute sur un port
    * Acccepter des clients
    * */
    public static void server() throws IOException{
        String message = "";
        int port = 2000;
        serverSocket = new ServerSocket(port);
        Date date = new Date();
    	SimpleDateFormat shortDateFormat = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss:SS");
    	Server.settext2("["+shortDateFormat.format(date).toString()+"], Le server est en ecoute sur le port 2000");
        System.out.println("Le server est en ecoute sur le port 2000");

        while (true){
            socket =serverSocket.accept(); //
            in = new DataInputStream(socket.getInputStream());
            out = new PrintStream(socket.getOutputStream());
            //Authentification de client
            String login = "";
            String mdp;
            boolean connect=false;
            while (!connect) {
                out.println("Entrez votre login.");
                out.flush();
                login = in.readLine().trim();

                out.println("Entrez votre mot de passe");
                out.flush();
                mdp = in.readLine().trim();
                if (dbConnexion.verifie(login, mdp) == true) {
                    connect=true;
                }
            }

            Server.settext1(login + " est connecté");
            System.out.println("message " + login);

            //Creation d'un nouveau client
            for (int i = 0; i < AClient.length; i++) {
                if (AClient[i] == null) {
                    (AClient[i] = new AcceptClient(socket, AClient)).start();

                    break;
                }

            }

        }
    }

    public static void main(String args[]) throws IOException {

        Fentre();
        server();

    }
}