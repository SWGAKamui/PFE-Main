import processing.data.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Client {
    static ParseJsonFormat jsonFormat = new ParseJsonFormat();
    //static Data data = new Data();

    public static void main(String[] args) {
        Socket socket = null;
        JSONObject jsonObject = jsonFormat.getStringJson("BaseStation/TestAffichage/jsonData/putAtt.json");
        String dataJSON = jsonObject.toString();

        try {
            socket = new Socket("127.0.0.1", 5555);
            String msgIn = "";
            String msgOut = "";

            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            dataOut.writeUTF(dataJSON);
//            msgIn = dataIn.readUTF();
  //          dataOut.writeUTF(msgIn);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
