import javax.swing.*;
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
    private static JTextArea text1=new JTextArea();
     static JTextArea text2=new JTextArea();
    private static JScrollPane jspan1=new JScrollPane(text1);
    private static JScrollPane jspan2=new JScrollPane(text2);
    private static JLabel label=new JLabel("Liste des composants connecté");
    private static JLabel label1=new JLabel("les information I/O");

    //Creation de IHM
    public static void Fentre(){
        Fentre.setLocation(10,10);
        Fentre.setTitle("Débuggue");
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

        Fentre.getContentPane().add(container);
        Fentre.setVisible(true);

    }
    //Setters & getters pour text1 & text2
    public static void settext1(String s){  text1.append(s+"\n");  }
    public String gettext1(){ return text1.getText(); }

    public static void settext2(String s){  text2.append(s+"\n");  }
    public String gettext2(){ return text2.getText(); }

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