package Gauge;

import processing.core.PApplet;

/**
 * http://www.local-guru.net/processing/gauges/
 *
 * @author Kinda AL CHAHID
 */
abstract class Gauge {
    PApplet parent;//Conserve l'executable qui l'appelle pour y mettre son affichage

    public Gauge(PApplet p) {
        parent = p;
    }

    public void draw(int x, int y) {
        parent.strokeWeight(2); //epaisseur des traits
        parent.pushMatrix();// sauvegarde les coordonnées de la jauge
        parent.translate(x, y); //décale la jauge
    }

    public void drawGauge(float val, float max, int step, double stepNumber, String name) {
        parent.textSize(20);//taille du texte
        parent.stroke(255);//remplissage en blanc

        for (int i = 0; i < max + 1; i++) {
            float a = parent.radians(210 + i * 120 / max); //orientation de la graduation
            float r1 = 200;
            float r2 = 190;
            r2 = i % step == 0 ? 185 : r2; //taille et position des gradutions fortes
            r1 = i % step == 0 ? 215 : r1; //taille et position des gradutions fortes
            parent.line(r1 * parent.cos(a), r1 * parent.sin(a), r2 * parent.cos(a), r2 * parent.sin(a));
            a = parent.radians(180 + i * 170 / max);
            if (i % step == 0 || i == 0) {
                if(i * stepNumber >= 170 && name.equals("Altitude")) //cas particulier de la jauge altitude
                    parent.text(String.valueOf(170), r2 * parent.cos(a), r2 * parent.sin(a) - 80);
                else if(i * stepNumber >= 100 && name.equals("Speed")) //cas particulier de la jauge altitude
                    parent.text(String.valueOf(100), r2 * parent.cos(a), r2 * parent.sin(a) - 80);
                else //affichage de la graudation (nombre)
                    parent.text(String.valueOf((int)(i * stepNumber)), r2 * parent.cos(a), r2 * parent.sin(a) - 80);
                parent.fill(255, 255, 255);//affichage de la graudation (traits)
            }
        }
        parent.fill(255, 255, 255); //remplissage en blanc
        parent.text(name, -40, -100);//affichage du nom de la jauge

        //Paramètre pour l'aiguille de la jauge
        parent.stroke(255, 0, 0);//remplissage en rouge
        float b = parent.radians(213 + val * 111);
        parent.fill(255, 0, 0);
        parent.ellipse(0, 0, 10, 10);
        parent.line(-10 * parent.cos(b), -10 * parent.sin(b), 200 * parent.cos(b), 200 * parent.sin(b));

        parent.popMatrix();//restore les coordonnées de la jauge
    }
}
