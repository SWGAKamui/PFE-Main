package Gauge;

import processing.core.PApplet;

/**
 * http://www.local-guru.net/processing/gauges/
 *
 * @author Kinda AL CHAHID
 */
public class AltitudeGauge extends Gauge {
    /**
     * Affichage d'une jauge pour l'altitude reçu par le drone
     * Valeur comprise entre 0 et 170 (dépendant des valeurs captés minimum et maximum par le drone
     */
    private float alt; //Valeur de la jauge
    private PApplet parent; //Conserve l'executable qui l'appelle pour y mettre son affichage
    private int x;// position en x
    private int y;//position en y
    private int max = 35; //nombre de barre a afficher
    private int step = 5; //pas de de la barre
    private int stepNumber = 5;//pas des valeurs


    public AltitudeGauge(PApplet p) {
        super(p);
        parent = p;
    }

    public void setup(int x, int y) {
        //Récupére la position de la jauge
        this.x = x;
        this.y = y;
    }

    public void draw() {
        super.draw(x, y);
        //map permet de convertir une valeur vers une valeur comprise entre 0 et la taille de l'écran
        drawGauge(PApplet.map(alt, 0, parent.width, 0, 1), max, step, stepNumber, "Altitude");
    }

    public void setAlt(float altitude) {
        if (altitude > 170) //correction de l'affichage, l'altitude ne peut être supérieur à 170
            this.alt = 170;
        else if (altitude < 0)//correction de l'affichage, l'altitude ne peut être inférieur à 0
            this.alt = 0;
        this.alt = altitude;
    }
}