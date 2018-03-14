import Config.Config;
import DataCollect.Data;
import DataCollect.DataPath;
import DataCollect.ParseJsonFormat;
import processing.data.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Client implements Runnable, DataPath {
    private static ParseJsonFormat jsonFormat = new ParseJsonFormat();
    private static String host;
    private static int port;
    private static String path;
    private JSONObject dataJSON;
    private Data dataReceived;
    private String order;


    public Client(Data dataReceived, Data dataorder, String order) {
        Config config = new Config();
        host = config.getHost();
        port = config.getPort();
        path = config.getPath();
        this.dataReceived = dataReceived;
        Data dataOrder = dataorder;
        this.order = order;
        if (order.equals("GET"))
            dataJSON = jsonFormat.getStringJson(path + getData);
        if (order.equals("PUTALT"))
            dataJSON = jsonFormat.getJson(dataOrder.getJsonDataAlt());
        if (order.equals("PUTCOORD"))
            dataJSON = jsonFormat.getJson(dataOrder.getJsonDataCoord());
    }

    public void run() {
        Socket socket;
        try {
            socket = new Socket(host, port);
            String msgIn = "";

            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            dataOut.writeUTF(dataJSON.toString());
            dataOut.flush();
            msgIn = dataIn.readUTF();
            jsonFormat.setData(jsonFormat.getJson(msgIn), dataReceived);
            socket.close();
        } catch (IOException e) {
            System.out.println("Error : " + order);
            e.printStackTrace();
        }

    }

}
