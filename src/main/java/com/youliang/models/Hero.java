package com.youliang.models;

import java.util.Random;

/**
 * Created by youliang.cheng on 2018/2/23.
 */
public class Hero extends Flyer{
    private int doubleFire;
    private int life;
    private int score;

    public Hero() {
        image = ShootGame.hero0;
        height = image.getHeight();
        width = image.getWidth();
        x = 127;
        y = 388;
        doubleFire = 0;
        life = 3;
        score = 0;
    }

    public void move(int x, int y) {
        this.x = x - width / 2;
        this.y = y - height / 2;
    }

    @Override
    public void step() {
        Random r = new Random();
        if(r.nextInt(2) == 0) {
            image = ShootGame.hero0;
        } else {
            image = ShootGame.hero1;
        }
    }

    @Override
    public boolean outOfBounds() {
        return false;
    }

    public void getScore_Award(Flyer f) {
        if(f instanceof Airplane) {
            score += ((Airplane)f).getScore();
        } else {
            if(((Bigplane)f).getAwardType() == Bigplane.DOUBLE_FIRE) {
                doubleFire += 20;
            } else {
                life += 1;
            }
        }
    }

    public  Bullet[] shoot() {
        Bullet[] bullets = null;
        if(doubleFire != 0) {
            bullets = new Bullet[2];
            Bullet b1 = new Bullet(x + width/4 - ShootGame.bullet.getWidth()/2, y + ShootGame.bullet.getWidth());
            Bullet b2 = new Bullet(x + width*3/4 - ShootGame.bullet.getWidth()/2, y + ShootGame.bullet.getWidth());
            bullets[0] = b1;
            bullets[1] = b2;
            doubleFire -= 1;
        } else {
            bullets = new Bullet[1];
            bullets[0] = new Bullet(x + width/2 - ShootGame.bullet.getWidth() / 2, y - ShootGame.bullet.getHeight());
        }
        return bullets;
    }

    public boolean hit(Flyer f) {
        boolean r = Flyer.boom(this, f);
        if(r) {
            life--;
            doubleFire = 0;
        }
        return r;
    }

    public int getLife() {
        return life;
    }

    public int getScore() {
        return score;
    }
}
