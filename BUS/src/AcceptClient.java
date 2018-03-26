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
	private String login;
       
    //Constructeur
    public AcceptClient(Socket clientSocket, AcceptClient[] AC, String lg) {
        this.clientSocket = clientSocket;
        this.AClient = AC;
        this.login=lg;
    }
    
    //teste si les donn�es entr�es sont au format JSON
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
                System.out.println("message"+message);
                Date date = new Date();
            	SimpleDateFormat shortDateFormat = new SimpleDateFormat("'le' dd/MM/yyyy '-' hh:mm:ss:SS");
            	
                boolean b=isJSONDataValid(message);
                if (message!=null && b==true){
                	
                    IHM_BUS.setText2TRACE("["+shortDateFormat.format(date).toString()+"], "+login+" "+message); //Log action re�u
                    Log.Ecriture("["+shortDateFormat.format(date).toString()+"],"+" "+login+" "+message);

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
                            Date datePUT = new Date();
                            SimpleDateFormat shortDatePUT = new SimpleDateFormat("'le' dd/MM/yyyy '-' hh:mm:ss:SS");
                          //Log action de reponse envoy�
                        	IHM_BUS.setText2INFO_Insered("["+shortDatePUT.format(datePUT).toString()+"],"+login+" "+" {\"action\":\"PUT\",\"content\":"+content);
                            Log.Ecriture("["+shortDateFormat.format(date).toString()+"], "+login+" "+content);

                            sleep(500);
                        	
                        } else if (action.equals("GET")) {
                            JSONObject content1 = obj.getJSONObject("content");
                            String response = dbConnexion.Select(content1);
                            Date dateGET = new Date();
                         	SimpleDateFormat shortDateGET = new SimpleDateFormat("'le' dd/MM/yyyy '-' hh:mm:ss:SS");
                            //Log action de reponse envoy�
                        	IHM_BUS.setText2INFO_Send("["+shortDateGET.format(dateGET).toString()+"], "+login+" "+response);
                            Log.Ecriture("["+shortDateFormat.format(date).toString()+"],"+" "+login+" "+response);

                            sleep(500);
                        	out.println(response);
                            out.flush();
                        }

                }else{
                	 Date d = new Date();
                 	SimpleDateFormat shortDateF = new SimpleDateFormat("'le' dd/MM/yyyy '-' hh:mm:ss:SS");
                	//Log error s'il n'est pas au format JSON
                	IHM_BUS.setText2EROR("["+shortDateF.format(d).toString()+"], "+login+" "+message+": Les donn\u00E9es ne sont pas au format JSON !?");
                    Log.Ecriture("["+shortDateFormat.format(date).toString()+"],"+" "+login+" "+message+": Les donn\u00E9es ne sont pas au format JSON !?");

                    sleep(500);
                	out.println("Les donn\u00E9es ne sont pas au format JSON !?");
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
        } catch (InterruptedException e) {
			e.printStackTrace();
		}


    }
}