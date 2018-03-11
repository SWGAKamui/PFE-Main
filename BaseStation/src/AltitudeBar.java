import processing.core.PApplet;
/**
 * Classe écrite par Kinda AL CHAHID
 */
public class AltitudeBar {
    private int swidth, sheight;    // width and height of bar
    private float xpos, ypos;       // x and y position of bar
    private float spos, newspos;    // x position of slider
    private float sposMin, sposMax; // max and min values of slider
    private int loose;              // how loose/heavy
    private boolean over;           // is the mouse over the slider?
    private boolean locked;
    private PApplet parent;

    public AltitudeBar(PApplet p) {
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
        parent.stroke(255);
        parent.textSize(20);
        parent.fill(255);
        parent.text("Altitude cm", -(xpos+130), ypos -60);
        parent.textSize(15);
        for (int i = 1; i < 8; i++) {
            parent.fill(255);
            parent.text((i - 1) * 10 + 15 * (i - 1) + 20, (sposMax / 7) * i - swidth / 2 - 10 + xpos/2, ypos - 30);
            parent.line((sposMax / 7) * i - swidth / 2 + xpos/2, ypos - 20, (sposMax / 7) * i - swidth / 2 + xpos/2, ypos - 10);
        }
        parent.fill(0);
        update();
        display();
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

    private float constrain() {
        if (parent.mouseX > sposMax)
            return sposMax - swidth / 2;
        if (parent.mouseX < sposMin + 20)
            return sposMin - swidth / 2 + 50;
        return parent.mouseX - swidth / 2;
    }

    private void display() {
        parent.noStroke();
        parent.fill(204);
        parent.rect(xpos, ypos, swidth, sheight);
        if (over || locked)
            parent.fill(0, 0, 0);
        else
            parent.fill(102, 102, 102);
        parent.rect(spos, ypos, sheight, sheight);
    }

    public float getPos() {
        return (spos / 30) * 10;
    }
}