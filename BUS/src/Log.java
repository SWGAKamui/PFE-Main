import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Ali-ZAMOUCHE on 21/03/2018.
 */
//Ecriture les logs dans un fichier txt
public class Log {
    private static File log=null;
    private static FileWriter fw=null;
    private static BufferedWriter bw=null;
    public static String path="/net/cremi/sbouyahmed/IdeaProjects/Bus_1/src/log.txt";

     public static void Ecriture(String chaine){
         log=new File(path);
         try {
             fw = new FileWriter(log, true);
             bw = new BufferedWriter(fw);
             bw.write(chaine+"\n");
             bw.close();
             fw.close();
         } catch (IOException e) {
             System.out.println(e);
         }
     }
     public static void main(String []args){
         for (int i=0;i<1000;i++){
             Ecriture(path+" "+i+"\n");
         }

     }

}
