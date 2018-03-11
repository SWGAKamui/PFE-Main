package Gauge;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
abstract class Gauge {
    PApplet parent;

    public Gauge(PApplet p) {
        parent = p;
    }

    public void draw(int x, int y) {
        parent.strokeWeight(2);
        parent.pushMatrix();
        parent.translate(x, y);
    }

    public void drawGauge(float val, float max, int step, double stepNumber, String name) {
        parent.textSize(20);
        parent.stroke(255);

        for (int i = 0; i < max + 1; i++) {
            float a = parent.radians(210 + i * 120 / max); //orientation de la graduation
            float r1 = 200;
            float r2 = 190;
            r2 = i % step == 0 ? 185 : r2; //taille et position des gradutions fortes
            r1 = i % step == 0 ? 215 : r1; //taille et position des gradutions fortes
            parent.line(r1 * parent.cos(a), r1 * parent.sin(a), r2 * parent.cos(a), r2 * parent.sin(a));
            a = parent.radians(180 + i * 170 / max);
            if (i % step == 0 || i == 0) {
                parent.text(String.valueOf((int) (i * stepNumber)), r2 * parent.cos(a), r2 * parent.sin(a) - 80);
                parent.fill(255, 255, 255);
            }
        }
        parent.fill(255, 255, 255);
        parent.text(name, -40, -100);

        parent.stroke(255, 0, 0);
        float b = parent.radians(210 + val * 120);
        parent.fill(255, 0, 0);
        parent.ellipse(0, 0, 10, 10);
        parent.line(-10 * parent.cos(b), -10 * parent.sin(b), 200 * parent.cos(b), 200 * parent.sin(b));
    }
}
