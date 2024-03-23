package main;

import javax.swing.*;

import static main.Main.GAME_PANEL;


public class StartPanel extends JPanel {

    private int scale = 2;

    private final GamePanel gamePanel;
    private final JPanel mainPanel;
    private final JFrame frame;

    public StartPanel(GamePanel gamePanel, JPanel mainPanel, JFrame frame) {

        this.gamePanel = gamePanel;
        this.mainPanel = mainPanel;
        this.frame = frame;
        initialize();

    }

    private void initialize() {
        this.setSize(500, 500);

        JButton grafic1 = new JButton("640 * 400");
        grafic1.addActionListener(e -> {
            this.scale = 1;
        });
        this.add(grafic1);

        JButton grafic2 = new JButton("1280 * 800");
        grafic2.addActionListener(e -> {
            this.scale = 2;
        });
        this.add(grafic2);

        JButton grafic3 = new JButton("1920 * 1200");
        grafic3.addActionListener(e -> {
            this.scale = 3;
        });
        this.add(grafic3);

        JButton start = new JButton("Start");
        start.addActionListener(e -> {
            startGame();
        });
        this.add(start);

    }

    public void startGame() {
        Main.cardLayout.show(mainPanel, GAME_PANEL);

        gamePanel.setupGame(mainPanel,scale);

        frame.setSize((gamePanel.screenWidth), (gamePanel.screenHeight));
        frame.setResizable(false);

        gamePanel.startGame();
    }


}
