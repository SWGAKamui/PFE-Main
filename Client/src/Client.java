import java.net.Socket;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;

/**
 * Created by alexandrebrouste on 10/03/2018.
 */
public class Client {
    public static void main(String[] args) throws JSONException, IOException {
        BufferedReader in = new BufferedReader(new FileReader("/dev/cu.wchusbserial1410"));
        String line = in.readLine();
        Socket server = new Socket("127.0.0.1", 2000);
        while (true) {
            JSONObject object = new JSONObject();
            object.put("action", "PUT");
            JSONObject content = new JSONObject();
            String[] data = line.split(":");
            switch (data[0]) {
                case "alt":
                    content.put("type", "altitude");
                    content.put("value", data[1]);
                    break;
                case "alert":
                    content.put("type", "alert");
                    break;
                case "ypr":
                    content.put("type", "attitude");
                    JSONObject value = new JSONObject();
                    String[] ypr = data[1].split(",");
                    value.put("yaw", ypr[0]);
                    value.put("pitch", ypr[1]);
                    value.put("roll", ypr[2]);
                    content.put("value", value);
                    break;
                default:
                    break;
            }
            object.put("content", content);
            System.out.println(object.toString());
            try (OutputStreamWriter out = new OutputStreamWriter(server.getOutputStream(), StandardCharsets.UTF_8)) {
                out.write(object.toString());
            }
            line = in.readLine();
        }
    }
}
