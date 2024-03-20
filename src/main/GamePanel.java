package main;

import player.KeyHandler;
import player.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    static final int originalTileSize = 16;
    static final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Player player;
    Thread game;
    KeyHandler keyHandler = new KeyHandler();

    TileManager tileManager;

    public GamePanel() {
        this.player = new Player(this, keyHandler);
        this.tileManager = new TileManager(this);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(player.keyHandler);
        this.setFocusable(true);
    }

    public void startGame() {
        game = new Thread(this);
        game.start();
    }


    @Override
    public void run() {

        while (this.game != null) {

            update();

            repaint();

            try {
                Thread.sleep((1000 / 60));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        tileManager.draw(graphics2D);

        player.draw(graphics2D);

        graphics2D.dispose();
    }
}
