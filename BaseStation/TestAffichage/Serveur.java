import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);

            String dataSend = "data,10,15,150,200"; //type ordre, dst||pos, vitesse|| vent, altitude
            String msgIn = "";
            while (true) {
                Socket socket = serverSocket.accept();

                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

                while (!msgIn.equals("data,10,15,150,200")) {
                    msgIn = dataIn.readUTF();
                    System.out.println("Message reçu   =======>        " + (msgIn.equals("put,10,15,150,200") ? "Réussi" : "Raté") + " " + msgIn);
                    dataOut.writeUTF(dataSend);
                    msgIn = dataIn.readUTF();
                }
                System.out.println("Message reçu   =======>        " + (msgIn.equals("data,10,15,150,200") ? "Réussi" : "Raté") + " " + msgIn);

                socket.close();
                System.exit(0);
            }
        } catch (Exception e) {

        }
    }
}
