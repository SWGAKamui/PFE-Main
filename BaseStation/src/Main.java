import processing.core.PApplet;

public class Main extends PApplet {
    private int width = 1600;
    private int height = 800;
    private AltitudeGauge altGauge = new AltitudeGauge(this);
    public BaseStation base = new BaseStation();
    private LineHorizon lineHorizon = new LineHorizon(this);
    private SpeedGauge speedGauge = new SpeedGauge(this);
    private WindBar windBar = new WindBar(this);
    private AltitudeBar altitudeBar = new AltitudeBar(this);
    private EditTextXOrder editTextXOrder = new EditTextXOrder(this);

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(width, height);
    }

    public void setup() {
        background(0);
        rectMode(CENTER);
        smooth();
    }

    public void draw() {
        lineHorizon.draw();
        altGauge.draw();
        speedGauge.draw();
        windBar.draw();
        altitudeBar.draw();
        editTextXOrder.draw();
    }
}