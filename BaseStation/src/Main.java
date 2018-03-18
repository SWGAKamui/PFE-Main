import DataCollect.Data;
import EditText.EditTextReceived;
import EditText.EditTextXOrder;
import EditText.EditTextYOrder;
import Gauge.AltitudeGauge;
import Gauge.SpeedGauge;
import Map.MapVisual;
import OtherVisual.LineHorizon;
import OtherVisual.Box;
import ScrollBar.AltitudeBar;
import ScrollBar.WindBar;
import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Main extends PApplet {
    static Thread client;
    private static volatile Data dataReceived = new Data();
    private static volatile Data dataorder = new Data();
    private AltitudeGauge altGauge = new AltitudeGauge(this);
    private LineHorizon lineHorizon = new LineHorizon(this);
    private SpeedGauge speedGauge = new SpeedGauge(this);
    private WindBar windBar = new WindBar(this);
    private AltitudeBar altitudeBar = new AltitudeBar(this);
    private EditTextXOrder editTextXOrder = new EditTextXOrder(this);
    private EditTextYOrder editTextYOrder = new EditTextYOrder(this);
    private EditTextReceived editTextReceived = new EditTextReceived(this);
    private Box box = new Box(this);
    private MapVisual map = new MapVisual(this);

    private int timer = 0;

    public static void main(String[] args) {
        PApplet.main("Main");
        client = new Thread(new Client(dataReceived, dataorder, "GET"));
        client.start();
    }

    public void settings() {
        size(displayWidth, displayHeight, P2D);
    }

    public void setup() {

        rectMode(CENTER);
        smooth();

        altGauge.setup(width / 3, 15);
        speedGauge.setup(width / 3, height / 3 + 50);

        windBar.setup(90, height - height / 3 - 30);
        altitudeBar.setup(90, height / 2 + height / 4 + 10);

        editTextXOrder.setup(width / 5 + width / 10 + 50, height - height / 3);
        editTextYOrder.setup(width / 5 + width / 6 + 50, height - height / 3);

        editTextReceived.setup(width / 15, height / 3 + 70);


        map.setup(width / 2 + 70, -180);

        box.setup(width / 6 + 30, height - height / 3,
                width / 6 + 10, 50,
                width - width / 10, 200);
    }

    public void draw() {
        update();

        background(0);
        scale((float) 0.5);
        lineHorizon.draw();
        scale((float) 1.35);
        fill(0);
        rect(0, height, displayWidth, displayHeight);
        altGauge.draw();
        speedGauge.draw();
        windBar.draw();
        altitudeBar.draw();
        editTextXOrder.draw();
        editTextYOrder.draw();
        editTextReceived.draw();

        map.draw();
        box.draw();
    }


    public void update() {
        dataorder.setCoord(new int[]{editTextXOrder.getX(), editTextYOrder.getY()});
        //dataorder.set(windBar.getPos());
        altGauge.setAlt(dataReceived.getAltitude() * 10 - 50);
        editTextReceived.setXY(dataReceived.getCoord());
        if (editTextXOrder.enter && editTextYOrder.enter) {
            editTextYOrder.enter = false;
            editTextXOrder.enter = false;
            client = new Thread(new Client(dataReceived, dataorder, "PUTCOORD"));
            client.start();
            client.interrupt();
        }
        if (dataorder.getAltitude() != altitudeBar.getPos() && altitudeBar.mouse) {
            altitudeBar.mouse = false;
            dataorder.setAltitude(altitudeBar.getPos());
            client = new Thread(new Client(dataReceived, dataorder, "PUTALT"));
            client.start();
            client.interrupt();
        }
        if (++timer > 50) { //demande d'information sur le drone
            timer = 0;
            client = new Thread(new Client(dataReceived, dataorder, "GET"));
            client.start();
        }
    }

    public void keyReleased() {
        editTextXOrder.keyReleased();
        editTextYOrder.keyReleased();
    }

    public void mouseReleased() {
        altitudeBar.mouseReleased();

    }
}