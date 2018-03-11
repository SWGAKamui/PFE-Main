import processing.core.PApplet;
/**
 * Classe écrite par Kinda AL CHAHID
 */
public class Box {
    private PApplet parent;
    int xOrder, yOrder, xData, yData, xPos, yPos;

    public Box(PApplet p) {
        parent = p;
    }

    public void setup(int xO, int yO, int xD, int yD, int xpos, int ypos) {
        this.xOrder = xO;
        this.yOrder = yO;

        this.xData = xD;
        this.yData = yD;

        this.xPos = xpos;
        this.yPos = ypos;
    }

    public void draw() {
        //Order Box
        parent.noFill();
        parent.stroke(255);
        parent.rect(xOrder, yOrder, parent.width / 2 + parent.width / 5, 270);
        parent.textSize(30);
        parent.fill(255, 0, 0);
        parent.text("Order Box", xOrder * 2, yOrder - 100);
        //Drone data
        parent.noFill();
        parent.stroke(255);
        parent.rect(xData, yData, parent.width / 2 + parent.width / 5, parent.height / 2 + parent.height / 3);
        parent.textSize(30);
        parent.fill(255, 0, 0);
        parent.text("Drone data", -200, parent.width / 5 + 20);
        //Drone Position
        parent.noFill();
        parent.stroke(255);
        parent.rect(xPos, yPos, parent.width / 2 + parent.width / 5, parent.height);
        parent.textSize(30);
        parent.fill(255, 0, 0);
        parent.text("Drone Position", xPos - xPos /3, -100);
    }
}
