package Config;

import processing.core.PApplet;
import processing.data.JSONObject;

import java.io.File;

public class Config {
    /**
     * @author Kinda AL CHAHID
     * Classe permettant de récupérer les confguration de base:
     * Le lien vers le dossier de test des fichier JSON
     * L'ip du serveur
     * Le port du serveur
     */
    private final JSONObject object;

    public Config() {
        File file = new File("src/Config/Config.json");
        object = PApplet.loadJSONObject(file);
    }

    public int getPort() {
        return object.getInt("port");
    }

    public String getHost() {
        return object.getString("host");
    }

    public String getPath() {
        return object.getString("os");
    }
}
