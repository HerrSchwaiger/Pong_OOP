import java.awt.Color;
import java.awt.Graphics;

/**
 * Abstrakte Basisklasse fuer alle Formen.
 * 
 * Nicht veraendern!
 */
public abstract class Shape {

    protected int x;
    protected int y;
    protected Color color;
    protected GameWorld world;

    /**
     * Erstellt eine Shape an Position (x, y) mit Standardfarbe Weiss.
     *
     * @param x X-Position in Pixeln
     * @param y Y-Position in Pixeln
     */
    public Shape(int x, int y) {
        this(x, y, Color.WHITE);
    }

    /**
     * Erstellt eine Shape an Position (x, y) mit der angegebenen Farbe.
     *
     * @param x     X-Position in Pixeln
     * @param y     Y-Position in Pixeln
     * @param color Fuellfarbe (z. B. Color.RED, new Color(100, 200, 50))
     */
    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    public void move(double vx, double vy) {
        this.x += Math.round(vx);
        this.y += Math.round(vy);
    }

    /**
     * Zeichnet diese Shape auf den Bildschirm.
     * Muss von jeder Unterklasse implementiert werden.
     *
     * @param g Graphics-Objekt des Spielfensters (wird automatisch uebergeben)
     */
    public abstract void draw(Graphics g);

    /**
     * Wird einmal pro Frame aufgerufen.
     * Standardmaessig leer – kann in Unterklassen ueberschrieben werden,
     * um Bewegung oder andere Logik zu implementieren.
     */
    public void update() {
        // Standardmaessig keine Aktion.
        // In Unterklassen mit @Override ueberschreiben.
    }

    // -------------------------------------------------------------------------
    // Getter und Setter
    // -------------------------------------------------------------------------

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setWorld(GameWorld world) {
        this.world = world;
    }
}
