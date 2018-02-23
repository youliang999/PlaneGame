package com.youliang.models;

import java.awt.image.BufferedImage;

/**
 * Created by youliang.cheng on 2018/2/23.
 */
public abstract class Flyer {
    protected BufferedImage image = null;
    protected  int x;
    protected  int y;
    protected  int height;
    protected  int width;

    public abstract void step();

    public static boolean boom(Flyer f1, Flyer f2) {
        int f1x = f1.x + f1.width / 2;
        int f1y = f1.y + f1.height / 2;
        int f2x = f2.x + f2.width / 2;
        int f2y = f2.y + f2.height / 2;
        boolean H = Math.abs(f1x - f2x) < (f1.width + f2.width) / 2;
        boolean V = Math.abs(f1y - f2y) < (f1.height + f2.height) / 2;
        return H&V;
    }

    public abstract boolean outOfBounds();
}
