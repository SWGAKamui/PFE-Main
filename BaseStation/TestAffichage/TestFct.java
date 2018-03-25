import Widget.Gauge;
import Widget.LineHorizon;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Classe écrite par Kinda AL CHAHID
 * Permet de renvoyer les valeurs de la souris
 */
public class TestFct {

    private PApplet parent;
    private Gauge altitudeGauge;
    private LineHorizon lineHorizon;
    private Gauge speedGauge;

    public TestFct(PApplet p) {
        parent = p;
    }

    public void testMain(Gauge altitudeGauge, LineHorizon lineHorizon, Gauge speedGauge) {
        this.altitudeGauge = altitudeGauge;
        this.lineHorizon = lineHorizon;
        this.speedGauge = speedGauge;

    }

    public void draw() {
        PVector v1 = new PVector(parent.width / 2, parent.height / 2);
        PVector v2 = new PVector(parent.mouseX, parent.mouseY);

        altitudeGauge.setValue(parent.mouseX);
        speedGauge.setValue(parent.mouseX);
        lineHorizon.setRoll(PVector.angleBetween(v1, v2));
        lineHorizon.setPitch(parent.mouseY - parent.height / 2);
    }

}
