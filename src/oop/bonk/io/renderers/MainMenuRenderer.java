package oop.bonk.io.renderers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import oop.bonk.io.Main;
import oop.bonk.io.MouseInput;
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
        this.addMouseListener(new MouseInput());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Main.xSize, Main.ySize);
    }
    public static Rectangle PlayButton = new Rectangle(Main.xSize / 2 - 50,200,100,50);
    public static Rectangle OptButton = new Rectangle(Main.xSize / 2 - 50,300,100,50);
    public static Rectangle QuitButton = new Rectangle(Main.xSize / 2 - 50,400,100,50);

    public void renderButtons(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
    	Font font2 = new Font("arial",Font.BOLD,30);
    	g.setFont(font2);
    	g.drawString("Play",PlayButton.x + 19,PlayButton.y + 30);
    	g2d.draw(PlayButton);
    	g.drawString("Options",OptButton.x + 19,OptButton.y + 30);
    	g2d.draw(OptButton);
    	g.drawString("Quit",QuitButton.x + 19,QuitButton.y + 30);
    	g2d.draw(QuitButton);


    } //ovo npr nece da radi

    @Override
    public void paintComponent(Graphics g) {
        Font font = new Font("arial",Font.BOLD,50);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("BUDGET BONK.IO",Main.xSize/4,Main.ySize/4);
        renderButtons(g);
    }




    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Finalize " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
    }


}
