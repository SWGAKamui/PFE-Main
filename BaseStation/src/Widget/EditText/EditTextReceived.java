package Widget.EditText;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class EditTextReceived {
    private PApplet parent;
    private int xpos;
    private int ypos;
    private int swidth = 55;//largeur
    private int sheight = 55;//longueur
    private int colorWhite = 255;
    private int colorBlack = 0;

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
        int space = 100; //écart entre les deux boites x et y
        int spaceTextBox = 40;
        int spaceInt = 8;
        parent.fill(colorWhite); // la boite prend la teinte blanche
        //------------------------X-------------
        parent.rect(xpos, ypos, swidth, sheight); //création de la boite carré de 55 de coté
        parent.textSize(20);//Taille du texte
        parent.fill(colorWhite); //Couleur du nom de la boite: blanc
        parent.text("X", xpos - 5, ypos - spaceTextBox);//Ecriture du nom de la boite au dessus de celle-ci
        parent.fill(colorBlack);//couleur noir de la variable récupérer
        parent.text(x, xpos - (spaceInt * (int) (Math.log10(x) + 1) - spaceInt), ypos + spaceInt); //On décale pour chaque nouveau chiffre dans le nombre saisie
        //------------------------Y-------------
        parent.fill(colorWhite);// la boite prend la teinte blanche
        parent.rect(xpos + space, ypos, swidth, sheight);//création de la boite carré de 55 de coté
        parent.textSize(20);//Taille du texte
        parent.fill(colorWhite);//Couleur du nom de la boite: blanc
        parent.text("Y", xpos + space - 5, ypos - 40);//Ecriture du nom de la boite au dessus de celle-ci
        parent.fill(colorBlack);//couleur noir de la variable récupérer
        parent.text(y, xpos + space - (spaceInt * (int) (Math.log10(y) + 1) - spaceInt), ypos + spaceInt); //On décale pour chaque nouveau chiffre dans le nombre saisie
    }

    public void setXY(int[] coord) {
        /**
         * Conserve les valeurs a afficher
         */
        this.x = coord[0];
        this.y = coord[1];
    }
}
