package com.example.jasper.virgielowee2016;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Jasper on 26-6-2016.
 */
public class Pint {

    public static final int PINT_WIDTH = 100;
    public static final int PINT_HEIGHT = 100;

    private int x;
    private int y;
    private int speed;
    private Drawable img;

    public Pint(int x, int y, int speed, Drawable img) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.img = img;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void draw(Canvas canvas) {
        img.setBounds(x, y, x + PINT_WIDTH, y + PINT_HEIGHT);
        img.draw(canvas);

        y += speed;

    }

}
