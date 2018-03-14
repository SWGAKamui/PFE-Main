package Gauge;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class AltitudeGauge extends Gauge {
    private float alt;
    private PApplet parent;
    private int x;
    private int y;
    private int max = 30;
    private int step = 5;
    private double stepNumber = 5.67;


    public AltitudeGauge(PApplet p) {
        super(p);
        parent = p;
    }

    public void setup(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        super.draw(x, y);
        drawGauge(PApplet.map(alt, 0, parent.width, 0, 1), max, step, stepNumber, "Altitude"); //map permet de convertir une valeur vers une valeur comprise entre 0 et la taille de l'écran

        parent.popMatrix();
    }

    public void setAlt(float alt) {
        this.alt = alt;
    }
}