package ScrollBar;

import processing.core.PApplet;

/**
 *https://processing.org/examples/scrollbar.html
 *
 * @author Kinda AL CHAHID
 */
public class AltitudeBar extends ScrollBarProcessing {
    public Boolean mouse = false;
    private float xpos, ypos;// position
    private float spos, newspos;// position du bouton
    private int loose;// poids du mouvement
    private boolean over;// la souris est au dessus du bouton?
    private boolean locked;// la souris a cliqué?
    private PApplet parent;//executable où mettre l'affichage
    private int max = 6; //nombre de graduation max
    private int step = 20;//pas de l'affichage

    public AltitudeBar(PApplet p) {
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
        draw(this.xpos, this.ypos, this.spos, this.over, this.locked, "Altitude", step, max);
        update();
    }

    private void update() {
        //La souris est au dessus du bouton?
        over = parent.mouseX > xpos -50 && parent.mouseX < xpos + swidth - 230 &&
                parent.mouseY > ypos - 10 && parent.mouseY < ypos + sheight - 10;

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
        if (spos <= 0)//altitude min
            return (int) (90 + (spos / 269 * 90));
        if ((spos / 279 * 90) + 90 >= 170) //altitude max
            return 170;
        return (int) (spos / 279 * 90) + 90;
    }

    public void mouseReleased() {
        mouse = true;
    }
}