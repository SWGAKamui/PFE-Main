package DataCollect;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public interface DataPath {
    /**
     * Classe qui stocke les chemins vers les fichiers de tests JSON
     * Attention, selon l'OS il est nécessaire de changer certain chemin
     * Pour un travail sous windows: BaseStation/TestAffichage/jsonData
     * Linux sous windows: BaseStation/TestAffichage/jsonData
     */

    String getData =  "get.json";
    String getAltJsonBegin = "{\n\"action\": \"PUT\",\n\"content\": {\n\"type\": \"altitude\",\n\"value\":";
    String getAltJsonEnd = " \n}\n}";

    String getCoordJsonBegin = "{\n\"action\": \"PUT\",\n\"content\": {\n\"type\": \"coordonnees\",\n\"value\": {\n\"x\":";
    String getCoordJsonY = ",\n\"y\":";
    String getCoordJsonEnd = "\n}\n}\n}";

}
