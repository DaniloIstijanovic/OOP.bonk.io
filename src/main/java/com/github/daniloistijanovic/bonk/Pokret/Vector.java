package com.github.daniloistijanovic.bonk;

public class Vector {
    
    public double x;
    public double y;

    public Vector(){
        this.set(0, 0);
    }

    public Vector(double x, double y){
        this.set(x,y);
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void add(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }

    public void pomnozi(double m){
        this.x *= m;
        this.y *= m;
    }

    public double getLength(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void setLength(double L){
        double trenutnoL = this.getLength();
        if(trenutnoL == 0){
            this.set(L, 0);
        }
        else{
        this.pomnozi(1/trenutnoL);
        this.pomnozi(L);
        }
    }

    public double getUgao(){
        return Math.toDegrees(Math.atan2(this.y, this.x));
    }

    public void setUgao(double Ugao){
        double L = this.getLength();
        double radian = Math.toRadians(Ugao);
        this.x = L * Math.cos(radian);
        this.y = L * Math.sin(radian);
    }
}
