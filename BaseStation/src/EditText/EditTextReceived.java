package EditText;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class EditTextReceived {
    private PApplet parent;
    private int xpos;
    private int ypos;
    private int swidth = 55;
    private int sheight = 55;

    private int x = 0;
    private int y = 0;

    public EditTextReceived(PApplet p) {
        parent = p;
    }

    public void setup(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public void draw() {
        int space = 100;
        parent.fill(255);
        //------------------------X-------------
        parent.rect(xpos, ypos, swidth, sheight);
        parent.textSize(20);
        parent.fill(255, 255, 255);
        parent.text("X", xpos - 5, ypos - 40);
        parent.fill(0, 0, 0);
        parent.text(x, xpos - (8 * (int) (Math.log10(x) + 1) - 8), ypos + 8); //On décale pour chaque nouveau chiffre dans le nombre saisie
        //------------------------Y-------------
        parent.fill(255);
        parent.rect(xpos + space, ypos, swidth, sheight);
        parent.textSize(20);
        parent.fill(255, 255, 255);
        parent.text("Y", xpos + space - 5, ypos - 40);
        parent.fill(0, 0, 0);
        parent.text(y, xpos + space - (8 * (int) (Math.log10(y) + 1) - 8), ypos + 8); //On décale pour chaque nouveau chiffre dans le nombre saisie
    }

    public void setXY(int[] coord) {
        this.x = coord[0];
        this.y = coord[1];
    }
}
