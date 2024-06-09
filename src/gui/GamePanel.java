package gui;

import Items.Item;
import Items.ItemSetter;
import collision.CollisionChecker;
import enviroment.EnviromentManager;
import player.KeyHandler;
import player.Player;
import tile.TileManager;
import tools.FolderContent;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private static final int originalTileSize = 16;
    public static int scale = 2;
    public static int tileSize = originalTileSize * scale;
    public final int maxScreenRow = 40;
    public final int maxScreenCol = 25;
    private final KeyHandler keyHandler = new KeyHandler();
    private final GameScreens gameScreens;
    public int screenWidth = tileSize * maxScreenRow;
    public int screenHeight = tileSize * maxScreenCol;
    public Player player;
    private ItemSetter itemSetter;
    public CollisionChecker collisionChecker;
    public TileManager tileManager;
    private EnviromentManager enviromentManager;

    public Item[] items = new Item[10];
    public Item[] doors = new Item[10];
    public int mapCounter = 1;
    public int maxMapCounter = FolderContent.countFolderContents();
    public boolean end = false;
    public boolean closeGame = false;

    public GamePanel() {
        gameScreens = new GameScreens(this);
    }

    @Override
    public void run() {
        int delay = (1000 / (15 * scale));

        if (scale == 3) {
            delay = (1000 / (25 * scale));
        }

        Timer timer = new Timer(delay, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        final Graphics2D graphics2D = (Graphics2D) graphics;

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

        gameScreens.draw(graphics2D);

        graphics2D.dispose();
    }

    public void setupGame(int scale) {
        this.player = new Player(this, keyHandler);
        this.tileManager = new TileManager(this);
        this.collisionChecker = new CollisionChecker(this);
        this.itemSetter = new ItemSetter(this);
        this.enviromentManager = new EnviromentManager(this);

        GamePanel.scale = scale;
        setPlayerandGameSize();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(player.keyHandler);
        this.setFocusable(true);
        this.requestFocus();

        itemSetter.populateItems();
        itemSetter.populateDoors();
        enviromentManager.setup();
    }

    private void setPlayerandGameSize() {
        if (scale == 999) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            screenWidth = screenSize.width;
            screenHeight = screenSize.height;

            tileSize = ((screenWidth / maxScreenRow) + (screenHeight / maxScreenCol)) / 2;

            scale = tileSize / originalTileSize;

            player.rectanglePlayer.height = (12 * GamePanel.scale);
            player.rectanglePlayer.width = (10 * GamePanel.scale);


        } else {
            player.rectanglePlayer.height = (12 * GamePanel.scale);
            player.rectanglePlayer.width = (10 * GamePanel.scale);

            tileSize = originalTileSize * scale;

            screenWidth = (tileSize * maxScreenRow);
            screenHeight = (tileSize * maxScreenCol);
        }
    }

    public void restart() {
        this.player.setDefaultValue();

        if (mapCounter < maxMapCounter) {
            this.tileManager.loadMap(tileManager.loadMapByNumber(mapCounter));

            itemSetter.populateItems();
            itemSetter.populateDoors();
            enviromentManager.setup();
        }
        mapCounter++;
    }
}
