package com.youliang.models;

/**
 * Created by youliang.cheng on 2018/2/23.
 */
public class BossLv2 extends Flyer {
    private int speed = 1;
    private int score = 20;
    private int life = 500;

    public BossLv2() {
        image = ShootGame.bosslv2;
        width = image.getWidth();
        height = image.getHeight();
        y = -height;
        x = ShootGame.WIDTH / 2;
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
