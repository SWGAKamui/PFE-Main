public class BaseStation {
    public void parseYPR(String ypr, LineHorizon lineHorizon) {
        String[] data = ypr.split(":");
        ypr = data[1];
        String[] yprInt = ypr.split(",");
        lineHorizon.setYaw(Float.parseFloat(yprInt[0]));
        lineHorizon.setPitch(Float.parseFloat(yprInt[1]));
        lineHorizon.setRoll(Float.parseFloat(yprInt[2]));
    }

    public void parseAlt(String altString, AltitudeGauge altitudeGauge) {
        String[] data = altString.split(":");
        altitudeGauge.setAlt(Float.parseFloat(data[1]));
    }

    public void parseCoord(String coord, float x, float y) {
        String[] data = coord.split(":");
        coord = data[1];
        String[] yprInt = coord.split(",");
        x = Float.parseFloat(yprInt[0]);
        y = Float.parseFloat(yprInt[1]);
    }
}