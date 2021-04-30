package oop.bonk.io.frames;

import oop.bonk.io.Main;
import oop.bonk.io.MouseInput;
import oop.bonk.io.MyButton;
import oop.bonk.io.utils.DebugUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * render za pocetni meni
 */
public class MainMenuFrame extends JPanel {
    public static final MyButton playButton = new MyButton();
    public static final MyButton optButton = new MyButton();
    public static final MyButton quitButton = new MyButton();
    public static final List<Point> zmijica = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    static {
        playButton.center();
        playButton.setLocation(playButton.getLocation().x, playButton.getLocation().y - (int) (1.5 * MyButton.BUTTONSIZE.y));
        optButton.center();
        quitButton.center();
        quitButton.setLocation(quitButton.getLocation().x, quitButton.getLocation().y + (int) (1.5 * MyButton.BUTTONSIZE.y));
    }

    public MainMenuFrame() {
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Instantiate " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
        setBackground(Color.black);
        this.addMouseListener(new MouseInput());

        //proba za animiranu pozadinu
        zmijica.add(new Point((int) (Math.random() * Main.WINDOWSIZE.x), (int) (Math.random() * Main.WINDOWSIZE.y)));
        for (int i = 1; i < 5000; i++) {
            Point old = zmijica.get(i - 1);
            zmijica.add(new Point(old.x + (int) (Math.random() * 3) - 1, old.y + (int) (Math.random() * 3) - 1));
        }
    }

    public void renderButtons(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font font2 = new Font("arial", Font.BOLD, 30);
        g.setFont(Main.neoSansFont.deriveFont(30f));
        g.drawString("Play", playButton.getLocation().x + 19, playButton.getLocation().y + 30);
        g2d.draw(playButton.toRectangle());
        g.drawString("Options", optButton.getLocation().x + 19, optButton.getLocation().y + 30);
        g2d.draw(optButton.toRectangle());
        g.drawString("Quit", quitButton.getLocation().x + 19, quitButton.getLocation().y + 30);
        g2d.draw(quitButton.toRectangle());


    } //ovo npr nece da radi

    @Override
    public void paintComponent(Graphics g) {
        Font font = new Font("arial", Font.BOLD, 50);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("BonkJar", Main.WINDOWSIZE.x / 4, Main.WINDOWSIZE.y / 4);
        renderButtons(g);
        g.setColor(Color.green);

        //proba za animiranu pozadinu
        Point old = zmijica.get(4999);
        zmijica.remove(0);
        zmijica.add(new Point(old.x + (int) (Math.random() * 3) - 1, old.y + (int) (Math.random() * 3) - 1));
        for (int i = 0; i < 5000; i++) {
            Point sad = zmijica.get(i);
            g.drawString(".", sad.x, sad.y);
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

}
