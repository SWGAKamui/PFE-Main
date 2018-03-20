package ScrollBar;

import processing.core.PApplet;

/**
 *
 * https://processing.org/examples/scrollbar.html
 *
 * Modifié par Kinda AL CHAHID
 */
abstract class ScrollBarProcessing {
    public int swidth, sheight;   //largeur et longueur de la bar
    public float sposMin, sposMax; //position minimum et maximum du bouton
    public PApplet parent;//executable où mettre l'affichage

    public ScrollBarProcessing(PApplet p) {
        parent = p;
        swidth = parent.width * 6;
        sheight = 16;

        sposMin = 0;
        sposMax = swidth;
    }

    public void draw(float xpos, float ypos, float spos, Boolean over, Boolean locked, String name, int step, int max) {
        parent.stroke(255);//bord blanc
        parent.textSize(20);//taille texte
        parent.fill(255);//remplissage lanc
        parent.text(name, -(xpos + 130), ypos - 60);//nom du scrollbar
        parent.textSize(15);//taille texte
        for (int i = 1; i < max + 1; i++) {
            parent.fill(255);//remplissage blanc
            parent.text((i - 1) * 10 + ((i - 1) * step + step), (sposMax / max) * i - swidth / 2 - 10 + xpos / 2, ypos - 30);// affichage de la graduation numérique
            parent.line((sposMax / max) * i - swidth / 2 + xpos / 2, ypos - 20, (sposMax / max) * i - swidth / 2 + xpos / 2, ypos - 10);//affichage de la graduation des barres
        }
        display(xpos, ypos, spos, over, locked);//affichage du nouton
    }


    public float constrain() {
        /**
         * ignoré la souris si elle n'est pas au dessus de la bar
         */
        if (parent.mouseX > sposMax)
            return sposMax - swidth / 2 -20;
        if (parent.mouseX < sposMin + 20)
            return sposMin - swidth / 2 + 30;
        return parent.mouseX - swidth / 2;
    }

    public void display(float xpos, float ypos, float spos, Boolean over, Boolean locked) {
        parent.noStroke();//pas de bord
        parent.fill(204);//remplissage gris
        parent.rect(xpos, ypos, swidth, sheight);//affichage bar
        if (over || locked)//si la souris est au dessus du bouton, on l'affiche du bouton en noir
            parent.fill(0, 0, 0);
        else
            parent.fill(102, 102, 102);//sinon affichage gris foncé
        parent.rect(spos + xpos, ypos, sheight, sheight);//affichage du bouton
    }
}
