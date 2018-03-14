import DataCollect.Data;
import DataCollect.ParseJsonFormat;
import processing.data.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Server {
    private static ParseJsonFormat jsonFormat = new ParseJsonFormat();
    private static Data data = new Data();

    public static void main(String[] args) {
        jsonTest();

        serverTest();
    }

    private static void jsonTest() {
        JSONObject jsonObject = jsonFormat.getStringJson("BaseStation/TestAffichage/jsonData/putAlt.json");
        jsonFormat.setData(jsonObject, data);
        System.out.println("Test Alt             =======> " + (data.getAltitude() == 50 ? "Réussi" : "Raté"));
        jsonFormat.setData(jsonObject, data);
        jsonObject = jsonFormat.getStringJson("BaseStation/TestAffichage/jsonData/putAtt.json");
        System.out.println("Test ypr             =======> " + (data.getYpr()[0] == 10 && data.getYpr()[1] == 42 && data.getYpr()[2] == 42 ? "Réussi" : "Raté"));
        jsonFormat.setData(jsonObject, data);
        jsonObject = jsonFormat.getStringJson("BaseStation/TestAffichage/jsonData/putCoord.json");
        jsonFormat.setData(jsonObject, data);
        System.out.println("Test XY              =======> " + (data.getCoord()[0] == 10 && data.getCoord()[1] == 42 ? "Réussi" : "Raté"));
    }

    private static void serverTest() {
        JSONObject object = jsonFormat.getStringJson("BaseStation/TestAffichage/jsonData/putAlt.json");
        String dataString = object.toString();
        try {
            while (true) {
                ServerSocket serverSocket = new ServerSocket(5555);
                String msgIn = "";
                Socket socket = serverSocket.accept();

                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                msgIn = dataIn.readUTF();
                String order = jsonFormat.detect(JSONObject.parse(msgIn));
                if (order.equals("GET")) {
                    dataOut.writeUTF(dataString);
                    dataOut.flush();
                } else {
                    dataOut.writeUTF(msgIn);
                    dataOut.flush();
                }
                serverSocket.close();
                socket.close();
            }
        } catch (Exception e) {

        }
    }

}
