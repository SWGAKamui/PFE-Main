import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 5555);
            String msgIn = "";
            String msgOut = "";

            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            dataOut.writeUTF("put,10,15,150,200"); //type ordre, dst||pos, vitesse|| vent, altitude
            msgIn = dataIn.readUTF();
            dataOut.writeUTF(msgIn);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
