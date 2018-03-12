import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *@authors ZAMOUCHE & ZEROUALI
 */

public class AcceptClient extends Thread {
    private DataInputStream in = null;
    private PrintStream out = null;
    private Socket clientSocket = null; //socket de client
    private final AcceptClient[] AClient; //liste des clients

    //Constructeur
    public AcceptClient(Socket clientSocket, AcceptClient[] AC) {
        this.clientSocket = clientSocket;
        this.AClient = AC;
    }

    //Gestion des actions
    public void run() {

        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new PrintStream(clientSocket.getOutputStream());

            while(clientSocket!=null&&out!=null&&in!=null){
                JSONObject obj = null;
                String message = in.readLine();

                if (message!=null){

                        obj = new JSONObject(message);
                        System.out.println(obj.getClass());

                        String action = obj.getString("action");
                        System.out.println("action" + action);
                        if (action.equals("set")) {
                            JSONObject content = obj.getJSONObject("content");
                            System.out.println("content" + content);
                            dbConnexion.insert(content);

                        } else if (action.equals("get")) {
                            JSONArray content1 = obj.getJSONArray("content");
                            String response = dbConnexion.Select(content1);
                            System.out.println("****** response   " + response + "********");
                            out.println(response);
                        }

                }else{
                    clientSocket.close();
                    clientSocket=null;
                    out.close();
                    in.close();
                }

            }

        } catch (IOException e) {
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}