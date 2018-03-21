import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    
    //teste si les données entrées sont au format JSON
    public static boolean isJSONDataValid(String data) {
        try {
            new JSONObject(data);
        } catch (JSONException e) {
            try {
                new JSONArray(data);
            } catch (JSONException e1) {
                return false;
            }
        }
        return true;
    }
    
    //Gestion des actions
    public void run() {

        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new PrintStream(clientSocket.getOutputStream());

            while(clientSocket!=null && out!=null && in!=null){
                JSONObject obj = null;
                String message = in.readLine();
                Date date = new Date();
            	SimpleDateFormat shortDateFormat = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss:SS");
            	
                boolean b=isJSONDataValid(message);
                if (message!=null && isJSONDataValid(message)==true){
                	
                    	Server.settext2("["+shortDateFormat.format(date).toString()+"], "+message); //Log action reçu
                    	
                    	long startTime = System.nanoTime();
                    	
                    	obj = new JSONObject(message);

                    	long endTime = System.nanoTime();
                    	long duration = (endTime - startTime);
                        System.out.println(duration);

                        String action = obj.getString("action");
                        System.out.println("action" + action);
                        if (action.equals("PUT")) {
                            JSONObject content = obj.getJSONObject("content");
                            System.out.println("content" + content);
                            dbConnexion.insert(content);
                          //Log action de reponse envoyé
                        	Server.text2INFO_Insered("["+shortDateFormat.format(date).toString()+"], {\"action\":\"PUT\",\"content\":"+content);
                        	
                        } else if (action.equals("GET")) {
                            JSONObject content1 = obj.getJSONObject("content");
                            String response = dbConnexion.Select(content1);
                            System.out.println("****** response   " + response + "********");
                            
                            //Log action de reponse envoyé
                        	Server.text2INFO_Send("["+shortDateFormat.format(date).toString()+"], "+response);
                            out.println(response);
                            out.flush();
                        }

                }else{
                	
                	//Log error s'il n'est pas au format JSON
                	Server.text2EROR("["+shortDateFormat.format(date).toString()+"], "+message+": Les données ne sont pas au format JSON !?");
                	
                	out.println("Les données ne sont pas au format JSON !?");
                    out.flush();
                }

            }
            clientSocket.close();
            out.close();
            in.close();

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