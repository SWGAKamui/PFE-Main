package ScrollBar;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class AltitudeBar extends ScrollBarProcessing {
    private int swidth, sheight;    // width and height of bar
    private float xpos, ypos;       // x and y position of bar
    private float spos, newspos;    // x position of slider
    private int loose;              // how loose/heavy
    private boolean over;           // is the mouse over the slider?
    private boolean locked;
    private PApplet parent;
    private int max = 7;
    private int step = 15;
    public Boolean mouse = false;

    public AltitudeBar(PApplet p) {
        super(p);
        parent = p;
        swidth = parent.width * 6;
        sheight = 16;
        spos = swidth / 30;
        newspos = spos;
        sposMin = 0;
        sposMax = swidth;
        loose = max;
    }

    public void setup(int x, int y) {
        xpos = x;
        ypos = y;
    }

    public void draw() {
        draw(this.xpos, this.ypos, this.spos, this.over, this.locked, "Altitude", step, max);
        update();
    }

    private void update() {
        over = parent.mouseX > xpos && parent.mouseX < xpos + swidth - 110 &&
                parent.mouseY > ypos - 10 && parent.mouseY < ypos + sheight - 10;
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

    public int getPos() {
        if (spos <= 0)
            return (int) (90 + (spos / 269 * 90));
        if ((spos / 279 * 90) + 90 >= 170) //altitude max
            return 170;
        return (int) (spos / 279 * 90) + 90;
    }
    public void mouseReleased(){
        mouse = true;
    }
}