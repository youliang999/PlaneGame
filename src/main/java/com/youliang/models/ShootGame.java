package com.youliang.models;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by youliang.cheng on 2018/2/23.
 */

public class ShootGame extends JPanel {
    private static final long serialVersionUID = 1L;
    //背景图片的大小320*568
    public static final int WIDTH = 320;
    public static final int HEIGHT = 568;
    //游戏界面固定大小336*607
    public static final int FRAME_WIDTH = 336;
    public static final int FRAME_HEIGHT = 607;

    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage airplane;  //敌机
    public static BufferedImage bigplane;  //大飞机
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage bullet;
    public static BufferedImage pause;
    public static BufferedImage gameover;

    static {
        try {

            background = ImageIO.read(new URL("file:///E:\\javastudy\\PlaneGame\\recources\\background.png"));
            start = ImageIO.read(new URL("file:///E:\\javastudy\\PlaneGame\\recources\\start.png"));
            airplane = ImageIO.read(new URL("file:///E:\\javastudy\\PlaneGame\\recources\\airplane.png"));
            bigplane = ImageIO.read(new URL("file:///E:\\javastudy\\PlaneGame\\recources\\bigplane.png"));
            hero0 = ImageIO.read(new URL("file:///E:\\javastudy\\PlaneGame\\recources\\hero0.png"));
            hero1 = ImageIO.read(new URL("file:///E:\\javastudy\\PlaneGame\\recources\\hero0.png"));
            bullet = ImageIO.read(new URL("file:///E:\\javastudy\\PlaneGame\\recources\\bullet.png"));
            pause = ImageIO.read(new URL("file:///E:\\javastudy\\PlaneGame\\recources\\pause.png"));
            gameover = ImageIO.read(new URL("file:///E:\\javastudy\\PlaneGame\\recources\\gameover.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int state = START;
    //定义游戏状态的备选项常量：
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;

    private Hero hero = new Hero();
    private Flyer[] flyers = {};
    private Bullet[] bullets = {};


    public static void main(String[] args) {
        JFrame planeFrame = new JFrame("ShootGame");
        planeFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        planeFrame.setAlwaysOnTop(true);
        planeFrame.setLocationRelativeTo(null);
        planeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ShootGame game = new ShootGame();
        planeFrame.add(game);
        planeFrame.setVisible(true);
        game.action();
    }

    public void action() {
        MouseAdapter l = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(state == PAUSE) {
                    state = RUNNING;
                }
            }

            /**
             * 鼠标滑出
             */
            @Override
            public void mouseExited(MouseEvent e) {
                if(state == RUNNING) {
                    state = PAUSE;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(state == START || state == PAUSE) {
                    state = RUNNING;
                } else if(state == RUNNING) {
                    state = PAUSE;
                } else if(state == GAME_OVER) {
                    state = START;
                    flyers = new Flyer[0];
                    bullets = new Bullet[0];
                    hero = new Hero();
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(state == RUNNING) {
                    int x = e.getX();
                    int y = e.getY();
                    hero.move(x, y);
                }
            }
        };
        this.addMouseMotionListener(l);
        this.addMouseListener(l);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            private int runTimes = 0;
            @Override
            public void run() {
                if(state == RUNNING) {
                    runTimes++;

                    if (runTimes % 50 == 0) {
                        nextOne();
                    }

                    for (int i = 0; i < flyers.length; i++) {
                        flyers[i].step();
                    }

                    if (runTimes % 30 == 0) {
                        shoot();
                    }

                    for (int i = 0; i < bullets.length; i++) {
                        bullets[i].step();
                    }

                    hero.step();

                    boom();

                    hit();

                    outOfBounds();
                }
                repaint();
            }
        }, 10, 10);
    }

    public void nextOne() {
        Random r = new Random();
        Flyer f = null;
        if(r.nextInt(20) == 0) {
            f = new Bigplane();
        } else {
            f = new Airplane();
        }
        flyers = Arrays.copyOf(flyers, flyers.length+1);
        flyers[flyers.length - 1] = f;
    }

    public void shoot() {
        Bullet[] newBullets = hero.shoot();
        bullets = Arrays.copyOf(bullets, bullets.length + newBullets.length);
        System.arraycopy(newBullets, 0, bullets, bullets.length - newBullets.length, newBullets.length);
    }

    public void boom() {
        for (int i=0; i<bullets.length; i++) {
            for (int j=0; j<flyers.length; j++) {
                if(Flyer.boom(bullets[i], flyers[j])) {
                    hero.getScore_Award(flyers[j]);
                    flyers[j] = flyers[flyers.length - 1];
                    flyers = Arrays.copyOf(flyers, flyers.length - 1);
                    bullets[i] = bullets[bullets.length - 1];
                    bullets = Arrays.copyOf(bullets, bullets.length - 1);
                    i--;
                    break;
                }
            }
        }
    }

    public void hit() {
        Flyer[] lives = new Flyer[flyers.length];
        int index = 0;
        for (int i = 0; i<flyers.length; i++) {
            if(!hero.hit(flyers[i])) {
                lives[index] = flyers[i];
                index++;
            }
        }

        if(hero.getLife() <= 0) {
            state = GAME_OVER;
        }

        flyers = Arrays.copyOf(lives, index);
    }

    public void outOfBounds() {
        Flyer[] Flives = new Flyer[flyers.length];
        int index = 0;
        for (int i=0; i < flyers.length; i++) {
            if(!flyers[i].outOfBounds()) {
                Flives[index] = flyers[i];
                index ++ ;
            }
        }
        flyers = Arrays.copyOf(Flives, index);

        Bullet[] Blives = new Bullet[bullets.length];
        index = 0;
        for (int i=0; i< bullets.length; i++) {
            if(!bullets[i].outOfBounds()) {
                Blives[index] = bullets[i];
                index ++ ;
            }
        }
        bullets = Arrays.copyOf(Blives, index);
    }

/*            //双缓冲技术解决屏幕闪烁
            private BufferedImage offScreenImage = null;   //利用双缓冲技术消除闪烁
    @Override
   public void update(Graphics g) {
           if (offScreenImage == null)
                    offScreenImage =(BufferedImage) this.createImage(WIDTH, HEIGHT);

           Graphics gOff = offScreenImage.getGraphics();

            paint(gOff);
             g.drawImage(offScreenImage, 0, 0, null);
        }*/

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);

        paintHero(g);

        paintFlyers(g);

        paintBullets(g);

        paintScore_Life(g);

        if(state == START) {
            g.drawImage(start, 0, 0, null);
        } else if(state == PAUSE) {
            g.drawImage(pause, 0, 0, null);
        } else if(state == GAME_OVER) {
            g.drawImage(gameover, 0, 0, null);
        }
    }

    public void paintHero(Graphics g) {
        g.drawImage(hero.image, hero.x, hero.y, null);
    }

    public void paintFlyers(Graphics g) {
        for(int i=0; i<flyers.length; i++) {
            g.drawImage(flyers[i].image, flyers[i].x, flyers[i].y, null);
        }
    }

    public void paintBullets(Graphics g) {
        for (int i=0; i<bullets.length; i++) {
            g.drawImage(bullets[i].image, bullets[i].x, bullets[i].y, null);
        }
    }

    public void paintScore_Life(Graphics g) {
        int x = 10;
        int y = 15;
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g.setFont(font);
        g.drawString("SCORE: " + hero.getScore(), x, y);
        g.drawString("LIFE: " + hero.getLife(), x, y+20);

    }
}
