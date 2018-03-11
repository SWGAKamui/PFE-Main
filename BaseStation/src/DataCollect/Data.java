package DataCollect;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Data {
    public int altitude;
    public int[] ypr = new int[3];
    public int[] coord = new int[2];
    public Data(){
        altitude = 0;
        ypr = new int[]{0,0,0};
        coord = new int[]{0,0};
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

    public int[] getYpr() {
        return ypr;
    }

    public void setCoord(int[] coord) {
        this.coord = coord;
    }

    public void setYpr(int[] ypr) {
        this.ypr = ypr;
    }
}
