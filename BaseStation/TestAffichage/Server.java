import processing.data.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Server {
    static ParseJsonFormat jsonFormat = new ParseJsonFormat();
    static Data data = new Data();

    public static void main(String[] args) {
        JSONObject object = jsonFormat.getStringJson("BaseStation/TestAffichage/jsonData/putAtt.json");
        serverTest(object);
    }

    public static void jsonTest(JSONObject object) {
        jsonFormat.setData(object, data);
        JSONObject jsonObject = jsonFormat.getStringJson("BaseStation/TestAffichage/jsonData/putAlt.json");
        jsonFormat.setData(jsonObject, data);
        jsonObject = jsonFormat.getStringJson("BaseStation/TestAffichage/jsonData/putCoord.json");
        jsonFormat.setData(jsonObject, data);
        System.out.println("Test Alt             =======> " + (data.getAltitude() == 50 ? "Réussi" : "Raté"));
        System.out.println("Test XY              =======> " + (data.getCoord()[0] == 10 && data.getCoord()[1] == 42 ? "Réussi" : "Raté"));
        System.out.println("Test ypr  && SERVER    =======> " + (data.getYpr()[0] == 10 && data.getYpr()[1] == 42 && data.getYpr()[2] == 42 ? "Réussi" : "Raté"));
    }

    public static void serverTest(JSONObject jsonobject) {
        String data = jsonobject.toString();
        try {
            ServerSocket serverSocket = new ServerSocket(5555);

            String dataSend = data;
            String msgIn = "";
            while (true) {
                Socket socket = serverSocket.accept();

                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                while (!msgIn.equals("exit")) {
                    msgIn = dataIn.readUTF();
                    JSONObject newObject = JSONObject.parse(msgIn);
                    jsonTest(newObject);

                    dataOut.writeUTF(dataSend);
                    msgIn = dataIn.readUTF();
                }

                socket.close();
                System.exit(0);
            }
        } catch (Exception e) {

        }
    }

}
