package DataCollect;

import processing.core.PApplet;
import processing.data.JSONObject;

import java.io.File;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class ParseJsonFormat {

    public JSONObject getStringJson(String filename) {
        File file = new File(filename);
        return PApplet.loadJSONObject(file);
    }

    public String detect(JSONObject object) {
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
        return object.getJSONObject("content").getInt("value");
    }

    public int[] getCoord(JSONObject object) {
        int[] tabCoord = new int[2];
        tabCoord[0] = object.getJSONObject("content").getJSONObject("value").getInt("x");
        tabCoord[1] = object.getJSONObject("content").getJSONObject("value").getInt("y");
        return tabCoord;
    }

    public int[] getAtt(JSONObject object) {
        int[] tabAtt = new int[3];
        tabAtt[0] = object.getJSONObject("content").getJSONObject("value").getInt("yaw");
        tabAtt[1] = object.getJSONObject("content").getJSONObject("value").getInt("pitch");
        tabAtt[2] = object.getJSONObject("content").getJSONObject("value").getInt("roll");
        return tabAtt;
    }

    public void setData(JSONObject object, Data data) {
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
