package EditText;

import processing.core.PApplet;
/**
 * Classe écrite par Kinda AL CHAHID
 */
abstract class EditTextOrder {
    PApplet parent;
    public int swidth;
    public int sheight;

    public EditTextOrder(PApplet p) {
        parent = p;
        swidth = 55;
        sheight = 55;
    }
    public void draw(int xpos, int ypos, String name, int value) {
        parent.rect(xpos, ypos, 55, 55);
        parent.textSize(20);
        parent.fill(255, 255, 255);
        parent.text(name, xpos - 5, ypos - 40);
        parent.fill(0, 0, 0);
        parent.text(value, xpos - (8 * (int) (Math.log10(value) + 1) - 8), ypos + 8); //On décale pour chaque nouveau chiffre dans le nombre saisie
    }
    public void display(Boolean over, Boolean click) {
        if (over || click)
            parent.fill(127);
        else
            parent.fill(204);
    }
}
