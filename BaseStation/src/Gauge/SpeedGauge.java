package Gauge;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class SpeedGauge extends Gauge {
    private float speed;//Valeur de la jauge
    private PApplet parent;//Conserve l'executable qui l'appelle pour y mettre son affichage
    private int x;// position en x
    private int y;//position en y
    private int max = 35;//nombre de barre a afficher
    private int stepVisual = 5;//pas de de la barre
    private int stepNumber = 3;//pas des valeurs

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
        //map permet de convertir une valeur vers une valeur comprise entre 0 et la taille de l'écran
        drawGauge(parent.map(speed, 0, parent.width, 0, 1), max, stepVisual, stepNumber, "Speed");
    }


    public void setSpeed(float speed) {
        this.speed = speed;
    }
}