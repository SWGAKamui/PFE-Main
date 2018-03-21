package Widget.EditText;

import processing.core.PApplet;

import java.awt.*;

/**
 * @author Kinda AL CHAHID
 */
public class EditTextOrder {
    /**
     * Classe abstraite qui affiche des boites de textes récupérant la saisie clavier pour les coordonnées x/y
     */
    public Boolean enter = false; //Validation de la variable par la touche ENTRER
    private PApplet parent;//Conserve l'executable qui l'appelle pour y insérer son affichage
    private int xpos; //position x de la boite
    private int ypos;//position y de la boite
    private boolean over = false; //Conserve si la souris est sur la boite
    private boolean click = false; //Conserve si la souris a cliqué sur la boite
    private int value = 0; //valeur de la boite
    private String numberXString = ""; //version de la variable en string
    private int swidth; //largeur
    private int sheight; //longueur
    private Color colorWhite = new Color(255, 255, 255); //Couleur blanche
    private Color colorBlack = new Color(0, 0, 0);//Couleur noir
    private Color colorDarkGrey = new Color(127, 127, 127);//Couleur gris foncé
    private Color colorGrey = new Color(204, 204, 204);//Couleur gris clair
    private int sizeText = 20;
    private String name;

    public EditTextOrder(PApplet p) {
        parent = p;
        swidth = 55;
        sheight = 55;
    }

    public void setup(int x, int y, String name) {
        /**
         * Conserve les positions x/y de la boite
         */
        xpos = x;
        ypos = y;
        this.name = name;
    }

    public void draw() {
        update();
        display();
        int spaceInt = 8;
        int spaceTextBoxY = 50;
        int spaceTextBoxX = 10;
        parent.rect(xpos, ypos, swidth, sheight); //Affiche une boite carré de taille 55
        parent.textSize(sizeText); //défini la taille du texte
        parent.fill(colorWhite.getRGB()); // Le texte sera de couleur blanche
        parent.text(name, xpos - spaceTextBoxX, ypos - spaceTextBoxY); //Ecrit le nom de la boite au desssus de la boite (5 et 40 sont des valeurs empiriques)
        parent.fill(colorBlack.getRGB()); //L'écriture du text sera noire
        parent.text(value, xpos - (spaceInt * (int) (Math.log10(value) + 1) - spaceInt), ypos + spaceInt); //On décale pour chaque nouveau chiffre dans le nombre saisie
    }

    public void display() {
        if (parent.mousePressed && over) {
            //si la souris a cliqué sur la boite
            click = true;
            enter = false;
        }
        if (over || click)
            parent.fill(colorDarkGrey.getRGB()); //Si la souris passe au dessus de la boite ou qu'un click a été effectué dessus, la boite devient grise foncé
        else
            parent.fill(colorGrey.getRGB()); //Sinon conserve une teinte gris clair
    }

    public void keyReleased() {
        if (click && parent.key != parent.ENTER && Character.isDigit(parent.key)) {
            //on bloque tout ce qui n'est pas un chiffre rentrée au clavier
            numberXString += Integer.parseInt(String.valueOf(parent.key));
            value = Integer.parseInt(numberXString);
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

    public int getValue() {
        return value;
    }
}
