package com.github.daniloistijanovic.bonk;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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

    public void center() {
        setLocation((Main.WINDOWSIZE.x - size.x) / 2, (Main.WINDOWSIZE.y - size.y) / 2);
    }

    public void draw(GraphicsContext gc) {
        Paint p = gc.getFill();
        gc.fillRect(location.x, location.y, size.x, size.y);
        gc.setFill(Color.BLACK);
        gc.fillRect(location.x + 2, location.y + 2, size.x - 4, size.y - 4);
        gc.setFill(p);
        gc.fillText(text, getCenter().x, getCenter().y);
    }

    public Point getCenter() {
        return new Point(location.x + size.x / 2, location.y + size.y / 2);
    }

    public Point getLocation() {
        return location;
    }

    public Point getSize() {
        return size;
    }

    //da li linija sece dugme (nedovrseno)
    public boolean isCuttingButton(Point p1, Point p2) {
        return false;
    }

    public boolean isInsideButton(Point p) {
        return isInsideButton(p.x, p.y);
    }

    //da li je tacka unutrasnjost dugmeta
    public boolean isInsideButton(int x, int y) {
        return x >= location.x && x <= location.x + size.x && y >= location.y && y <= location.y + size.y;
    }

    public void setLocation(int x, int y) {
        location = new Point(x, y);
    }

    public void setSize(int x, int y) {
        size = new Point(x, y);
    }

}
