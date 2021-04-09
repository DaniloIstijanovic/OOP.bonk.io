package oop.bonk.io;

import java.io.FileWriter;
import java.io.IOException;

import oop.bonk.io.login.LoginPage;
import oop.bonk.io.login.Passwords;
import oop.bonk.io.renderers.MainMenuRenderer;
import oop.bonk.io.utils.DebugUtil;

/*
 * ovde se treba odluciti da li pokrenuti firstrun dijalog
 */
public class Main {

    public static final boolean hocuMuziku = true;

    public static final int xSize = 1280;
    public static final int ySize = 720;
    /*
     * this.addMouseListener(new MouseInput());  ----- Input Misa za Button
     */


    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        if (DebugUtil.debugMethod == DebugUtil.DebugMethod.FILE) {
            try {
                DebugUtil.fw = new FileWriter("data/txt/debug.txt");
            } catch (IOException e) {
                DebugUtil.debugMethod = DebugUtil.DebugMethod.CONSOLE;
                e.printStackTrace();
                DebugUtil.debug(DebugUtil.DebugReason.FILE, "Nece da radi debug u file ");
            }
        }
        // privremeni login sistem, problem je sto ce i igra i login da se pokrenu u isto vreme za sad
        Passwords idPasswords = new Passwords();
        LoginPage loginpage = new LoginPage(idPasswords.getlogininfo());
        // root treba da pozove login u buducnosti
        //new Root(true);
    }

    // ovo je u potpunosti staticka klasa
    private Main() {
    }

}