package com.youliang.models;

import java.util.Random;

/**
 * Created by youliang.cheng on 2018/2/23.
 */
public class Hero extends Flyer{
    private int doubleFire;
    private int life;
    private int score;
    private int speed = 5;

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
        if(x + width/2 <= 0) {
            x = width/2;
        }
        if(x + width / 2 >= ShootGame.WIDTH ) {
            x = ShootGame.WIDTH - width / 2;
        }
        if(y + height / 2 <= 0) {
            y = height/2;
        }
        if(y + width / 2 >= ShootGame.HEIGHT) {
            y = ShootGame.HEIGHT -height / 2;
        }
        //注释部分适用于鼠标控制
   /*     this.x = x - width / 2;
        this.y = y - height / 2;*/
        this.x = x;
        this.y = y;
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
        } else if(f instanceof Bee) {
            speed += ((Bee)f).getAwardSpeed();
            System.out.println("hero current speed: " + speed);
        } else if(f instanceof BossLv1) {
            score +=((BossLv1)f).getScore();
        } else if(f instanceof BossLv2) {
            score += ((BossLv2)f).getScore();
        } else {
            if(((Bigplane)f).getAwardType() == Bigplane.DOUBLE_FIRE) {
                doubleFire += 20;
            } else if(((Bigplane)f).getAwardType() == Bigplane.FILE){
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

    public int getSpeed() {
        return speed;
    }
}
