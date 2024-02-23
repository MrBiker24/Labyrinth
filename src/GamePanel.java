import javax.swing.*;
import javax.xml.datatype.Duration;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    static final int originalTileSize = 16;
    static final int scale = 3;
    static final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Player player;
    Thread game;

    int playerPositionX = 1;
    int playerPositionY = 1;
    int playerSpeed = 1;

    public GamePanel() {
        this.player = new Player(new CharacterBuilder());

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
        if (Direction.NORTH.getValue()) {
            playerPositionY -= playerSpeed;
        } else if (Direction.SOUTH.getValue()) {
            playerPositionY += playerSpeed;
        } else if (Direction.WEST.getValue()) {
            playerPositionX -= playerSpeed;
        } else if (Direction.EAST.getValue()) {
            playerPositionX += playerSpeed;
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.BLUE);
        graphics2D.fillRect(playerPositionX, playerPositionY, tileSize, tileSize);
        graphics2D.dispose();
    }
}
