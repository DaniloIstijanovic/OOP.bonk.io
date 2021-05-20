package oop.bonk.io;

import oop.bonk.io.login.LoginPage;
import oop.bonk.io.login.Passwords;
import oop.bonk.io.utils.DebugUtil;
import oop.bonk.io.utils.MiscUtil;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

/*
 * ovde se treba odluciti da li pokrenuti firstrun dijalog
 */
public class Main {

    public static final boolean hocuMuziku = true;
    public static final Point[] COMMONSIZES = {
            //4:3
            new Point(640, 480),
            new Point(800, 600),
            new Point(960, 720),
            new Point(1024, 768),
            new Point(1280, 960),
            //16:9
            new Point(640, 360),
            new Point(854, 480),
            new Point(960, 540),
            new Point(1280, 720),
            new Point(1920, 1080),
    };
    public static Point WINDOWSIZE = COMMONSIZES[1];
    public static Font neoSansFont;

    /*
     * this.addMouseListener(new MouseInput());  ----- Input Misa za Button
     */

    // ovo je u potpunosti staticka klasa
    private Main() {
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("sun.java2d.opengl", "true");
        neoSansFont = Font.createFont(Font.TRUETYPE_FONT, MiscUtil.getResource("fonts/neo-sans-bold.otf"));
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
        if(args.length > 1) {
            WINDOWSIZE = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
    }

}