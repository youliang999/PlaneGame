package com.youliang.models;

/**
 * Created by youliang.cheng on 2018/2/23.
 */
public class Bullet extends Flyer {

    private int speed = 3;

    private int damage = 10;

    public Bullet(int x, int y) {
        image = ShootGame.bullet;
        width = image.getWidth();
        height = image.getHeight();
        this.x = x;
        this.y = y;
    }

    @Override
    public void step() {
        y -= speed;
    }

    @Override
    public boolean outOfBounds() {
        return (y+height) < 0;
    }
}
