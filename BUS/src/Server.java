import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *@authors ZAMOUCHE & ZEROUALI
 */
/*
 * creation de server socket qui ecoute sur un port
 * Acccepter des clients
 * */
public class Server {

    //Declaration des variables
    private static ServerSocket serverSocket=null;
    private static Socket socket=null;
    private static DataInputStream in = null;
    private static PrintStream out = null;
    private static int maxConnect=10;
    private static AcceptClient [] AClient =new AcceptClient[maxConnect];

    public static void server() throws IOException{
        String message = "";
        int port = 2000;
        serverSocket = new ServerSocket(port);
        Date date = new Date();
    	SimpleDateFormat shortDateFormat = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss:SS");
    	API.settext2("["+shortDateFormat.format(date).toString()+"], Le server est en ecoute sur le port 2000");
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

            API.settext1(login + " est connecté");
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

        API.Fentre();
        server();

    }
}