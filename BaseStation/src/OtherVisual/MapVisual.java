package OtherVisual;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * http://unfoldingmaps.org/tutorials/markers-simple
 *
 * Classe écrite par Kinda AL CHAHID
 */
public class MapVisual {
    UnfoldingMap map;
    int sizeW;
    int sizeH;
    int x, y;
    PApplet parent;
    //Bordeaux = 44.838060,-0.579024
    //Paris = 48.853401,2.348781
    Location locationStart = new Location(44.838060f, -0.579024f); // position gps de Bordeaux
    Location locationEnd = new Location(48.853401f, 2.348781f); // position gps de Paris
    Location locationDrone = new Location(44.808302, -0.596738f); // Position du drone en coordonnée gps (cremi)
    double distanceLeft;

    public MapVisual(PApplet p) {
        parent = p;
    }

    public void setup(int x, int y) {
        this.x = x;
        this.y = y;
        sizeW = parent.width / 2 + parent.width / 5;
        sizeH = parent.height;

        map = new UnfoldingMap(parent, x, y, sizeW, sizeH); //Création d'une nouvelle carte
        map.zoomAndPanTo(7, new Location(45.778007,3.08440)); //zoom sur la France en utilisant les coordonnées gps de Clermont Ferrand comme point central
        MapUtils.createDefaultEventDispatcher(parent, map); //Affichage dans l'executable
    }

    public void draw() {
        moveDrone(); //mise en place d'une simulation de mouvement du drone
        map.draw(); //affichage de la carte

        //Affichage du point de départ
        ScreenPosition posStart = map.getScreenPosition(locationStart); //A partir de coordonnées gps, transférer en point x/y de l'écran
        parent.fill(0, 200, 0, 100);//remplissage vert avec une transparence
        parent.ellipse(posStart.x, posStart.y, 20, 20);//affichage d'un point circulaire

        //Affichage du point de destination
        ScreenPosition posEnd = map.getScreenPosition(locationEnd); //A partir de coordonnées gps, transférer en point x/y de l'écran
        parent.fill(0, 200, 0, 100);//remplissage vert avec une transparence
        parent.ellipse(posEnd.x, posEnd.y, 20, 20);//affichage d'un point circulaire


        //Affichage du point du drone
        ScreenPosition posDrone = map.getScreenPosition(locationDrone); //A partir de coordonnées gps, transférer en point x/y de l'écran
        parent.fill(0, 0, 200, 100);//remplissage bleu avec une transparence
        parent.ellipse(posDrone.x, posDrone.y, 20, 20);//affichage d'un point circulaire

        distanceLeft = locationDrone.getDistance(locationEnd) * 1000; //Calcule de la distance en mètre
        String distance = String.valueOf(distanceLeft); //converstion en string
        parent.textSize(25);//taille du text
        parent.fill(255, 0, 0);//remplissage en rouge
        parent.text("Distance left: " + distance + " m", x, y - 10); //affichage de la distance qui reste au drone a parcourir

        parent.stroke(255, 0, 0);//remplissage rouge
        parent.line(posStart.x, posStart.y, posEnd.x, posEnd.y);//affichage de la direction que doit prendre le drone
    }

    public void setLocationStart(int x, int y) {
        locationStart = new Location(44.808302 + x, -0.596738f + y);
    }

    public void setLocationDrone(double x, double y) {
        this.locationDrone = new Location(locationDrone.x + x, locationDrone.y + y);
    }

    public void setLocationEnd(int x, int y) {
        this.locationEnd = new Location(44.808302 + x, -0.596738f + y);
    }

    public void newLocationStart() {
        if (locationEnd.equals(locationDrone)) {
            locationStart = locationEnd;
        }
    }

    public void moveDrone() {
        /**
         *mise en place d'une simulation de mouvement du drone
         */

        double x = (locationEnd.x - locationDrone.x)/1000;
        double y = (locationEnd.y - locationDrone.y) / 1000;
        if (distanceLeft < 30) {
            x *= 10;
            y *= 10;
        }
        setLocationDrone(x * 10, y * 10);
    }
}