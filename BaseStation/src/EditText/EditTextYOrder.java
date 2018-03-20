package EditText;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class EditTextYOrder extends EditTextOrder {
    public Boolean enter = false;//Validation de la variable par la touche ENTRER
    private PApplet parent;//Conserve l'executable qui l'appelle pour y insérer son affichage
    private int xpos;//position x de la boite
    private int ypos;//position y de la boite
    private boolean over = false; //Conserve si la souris est sur la boite
    private boolean click = false;//Conserve si la souris a cliqué sur la boite
    private int y = 0;//valeur de la boite: y
    private String numberXString = ""; //version de la variable en string


    public EditTextYOrder(PApplet p) {
        super(p);
        parent = p;
    }

    public void setup(int x, int y) {
        /**
         * Conserve les positions x/y de la boite
         */
        xpos = x;
        ypos = y;
    }

    public void draw() {
        update();
        super.display(click, over);
        if (parent.mousePressed && over) {
            //si la souris a cliqué sur la boite
            click = true;
            enter = false;
        }
        super.draw(xpos, ypos, "Y", y); //Affiche les données
    }

    public void keyReleased() {
        if (click && parent.key != parent.ENTER && Character.isDigit(parent.key)) {
            //on bloque tout ce qui n'est pas un chiffre rentrée au clavier
            numberXString += Integer.parseInt(String.valueOf(parent.key));
            y = Integer.parseInt(numberXString);
        } else if (parent.key == parent.ENTER) {
            //Si la touche ENTRER a été saisie, affiche et conserve la valeur écrite
            click = false;
            numberXString = "";
            enter = true;
        }
    }


    private void update() {
        //Detecte si la souris est sur la boite
        over = parent.mouseX > xpos && parent.mouseX < xpos + swidth &&
                parent.mouseY > ypos && parent.mouseY < ypos + sheight;
    }

    public int getY() {
        return y;
    }

}
