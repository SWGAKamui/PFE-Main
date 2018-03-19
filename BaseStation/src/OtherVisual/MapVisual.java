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
    Location locationStart = new Location(44.838060f, -0.579024f); //Bordeaux
    Location locationEnd = new Location(48.853401f, 2.348781f); // Paris
    Location locationDrone = new Location(44.808302, -0.596738f);
    double distanceLeft;

    public MapVisual(PApplet p) {
        parent = p;
    }

    public void setup(int x, int y) {
        this.x = x;
        this.y = y;
        sizeW = parent.width / 2 + parent.width / 5;
        sizeH = parent.height;

        map = new UnfoldingMap(parent, x, y, sizeW, sizeH);
        map.zoomAndPanTo(7, new Location(45.778007,3.08440));
        MapUtils.createDefaultEventDispatcher(parent, map);
    }

    public void draw() {
        moveDrone();
        map.draw();

        // Draws locations on screen positions according to their geo-locations.

        // Fixed-size marker start
        ScreenPosition posStart = map.getScreenPosition(locationStart);
        parent.fill(0, 200, 0, 100);
        parent.ellipse(posStart.x, posStart.y, 20, 20);

        // Fixed-size marker end
        ScreenPosition posEnd = map.getScreenPosition(locationEnd);
        parent.fill(0, 200, 0, 100);
        parent.ellipse(posEnd.x, posEnd.y, 20, 20);


        // Fixed-size marker end
        ScreenPosition posDrone = map.getScreenPosition(locationDrone);
        parent.fill(0, 0, 200, 100);
        parent.ellipse(posDrone.x, posDrone.y, 20, 20);

        distanceLeft = locationDrone.getDistance(locationEnd) * 1000;
        String distance = String.valueOf(distanceLeft);
        parent.textSize(25);
        parent.fill(255, 0, 0);
        parent.text("Distance left: " + distance + " m", x, y - 10);

        parent.stroke(255, 0, 0);
        parent.line(posStart.x, posStart.y, posEnd.x, posEnd.y);
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
        double x = (locationEnd.x - locationDrone.x)/1000;
        double y = (locationEnd.y - locationDrone.y) / 1000;
        if (distanceLeft < 30) {
            x *= 10;
            y *= 10;
        }
        setLocationDrone(x * 10, y * 10);
    }

    public void distance() {

    }
}