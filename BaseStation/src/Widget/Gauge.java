package Widget;

import processing.core.PApplet;

import java.awt.*;

import static processing.core.PApplet.map;

/**
 *
 * @author Guru
 * Licence: aucune
 * url : http://www.local-guru.net/processing/gauges/
 *
 * Modifié par Kinda AL CHAHID
 *
 * Code initialement écrit pour l'IDE Processing en pseudo JAVA
 *
 * Adapté pour du JAVA traditionnelle et pour fonctionner avec les autres widgets et avec le moins de valeur empirique possible
 */
public class Gauge {
    private PApplet parent;//Conserve l'executable qui l'appelle pour y mettre son affichage
    private int x;// position en x
    private int y;//position en y
    private int maxNumber;
    private int maxLine; //nombre de barre a afficher
    private int stepLine; //pas de de la barre
    private int stepNumber;
    private float value;
    private String name;
    private Color colorWhite = new Color(255, 255, 255); //Couleur blanche
    private Color colorRed = new Color(255, 0, 0); //Couleur rouge

    public Gauge(PApplet p) {
        parent = p;
    }

    public void setup(int x, int y, String name, int maxValue, int maxLine, int stepLine, int stepNumber) {
        this.x = x;
        this.y = y;
        maxNumber = maxValue;
        this.maxLine = maxLine;
        this.stepLine = stepLine;
        this.stepNumber = stepNumber;
        this.name = name;
    }

    public void draw() {
        parent.strokeWeight(2); //epaisseur des traits
        parent.pushMatrix();// sauvegarde les coordonnées de la jauge
        parent.translate(x, y); //décale la jauge
        drawGauge();
        drawArraw(map(value, 0, parent.width, 0, 1));
        parent.popMatrix();//restore les coordonnées de la jauge
    }

    private void drawGauge() {
        int spaceGaugeNumber = 80;
        int textSize = 20;

        parent.textSize(textSize);//taille du texte
        parent.stroke(colorWhite.getRGB());//remplissage en blanc

        for (int i = 0; i < maxLine + 1; i++) {
            float a = PApplet.radians(210 + i * 120 / maxLine); //orientation de la graduation

            float r1 = 200;
            float r2 = 190;

            r2 = i % stepLine == 0 ? 185 : r2; //taille et position des gradutions fortes
            r1 = i % stepLine == 0 ? 215 : r1; //taille et position des gradutions fortes
            parent.line(r1 * PApplet.cos(a), r1 * PApplet.sin(a), r2 * PApplet.cos(a), r2 * PApplet.sin(a));

            a = PApplet.radians(180 + i * 170 / maxLine);
            drawNumber(i, r2, a, spaceGaugeNumber);
        }
        parent.fill(colorWhite.getRGB()); //remplissage en blanc
        parent.text(name, -40, -100);//affichage du nom de la jauge
    }

    private void drawArraw(float newValue) {
        //Paramètre pour l'aiguille de la jauge
        parent.stroke(colorRed.getRGB());//remplissage en rouge
        float value = map(newValue, 0, 1, parent.width / 9f, parent.width / 5.6f);
        float b = PApplet.radians(value);
        parent.fill(colorRed.getRGB());
        int size = 10;
        int sizeLine = 200;
        parent.ellipse(0, 0, size, size);
        parent.line(-size * PApplet.cos(b), -size * PApplet.sin(b), sizeLine * PApplet.cos(b), sizeLine * PApplet.sin(b));
    }

    private void drawNumber(int i, float r2, float a, int spaceGaugeNumber) {
        if (i % stepLine == 0 || i == 0) {
            if (i * stepNumber >= maxNumber)
                parent.text(String.valueOf(maxNumber), r2 * PApplet.cos(a), r2 * PApplet.sin(a) - spaceGaugeNumber);
            else //affichage de la graduation (nombre)
                parent.text(String.valueOf(i * stepNumber), r2 * PApplet.cos(a), r2 * PApplet.sin(a) - spaceGaugeNumber);
            parent.fill(colorWhite.getRGB());//affichage de la graduation (traits)
        }
    }

    public void setValue(float value) {
        this.value = value;
    }
}
