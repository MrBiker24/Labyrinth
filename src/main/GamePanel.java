package main;

import Items.Item;
import Items.ItemSetter;
import enviroment.EnviromentManager;
import player.KeyHandler;
import player.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    static final int originalTileSize = 16;
    static final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 25;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public Player player;
    Thread game;
    KeyHandler keyHandler = new KeyHandler();
    public Item items[] = new Item[7];
    public Item doors[] = new Item[50];

    public ItemSetter itemSetter;
    public CollisionChecker collisionChecker;

    public TileManager tileManager;

    EnviromentManager enviromentManager;

    public GamePanel() {
        this.player = new Player(this, keyHandler);
        this.tileManager = new TileManager(this);
        this.collisionChecker = new CollisionChecker(this);
        this.itemSetter = new ItemSetter(this);
        this.enviromentManager = new EnviromentManager(this);

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

        for (Item item : this.items) {
            if (item != null) {
                item.draw(graphics2D, this, false);
            }
        }

        for (Item door : this.doors) {
            if (door != null) {
                door.draw(graphics2D, this, true);
            }
        }

        player.draw(graphics2D);

        enviromentManager.draw(graphics2D);

        graphics2D.dispose();
    }

    public void setupGame() {
        itemSetter.populateItems();
        itemSetter.populateDoors();
        enviromentManager.setup();
    }
}
