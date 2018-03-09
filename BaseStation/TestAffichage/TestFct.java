import processing.core.PApplet;
import processing.core.PVector;

public class TestFct {

    private PApplet parent;
    private AltitudeGauge altitudeGauge;
    private LineHorizon lineHorizon;
    private SpeedGauge speedGauge;
    private BaseStation base;

    public TestFct(PApplet p) {
        parent = p;
    }

    public void testMain(AltitudeGauge altitudeGauge, LineHorizon lineHorizon, SpeedGauge speedGauge, BaseStation base) {
        this.altitudeGauge = altitudeGauge;
        this.lineHorizon = lineHorizon;
        this.speedGauge = speedGauge;
        this.base = base;

        parseYPRTest();
        parseAltTest();
        parseCoordTest();
    }

    public void draw() {
        PVector v1 = new PVector(parent.width / 2, parent.height / 2);
        PVector v2 = new PVector(parent.mouseX, parent.mouseY);

        altitudeGauge.setAlt(parent.mouseX);
        speedGauge.setSpeed(parent.mouseX);
        lineHorizon.setRoll(PVector.angleBetween(v1, v2));
        lineHorizon.setPitch(parent.mouseY - parent.height / 2);
    }

    private void parseYPRTest() {
        String ypr = "ypr:50,100,30";
        base.parseYPR(ypr, lineHorizon);
        Boolean test = (lineHorizon.getYaw() == 50) && (lineHorizon.getPitch() == 100) && (lineHorizon.getRoll() == 30);
        System.out.println("Test convertion ypr   =======>        " + (test ? "Réussi" : "Raté"));
    }

    private void parseAltTest() {
        String altString = "alt:5000";
        base.parseAlt(altString, altitudeGauge);
        Boolean test = (altitudeGauge.getAlt() == 5000);
        System.out.println("Test convertion alt   =======>        " + (test ? "Réussi" : "Raté"));
    }

    private void parseCoordTest() {
        String coord = "coord:50,100";
        base.parseCoord(coord, 50, 100);
        //Boolean test = (x == 50 && y == 100);
        //System.out.println("Test convertion x/y   =======>        " + (test ? "Réussi" : "Raté"));
        //TODO
    }
}
