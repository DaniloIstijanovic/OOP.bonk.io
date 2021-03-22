package oop.bonk.io.renderers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import oop.bonk.io.Main;
import oop.bonk.io.utils.DebugUtil;

/*
 * render za pocetni meni
 */
public class MainMenuRenderer extends JPanel {

    private static final long serialVersionUID = 1L;

    public MainMenuRenderer() {
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Instantiate " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
        setBackground(new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Main.xSize, Main.ySize);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("tekst u main menu", 200, 200);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Finalize " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
    }

}
