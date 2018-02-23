package com.youliang.models;

import java.util.Random;

/**
 * Created by youliang.cheng on 2018/2/23.
 */
public class Airplane extends Flyer{
    private int speed = 2;
    private int score = 5;

    public Airplane() {
        image = ShootGame.airplane;
        width = image.getWidth();
        height = image.getHeight();
        y = -height;
        Random r = new Random();
        x = r.nextInt(ShootGame.WIDTH - width);
    }
    @Override
    public void step() {
        y += speed;
    }

    @Override
    public boolean outOfBounds() {
        return y > ShootGame.HEIGHT;
    }

    public int getScore() {
        return score;
    }
}
