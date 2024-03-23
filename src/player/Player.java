package player;

import Items.*;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    public KeyHandler keyHandler;

    public GamePanel gamePanel;

    public int hasKey = 1;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValue();

        getPlayerImage();
    }

    public void setDefaultValue() {
        playerPositionX = 0;
        playerPositionY = 0;
        playerSpeed = 2.0;
    }

    public void getPlayerImage() {

        try {
            stand = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/explorer.png")));
            run1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/explorer1.png")));
            run2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/explorer2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {

        collisionOn = false;
        collisionSlow = false;
        collisionExit = false;
        collisionDoor = false;

        gamePanel.collisionChecker.checkTile(this);

        final int itemIndex = gamePanel.collisionChecker.checkItem(this);
        pickUpObject(itemIndex);

        final int doorIndex = gamePanel.collisionChecker.checkDoor(this);
        openDoor(doorIndex);


        if (collisionSlow) {
            playerSpeed = 0.5;
        } else {
            playerSpeed = 2.0;
        }

        if (!collisionOn && !collisionDoor) {
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

    }

    public void pickUpObject(int itemIndex) {

        if (itemIndex != 999) {
            hasKey++;
            gamePanel.items[itemIndex] = null;
        }
    }

    private void openDoor(int doorIndex) {
        if (doorIndex != 999 && hasKey > 0) {
            hasKey--;

            final Item door = gamePanel.doors[doorIndex];
            this.collisionDoor = false;
            if (door instanceof Door) {
                gamePanel.doors[doorIndex] = new DoorOpen();
                gamePanel.doors[doorIndex].rectangleItem.x = door.rectangleItem.x;
                gamePanel.doors[doorIndex].rectangleItem.y = door.rectangleItem.y;
            } else if (door instanceof DoorRotated) {
                gamePanel.doors[doorIndex] = new DoorRotatedOpen();
                gamePanel.doors[doorIndex].rectangleItem.x = door.rectangleItem.x;
                gamePanel.doors[doorIndex].rectangleItem.y = door.rectangleItem.y;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {

        BufferedImage image;

        if (Direction.NORTH.getValue()) {
            if (runCount == 1) {
                runCount = 2;
            } else {
                runCount = 1;
            }
        } else if (Direction.SOUTH.getValue()) {
            if (runCount == 1) {
                runCount = 2;
            } else {
                runCount = 1;
            }
        } else if (Direction.WEST.getValue()) {
            if (runCount == 1) {
                runCount = 2;
            } else {
                runCount = 1;
            }
        } else if (Direction.EAST.getValue()) {
            if (runCount == 1) {
                runCount = 2;
            } else {
                runCount = 1;
            }
        }
        if (runCount == 1) {
            image = run1;
        } else if (runCount == 2) {
            image = run2;
        } else {
            image = stand;

        }


        //graphics2D.drawImage(image, (int) playerPositionX, (int) playerPositionY,  null);
        graphics2D.drawImage(image, (int) playerPositionX, (int) playerPositionY, rectanglePlayer.width, rectanglePlayer.height, null);

    }
}