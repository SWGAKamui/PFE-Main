import Config.Config;
import DataCollect.Data;
import DataCollect.DataPath;
import DataCollect.ParseJsonFormat;
import processing.data.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Server implements DataPath {
    private static ParseJsonFormat jsonFormat = new ParseJsonFormat();
    private static Data data = new Data();
    private static int port;
    private static String path;

    public static void main(String[] args) {
        Config config = new Config();
        port = config.getPort();
        path = config.getPath();
        jsonTest();
        serverTest();
    }

    private static void jsonTest() {
        JSONObject jsonObject = jsonFormat.getStringJson(path + "putAlt.json");
        jsonFormat.setData(jsonObject, data);
        System.out.println("Test Alt             =======> " + (data.getAltitude() == 50 ? "Réussi" : "Raté"));
        jsonFormat.setData(jsonObject, data);
        jsonObject = jsonFormat.getStringJson(path + "putAtt.json");
        jsonFormat.setData(jsonObject, data);
        System.out.println("Test ypr             =======> " + (data.getYpr()[0] == 10 && data.getYpr()[1] == 42 && data.getYpr()[2] == 42 ? "Réussi" : "Raté"));
        jsonObject = jsonFormat.getStringJson(path + "putCoord.json");
        jsonFormat.setData(jsonObject, data);
        System.out.println("Test XY              =======> " + (data.getCoord()[0] == 10 && data.getCoord()[1] == 42 ? "Réussi" : "Raté"));
    }

    private static void serverTest() {
        JSONObject object = jsonFormat.getStringJson(path + "putAlt.json");
        String dataString = object.toString();
        try {
            while (true) {
                ServerSocket serverSocket = new ServerSocket(port);
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
