import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Spielwelt und Rendering-Engine.
 * 
 * Nicht veraendern!
 *
 * Verwendung:
 *
 *   GameWorld world = new GameWorld("Mein Spiel", 800, 600);
 *   world.setBackground(Color.BLACK);
 *   world.add(new Circle(400, 300, 50, Color.RED));
 *   world.start();
 */
public class GameWorld {

    private final String title;
    private final int width;
    private final int height;

    private Color background = Color.BLACK;
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private final ArrayList<KeyListener> keyListeners = new ArrayList<>();

    private JFrame frame;
    private GamePanel panel;

    /** Frames pro Sekunde */
    private static final int FPS = 60;

    // -------------------------------------------------------------------------
    // Konstruktoren
    // -------------------------------------------------------------------------

    /**
     * Erstellt eine Spielwelt mit Standardgroesse (800 x 600).
     *
     * @param title Fenstertitel
     */
    public GameWorld(String title) {
        this(title, 800, 600);
    }

    /**
     * Erstellt eine Spielwelt mit benutzerdefinierter Groesse.
     *
     * @param title  Fenstertitel
     * @param width  Breite des Fensters in Pixeln
     * @param height Hoehe des Fensters in Pixeln
     */
    public GameWorld(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    // -------------------------------------------------------------------------
    // Schüler-API
    // -------------------------------------------------------------------------

    /**
     * Fuegt eine Shape zur Spielwelt hinzu.
     * Die Shape wird ab dem naechsten Frame gezeichnet und aktualisiert.
     *
     * @param shape Die hinzuzufuegende Shape
     */
    public void add(Shape shape) {
        shape.setWorld(this);
        shapes.add(shape);
    }

    /**
     * Entfernt eine Shape aus der Spielwelt.
     *
     * @param shape Die zu entfernende Shape
     */
    public void remove(Shape shape) {
        shapes.remove(shape);
    }

    /**
     * Setzt die Hintergrundfarbe des Spielfensters.
     *
     * @param color Hintergrundfarbe (z. B. Color.BLACK)
     */
    public void setBackground(Color color) {
        this.background = color;
    }

    /**
     * Registriert einen KeyListener fuer Tastatureingaben.
     *
     * Beispiel:
     *
     *   world.addKeyListener(new KeyAdapter() {
     *       public void keyPressed(KeyEvent e) {
     *           if (e.getKeyCode() == KeyEvent.VK_LEFT) {
     *               paddle.setX(paddle.getX() - 10);
     *           }
     *       }
     *   });
     *
     * @param listener Der zu registrierende KeyListener
     */
    public void addKeyListener(KeyListener listener) {
        keyListeners.add(listener);
    }

    /**
     * Oeffnet das Spielfenster und startet die Game Loop.
     * update() und draw() werden automatisch FPS-mal pro Sekunde aufgerufen.
     */
    public void start() {
        panel = new GamePanel();
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);

        for (KeyListener kl : keyListeners) {
            frame.addKeyListener(kl);
        }
        frame.setFocusable(true);
        frame.requestFocus();

        frame.setVisible(true);

        Timer timer = new Timer(1000 / FPS, e -> {
            for (Shape s : shapes) {
                s.update();
            }
            panel.repaint();
        });
        timer.start();
    }

    /**
     * Gibt die Breite der Spielwelt in Pixeln zurueck.
     *
     * @return Breite in Pixeln
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gibt die Hoehe der Spielwelt in Pixeln zurueck.
     *
     * @return Hoehe in Pixeln
     */
    public int getHeight() {
        return height;
    }

    // -------------------------------------------------------------------------
    // Internes Rendering
    // -------------------------------------------------------------------------

    private class GamePanel extends JPanel {

        public GamePanel() {
            setPreferredSize(new java.awt.Dimension(width, height));
            setBackground(background);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(background);
            for (Shape s : shapes) {
                s.draw(g);
            }
        }
    }
}
