package oop.bonk.io;

import oop.bonk.io.login.LoginPage;
import oop.bonk.io.login.Passwords;
import oop.bonk.io.utils.DebugUtil;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * ovde se treba odluciti da li pokrenuti firstrun dijalog
 */
public class Main {

    public static final boolean hocuMuziku = true;
    public static final Point[] COMMONSIZES = {
            new Point(600, 400),
            new Point(854, 480),
            new Point(1280, 720),
            new Point(1920, 1080),
    };
    public static final Point WINDOWSIZE = COMMONSIZES[1];
    public static Font neoSansFont;

    /*
     * this.addMouseListener(new MouseInput());  ----- Input Misa za Button
     */

    // ovo je u potpunosti staticka klasa
    private Main() {
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("sun.java2d.opengl", "true");

        neoSansFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/neo-sans-bold.otf"));
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(neoSansFont);

        if (DebugUtil.debugMethod == DebugUtil.DebugMethod.FILE) {
            try {
                DebugUtil.fw = new FileWriter("data/txt/debug.txt");
            } catch (IOException e) {
                DebugUtil.debugMethod = DebugUtil.DebugMethod.CONSOLE;
                e.printStackTrace();
                DebugUtil.debug(DebugUtil.DebugReason.FILE, "Nece da radi debug u file ");
            }
        }
        Passwords idPasswords = new Passwords();
        new LoginPage(idPasswords.getlogininfo());
    }

}