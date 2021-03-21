package oop.bonk.io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import oop.bonk.io.utils.DebugUtil;
import oop.bonk.io.utils.MusicUtil;
/*
* pozvo ga preload
*/
public class Root {

    private static JFrame frame = new JFrame();

    @SuppressWarnings("unused")
    private final boolean noConfig;

    public enum Menu {
        MAINMENU, OPTIONS, ROOM
    }

    public Root(boolean a2) {
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Instantiate " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));


        frame = new JFrame("oop.bonk.io");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
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
