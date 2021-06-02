package com.github.daniloistijanovic.bonk.multiplayer;

import javafx.scene.paint.Color;

public class Circle extends Shape {

    private final int radius;
    private double xVel;
    private double yVel;

    public Circle(int radius, Color color, double bounciness, double x, double y) {
        super(color, bounciness, x, y);
        this.radius = radius;
    }

    public void addXVel(double d) {
        xVel += d;
    }

    public void addYVel(double d) {
        yVel += d;
    }

    public int getRadius() {
        return radius;
    }

    public double getRealX() {
        return getX() + getRadius();
    }

    public double getRealY() {
        return getY() + getRadius();
    }

    public double getXVel() {
        return xVel;
    }

    public void setXVel(double xVel) {
        this.xVel = xVel;
    }

    public double getYVel() {
        return yVel;
    }

    public void setYVel(double yVel) {
        this.yVel = yVel;
    }

    @Override
    public boolean isCollide(Shape shape) {
        if (shape instanceof Circle) {
            Circle c2 = (Circle) shape;
            double x1 = getRealX();
            double y1 = getRealY();
            double x2 = c2.getRealX();
            double y2 = c2.getRealY();
            int r1 = getRadius();
            int r2 = c2.getRadius();

            double distance = Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
            return distance < r1 + r2;
        } else if (shape instanceof Rectangle) {
            Rectangle p = (Rectangle) shape;
            double x = p.getX();
            double y = p.getY();
            double w = p.getWidth();
            double h = p.getHeight();

            double closestX = getRealX() < x ? x : getRealX() > x + w ? x + w : getRealX();
            double closestY = getRealY() < y ? y : getRealY() > y + h ? y + h : getRealY();

            double distanceX = getRealX() - closestX;
            double distanceY = getRealY() - closestY;

            return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) < Math.pow(getRadius(), 2);
        }
        return false;
    }

    @Override
    public void onCollide(Shape shape) {
        if (shape instanceof Circle) {
            Circle c2 = (Circle) shape;
            double sbx1 = -getXVel();
            double sby1 = -getYVel();
            double sbx2 = -c2.getXVel();
            double sby2 = -c2.getYVel();

            while (isCollide(c2)) {
                move(sbx1, sby1);
                c2.move(sbx2, sby2);
            }

            double temp;
            temp = getXVel();
            setXVel(-c2.getXVel());
            c2.setXVel(temp);

            temp = getYVel();
            setXVel(-c2.getYVel());
            c2.setYVel(temp);

        } else if (shape instanceof Rectangle) {
            Rectangle p = (Rectangle) shape;
            double x = p.getX();
            double y = p.getY();
            double w = p.getWidth();
            double h = p.getHeight();

            double cx = getRealX();
            double cy = getRealY();

            //levo ili desno
            if (cx < x || cx > x + w) {
                setXVel(-getXVel());
            }
            //gore ili dole
            if (cy < y || cy > y + h) {
                setYVel(-getYVel());
            }
        }
    }

    public double makeVector() {
        return Math.sqrt(Math.pow(getXVel(), 2) + Math.pow(getYVel(), 2));
    }

}
