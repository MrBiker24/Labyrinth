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
    public int scale = 2;
    public int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 25;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

    public Player player;
    private Thread game;
    public Item[] items = new Item[7];
    public Item[] doors = new Item[50];

    private final KeyHandler keyHandler = new KeyHandler();
    public ItemSetter itemSetter;
    public CollisionChecker collisionChecker;

    public TileManager tileManager;

    public EnviromentManager enviromentManager;

    private int mapCounter = 1;

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
                Thread.sleep((1000 / (30 * scale)));
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

    public void setupGame(JPanel mainPanel, int scale) {
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
        this.requestFocus();

        this.scale = scale;
        player.rectanglePlayer.height = 12 * this.scale;
        player.rectanglePlayer.width = 10 * this.scale;

        tileSize = originalTileSize * scale;
        screenWidth = tileSize * maxScreenCol;
        screenHeight = tileSize * maxScreenRow;

        itemSetter.populateItems();
        itemSetter.populateDoors();
        enviromentManager.setup();
    }

    public void restart() {
        this.player.setDefaultValue();

        this.tileManager.loadMap(tileManager.loadMapByNumber(mapCounter));

        itemSetter.populateItems();
        itemSetter.populateDoors();
        enviromentManager.setup();

        mapCounter++;
    }
}
