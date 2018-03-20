package ScrollBar;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class WindBar extends ScrollBarProcessing {
    private float xpos, ypos;// position
    private float spos, newspos;// position du bouton
    private int loose;// poids du mouvement
    private boolean over;// la souris est au dessus du bouton?
    private boolean locked;// la souris a cliqué?
    private PApplet parent;//executable où mettre l'affichage
    private int max = 10; //nombre de graduation max
    private int step = 0;//pas de l'affichage

    public WindBar(PApplet p) {
        super(p);
        parent = p;
        newspos = spos;
        loose = max;
    }

    public void setup(int x, int y) {
        xpos = x;
        ypos = y;
    }


    public void draw() {
        draw(this.xpos, this.ypos, this.spos, this.over, this.locked, "Wind", step, max);
        update();
    }


    public void update() {
        //La souris est au dessus du bouton?
        over = parent.mouseX > xpos -50&& parent.mouseX < xpos + swidth - 220 &&
                parent.mouseY > ypos + 30 && parent.mouseY < ypos + sheight + 30;
        if (parent.mousePressed && over)//Si l'utilisateur a cliqué au dessus du bouton
            locked = true;
        if (!parent.mousePressed)//Si il n'y a pas de click
            locked = false;
        if (locked)//Si click au dessus du bouton, on change la position du bouton
            newspos = constrain();
        if (PApplet.abs(newspos - spos) > 1)//calcul de la nouvelle position du bouton
            spos = spos + (newspos - spos) / loose;
    }


    public int getPos() {
        /**
         * Recalcul de la position et retourne de la données a envoyer au drone
         */
        if (spos <= 0) {//vitesse minimum
            return (int) (51 + (spos / 269 * 50));
        }
        return (int) (spos / 279 * 50) + 50;
    }
}