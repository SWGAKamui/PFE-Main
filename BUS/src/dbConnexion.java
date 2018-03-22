import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
/**
 *@authors ZAMOUCHE & ZEROUALI
 */

public class dbConnexion {
    static String url="jdbc:mysql://localhost/data";
    static String root="root";
    static String pw="";

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

    //Connexion à la base
    public static Connection connecte() throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url,root,pw);

        return connect;
    }

    //Insersion des donnees reçues
    public static void insert(JSONObject content) throws JSONException, SQLException, ClassNotFoundException {
        String type= content.getString("type");
        String value= content.getString("value");

        Connection conn= connecte();
        String sql="INSERT INTO Data (type, value)VALUES(?,?)";
        PreparedStatement pstate= conn.prepareStatement(sql);
        pstate.setString(1, type);
        pstate.setString(2, value);

        pstate.executeUpdate();
        pstate.close();
        conn.close();

    }

    //Selection des donnees demandees
    public static String Select(JSONObject obj) throws JSONException {
        JSONObject json = obj;
        String type=obj.getString("type");

        String sql="SELECT value FROM Data WHERE time in (SELECT MAX(time) FROM Data where type = "+"'"+type+"'"+") and type = "+"'"+type+"'"+"";

        Statement state =state();
        String selctInfo="{\"action\":\"INFO\",\"content\":{\"type\":"+"\""+type+"\", "+"\"value\":";
        try {
            ResultSet resultSet = state.executeQuery(sql);
            while (resultSet.next()) {
                selctInfo=selctInfo+"\""+resultSet.getString("value")+"\"}";
            }
            selctInfo=selctInfo+"}";
            System.out.println(" info "+selctInfo);
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
        return bool;
    }


    public static void delete(String sql) throws SQLException {
        Statement stat=state();
        stat.executeUpdate(sql);
    }
    /* public static void main(String[]args) throws SQLException {

        //Inserer un client
       Statement stat=state();
        String AP= "AP";
        String sql="INSERT INTO Authentification values (null,'AP',"+"'"+MD5.getMD5(AP)+"'"+")";
        System.out.println(sql);
        stat.execute(sql);

        if( verifie("test","test")==true){
            System.out.println("existe");
        }else{System.out.println("n'existe pas");}*/
    	/*
        String p="{\"type\":\"altitude\"}";
        try {
            JSONObject o=new JSONObject(p);
            Select(o);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }*/
}
