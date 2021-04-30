package oop.bonk.io;

import oop.bonk.io.renderers.MainMenuRenderer;
import oop.bonk.io.utils.DebugUtil;
import oop.bonk.io.utils.MusicUtil;

import javax.swing.*;
import java.awt.*;
/*
 * pozvo ga preload
 */


public class Root {

    private static JFrame frame = new JFrame();

    //enum GAME ce se koristiti za proveru stanja da li je u igrici ili u main manu-u
    @SuppressWarnings("unused")
    public enum Menu {
        MAINMENU, OPTIONS, ROOM, GAME
    }

    public Root() {
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

        new Thread(() -> {
            while (true) {
                frame.repaint();
            }
        }).start();

        // zavrsili smo sa prikazivanjem pocetnog frame-a

        // https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html

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