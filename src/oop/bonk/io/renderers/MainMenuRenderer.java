package oop.bonk.io.renderers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import oop.bonk.io.Main;
import oop.bonk.io.Root;
import oop.bonk.io.utils.DebugUtil;

/*
 * render za pocetni meni
 */



public class MainMenuRenderer extends JPanel {

    private static final long serialVersionUID = 1L;

    public MainMenuRenderer() {
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Instantiate " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
        setBackground(Color.black);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Main.xSize, Main.ySize);
    }
    /*public Rectangle PlayButton = new Rectangle(Main.ySize / 2 + 120,200);
    public Rectangle OptButton = new Rectangle(Main.ySize / 2 + 120,200);
    public Rectangle QuitButton = new Rectangle(Main.ySize / 2 + 120,200);

    public void render(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
    	Font font2 = new Font("arial",Font.BOLD,30);
    	g.setFont(font2);
    	g.drawString("Play",PlayButton.height + 19,PlayButton.width + 30);
    	g2d.draw(PlayButton);
    	g.drawString("Options",OptButton.height + 19,OptButton.width + 30);
    	g2d.draw(OptButton);
    	g.drawString("Quit",QuitButton.height + 19,QuitButton.width + 30);
    	g2d.draw(QuitButton);


    }*/ //ovo npr nece da radi

    @Override
    public void paintComponent(Graphics g) {
        Font font = new Font("arial",Font.BOLD,50);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("BUDGET BONK.IO",Main.ySize/2,200);

    }




    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Finalize " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
    }


}
