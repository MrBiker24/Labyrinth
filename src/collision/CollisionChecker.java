package collision;

import Items.Door;
import Items.DoorRotated;
import Items.Item;
import gui.GamePanel;
import player.Entity;
import player.KeyEnum;

import java.awt.*;

public class CollisionChecker {

    private final GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        if ((KeyEnum.NORTH.getValue() && entity.playerPositionY <= 0) || (KeyEnum.WEST.getValue() && entity.playerPositionX <= 0) || (KeyEnum.SOUTH.getValue() && (entity.playerPositionY + entity.rectanglePlayer.height + entity.playerSpeed) >= gamePanel.screenHeight) || (KeyEnum.EAST.getValue() && (entity.playerPositionX + entity.rectanglePlayer.width + entity.playerSpeed) >= gamePanel.screenWidth)) {
            entity.collisionOn = true;
            return;
        }

        final double playerHeadXLeft = entity.playerPositionX;
        final double playerHeadXRight = entity.playerPositionX + entity.rectanglePlayer.width;
        final double playerHeadY = entity.playerPositionY;

        final double playerFeedXLeft = entity.playerPositionX;
        final double playerFeedXRight = entity.playerPositionX + entity.rectanglePlayer.width;
        final double playerFeedY = entity.playerPositionY + entity.rectanglePlayer.height;

        final double playerLeftArmX = entity.playerPositionX;
        final double playerLeftArmYTop = entity.playerPositionY;
        final double playerLeftArmYBottom = entity.playerPositionY + entity.rectanglePlayer.height;

        final double playerRightArmX = entity.playerPositionX + entity.rectanglePlayer.width;
        final double playerRightArmYTop = entity.playerPositionY;
        final double playerRightArmYBottom = entity.playerPositionY + entity.rectanglePlayer.height;


        if (KeyEnum.NORTH.getValue()) {
            int row = (int) ((playerHeadY - entity.playerSpeed) / GamePanel.tileSize);
            int colLeft = (int) ((playerHeadXLeft + 1) / GamePanel.tileSize);
            int colRight = (int) (playerHeadXRight - 1) / GamePanel.tileSize;
            collisionSetter(entity, row, row, colLeft, colRight);
        } else if (KeyEnum.SOUTH.getValue()) {
            int row = (int) ((playerFeedY + entity.playerSpeed) / GamePanel.tileSize);
            int colLeft = (int) (playerFeedXLeft + 1) / GamePanel.tileSize;
            int colRight = (int) (playerFeedXRight - 1) / GamePanel.tileSize;
            collisionSetter(entity, row, row, colLeft, colRight);
        } else if (KeyEnum.WEST.getValue()) {
            int col = (int) ((playerLeftArmX - entity.playerSpeed) / GamePanel.tileSize);
            int rowTop = (int) (playerLeftArmYTop + 1) / GamePanel.tileSize;
            int rowBottom = (int) (playerLeftArmYBottom - 1) / GamePanel.tileSize;
            collisionSetter(entity, rowTop, rowBottom, col, col);
        } else if (KeyEnum.EAST.getValue()) {
            int col = (int) ((playerRightArmX + entity.playerSpeed) / GamePanel.tileSize);
            int rowTop = (int) (playerRightArmYTop + 1) / GamePanel.tileSize;
            int rowBottom = (int) (playerRightArmYBottom - 1) / GamePanel.tileSize;
            collisionSetter(entity, rowTop, rowBottom, col, col);
        }

    }

    private void collisionSetter(Entity entity, int rowTop, int rowBottom, int colLeft, int colRight) {
        final int tileNum1 = gamePanel.tileManager.mapTileNum[colLeft][rowTop];
        final int tileNum2 = gamePanel.tileManager.mapTileNum[colRight][rowBottom];
        if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
            entity.collisionOn = true;
        } else if (gamePanel.tileManager.tile[tileNum1].slowCollision || gamePanel.tileManager.tile[tileNum2].slowCollision) {
            entity.collisionSlow = true;
        } else if (gamePanel.tileManager.tile[tileNum1].exitCollision || gamePanel.tileManager.tile[tileNum2].exitCollision) {
            entity.collisionExit = true;
            gamePanel.end = true;
            gamePanel.restart();
        }
    }

    public int checkItem(Entity entity) {

        int index = 0;
        for (Item item : gamePanel.items) {
            if (item != null) {

                final Rectangle rectanglePlayer = new Rectangle(entity.rectanglePlayer);
                rectanglePlayer.x = (int) (entity.playerPositionX + entity.rectanglePlayer.width);
                rectanglePlayer.y = (int) (entity.playerPositionY + entity.rectanglePlayer.height);

                final Rectangle rectangleItem = new Rectangle(item.rectangleItem);
                rectangleItem.x = item.rectangleItem.x + item.rectangleItem.height;
                rectangleItem.y = item.rectangleItem.y + item.rectangleItem.width;

                if (KeyEnum.NORTH.getValue()) {
                    rectanglePlayer.y -= entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleItem)) {
                        entity.collisionKey = true;
                        return index;
                    }
                } else if (KeyEnum.SOUTH.getValue()) {
                    rectanglePlayer.y += entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleItem)) {
                        entity.collisionKey = true;
                        return index;
                    }
                } else if (KeyEnum.WEST.getValue()) {
                    rectanglePlayer.x -= entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleItem)) {
                        entity.collisionKey = true;
                        return index;
                    }
                } else if (KeyEnum.EAST.getValue()) {
                    rectanglePlayer.x += entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleItem)) {
                        entity.collisionKey = true;
                        return index;
                    }
                }
            }
            index++;
        }
        return 999;
    }

    public int checkDoor(Entity entity) {

        int index = 0;
        for (Item door : gamePanel.doors) {
            if (door instanceof Door || door instanceof DoorRotated) {

                Rectangle rectanglePlayer = new Rectangle(entity.rectanglePlayer);
                rectanglePlayer.x = (int) (entity.playerPositionX + entity.rectanglePlayer.width);
                rectanglePlayer.y = (int) (entity.playerPositionY + entity.rectanglePlayer.height);

                Rectangle rectangleDoor = new Rectangle(door.rectangleItem);
                rectangleDoor.x = door.rectangleItem.x + door.rectangleItem.height;
                rectangleDoor.y = door.rectangleItem.y + door.rectangleItem.width;

                if (KeyEnum.NORTH.getValue()) {
                    rectanglePlayer.y -= entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleDoor)) {
                        entity.collisionDoor = true;
                        return index;
                    }
                } else if (KeyEnum.SOUTH.getValue()) {
                    rectanglePlayer.y += entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleDoor)) {
                        entity.collisionDoor = true;
                        return index;
                    }
                } else if (KeyEnum.WEST.getValue()) {
                    rectanglePlayer.x -= entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleDoor)) {
                        entity.collisionDoor = true;
                        return index;
                    }
                } else if (KeyEnum.EAST.getValue()) {
                    rectanglePlayer.x += entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleDoor)) {
                        entity.collisionDoor = true;
                        return index;
                    }
                }
            }
            index++;
        }
        return 999;
    }

}
