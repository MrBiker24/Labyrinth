package gui;

import Items.Item;
import Items.ItemSetter;
import collision.CollisionChecker;
import enviroment.EnviromentManager;
import player.KeyHandler;
import player.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int originalTileSize = 16;
    public static int scale = 2;
    public static int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 25;
    private final KeyHandler keyHandler = new KeyHandler();
    private final UI ui;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;
    public Player player;
    public ItemSetter itemSetter;
    public CollisionChecker collisionChecker;
    public TileManager tileManager;
    public EnviromentManager enviromentManager;

    public Item[] items = new Item[7];
    public Item[] doors = new Item[50];
    public int mapCounter = 1;
    public boolean end = false;
    private Thread game;

    public GamePanel() {
        ui = new UI(this);
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
                if (scale == 3) {
                    Thread.sleep((1000 / (25L * scale)));
                } else {
                    Thread.sleep((1000 / (30L * scale)));
                }

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
                item.draw(graphics2D, false);
            }
        }

        for (Item door : this.doors) {
            if (door != null) {
                door.draw(graphics2D, true);
            }
        }

        player.draw(graphics2D);

        enviromentManager.draw(graphics2D);

        ui.draw(graphics2D);

        graphics2D.dispose();
    }

    public void setupGame(int scale) {
        this.player = new Player(this, keyHandler);
        this.tileManager = new TileManager(this);
        this.collisionChecker = new CollisionChecker(this);
        this.itemSetter = new ItemSetter(this);
        this.enviromentManager = new EnviromentManager(this);

        GamePanel.scale = scale;
        setPlayerandGamneSize();

        this.setPreferredSize(new Dimension((int) screenWidth, (int) screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(player.keyHandler);
        this.setFocusable(true);
        this.requestFocus();

        itemSetter.populateItems();
        itemSetter.populateDoors();
        enviromentManager.setup();
    }

    private void setPlayerandGamneSize() {
        if (scale == 999) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            screenWidth = screenSize.width;
            screenHeight = screenSize.height;

            tileSize = ((screenWidth / maxScreenCol) + (screenHeight / maxScreenRow)) / 2;

            scale = tileSize / originalTileSize;

            player.rectanglePlayer.height = (int) (12 * GamePanel.scale);
            player.rectanglePlayer.width = (int) (10 * GamePanel.scale);


        } else {
            player.rectanglePlayer.height = (int) (12 * GamePanel.scale);
            player.rectanglePlayer.width = (int) (10 * GamePanel.scale);

            tileSize = originalTileSize * scale;

            screenWidth = (tileSize * maxScreenCol);
            screenHeight = (tileSize * maxScreenRow);
        }
    }

    public void restart() {
        this.player.setDefaultValue();



        if (mapCounter < 3) {
            this.tileManager.loadMap(tileManager.loadMapByNumber(mapCounter));

            itemSetter.populateItems();
            itemSetter.populateDoors();
            enviromentManager.setup();
        }
        mapCounter++;
    }
}
