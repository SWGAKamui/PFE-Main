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
    static ParseJsonFormat jsonFormat = new ParseJsonFormat();
    JSONObject dataJSON;
    Data dataReceived, dataOrder;


    public Client(Data dataReceived, Data dataorder, String order) {
        this.dataReceived = dataReceived;
        this.dataOrder = dataorder;
        if (order.equals("GET"))
            dataJSON = jsonFormat.getStringJson(getData);
        if (order.equals("PUTALT"))
            dataJSON = jsonFormat.getJson(dataOrder.getJsonDataAlt());
        if (order.equals("PUTCOORD"))
            dataJSON = jsonFormat.getJson(dataOrder.getJsonDataCoord());
    }

    public void run() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 5555);
            String msgIn = "";

            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            dataOut.writeUTF(dataJSON.toString());
            dataOut.flush();
            msgIn = dataIn.readUTF();
            jsonFormat.setData(jsonFormat.getJson(msgIn), dataReceived);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
