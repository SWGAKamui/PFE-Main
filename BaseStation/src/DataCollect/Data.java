package DataCollect;

/**
 * @author Kinda AL CHAHID
 */
public class Data implements DataPath {
    /**
     * Classe qui stocke l'ensemble des valeurs reçu ou a envoyer
     */
    public int altitude;
    public int[] ypr = new int[3];
    public int[] coord = new int[2];

    public Data() {
        altitude = 0;
        ypr = new int[]{0, 0, 0};
        coord = new int[]{0, 0};
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int[] getCoord() {
        return coord;
    }

    public void setCoord(int[] coord) {
        this.coord = coord;
    }

    public int[] getYpr() {
        return ypr;
    }

    public void setYpr(int[] ypr) {
        this.ypr = ypr;
    }

    public String getJsonDataAlt() {
        return getAltJsonBegin + altitude + getAltJsonEnd;
    }
    public String getJsonDataCoord() {
        return getCoordJsonBegin + coord[0] + getCoordJsonY+coord[1]+getCoordJsonEnd;
    }
}
