package player;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    public KeyHandler keyHandler;

    public GamePanel gamePanel;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValue();

        getPlayerImage();
    }

    public void setDefaultValue() {
        playerPositionX = 100;
        playerPositionY = 100;
        playerSpeed = 4;
    }

    public void getPlayerImage() {

        try {
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fighter1.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fighter2.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fighter3.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fighter1.png")));
        } catch (IOException e) {
            e.printStackTrace();
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

    public void draw(Graphics2D graphics2D) {

        BufferedImage image;

        if (Direction.NORTH.getValue()) {
            image = up;
        } else if (Direction.SOUTH.getValue()) {
            image = down;
        } else if (Direction.WEST.getValue()) {
            image = left;
        } else if (Direction.EAST.getValue()) {
            image = right;
        } else {
            image = up;
        }

        graphics2D.drawImage(image, playerPositionX, playerPositionY, gamePanel.tileSize, gamePanel.tileSize, null);

    }
}