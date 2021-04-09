package oop.bonk.io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import oop.bonk.io.renderers.MainMenuRenderer;
import oop.bonk.io.utils.DebugUtil;
import oop.bonk.io.utils.MusicUtil;
/*
 * pozvo ga preload
 */



public class Root {

    private static JFrame frame = new JFrame();


    @SuppressWarnings("unused")
    private final boolean noConfig;
    //enum GAME ce se koristiti za proveru stanja da li je u igrici ili u main manu-u
    public enum Menu {
        MAINMENU, OPTIONS, ROOM,GAME
    }

    public Root(boolean a2) {
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Instantiate " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));



        frame = new JFrame("oop.bonk.io");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);
        frame.setResizable(false);
        frame.setContentPane(new MainMenuRenderer());

        frame.setVisible(true);
        frame.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    frame.repaint();
                }
            }
        }.start();

        // zavrsili smo sa prikazivanjem pocetnog frame-a

        // https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
        noConfig = a2;

        if (Main.hocuMuziku) {
            MusicUtil.playMusic();
        }
    }

    public static JFrame getFrame() {
        return frame;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Finalize " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
    }
}