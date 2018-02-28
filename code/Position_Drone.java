import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Position_Drone {
    static Double px,py,pz;

   public static void main(String[] test) {
       /****************************************************************/
        px=Math.random()*1000;
        py=Math.random()*1000;
        pz=Math.random()*500;
        ArrayList tab=new ArrayList();
        tab.add(px);
        tab.add(py);
        tab.add(pz);
       /******************************************************************/
       final ServerSocket serveurSocket  ;
       final Socket clientSocket ;
       final BufferedReader in;
       final PrintWriter out;
       final Scanner sc=new Scanner(System.in);

       try {
           serveurSocket = new ServerSocket(5000);
           clientSocket = serveurSocket.accept();
           out = new PrintWriter(clientSocket.getOutputStream());
           in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
           
           Thread envoi= new Thread(new Runnable() {
               ArrayList msg=tab;
               @Override
               public void run() {
                   while(msg!=null){
                       //msg =tab;
                       //msg = sc.nextLine();
                       out.println(tab);
                       out.flush();
                       msg=null;
                       
                   }
               }
           });
           envoi.start();
           Thread recevoir= new Thread(new Runnable() {
               String msg ;
               @Override
               public void run() {
                   try {
                       msg = in.readLine();
                       //tant que le client est connecté
                       while(msg!=null){
                           System.out.println("Client N=° : "+msg);
                           msg = in.readLine();
                       }
                       //sortir de la boucle si le client a déconecté
                       System.out.println("Client déconecté");
                       //fermer le flux et la session socket
                       out.close();
                       clientSocket.close();
                       serveurSocket.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           });
           recevoir.start();
       }catch (IOException e) {
           e.printStackTrace();
       }
   }
}

