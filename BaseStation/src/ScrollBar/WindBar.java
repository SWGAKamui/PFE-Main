package ScrollBar;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class WindBar extends ScrollBarProcessing {
    private float xpos, ypos;       // x and y position of bar
    private float spos, newspos;    // x position of slider
    private int loose;              // how loose/heavy
    private boolean over;           // is the mouse over the slider?
    private boolean locked;
    private PApplet parent;

    public WindBar(PApplet p) {
        super(p);
        parent = p;
        newspos = spos;
        loose = 16;
    }

    public void setup(int x, int y) {
        xpos = x;
        ypos = y;
    }


    public void draw() {
        draw(this.xpos, this.ypos, this.spos, this.over, this.locked, "Wind");
        update();
    }


    public void update() {
        over = parent.mouseX > xpos && parent.mouseX < xpos + swidth &&
                parent.mouseY > ypos + 30 && parent.mouseY < ypos + sheight + 30;
        if (parent.mousePressed && over)
            locked = true;
        if (!parent.mousePressed)
            locked = false;
        if (locked)
            newspos = constrain();
        if (PApplet.abs(newspos - spos) > 1)
            spos = spos + (newspos - spos) / loose;
    }



    public float getPos() {
        return (spos / 30) * 10;
    }
}