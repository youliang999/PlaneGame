package com.youliang.models;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by youliang.cheng on 2018/2/23.
 */
public class Choose extends JPanel {
    private String text;
    private String buttonStatus;
    private final String BUTTON_HOVER = "BUTTON_HOVER";
    private final String BUTTON_NORMAL = "BUTTON_NORMAL";
    private static final long serialVersionUID = 1L;
    private JLabel logoLabel;
    private JButton startGameButton;
    private JButton exitGameButton;
    private JButton top10ScoresButton;
    private JButton helpButton;

    public final static String START_GAME_BUTTON = "START_GAME_BUTTON";
    public final static String EXIT_GAME_BUTTON = "EXIT_GAME_BUTTON";
    public final static String TOP_10_SCORES_BUTTON = "TOP_10_SCORES_BUTTON";
    public final static String HELP_BUTTON = "HELP_BUTTON";

    public Choose(ShootGame mainFrame) {
        this.logoLabel = new JLabel();
        BufferedImage image = ShootGame.button;
        this.logoLabel.setIcon(new ImageIcon(ShootGame.icon));

        this.startGameButton = new JButton("New Game");
        this.startGameButton.addActionListener(mainFrame);
        this.startGameButton.setActionCommand(START_GAME_BUTTON);
    /*    this.startGameButton.setIcon(new ImageIcon(image));
        this.startGameButton.setSize(image.getWidth(), image.getHeight());
        this.startGameButton.setContentAreaFilled(true);*/
        this.startGameButton.setOpaque(false);

        this.top10ScoresButton = new JButton("Top 10 Scores");
        this.top10ScoresButton.addActionListener(mainFrame);
        this.top10ScoresButton.setActionCommand(TOP_10_SCORES_BUTTON);
/*        this.top10ScoresButton.setIcon(new ImageIcon(image));
        this.top10ScoresButton.setSize(image.getWidth(), image.getHeight());
        this.top10ScoresButton.setContentAreaFilled(true);//设置图片填满按钮所在的区域*/
        this.top10ScoresButton.setOpaque(false);

        this.helpButton = new JButton("Help");
        this.helpButton.addActionListener(mainFrame);
        this.helpButton.setActionCommand(HELP_BUTTON);
/*        this.helpButton.setIcon(new ImageIcon(image));
        this.helpButton.setSize(image.getWidth(), image.getHeight());
        this.helpButton.setContentAreaFilled(true);*/
        this.helpButton.setOpaque(false);

        this.exitGameButton = new JButton("Exit Game");
        this.exitGameButton.addActionListener(mainFrame);
        this.exitGameButton.setActionCommand(EXIT_GAME_BUTTON);
/*        this.exitGameButton.setIcon(new ImageIcon(image));
        this.exitGameButton.setSize(image.getWidth(), image.getHeight());
        this.exitGameButton.setContentAreaFilled(true);*/
        this.exitGameButton.setOpaque(false);

        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.add(logoLabel);

        GridLayout gridLayout = new GridLayout(4, 1, 0, 10);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(gridLayout);

        buttonPanel.add(startGameButton);
        buttonPanel.add(top10ScoresButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(exitGameButton);


        Dimension d = new Dimension(160, 248);
        buttonPanel.setSize(d);
        buttonPanel.setPreferredSize(d);

        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setVgap(25);
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(mainLayout);
        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        this.setOpaque(false);
        this.add(mainPanel);
    }
}
