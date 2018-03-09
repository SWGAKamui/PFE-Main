import processing.core.*;

public class SpeedGauge {
    private float speed;
    private PApplet parent;
    private int x;
    private int y;

    public SpeedGauge(PApplet p) {
        parent = p;
    }

    public void setup(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        parent.strokeWeight(2);
        parent.pushMatrix();
        parent.translate(x, y);
        drawGaugeSpeed(parent.map(speed, 0, parent.width, 0, 1), 100);
        parent.popMatrix();
    }

    public void drawGaugeSpeed(float val, float max) {
        parent.textSize(20);
        parent.stroke(255);


        for (int i = 0; i < max + 1; i++) {
            float a = parent.radians(210 + i * 120 / max); //orientation de la graduation
            float r1 = 200;
            float r2 = 190;
            r2 = i % 10 == 0 ? 185 : r2; //taille et position des gradutions fortes
            r1 = i % 10 == 0 ? 215 : r1; //taille et position des gradutions fortes
            parent.line(r1 * parent.cos(a), r1 * parent.sin(a), r2 * parent.cos(a), r2 * parent.sin(a));
            a = parent.radians(180 + i * 170 / max);
            if (i % 20 == 0 || i == 0) {
                parent.text(String.valueOf(i)+" %", r2 * parent.cos(a), r2 * parent.sin(a) - 80);
                parent.fill(255, 255, 255);
            }

        }
        parent.fill(255, 255, 255);
        parent.text("Speed", -40, -100);

        parent.stroke(255, 0, 0);
        float b = parent.radians(210 + val * 120);
        parent.fill(255, 0, 0);
        parent.ellipse(0, 0, 10, 10);
        parent.line(-10 * parent.cos(b), -10 * parent.sin(b), 200 * parent.cos(b), 200 * parent.sin(b));


    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}