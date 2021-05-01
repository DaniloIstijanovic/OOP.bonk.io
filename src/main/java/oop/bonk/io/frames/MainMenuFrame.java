package oop.bonk.io.frames;

import oop.bonk.io.Main;
import oop.bonk.io.MouseInput;
import oop.bonk.io.MyButton;
import oop.bonk.io.utils.DebugUtil;
import oop.bonk.io.utils.MiscUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * render za pocetni meni
 */
public class MainMenuFrame extends JPanel {

    public static final MyButton playButton = new MyButton("Play");
    public static final MyButton optButton = new MyButton("Options");
    public static final MyButton quitButton = new MyButton("Quit");
    private static final long serialVersionUID = 1L;
    //proba za animacije
    private static final List<List<Point>> zmijice = new ArrayList<>();
    private static final int kolikoTacaka = 10000;
    private static final int kolikoRefresh = 100;
    private static final Color[] boje = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA};

    static {
        playButton.center();
        playButton.setLocation(playButton.getLocation().x, playButton.getLocation().y - (int) (1.5 * MyButton.BUTTONSIZE.y));
        optButton.center();
        quitButton.center();
        quitButton.setLocation(quitButton.getLocation().x, quitButton.getLocation().y + (int) (1.5 * MyButton.BUTTONSIZE.y));

        for (int i = 0; i < 7; i++) {
            List<Point> zmijica = new ArrayList<>();
            zmijica.add(new Point((int) (Math.random() * Main.WINDOWSIZE.x), (int) (Math.random() * Main.WINDOWSIZE.y)));
            for (int j = 0; j < kolikoTacaka - 1; j++) {
                zmijica.add(MiscUtil.withinWindow(MiscUtil.randomAdjacent(zmijica.get(j))));
            }
            zmijice.add(zmijica);
        }
    }

    public MainMenuFrame() {
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Instantiate " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
        setBackground(Color.black);
        this.addMouseListener(new MouseInput());
    }

    public void renderButtons(Graphics g) {
        g.setFont(Main.neoSansFont.deriveFont(Main.WINDOWSIZE.y * 0.05f));

        playButton.draw(g);
        optButton.draw(g);
        quitButton.draw(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        renderBackground(g);
        g.setFont(Main.neoSansFont.deriveFont(Main.WINDOWSIZE.y * 0.1f));
        g.setColor(Color.white);
        MiscUtil.drawStringCenter(g, "BonkJar", Main.WINDOWSIZE.x / 2, Main.WINDOWSIZE.y / 5);
        renderButtons(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Main.WINDOWSIZE.x, Main.WINDOWSIZE.y);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Finalize " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
    }

    private void renderBackground(Graphics g) {
        for (int i = 0; i < 7; i++) {
            g.setColor(boje[i]);
            mutirajZmijicu(zmijice.get(i), kolikoRefresh);
            for (Point p : zmijice.get(i)) {
                g.drawLine(p.x, p.y, p.x, p.y);
            }
        }
    }

    private void mutirajZmijicu(List<Point> zmijica, int n) {
        for (int i = 0; i < n; i++) {
            zmijica.add(MiscUtil.withinWindow(MiscUtil.randomAdjacent(zmijica.get(kolikoTacaka - 1))));
            zmijica.remove(0);
        }
    }

}
