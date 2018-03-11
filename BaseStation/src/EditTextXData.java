import processing.core.PApplet;
/**
 * Classe écrite par Kinda AL CHAHID
 */
public class EditTextXData {
    PApplet parent;
    private int xpos;
    private int ypos;
    private int swidth;
    private int sheight;
    private boolean over;
    private boolean click;
    private int x = 0;
    private String numberXString = "";

    public EditTextXData(PApplet p) {
        parent = p;
        swidth = 55;
        sheight = 55;
        over = false;
        click = false;
    }

    public void setup(int x, int y) {
        xpos = x;
        ypos = y;
    }

    public void draw() {
        update();
        display();
        parent.rect(xpos, ypos, 55, 55);
        parent.textSize(20);
        parent.fill(255, 255, 255);
        parent.text("X", xpos - 5, ypos - 40);
        parent.fill(0, 0, 0);
        parent.text(x, xpos - (8 * (int) (Math.log10(x) + 1) - 8), ypos + 8); //On décale pour chaque nouveau chiffre dans le nombre saisie
    }

    public void keyReleased() {
        if (click && parent.key != parent.ENTER && Character.isDigit(parent.key)) { //on bloque tout ce qui n'est pas un chiffre rentrée au clavier
            numberXString += Integer.parseInt(String.valueOf(parent.key));
            x = Integer.parseInt(numberXString);
        } else if (parent.key == parent.ENTER) {
            click = false;
            numberXString = "";
        }
    }

    public int getX() {
        return x;
    }

    private void display() {
        if (over || click)
            parent.fill(127);
        else
            parent.fill(204);
        if (parent.mousePressed && over)
            click = true;
    }

    private void update() {
        over = parent.mouseX > xpos + 150 && parent.mouseX < xpos + swidth + 150 &&
                parent.mouseY > ypos + 50 && parent.mouseY < ypos + sheight + 50;
    }
}
