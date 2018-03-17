package Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PApplet;

import java.util.ArrayList;

public class MapVisual{
    UnfoldingMap map;
    int sizeW;
    int sizeH;
    int x,y;
    PApplet parent;
    //LaBRI : 44.808302, -0.596738f
    //CREMI : 44.807686f, -0.597487f
    Location locationStart = new Location(44.808302, -0.596738f);
    Location locationEnd = new Location(44.807686f, -0.597487f);

    public MapVisual(PApplet p){
        parent = p;
    }

    public void setup(int x, int y) {
        this.x = x;
        this.y = y;
        sizeW = parent.width / 2 + parent.width / 5;
        sizeH = parent.height;

        map = new UnfoldingMap(parent,x, y, sizeW, sizeH);
        map.zoomAndPanTo(40,locationStart);
        MapUtils.createDefaultEventDispatcher(parent, map);
    }

    public void draw() {
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

        double distanceLeft = locationStart.getDistance(locationEnd) * 1000;
        String distance = String.valueOf(distanceLeft);

        parent.textSize(25);
        parent.fill(255, 0, 0);
        parent.text("Distance left: "+distance.substring(0, distance.indexOf(".")+3)+" m", x,y - 10);
        /*parent.fill(255, 0, 0);
        parent.stroke(255, 0, 0);
        parent.line(locationStart.x, locationStart.y, locationEnd.x, locationEnd.y);
        parent.stroke(255, 0, 0);
        parent.fill(255, 0, 0);*/
        //TODO line between markers
    }
}