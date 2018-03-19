package ScrollBar;

import processing.core.PApplet;

/**
 *
 * https://processing.org/examples/scrollbar.html
 *
 * Modifié par Kinda AL CHAHID
 */
abstract class ScrollBarProcessing {
    public int swidth, sheight;    // width and height of bar
    public float sposMin, sposMax; // max and min values of slider
    public int loose;              // how loose/heavy
    public PApplet parent;

    public ScrollBarProcessing(PApplet p) {
        parent = p;
        swidth = parent.width * 6;
        sheight = 16;

        sposMin = 0;
        sposMax = swidth;
        loose = 16;
    }

    public void draw(float xpos, float ypos, float spos, Boolean over, Boolean locked, String name, int step, int max) {
        parent.stroke(255);
        parent.textSize(20);
        parent.fill(255);
        parent.text(name, -(xpos + 130), ypos - 60);
        parent.textSize(15);
        for (int i = 1; i < max + 1; i++) {
            parent.fill(255);
            parent.text((i - 1) * 10 + ((i - 1) * step + step), (sposMax / max) * i - swidth / 2 - 10 + xpos / 2, ypos - 30);
            parent.line((sposMax / max) * i - swidth / 2 + xpos / 2, ypos - 20, (sposMax / max) * i - swidth / 2 + xpos / 2, ypos - 10);
        }
        parent.fill(0);
        display(xpos, ypos, spos, over, locked);
    }


    public float constrain() {
        if (parent.mouseX > sposMax)
            return sposMax - swidth / 2 -20;
        if (parent.mouseX < sposMin + 20)
            return sposMin - swidth / 2 + 30;
        return parent.mouseX - swidth / 2;
    }

    public void display(float xpos, float ypos, float spos, Boolean over, Boolean locked) {
        parent.noStroke();
        parent.fill(204);
        parent.rect(xpos, ypos, swidth, sheight);
        if (over || locked)
            parent.fill(0, 0, 0);
        else
            parent.fill(102, 102, 102);
        parent.rect(spos + xpos, ypos, sheight, sheight);
    }


}
