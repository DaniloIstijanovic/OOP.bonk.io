package oop.bonk.io;

import oop.bonk.io.frames.MainMenuFrame;
import oop.bonk.io.utils.DebugUtil;
import oop.bonk.io.utils.MusicUtil;

import javax.swing.*;
import java.awt.*;
/*
 * pozvo ga preload
 */


public class Root {

    private static JFrame frame = new JFrame();

    public Root() {
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Instantiate " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));


        frame = new JFrame("BonkJar");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);
        frame.setResizable(false);
        frame.setContentPane(new MainMenuFrame());

        frame.setVisible(true);
        frame.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        // zavrsili smo sa prikazivanjem pocetnog frame-a

        // https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html

        if (Main.hocuMuziku) {
            MusicUtil.playMusic();
        }

        new Thread(()->{
            while(true) {
                if(frame.isFocused()){
                    frame.repaint();
                }
            }
        }).start();
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

    //enum GAME ce se koristiti za proveru stanja da li je u igrici ili u main manu-u
    @SuppressWarnings("unused")
    public enum Menu {
        MAINMENU, OPTIONS, ROOM, GAME
    }
}