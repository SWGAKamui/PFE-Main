package Processing.pr;


import processing.core.PApplet;

public class Altitude extends Gauge {
    private static float alt;
    private PApplet parent;
    private int x;
    private int y;
    private int max = 30;
    private int step = 5;
    private double stepNumber = 5;

    public Altitude(PApplet p) {
        super(p);
        parent = p;
    }

    public void setup(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        super.draw(x, y);
        drawGauge(PApplet.map(80, 0, parent.width, 0, 1), max, step, stepNumber, "Altitude");
        parent.popMatrix();
    }

    public static void setAlt(float alte) {
        if (alte > 170)
            alt = 170;
        else if (alte < 0)
            alt = 0;
        alt = alte;

    }
}
