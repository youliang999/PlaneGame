package com.youliang.models;

import java.util.Random;

/**
 * Created by youliang.cheng on 2018/2/23.
 */
public class Bee extends Flyer {
    private int xspeed = 2;
    private int yspeed = 4;
    private int awardType = 3;
    private int awardSpeed = 5;

    public Bee() {
        image = ShootGame.bee;
        width = image.getWidth();
        height = image.getHeight();
        y = -height;
        Random r = new Random();
        x = r.nextInt(ShootGame.WIDTH - width);
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

    public int getAwardSpeed() {
        return awardSpeed;
    }
}
