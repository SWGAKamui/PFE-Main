import processing.core.PApplet;
/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Main extends PApplet {
    private AltitudeGauge altGauge = new AltitudeGauge(this);
    private LineHorizon lineHorizon = new LineHorizon(this);
    private SpeedGauge speedGauge = new SpeedGauge(this);
    private WindBar windBar = new WindBar(this);
    private AltitudeBar altitudeBar = new AltitudeBar(this);
    private EditTextXOrder editTextXOrder = new EditTextXOrder(this);
    private EditTextYOrder editTextYOrder = new EditTextYOrder(this);
    private EditTextXData editTextXData = new EditTextXData(this);
    private EditTextYData editTextYData = new EditTextYData(this);
    private Box box = new Box(this);

    private GoogleMap googleMap = new GoogleMap(this);


    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(1600, 800);
    }

    public void setup() {
        rectMode(CENTER);
        smooth();

        altGauge.setup(width / 3, 0);
        speedGauge.setup(width / 3, height / 3 + 50);

        windBar.setup(90, height - height / 3 - 30);
        altitudeBar.setup(90, height / 2 + height / 4 + 10);

        editTextXOrder.setup(width / 5 + width / 10, height - height / 3);
        editTextYOrder.setup(width / 5 + width / 6, height - height / 3);

        editTextXData.setup(width / 15 - 100, height / 3 + 70);
        editTextYData.setup(width / 15, height / 3 + 70);

        box.setup(width / 6 + 10, height - height / 3,
                width / 6 + 10, 50,
                width - width / 10, 200);

    }

    public void draw() {
        background(0);
        scale((float) 0.5);
        lineHorizon.draw();
        scale((float) 1.35);
        fill(0);
        rect(0, height, 1350, 900);
        altGauge.draw();
        speedGauge.draw();
        windBar.draw();
        altitudeBar.draw();
        editTextXOrder.draw();
        editTextYOrder.draw();
        editTextXData.draw();
        editTextYData.draw();
        box.draw();


        googleMap.draw();
    }

    public void keyReleased() {
        editTextXOrder.keyReleased();
        editTextYOrder.keyReleased();

        editTextXData.keyReleased();
        editTextYData.keyReleased();
    }
}