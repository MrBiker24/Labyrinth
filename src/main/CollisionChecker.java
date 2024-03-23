package main;

import Items.Door;
import Items.DoorRotated;
import Items.Item;
import player.Direction;
import player.Entity;

import java.awt.*;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        if ((Direction.NORTH.getValue() && entity.playerPositionY == 0) || (Direction.WEST.getValue() && entity.playerPositionX == 0) || (Direction.SOUTH.getValue() && entity.playerPositionY == gamePanel.screenHeight) || (Direction.EAST.getValue() && entity.playerPositionX == gamePanel.screenWidth)) {
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


        if (Direction.NORTH.getValue()) {
            int row = (int) ((playerHeadY - entity.playerSpeed) / gamePanel.tileSize);
            int colLeft = (int) ((playerHeadXLeft + 1) / gamePanel.tileSize);
            int colRight = (int) (playerHeadXRight - 1) / gamePanel.tileSize;
            extracted(entity, row, row, colLeft, colRight);
        } else if (Direction.SOUTH.getValue()) {
            int row = (int) ((playerFeedY + entity.playerSpeed) / gamePanel.tileSize);
            int colLeft = (int) (playerFeedXLeft + 1) / gamePanel.tileSize;
            int colRight = (int) (playerFeedXRight - 1) / gamePanel.tileSize;
            extracted(entity, row, row, colLeft, colRight);
        } else if (Direction.WEST.getValue()) {
            int col = (int) ((playerLeftArmX - entity.playerSpeed) / gamePanel.tileSize);
            int rowTop = (int) (playerLeftArmYTop + 1) / gamePanel.tileSize;
            int rowBottom = (int) (playerLeftArmYBottom - 1) / gamePanel.tileSize;
            extracted(entity, rowTop, rowBottom, col, col);
        } else if (Direction.EAST.getValue()) {
            int col = (int) ((playerRightArmX + entity.playerSpeed) / gamePanel.tileSize);
            int rowTop = (int) (playerRightArmYTop + 1) / gamePanel.tileSize;
            int rowBottom = (int) (playerRightArmYBottom - 1) / gamePanel.tileSize;
            extracted(entity, rowTop, rowBottom, col, col);
        }

    }

    private void extracted(Entity entity, int rowTop, int rowBottom, int colLeft, int colRight) {
        final int tileNum1;
        final int tileNum2;
        tileNum1 = gamePanel.tileManager.mapTileNum[colLeft][rowTop];
        tileNum2 = gamePanel.tileManager.mapTileNum[colRight][rowBottom];
        if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
            entity.collisionOn = true;
        } else if (gamePanel.tileManager.tile[tileNum1].slowCollision || gamePanel.tileManager.tile[tileNum2].slowCollision) {
            entity.collisionSlow = true;
        } else if (gamePanel.tileManager.tile[tileNum1].exitCollision || gamePanel.tileManager.tile[tileNum2].exitCollision) {
            entity.collisionExit = true;
        }
    }

    public int checkItem(Entity entity) {

        int index = 0;
        for (Item item : gamePanel.items) {
            if (item != null) {

                Rectangle rectanglePlayer = new Rectangle(entity.rectanglePlayer);
                rectanglePlayer.x = (int) (entity.playerPositionX + entity.rectanglePlayer.width);
                rectanglePlayer.y = (int) (entity.playerPositionY + entity.rectanglePlayer.height);

                Rectangle rectangleItem = new Rectangle(item.rectangleItem);
                rectangleItem.x = item.rectangleItem.x + item.rectangleItem.height;
                rectangleItem.y = item.rectangleItem.y + item.rectangleItem.width;

                if (Direction.NORTH.getValue()) {
                    rectanglePlayer.y -= entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleItem)) {
                        entity.collisionKey = true;
                        return index;
                    }
                } else if (Direction.SOUTH.getValue()) {
                    rectanglePlayer.y += entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleItem)) {
                        entity.collisionKey = true;
                        return index;
                    }
                } else if (Direction.WEST.getValue()) {
                    rectanglePlayer.x -= entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleItem)) {
                        entity.collisionKey = true;
                        return index;
                    }
                } else if (Direction.EAST.getValue()) {
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

                if (Direction.NORTH.getValue()) {
                    rectanglePlayer.y -= entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleDoor)) {
                        entity.collisionDoor = true;
                        return index;
                    }
                } else if (Direction.SOUTH.getValue()) {
                    rectanglePlayer.y += entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleDoor)) {
                        entity.collisionDoor = true;
                        return index;
                    }
                } else if (Direction.WEST.getValue()) {
                    rectanglePlayer.x -= entity.playerSpeed;
                    if (rectanglePlayer.intersects(rectangleDoor)) {
                        entity.collisionDoor = true;
                        return index;
                    }
                } else if (Direction.EAST.getValue()) {
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
