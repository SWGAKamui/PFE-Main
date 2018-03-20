package DataCollect;

/**
 * @author Kinda AL CHAHID
 */
public interface DataPath {
    /**
     * Interface permettant de stocker le format JSON des objets utilisé
     */

    String getData =  "get.json";
    String getAltJsonBegin = "{\n\"action\": \"PUT\",\n\"content\": {\n\"type\": \"altitude\",\n\"value\":";
    String getAltJsonEnd = " \n}\n}";

    String getCoordJsonBegin = "{\n\"action\": \"PUT\",\n\"content\": {\n\"type\": \"coordonnees\",\n\"value\": {\n\"x\":";
    String getCoordJsonY = ",\n\"y\":";
    String getCoordJsonEnd = "\n}\n}\n}";

}
