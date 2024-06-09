package player;

import Items.*;
import gui.GamePanel;
import tools.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

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
        playerSpeed = 4;
    }

    public void getPlayerImage() {
        stand = ImageUtils.loadImage("/player/explorer.png");
        run1 = ImageUtils.loadImage("/player/explorer1.png");
        run2 = ImageUtils.loadImage("/player/explorer2.png");
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
            playerSpeed = 1;
        } else {
            playerSpeed = 4;
        }

        if (KeyEnum.ENTER.getValue()) {
            if (gamePanel.closeGame) {
                System.exit(0);
            } else {
                gamePanel.end = false;
            }

        }
        if (!collisionOn && !collisionDoor && !gamePanel.end) {
            if (KeyEnum.NORTH.getValue()) {
                playerPositionY -= playerSpeed;
            } else if (KeyEnum.SOUTH.getValue()) {
                playerPositionY += playerSpeed;
            } else if (KeyEnum.WEST.getValue()) {
                playerPositionX -= playerSpeed;
            } else if (KeyEnum.EAST.getValue()) {
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

        if (KeyEnum.NORTH.getValue() || KeyEnum.SOUTH.getValue() || KeyEnum.WEST.getValue() || KeyEnum.EAST.getValue()) {
            runCount = (runCount == 1) ? 2 : 1;
        } else {
            runCount = 0;
        }

        image = switch (runCount) {
            case 1 -> run1;
            case 2 -> run2;
            default -> stand;
        };

        graphics2D.drawImage(image, (int) playerPositionX, (int) playerPositionY, rectanglePlayer.width, rectanglePlayer.height, null);

    }
}