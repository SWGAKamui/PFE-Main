package Gauge;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class SpeedGauge extends Gauge {
    private float speed;
    private PApplet parent;
    private int x;
    private int y;
    private int max = 100;
    private int stepVisual = 10;
    private int stepNumber = 1;

    public SpeedGauge(PApplet p) {
        super(p);
        parent = p;
    }

    public void setup(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        super.draw(x, y);
        drawGauge(parent.map(speed, 0, parent.width, 0, 1), max, stepVisual, stepNumber, "Speed");//map permet de convertir une valeur vers une valeur comprise entre 0 et la taille de l'écran
        parent.popMatrix();
    }


    public void setSpeed(float speed) {
        this.speed = speed;
    }
}