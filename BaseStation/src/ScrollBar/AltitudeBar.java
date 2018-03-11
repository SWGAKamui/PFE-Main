package ScrollBar;

import processing.core.PApplet;
/**
 * Classe écrite par Kinda AL CHAHID
 */
public class AltitudeBar extends ScrollBarProcessing {
    private int swidth, sheight;    // width and height of bar
    private float xpos, ypos;       // x and y position of bar
    private float spos, newspos;    // x position of slider
    private float sposMin, sposMax; // max and min values of slider
    private int loose;              // how loose/heavy
    private boolean over;           // is the mouse over the slider?
    private boolean locked;
    private PApplet parent;

    public AltitudeBar(PApplet p) {
        super(p);
        parent = p;
        swidth = parent.width * 6;
        sheight = 16;
        spos = swidth / 30;
        newspos = spos;
        sposMin = 0;
        sposMax = swidth;
        loose = 16;
    }
    public void setup(int x, int y){
        xpos = x;
        ypos = y;
    }

    public void draw() {
        draw(this.xpos, this.ypos, this.spos, this.over, this.locked, "Altitude");
        update();
    }

    private void update() {
        over = parent.mouseX > xpos && parent.mouseX < xpos + swidth - 110 &&
                parent.mouseY > ypos -10 && parent.mouseY < ypos + sheight-10;
        if (parent.mousePressed && over)
            locked = true;
        if (!parent.mousePressed)
            locked = false;
        if (locked)
            newspos = constrain();
        if (PApplet.abs(newspos - spos) > 1)
            spos = spos + (newspos - spos) / loose;
        getPos();
    }

    public float getPos() {
        return (spos / 30) * 10;
    }
}