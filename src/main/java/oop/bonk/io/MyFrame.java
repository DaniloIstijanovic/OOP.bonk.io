package oop.bonk.io;

import oop.bonk.io.panels.MainMenuPanel;
import oop.bonk.io.utils.DebugUtil;
import oop.bonk.io.utils.MiscUtil;
import oop.bonk.io.utils.MusicUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/*
 * pozvo ga preload
 */


public class MyFrame {

    private static JFrame frame = new JFrame();

    public MyFrame() {
        DebugUtil.debug(DebugUtil.DebugReason.MEMORY,
                "Instantiate " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));

        
        frame = new JFrame("BonkJar");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);
        frame.setResizable(false);
        try {
            frame.setIconImage(ImageIO.read(MiscUtil.getResource("img/image.png")));
        } catch (IOException ignored) {
        }
        frame.setContentPane(new MainMenuPanel());
        frame.setVisible(true);
        frame.pack();
     
        MiscUtil.moveToCenter(frame);

        if (Main.hocuMuziku) {
            MusicUtil.playMusic();
        }

        new Thread(() -> {
            while (true) {
                if (frame.isFocused()) {
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