package Widget;

import processing.core.PApplet;

/**
 * @author processor65
 * https://forum.processing.org/one/topic/artificial-horizon-compass-18-4-2013.html
 * Licence : aucune
 * Modifié par Kinda AL CHAHID
 */
public class LineHorizon {
    private float pitch, roll, yaw; //donnée yaw, pitch, roll récupérer par le drone
    private int scaleMax = 12;
    private int scaleMin = 24;

    private int sizeBlueW = 800;
    private int sizeBlueH = 900;

    private int sizeBrownW = 900;
    private int sizeBrownH = 600;
    private PApplet parent;

    public LineHorizon(PApplet p) {
        parent = p;
    }

    public void draw() {
        parent.translate(parent.width / 4, parent.height / 2); //décalage de l'affichage
        horizon(); //affichage du fond bleu et marron
        parent.rotate(-roll);//rotation pour la graduation
        pitchScale();//affichage de la graduation
        axis();// affichage de a flèche rouge de la direction du drone
        parent.rotate(roll);//remise a zéro de la rotation
        borders();//affichage des bords arrondies pour caché les carrés superposé de l'horizon
    }

    private void horizon() {
        /**
         * Affichage de l'horizon bleu et marron
         */
        parent.noStroke();
        parent.fill(0, 180, 255);
        parent.rect(0, -100, sizeBlueW, sizeBlueH); //affichage d'un rectangle bleu correspondant au ciel
        parent.fill(95, 55, 40);
        parent.rotate(-roll);//rotation mise en place de la barre marron
        parent.rect(0, 400 + pitch, sizeBrownW, sizeBrownH); //affichage d'un rectangle marron correspondant à la terre
        parent.rotate(roll);//remise a zéro de la rotation


        //graduation circulaire autour de la ligne d'horizon partie haute
        parent.rotate(-parent.PI - parent.PI / 6); //calcule de la rotation
        circularScale();
        parent.rotate(parent.PI + parent.PI / 6); //mise a zéro de la rotation

        //graduation circulaire autour de la ligne d'horizon partie basse
        parent.rotate(-parent.PI / 6);
        circularScale();
        parent.rotate(parent.PI / 6);
    }

    private void circularScale() {
        /**
         * graduation circulaire autour de la ligne d'horizon
         */
        float GaugeWidth = 800;
        parent.textSize(GaugeWidth / 30);//taille du texte
        float StrokeWidth = 1;
        float an;
        float radians;
        float x1, x2, y1, y2;
        parent.strokeWeight(2 * StrokeWidth); //épaisseur de la graduation
        parent.stroke(255);//remplissage blanc


        //Calcul de la position de la graduation
        float DivCloserPhasorLenght = GaugeWidth / 2 - GaugeWidth / 9 - StrokeWidth;
        float DivDistalPhasorLenght = GaugeWidth / 2 - GaugeWidth / 7 - StrokeWidth;

        for (int Division = 0; Division < scaleMin + 1; Division++) {
            an = 120 / 2 + Division * 120 / scaleMin;
            radians = PApplet.radians(an);
            x1 = DivCloserPhasorLenght * PApplet.cos(radians);
            x2 = DivDistalPhasorLenght * PApplet.cos(radians);
            y1 = DivCloserPhasorLenght * PApplet.sin(radians);
            y2 = DivDistalPhasorLenght * PApplet.sin(radians);
            parent.line(x1, y1, x2, y2);
        }

        DivCloserPhasorLenght = GaugeWidth / 2 - GaugeWidth / 10 - StrokeWidth;
        DivDistalPhasorLenght = GaugeWidth / 2 - GaugeWidth / 7 - StrokeWidth;

        for (int Division = 0; Division < scaleMax + 1; Division++) {
            an = 120 / 2 + Division * 120 / scaleMax;
            radians = PApplet.radians(an);
            x1 = DivCloserPhasorLenght * PApplet.cos(radians);
            x2 = DivDistalPhasorLenght * PApplet.cos(radians);
            y1 = DivCloserPhasorLenght * PApplet.sin(radians);
            y2 = DivDistalPhasorLenght * PApplet.sin(radians);
            if (Division == scaleMax / 2 | Division == 0 | Division == scaleMax) {
                parent.strokeWeight(15);
                parent.stroke(0);
                parent.line(x1, y1, x2, y2);
                parent.strokeWeight(8);
                parent.stroke(100, 255, 100);
                parent.line(x1, y1, x2, y2);
            } else {
                parent.strokeWeight(3);
                parent.stroke(255);
                parent.line(x1, y1, x2, y2);
            }
        }
    }

    private void axis() {
        /**
         * Affichage des flèches rouges de l'axe
         */
        parent.stroke(255, 0, 0); //remplissage rouge
        parent.strokeWeight(3);//épaisseur des ligens
        parent.line(-115, 0, 115, 0);//affichage de flèches horizontal
        parent.line(0, 280, 0, -280);//affichage de flèches vertical

        parent.fill(100, 255, 100);//remplissage vert
        parent.stroke(0);//contour noir
        parent.triangle(0, -285, -10, -255, 10, -255); //affichage de la pointe de la flèche vertical haute
        parent.triangle(0, 285, -10, 255, 10, 255); //affichage de la pointe de la flèche horizontal basse
    }

    private void borders() {
        int size = 1100;
        parent.noFill();//pas de remplissage
        parent.stroke(0);//contour noir
        parent.strokeWeight(400);//épaisseur du contour
        parent.rect(0, 0, size, size); //rectangle de contour noir
        parent.strokeWeight(200);//épaisseur du contour
        parent.ellipse(0, 0, size - 100, size - 100); //contour arrondie
    }

    private void pitchScale() {
        parent.stroke(255);//contour blanc
        parent.fill(255);//remplissage blanc
        parent.strokeWeight(3);//épaisseur
        parent.textSize(24);//taille du texte
        parent.textAlign(parent.CENTER);//Alignement au centre

        for (int i = -4; i < 5; i++) {
            //Affichage de la graduation numérique
            if (!(i == 0))
                parent.line(110, 50 * i, -110, 50 * i);
            parent.text(String.valueOf(i * 10), 140, 50 * i, 100, 30);
            parent.text(String.valueOf(i * 10), -140, 50 * i, 100, 30);
        }

        parent.textAlign(parent.CORNER); //affichage en démarrant du coin
        parent.strokeWeight(2);//épaisseur du trait

        for (int i = -9; i < 10; i++) {
            //affichage de la graudation (ttraits)
            if (!(i == 0))
                parent.line(25, 25 * i, -25, 25 * i);
        }
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
}