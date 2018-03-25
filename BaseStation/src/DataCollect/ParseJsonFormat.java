package DataCollect;

import processing.core.PApplet;
import processing.data.JSONObject;

import java.io.File;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class ParseJsonFormat {
    /**
     * @return donnée
     * Classe qui prend un fichier et/ou une variable JSON et la transforme en entier
     * @author Kinda AL CHAHID
     */

    public JSONObject getStringJson(String filename) {
        /**
         * Transforme une fichier JSON en variable JSONobject
         */
        File file = new File(filename);
        return PApplet.loadJSONObject(file);
    }

    public JSONObject getJson(String data) {
        /**
         * Transforme une string en variable JSON
         */
        return JSONObject.parse(data);
    }

    public String detect(JSONObject object) {
        /**
         * Permet de détecter quels types de données la varible JSON est
         *
         * GET || ALT || COORD || ATT
         */
        if (object.getString("action").equals("GET"))
            return "GET";
        if (object.getString("action").equals("PUT")) {
            if (object.getJSONObject("content").getString("type").equals("altitude"))
                return "ALT";
            if (object.getJSONObject("content").getString("type").equals("coordonnees"))
                return "COORD";
            if (object.getJSONObject("content").getString("type").equals("attitude"))
                return "ATT";
        }
        return "error";
    }

    public int getAlt(JSONObject object) {
        /**
         * Retourne la valeur de l'altitude en entier
         **/
        return object.getJSONObject("content").getInt("value");
    }

    public int[] getCoord(JSONObject object) {
        /**
         * Récupérer sous forme de tableau d'entier les coordonnées x|y
         */
        int[] tabCoord = new int[2];
        tabCoord[0] = object.getJSONObject("content").getJSONObject("value").getInt("x");
        tabCoord[1] = object.getJSONObject("content").getJSONObject("value").getInt("y");
        return tabCoord;
    }

    public int[] getAtt(JSONObject object) {
        /**
         * Récupérer sous forme de tableau d'entier les 3 valeurs de l'attitude du drone
         *
         * yaw | pitch | roll
         */
        int[] tabAtt = new int[3];
        tabAtt[0] = object.getJSONObject("content").getJSONObject("value").getInt("yaw");
        tabAtt[1] = object.getJSONObject("content").getJSONObject("value").getInt("pitch");
        tabAtt[2] = object.getJSONObject("content").getJSONObject("value").getInt("roll");
        return tabAtt;
    }

    public void setData(JSONObject object, Data data) {
        /**
         * Conserve dans une variable data, la donnée captée
         */
        if (detect(object).equals("ALT")) {
            data.setAltitude(getAlt(object));
        }
        if (detect(object).equals("COORD")) {
            data.setCoord(getCoord(object));
        }
        if (detect(object).equals("ATT"))
            data.setYpr(getAtt(object));
    }

}
