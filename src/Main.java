import java.awt.Color;

/**
 * Hier wird die Spielwelt erstellt und Shapes werden hinzugefuegt.
 */
public class Main {

    public static void main(String[] args) {
        // Erstellen der Gameworld mit Breite 800 und Höhe 600
        GameWorld world = new GameWorld("ShapeCraft 0.1", 800, 600);
        // Hintergrund Farbe dunkel grau
        world.setBackground(Color.DARK_GRAY);

        

        world.start();
    }
}
