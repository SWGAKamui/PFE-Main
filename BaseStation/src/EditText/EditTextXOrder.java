package EditText;

import processing.core.PApplet;

/**
 * Classe écrite par Kinda AL CHAHID
 */
public class EditTextXOrder extends EditTextOrder {
    public Boolean enter = false;
    private PApplet parent;
    private int xpos;
    private int ypos;
    private boolean over = false;
    private boolean click = false;
    private int x = 0;
    private String numberXString = "";


    public EditTextXOrder(PApplet p) {
        super(p);
        parent = p;
    }

    public void setup(int x, int y) {
        xpos = x;
        ypos = y;
    }

    public void draw() {
        update();
        super.display(click, over);
        if (parent.mousePressed && over) {
            click = true;
            enter = false;
        }
        super.draw(xpos, ypos, "X", x);
    }

    public void keyReleased() {
        if (click && parent.key != parent.ENTER && Character.isDigit(parent.key)) { //on bloque tout ce qui n'est pas un chiffre rentrée au clavier
            numberXString += Integer.parseInt(String.valueOf(parent.key));
            x = Integer.parseInt(numberXString);
        } else if (parent.key == parent.ENTER) {
            click = false;
            numberXString = "";
            enter = true;
        }
    }


    private void update() {
        over = parent.mouseX > xpos && parent.mouseX < xpos + swidth &&
                parent.mouseY > ypos && parent.mouseY < ypos + sheight;
    }

    public int getX() {
        return x;
    }

}
