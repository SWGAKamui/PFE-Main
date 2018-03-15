package Processing.pr;

import processing.core.PApplet;

public class LineH {
    private static float pitch;
    private static float roll;
    private static float yaw;
    private int scaleMin;
    private int scaleMax;
    private PApplet parent;

    public LineH(PApplet p) {
        parent = p;
    }

    public void draw() {
        parent.translate(parent.width / 2, parent.height / 1);
        horizon();
        parent.rotate(-roll);
        pitchScale();
        axis();
        parent.rotate(roll);
        borders();
    }

    private void horizon() {
        parent.noStroke();
        parent.fill(0, 180, 255);
        parent.rect(0, -100, 900, 1000);
        parent.fill(95, 55, 40);
        parent.rotate(-roll);
        parent.rect(0, 400 + pitch, 900, 800);
        parent.rotate(roll);
        parent.rotate(-parent.PI - parent.PI / 6);
        scaleMax = 12;
        scaleMin = 24;
        circularScale();
        parent.rotate(parent.PI + parent.PI / 6);
        parent.rotate(-parent.PI / 6);
        circularScale();
        parent.rotate(parent.PI / 6);
    }

    private void circularScale() {
        float GaugeWidth = 800;
        parent.textSize(GaugeWidth / 30);
        float StrokeWidth = 1;
        float an;
        float radians;
        float x1, x2, y1, y2;
        parent.strokeWeight(2 * StrokeWidth);
        parent.stroke(255);
        float DivCloserPhasorLenght = GaugeWidth / 2 - GaugeWidth / 9 - StrokeWidth;
        float DivDistalPhasorLenght = GaugeWidth / 2 - GaugeWidth / 7 - StrokeWidth;
        for (int Division = 0; Division < scaleMin + 1; Division++) {
            an = 120 / 2 + Division * 120 / scaleMin;
            radians = PApplet.radians(an);
            x1 = DivCloserPhasorLenght * PApplet.cos(radians);
            x2 = DivDistalPhasorLenght * PApplet.cos(radians);
            y1 = DivCloserPhasorLenght * PApplet.sin(radians);
            y2 = DivDistalPhasorLenght * PApplet.sin(radians);
            parent.line(x1, y1, x2, y2);
        }
        DivCloserPhasorLenght = GaugeWidth / 2 - GaugeWidth / 10 - StrokeWidth;
        DivDistalPhasorLenght = GaugeWidth / 2 - GaugeWidth / 7 - StrokeWidth;
        for (int Division = 0; Division < scaleMax + 1; Division++) {
            an = 120 / 2 + Division * 120 / scaleMax;
            radians = PApplet.radians(an);
            x1 = DivCloserPhasorLenght * PApplet.cos(radians);
            x2 = DivDistalPhasorLenght * PApplet.cos(radians);
            y1 = DivCloserPhasorLenght * PApplet.sin(radians);
            y2 = DivDistalPhasorLenght * PApplet.sin(radians);
            if (Division == scaleMax / 2 | Division == 0 | Division == scaleMax) {
                parent.strokeWeight(15);
                parent.stroke(0);
                parent.line(x1, y1, x2, y2);
                parent.strokeWeight(8);
                parent.stroke(100, 255, 100);
                parent.line(x1, y1, x2, y2);
            } else {
                parent.strokeWeight(3);
                parent.stroke(255);
                parent.line(x1, y1, x2, y2);
            }
        }
    }

    private void axis() {
        parent.stroke(255, 0, 0);
        parent.strokeWeight(3);
        parent.line(-115, 0, 115, 0);
        parent.line(0, 280, 0, -280);
        parent.fill(100, 255, 100);
        parent.stroke(0);
        parent.triangle(0, -285, -10, -255, 10, -255);
        parent.triangle(0, 285, -10, 255, 10, 255);
    }

    private void borders() {
        parent.noFill();
        parent.stroke(0);
        parent.strokeWeight(400);
        parent.rect(0, 0, 1100, 1100);
        parent.strokeWeight(200);
        parent.ellipse(0, 0, 1000, 1000);
        parent.fill(0);
        parent.noStroke();
        parent.rect(4 * parent.width / 5, 0, parent.width, 2 * parent.height);
        parent.rect(-4 * parent.width / 5, 0, parent.width, 2 * parent.height);
    }

    private void pitchScale() {
        parent.stroke(255);
        parent.fill(255);
        parent.strokeWeight(3);
        parent.textSize(24);
        parent.textAlign(parent.CENTER);
        for (int i = -4; i < 5; i++) {
            if (!(i == 0))
                parent.line(110, 50 * i, -110, 50 * i);
            parent.text("" + i * 10, 140, 50 * i, 100, 30);
            parent.text("" + i * 10, -140, 50 * i, 100, 30);
        }
        parent.textAlign(parent.CORNER);
        parent.strokeWeight(2);
        for (int i = -9; i < 10; i++)
            if (!(i == 0))
                parent.line(25, 25 * i, -25, 25 * i);
    }

    public float getRoll() {
        return roll;
    }

    public static void setRoll(float vroll) {
        roll = vroll;
    }

    public float getPitch() {
        return pitch;
    }

    public static void setPitch(float vpitch) {
        pitch = vpitch;
    }

    public float getYaw() {
        return yaw;
    }

    public static void setYaw(float vyaw) {
        yaw = vyaw;
    }
}