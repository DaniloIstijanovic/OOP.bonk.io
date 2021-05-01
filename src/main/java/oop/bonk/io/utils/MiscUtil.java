package oop.bonk.io.utils;

import oop.bonk.io.Main;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class MiscUtil {

    private MiscUtil() {
    }

    public static int trueModulo(int n, int m) {
        return (n % m + m) % m;
    }

    public static int mutate(int n, int d) {
        return n + (int) (Math.random() * (d * 2 + 1)) - d;
    }

    public static int mutateMore(int n, int d) {
        return n + (int) (Math.random() * (d + 1));
    }

    public static int mutateLess(int n, int d) {
        return n - (int) (Math.random() * (d + 1));
    }

    public static Point randomAdjacent(Point p) {
        switch ((int) (Math.random() * 4)) {
            case 0:
                return new Point(p.x, p.y - 1);
            case 1:
                return new Point(p.x - 1, p.y);
            case 2:
                return new Point(p.x + 1, p.y);
            case 3:
                return new Point(p.x, p.y + 1);
            default:
                throw new InternalError();
        }
    }

    public static Point withinWindow(Point p) {
        return new Point(trueModulo(p.x, Main.WINDOWSIZE.x), trueModulo(p.y, Main.WINDOWSIZE.y));
    }

    public static void drawStringCenter(Graphics g, String text, int x, int y) {
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(text, x - metrics.stringWidth(text) / 2, y - metrics.getHeight() / 2 + metrics.getAscent());
    }

    public static InputStream getResource(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    public static void moveToCenter(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
