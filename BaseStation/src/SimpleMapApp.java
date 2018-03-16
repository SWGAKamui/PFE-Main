import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.utils.*;
import processing.core.PApplet;

public class SimpleMapApp extends PApplet{

    UnfoldingMap map;

    public void settings(){
        size(800, 600);
    }
    public void setup() {
        map = new UnfoldingMap(this);
        map.zoomAndPanTo(10, new Location(44.8083788f, -0.5988937f));
        MapUtils.createDefaultEventDispatcher(this, map);
    }

    public void draw() {
        map.draw();
    }
    public static void main(String[] args) {
        PApplet.main("SimpleMapApp");
    }

}
