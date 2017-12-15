package com.ad.firstkotlinapp.test;

public class Rect {
    private int a;
    private int b;

    public Rect() {
        a = 1;
        b = 2;
    }

    public void setA(int a) {
        this.a = Math.abs(a);
    }

    public int getA() {
        return this.a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b =  Math.abs(b);
    }
}