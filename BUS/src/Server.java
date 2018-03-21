import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
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
    private static JScrollPane jspan1=new JScrollPane(text1);
    private static JScrollPane jspan2=new JScrollPane(text2);
    private static JLabel label=new JLabel("Liste des composants connecté");
    private static JLabel label1=new JLabel("les information I/O");
    public static final Color VERY_DARK_GREEN = new Color(0,102,0);
    public static final Color VERY_DARK_BLUE = new Color(0,0,204);

    //Creation de IHM
    public static void Fentre(){
        Fentre.setLocation(10,10);
        Fentre.setTitle("BUS Debug Log");
        Fentre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Fentre.setSize(600,400);

        container.add(label);
        label.setBounds(5,5,200,20);
        container.add(label1);
        label1.setBounds(250,5,200,20);
        container.add(jspan1);
        jspan1.setBounds(5,30,150,100);
        container.add(jspan2);
        jspan2.setBounds(200,30,350,300);
        
        text1.setEditable(false);
        text2.setEditable(false);

        Fentre.getContentPane().add(container);
        Fentre.setVisible(true);

    }
    
    //append du text de Debug Log selon couleur 
    private static void appendJTextWithColor(JTextPane textPane, String msg, Color color)
    {
        StyleContext style = StyleContext.getDefaultStyleContext();
        AttributeSet attr = style.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        attr = style.addAttribute(attr, StyleConstants.FontFamily, "Lucida Console");
        attr = style.addAttribute(attr, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        
        int l = textPane.getDocument().getLength();
        textPane.setCaretPosition(l);
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