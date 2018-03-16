package Processing;

import Processing.pr.Altitude;
import Processing.pr.LineH;
import Processing.pr.Vitesse;
import processing.core.PApplet;

public class TBord extends PApplet {
    private Altitude altitude = new Altitude(this);
    private Vitesse vite =new Vitesse(this);
    private LineH line =new LineH(this);

    public TBord() {
        //PApplet.main("Processing.TBord");
    }
    public void settings() {
        size(1200, 600);
    }
    public void setup() {
        rectMode(CENTER);
        smooth();
        altitude.setup(800, 0);
        vite.setup(800, 300);
        //line(200,200,100,600);
        //line.setPitch();
        // line.getRoll();
        //line.getYaw()
        /*box.setup(width / 6 + 10, height - height / 3,
                width / 6 + 10, 50,
                width - width / 10, 200);*/
    }
    public void draw() {
        background(0);
        scale((float) 0.5);
        line.draw();
        scale((float) 1.3);
        fill(0);
        //rect(0, height, 800, 500);
        altitude.draw();
        vite.draw();
        //box.draw();
    }
}