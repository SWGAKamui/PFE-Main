import processing.core.PApplet;
import processing.core.PVector;

public class TestFct {

    private PApplet parent;
    private AltitudeGauge altitudeGauge;
    private LineHorizon lineHorizon;
    private SpeedGauge speedGauge;

    public TestFct(PApplet p) {
        parent = p;
    }

    public void testMain(AltitudeGauge altitudeGauge, LineHorizon lineHorizon, SpeedGauge speedGauge) {
        this.altitudeGauge = altitudeGauge;
        this.lineHorizon = lineHorizon;
        this.speedGauge = speedGauge;

    }

    public void draw() {
        PVector v1 = new PVector(parent.width / 2, parent.height / 2);
        PVector v2 = new PVector(parent.mouseX, parent.mouseY);

        altitudeGauge.setAlt(parent.mouseX);
        speedGauge.setSpeed(parent.mouseX);
        lineHorizon.setRoll(PVector.angleBetween(v1, v2));
        lineHorizon.setPitch(parent.mouseY - parent.height / 2);
    }

}
