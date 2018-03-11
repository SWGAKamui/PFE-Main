import processing.core.*;

import static processing.core.PApplet.radians;
/**
 * Classe écrite par Kinda AL CHAHID
 */
public class AltitudeGauge {
    private float alt;
    private PApplet parent;
    private int x;
    private int y;

    public AltitudeGauge(PApplet p) {
        parent = p;
    }

    public void setup(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw() {
        parent.strokeWeight(2);
        parent.pushMatrix();
        parent.translate(x,y);
        drawGaugeAlt(PApplet.map(alt, 0, parent.width, 0, 1));
        parent.popMatrix();
    }

    public void drawGaugeAlt(float val) {
        parent.textSize(20);
        parent.stroke(255);
        for (int i = 0; i < 31; i++) {
            float a = radians(210 + i * 120 / 30);
            float r1 = 200;
            float r2 = 190;
            r2 = i % 5 == 0 ? 185 : r2;
            r1 = i % 5 == 0 ? 215 : r1;
            parent.line(r1 * PApplet.cos(a), r1 * PApplet.sin(a), r2 * PApplet.cos(a), r2 * PApplet.sin(a));
            a = radians(180 + i * 180 / 30);
            if (i % 5 == 0 || i % 10 == 0 || i == 0) {
                parent.text(String.valueOf(28 * (i / 5) + 2), r2 * PApplet.cos(a), r2 * PApplet.sin(a) - 80);
                parent.fill(255, 255, 255);
            }
        }
        parent.fill(255, 255, 255);
        parent.text("Altitude", -70, -100);
        parent.stroke(255, 0, 0);
        float b = radians(210 + val * 120);
        parent.fill(255, 0, 0);
        parent.ellipse(0, 0, 10, 10);
        parent.line(-10 * PApplet.cos(b), -10 * PApplet.sin(b), 200 * PApplet.cos(b), 200 * PApplet.sin(b));
    }

    public float getAlt() {
        return alt;
    }

    public void setAlt(float alt) {
        this.alt = alt;
    }
}