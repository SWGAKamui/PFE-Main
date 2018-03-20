package EditText;

import processing.core.PApplet;

/**
 * @author Kinda AL CHAHID
 */
abstract class EditTextOrder {
    /**
     * Classe abstraite qui affiche des boites de textes récupérant la saisie clavier pour les coordonnées x/y
     */
    public int swidth; //largeur
    public int sheight; //longueur
    PApplet parent; //Conserve l'executable qui l'appelle pour y insérer son affichage

    public EditTextOrder(PApplet p) {
        parent = p;
        swidth = 55;
        sheight = 55;
    }

    public void draw(int xpos, int ypos, String name, int value) {
        parent.rect(xpos, ypos, swidth, sheight); //Affiche une boite carré de taille 55
        parent.textSize(20); //défini la taille du texte
        parent.fill(255, 255, 255); // Le texte sera de couleur blanche
        parent.text(name, xpos - 5, ypos - 40); //Ecrit le nom de la boite au desssus de la boite (5 et 40 sont des valeurs empiriques)
        parent.fill(0, 0, 0); //L'écriture du text sera noire
        parent.text(value, xpos - (8 * (int) (Math.log10(value) + 1) - 8), ypos + 8); //On décale pour chaque nouveau chiffre dans le nombre saisie
    }

    public void display(Boolean over, Boolean click) {
        if (over || click)
            parent.fill(127); //Si la souris passe au dessus de la boite ou qu'un click a été effectué dessus, la boite devient grise foncé
        else
            parent.fill(204); //Sinon conserve une teinte gris clair
    }
}
