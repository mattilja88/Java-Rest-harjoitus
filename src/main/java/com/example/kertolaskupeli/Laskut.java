package com.example.kertolaskupeli;

public class Laskut {
    private int x;
    private int y;
    
    public Laskut(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getLasku(){
        return this.x + " * " + this.y + " = ";
    }

    public String getVastaus(){
        Integer lasku = this.x*this.y;
        String vastaus = lasku.toString();
        return vastaus;
    }

    public String getOikeatLaskus(){
        Integer lasku = this.x*this.y;
        return this.x + " * " + this.y + " = " + lasku;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
