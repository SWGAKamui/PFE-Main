package rpas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Connect {
    
        public String tab; 
        private String TabD=null;
        Socket clientSocket;
        BufferedReader in;
        PrintWriter out;
        Scanner sc = new Scanner(System.in);//pour lire à partir du clavier
        Station_Sol station= new Station_Sol();
      
        public void setTabD(String tabD ){
           this.TabD=tabD;
           //System.out.println("setter de connect affiche la table Destination :"+ tabD);
       } 
       public String getTabD(){
       return TabD;
       }
        
        
    public void Conn(){
        Socket clientSocket;
        BufferedReader in;
        PrintWriter out;
        Scanner sc = new Scanner(System.in);//pour lire à partir du clavier
      
            try {
           clientSocket = new Socket("127.0.0.1",5000);
           //flux pour envoyer
           out = new PrintWriter(clientSocket.getOutputStream());
           //flux pour recevoir
           in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           //****************************************************************************************
           
           

//System.out.println(" "+ dx+dy+dz);
           Thread envoyer = new Thread(new Runnable() {
                String msg;     
                @Override
                public void run() {
                     while(true){   
                         msg=getTabD(); 
                         System.out.println("getter de connect affiche la table Destination :"+ msg);
                         if (msg!=null){                            
                       // msg= sc.nextLine();  
                      
                        out.println(msg);
                        out.flush();
                        msg=null;
                        //setTabD(null);
                         }
                    }
                    }
            });
            envoyer.start();
            
           Thread recevoir = new Thread(new Runnable() {
                String msg;
               @Override
               public void run() {
                   try {
                       msg = in.readLine();
                       tab = msg;
                       System.out.println("le voila la table de position afficher par connect  "+ tab);

                       while(msg!=null){
                           //System.out.println("Serveur : "+msg);
                           msg = in.readLine();
                       }
                       System.out.println("Serveur déconecté");
                       out.close();
                       clientSocket.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           });
           recevoir.start();
       }
       catch (IOException e) {
           e.printStackTrace();
       }
    }
}
 //System.out.println("getter de connect affiche la table Destination :" getTabD());