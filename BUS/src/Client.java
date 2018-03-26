import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *@authors ZAMOUCHE & ZEROUALI
 */


/*
  Client pour test
 */
public class Client implements Runnable {

    private static Socket clientSocket = null;
    private static PrintStream out = null;
    private static DataInputStream in = null;
    private static BufferedReader sc = null;
    private static boolean closed = false;
    /*****************************************************************************/
    private static JFrame Fentre=new JFrame();
    private static Container container=new Container();
    private static JTextArea text1=new JTextArea();
    private static JTextArea text2=new JTextArea();
    private static JScrollPane jspan1=new JScrollPane(text1);
    private static JScrollPane jspan2=new JScrollPane(text2);
    private static JButton button=new JButton("Envoyer");
    /******************************************************************************/
    public static void fenetre(){
        Fentre.setLocation(10,10);
        Fentre.setTitle("Client");
        Fentre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Fentre.setSize(600,400);
        container.add(jspan1);
        jspan1.setBounds(100,20,400,200);
        container.add(jspan2);
        jspan2.setBounds(100,250,400,40);

        container.add(button);
        button.setBounds(280,300,80,40);
        Fentre.getContentPane().add(container);
        Fentre.setVisible(true);
    }
    public static void main(String[] args) {

        fenetre();
        int portNumber = 2000;
        String host = "localhost";

        try {
            clientSocket = new Socket(host, portNumber);
            sc = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
            out.println("AP");out.flush();
            out.println("AP");out.flush();

        } catch (UnknownHostException e) {
            System.err.println("H�te "+host+" inconnue " );
        } catch (IOException e) {
            System.err.println("Impossible d'obtenir des E / S pour la connexion � l'h�te: "+ host);
        }


        if (clientSocket != null && out != null && in != null) {


        	   new Thread(new Client()).start();
               // while (!closed) {
               button.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       String text1="{\"action\":\"Ptent\": {\"type\": \"altitude\", \"value\": \"50\"}}";
                       out.println(text1.replaceAll("[\r\n]+", ""));
                       out.flush();

                   }

               });
        }
    }


    public void run() {

        String message;
        try {
            while ((message = in.readLine()) != null) {
                System.out.println(message);
                text1.append(message+"\n");
            }

        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}