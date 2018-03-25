package Widget;

import processing.core.PApplet;

import java.awt.*;

/**
 * @author Processing
 * Le code original est une tutoriel:
 * url : https://processing.org/examples/scrollbar.html
 * Licence LGPL
 * Modifié et adapté par Kinda AL CHAHID
 */
public class ScrollBarProcessing {
    public Boolean mouse = false;
    private int swidth, sheight;   //largeur et longueur de la bar
    private float sposMin, sposMax; //position minimum et maximum du bouton
    private float x, y;// position
    private float spos, newspos;// position du bouton
    private int loose;// poids du mouvement
    private boolean over;// la souris est au dessus du bouton?
    private boolean locked;// la souris a cliqué?
    private PApplet parent;//executable où mettre l'affichage
    private int maxNumberLine; //nombre de graduation max
    private float step;//pas de l'affichage
    private String name;
    private Color colorWhite = new Color(255, 255, 255); //Couleur blanche
    private Color colorBlack = new Color(0, 0, 0);//Couleur noir
    private Color colorDarkGrey = new Color(127, 127, 127);//Couleur gris foncé
    private Color colorGrey = new Color(204, 204, 204);//Couleur gris clair

    private int min = 0;
    private int max;

    public ScrollBarProcessing(PApplet p) {
        parent = p;
        swidth = parent.width * 6;
        sheight = 16;
        sposMin = 10;
        sposMax = swidth;
    }

    public void setup(float x, float y, String name, float step, int maxNumberLine) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.step = step;
        this.maxNumberLine = maxNumberLine;
        loose = 2;
    }

    public void draw() {
        int textSize = 20;
        int spaceTextX = -250;
        int spaceTextY = 60;
        update();
        parent.stroke(colorWhite.getRGB());//bord blanc
        parent.textSize(textSize);//taille texte
        parent.fill(colorWhite.getRGB());//remplissage lanc
        parent.text(name, spaceTextX, y - spaceTextY);//nom du scrollbar
        textSize = 15;
        parent.textSize(textSize);//taille texte
        parent.fill(colorWhite.getRGB());//remplissage blanc
        for (int i = 1; i < maxNumberLine + 1; i++) {
            int value = (int) ((i - 1) * 10 + step * i + step) / 10;
            value *= 10;
            max = PApplet.max(value, max);
            parent.text(value, (sposMax / maxNumberLine) * i - swidth / 2 - 10 + x / 2, y - spaceTextY / 2);// affichage de la graduation numérique
            parent.line((sposMax / maxNumberLine) * i - swidth / 2 + x / 2, y, (sposMax / maxNumberLine) * i - swidth / 2 + x / 2, y - spaceTextY / 3);//affichage de la graduation des barres
        }
        display();//affichage du bouton et du scroll
    }


    public float constrain() {
        /**
         * reposition le bouton dans les limites des valeurs
         */
        if (parent.mouseX > sposMax - sposMin * 5)
            return sposMax - swidth / 2 - sposMin * 5;
        if (parent.mouseX < sposMin)
            return sposMin - swidth / 2;
        return parent.mouseX - swidth / 2;
    }

    public void display() {
        parent.noStroke();//pas de bord
        parent.fill(colorGrey.getRGB());//remplissage gris
        parent.rect(x, y, swidth, sheight);//affichage bar
        if (over || locked)//si la souris est au dessus du bouton, on l'affiche du bouton en noir
            parent.fill(colorBlack.getRGB());
        else
            parent.fill(colorDarkGrey.getRGB());//sinon affichage gris foncé
        parent.rect(spos + x, y, sheight, sheight);//affichage du bouton
    }

    private void update() {
        //La souris est au dessus du bouton?
        int spaceY = 30;
        over = parent.mouseX > x && parent.mouseX < x + swidth - swidth / 3 &&
                parent.mouseY > y && parent.mouseY < y + sheight + spaceY;

        if (parent.mousePressed && over)//Si l'utilisateur a cliqué au dessus du bouton
            locked = true;
        if (!parent.mousePressed)//Si il n'y a pas de click
            locked = false;
        if (locked) //Si click au dessus du bouton, on change la position du bouton
            newspos = constrain();
        if (PApplet.abs(newspos - spos) > 1)//calcul de la nouvelle position du bouton
            spos = spos + (newspos - spos) / loose;
    }

    public int getPos() {
        /**
         * Recalcul de la position et retourne de la données a envoyer au drone
         */
        int value;
        if (spos <= 0) //position central du bouton
            value = (int) ((spos / 289 * (max / 2.1)) + max / 1.854);
        else
            value = (int) ((spos / 249 * (max / 2.1)) + max / 1.854);
        return Math.max(Math.min(value, max), min);
    }

    public void mouseReleased() {
        mouse = true;
    }
}
