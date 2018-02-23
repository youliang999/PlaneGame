package com.youliang.models;

import java.util.Random;

/**
 * Created by youliang.cheng on 2018/2/23.
 */
public class Bigplane extends Flyer {

    public static final int DOUBLE_FIRE = 0;
    public static final int FILE = 1;

    private int xspeed = 1;
    private int yspeed = 2;
    private int awardType;

    public Bigplane() {
        image = ShootGame.bigplane;
        width = image.getWidth();
        height = image.getHeight();
        y = -height;
        Random r = new Random();
        x = r.nextInt(ShootGame.WIDTH - width);
        awardType = r.nextInt(2);
    }

    @Override
    public void step() {
        x += xspeed;
        y += yspeed;
        if(x < 0 || x > ShootGame.WIDTH - width) {
            xspeed *= -1;
        }
    }

    @Override
    public boolean outOfBounds() {
        return y > ShootGame.HEIGHT;
    }

    public int getAwardType() {
        return awardType;
    }
}
