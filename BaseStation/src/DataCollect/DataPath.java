package DataCollect;

public interface DataPath {
    //Pour un travail sous windows: BaseStation/TestAffichage/jsonData
    //Linux sous windows: BaseStation/TestAffichage/jsonData
    String path = "BaseStation/TestAffichage/jsonData/";
    String getData = path + "get.json";
    String getAltJsonBegin = "{\n\"action\": \"PUT\",\n\"content\": {\n\"type\": \"altitude\",\n\"value\":";
    String getAltJsonEnd = " \n}\n}";

    String getCoordJsonBegin = "{\n\"action\": \"PUT\",\n\"content\": {\n\"type\": \"coordonnees\",\n\"value\": {\n\"x\":";
    String getCoordJsonY=",\n\"y\":";
    String getCoordJsonEnd = "\n}\n}\n}";

    }
