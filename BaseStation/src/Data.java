/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Data {
    public int altitude;
    public int[] ypr;
    public int[] coord;

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
