package oop.bonk.io;

import oop.bonk.io.utils.MiscUtil;

import java.awt.*;

//i ovde ce mozda builder da ide
public class MyButton {

    public static final Point BUTTONSIZE = new Point(Main.WINDOWSIZE.x / 5, Main.WINDOWSIZE.y / 10);
    private Point location;
    private Point size;
    private String text;

    public MyButton(String text) {
        location = new Point(0, 0);
        size = BUTTONSIZE;
        this.text = text;
    }

    public MyButton(Point location, Point size) {
        this.location = location;
        this.size = size;
    }

    public MyButton(int locX, int locY, int sizeX, int sizeY) {
        this(new Point(locX, locY), new Point(sizeX, sizeY));
    }

    public boolean isInsideButton(Point p) {
        return isInsideButton(p.x, p.y);
    }

    //da li je tacka unutrasnjost dugmeta
    public boolean isInsideButton(int x, int y) {
        return x >= location.x && x <= location.x + size.x && y >= location.y && y <= location.y + size.y;
    }

    //da li linija sece dugme (nedovrseno)
    public boolean isCuttingButton(Point p1, Point p2) {
        return false;
    }

    public void center() {
        setLocation((Main.WINDOWSIZE.x - size.x) / 2, (Main.WINDOWSIZE.y - size.y) / 2);
    }

    public void setLocation(int x, int y) {
        location = new Point(x, y);
    }

    public void setSize(int x, int y) {
        size = new Point(x, y);
    }

    public Point getSize() {
        return size;
    }

    public Point getLocation() {
        return location;
    }

    public Point getCenter() {
        return new Point(location.x + size.x / 2, location.y + size.y / 2);
    }

    public Rectangle toRectangle() {
        return new Rectangle(location.x, location.y, size.x, size.y);
    }

    public void draw(Graphics g) {
        ((Graphics2D) g).draw(toRectangle());
        MiscUtil.drawStringCenter(g, text, getCenter().x, getCenter().y);
    }

}
