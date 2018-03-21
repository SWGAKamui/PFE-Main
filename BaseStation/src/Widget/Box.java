package Widget;

import processing.core.PApplet;

import java.awt.*;

/**
 * @author Kinda AL CHAHID
 */
public class Box {
    int x, y, sheight, swidth;//position de la boite
    String name;
    private PApplet parent;// l'executable est conserver pour y afficher les boites
    private Color colorRed = new Color(255, 0, 0);
    private Color colorWhite = new Color(255, 255, 255);

    public Box(PApplet p) {
        parent = p;
    }

    public void setup(int x, int y, int swidth, int sheight, String name) {
        //Position de la boite
        this.x = x;
        this.y = y;
        this.sheight = sheight;
        this.swidth = swidth;
        this.name = name;
    }

    public void draw() {

        parent.noFill();//pas de remplissage de la boite, elle doit rester transparente
        parent.stroke(colorWhite.getRGB());//bord en blanc
        parent.rect(x, y, swidth, sheight); //position et taille de la boite

    }

    public void drawText(int xText, int yText) {
        int sizeText = 30;
        parent.textSize(sizeText);//taille du texte à afficher
        parent.fill(colorRed.getRed(), colorRed.getGreen(), colorRed.getBlue());//affichage en rouge du texte
        parent.text(name, xText, yText); //affichage du text avec valeur empirique
    }
}
