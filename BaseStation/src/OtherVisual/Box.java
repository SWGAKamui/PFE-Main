package OtherVisual;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Box {
    int xOrder, yOrder, xData, yData; //position des différentes boites
    private PApplet parent;// l'executable est conserver pour y afficher les boites

    public Box(PApplet p) {
        parent = p;
    }

    public void setup(int xO, int yO, int xD, int yD) {
        //Position de la boite des ordres a envoyer au drone
        this.xOrder = xO;
        this.yOrder = yO;
        //Position de la boite des ordres reçu par le drone
        this.xData = xD;
        this.yData = yD;
    }

    public void draw() {
        //Order Box
        parent.noFill();//pas de remplissage de la boite, elle doit rester transparente
        parent.stroke(255);//bord en blanc
        parent.rect(xOrder, yOrder, parent.width / 2 + parent.width / 5, 270); //position et taille de la boite
        parent.textSize(30);//taille du texte à afficher
        parent.fill(255, 0, 0);//affichage en rouge du texte
        parent.text("Order Box", xOrder * 2, yOrder - 100); //affichage du text avec valeur empirique
        //Drone data
        parent.noFill();//pas de remplissage de la boite, elle doit rester transparente
        parent.stroke(255);//bord en blanc
        parent.rect(xData, yData, parent.width / 2 + parent.width / 5, parent.height / 2 + parent.height / 3);//position et taille de la boite
        parent.textSize(30);//taille du texte à afficher
        parent.fill(255, 0, 0);//affichage en rouge du texte
        parent.text("Drone data", -200, parent.width / 5 + 20);//affichage du text avec valeur empirique
    }
}
