import DataCollect.Data;
import Widget.*;
import Widget.EditText.EditTextOrder;
import Widget.EditText.EditTextReceived;
import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class TestAffichage extends PApplet {
    static Thread client;
    private static Data dataReceived = new Data();
    private static Data dataOrder = new Data();
    private TestFct testFct = new TestFct(this);
    private Gauge altGauge = new Gauge(this);
    private LineHorizon lineHorizon = new LineHorizon(this);
    private Gauge speedGauge = new Gauge(this);
    private ScrollBarProcessing windBar = new ScrollBarProcessing(this);
    private ScrollBarProcessing altitudeBar = new ScrollBarProcessing(this);
    private EditTextOrder editTextXOrder = new EditTextOrder(this);
    private EditTextOrder editTextYOrder = new EditTextOrder(this);
    private EditTextReceived editTextReceived = new EditTextReceived(this);
    private Box boxOrder = new Box(this);
    private Box boxData = new Box(this);
    private MapVisual map = new MapVisual(this);

    public static void main(String[] args) {
        PApplet.main("TestAffichage");
        client = new Thread(new Client(dataReceived, dataOrder, "GET"));
        client.start();
    }

    public void settings() {
        size(displayWidth, displayHeight, P2D);
    }

    public void setup() {
        testFct.testMain(altGauge, lineHorizon, speedGauge);
        rectMode(CENTER);
        smooth();

        altGauge.setup(width / 3, 0, "Altitude", 170, 35, 5, 5);
        speedGauge.setup(width / 3, height / 3, "Speed", 100, 35, 5, 3);

        int stepBar = 125;
        int yposBar = height / 3 + height / 3 - stepBar / 5;

        windBar.setup(90, yposBar, "Wind", 7.25f, 6);
        altitudeBar.setup(90, yposBar + stepBar, "Altitude", 11.9f, 8);

        editTextXOrder.setup(width / 5 + width / 10, height - height / 3, "X");
        editTextYOrder.setup(width / 5 + width / 6, height - height / 3, "Y");

        editTextReceived.setup(width / 15, height / 3);


        map.setup(width / 2 + width / 15, -height / 4);
        int space = (int) (height / 3.85);
        boxData.setup(space, space / 10, width / 2 + width / 9, height / 2 + height / 5, "Data Box");
        boxOrder.setup(space, height - height / 3, width / 2 + width / 9, height / 4, "Order Box");

    }

    public void draw() {
        testFct.draw();
        background(0);
        scale((float) 0.5);
        lineHorizon.draw();
        scale((float) 1.35);
        fill(0);
        rect(0, height + height / 10, displayWidth, displayHeight);
        altGauge.draw();
        speedGauge.draw();
        windBar.draw();
        altitudeBar.draw();
        editTextXOrder.draw();
        editTextYOrder.draw();
        editTextReceived.draw();

        map.draw();
        boxData.draw();
        boxOrder.draw();

        int space = -width / 10;
        boxData.drawText(space, height / 3);
        space = width / 3;
        boxOrder.drawText(space, height / 2 + height / 4);
    }
}