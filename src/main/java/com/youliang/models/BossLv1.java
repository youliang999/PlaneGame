package com.youliang.models;

/**
 * Created by youliang.cheng on 2018/2/23.
 */
public class BossLv1 extends Flyer {
    private int speed = 1;
    private int score = 10;
    private int life = 100;

    public BossLv1() {
        image = ShootGame.bosslv1;
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

    public int getLife() {
        return life;
    }
}
