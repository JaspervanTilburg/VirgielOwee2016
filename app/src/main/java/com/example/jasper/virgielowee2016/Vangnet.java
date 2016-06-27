package com.example.jasper.virgielowee2016;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Jasper on 26-6-2016.
 */
public class Vangnet {

    public static final int IMG_WIDTH = 300;
    public static final int IMG_HEIGHT = 50;

    private int x;
    private Drawable img;

    public Vangnet(int x, Drawable img) {
        this.x = x;
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void draw(Canvas canvas) {
        img.setBounds(x, canvas.getHeight() - IMG_HEIGHT, x + IMG_WIDTH, canvas.getHeight());
        img.draw(canvas);
    }
}
