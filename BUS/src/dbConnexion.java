import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
/**
 *@authors ZAMOUCHE & ZEROUALI
 */

public class dbConnexion {
    static String url="jdbc:mysql://dbserver/DataBase";
    static String root="root";
    static String pw="psw";

    static Connection connect= null;

    //Creation d'un statement pour l'execution des requettes
    public static Statement state(){

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection(url,root,pw);
            Statement stat =connect.createStatement();
            return stat;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Connexion a la base
    public static Connection connecte() throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url,root,pw);

        return connect;
    }

    //Insersion des donnees reçues
    public static void insert(JSONObject content) throws JSONException, SQLException, ClassNotFoundException {
        String altitude= content.getString("altitude");
        String attitude= content.getString("attitude");
        String coordonnee=  content.getString("coordonnee");
        String alert= content.getString("alert");

        Connection conn= connecte();
        String sql="INSERT INTO data (altitude, attitude, coordonnee, alert)VALUES(?,?,?,?)";
        PreparedStatement pstate= conn.prepareStatement(sql);
        pstate.setString(1, altitude);
        pstate.setString(2, attitude);
        pstate.setString(3, coordonnee);
        pstate.setString(4, alert);
        pstate.executeUpdate();
        pstate.close();
        conn.close();

    }

    //Selection des donnees demandées
    public static String Select (JSONArray jArray) throws JSONException {
        JSONArray jsonArray = jArray;
        String x = "";

        for (int i=0;i<jsonArray.length();i++){
            System.out.println(jsonArray.get(i)+"\t");
            if (jsonArray.length()==1){
                x= (String) jsonArray.get(0);
            }else if (i==jsonArray.length()-1){
                x=x+jsonArray.get(i);
                break;
            }else {
                x=x+jsonArray.get(i)+", ";
            }

        }
        String sql="SELECT "+x+" FROM data WHERE date = (SELECT MAX(date) FROM data)";

        Statement state =state();
        String selctInfo="{";
        try {
            ResultSet resultSet = state.executeQuery(sql);
            while (resultSet.next()) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    System.out.println("selectInfo" + selctInfo);
                    String attribut = (String) jsonArray.get(i);
                    if (i == jsonArray.length() - 1) {
                        selctInfo =selctInfo+ "\"" + (String) jsonArray.get(i) + "\"" + ":" + "\"" + resultSet.getString(attribut) + "\"";
                        break;
                    }
                    selctInfo =selctInfo+ "\"" + (String) jsonArray.get(i) + "\"" + ":" + "\"" + resultSet.getString(attribut) + "\"" + ",";
                }
            }
            selctInfo=selctInfo+"}";
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return selctInfo;

    }

    //Verification du login et mot de passe
    public static boolean verifie(String lg, String mp){
        String login=lg;
        String psw=MD5.getMD5(mp);
        String l="";
        String p="";
        boolean bool = false;
        Statement statement=state();
        String sql="SELECT login, psw FROM Authentification WHERE login=" +"'"+login+"'"+" and psw="+"'"+psw+"'" ;
        System.out.println(sql);
        try {
            ResultSet res=statement.executeQuery(sql);
            while (res.next()){
                 l=res.getString("login");
                 p= res.getString("psw");
                System.out.print("\t"+res.getString("login")+"\t");
                System.out.print("\t"+res.getString("psw")+"\n");
            }
            if(l.equals(login)&&p.equals(psw)){
                bool =true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("bool"+bool);
        return bool;
    }


    public static void delete(String sql) throws SQLException {
        Statement stat=state();
        stat.executeUpdate(sql);
    }
    /*public static void main(String[]args) throws SQLException {

        //Inserer un client
        Statement stat=state();
        String AP= "AP";
        String sql="INSERT INTO Authentification values (null,'AP',"+"'"+MD5.getMD5(AP)+"'"+")";
        System.out.println(sql);
        stat.execute(sql);

        if( verifie("test","test")==true){
            System.out.println("existe");
        }else{System.out.println("n'existe pas");}

    }*/
}
