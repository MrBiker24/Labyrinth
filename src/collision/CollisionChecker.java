package collision;

import Items.Door;
import Items.DoorRotated;
import Items.Item;
import gui.GamePanel;
import player.Entity;
import player.KeyEnum;
import tile.TileImage;

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

        if (KeyEnum.NORTH.getValue()) {
            final double playerHeadXLeft = entity.playerPositionX;
            final double playerHeadXRight = entity.playerPositionX + entity.rectanglePlayer.width;
            final double playerHeadY = entity.playerPositionY;

            int col = (int) ((playerHeadY - entity.playerSpeed) / GamePanel.tileSize);
            int rowTop = (int) ((playerHeadXLeft + 1) / GamePanel.tileSize);
            int rowBottom = (int) (playerHeadXRight - 1) / GamePanel.tileSize;
            collisionSetter(entity, col, col, rowTop, rowBottom);
        } else if (KeyEnum.SOUTH.getValue()) {
            final double playerFeedXLeft = entity.playerPositionX;
            final double playerFeedXRight = entity.playerPositionX + entity.rectanglePlayer.width;
            final double playerFeedY = entity.playerPositionY + entity.rectanglePlayer.height;

            int col = (int) ((playerFeedY + entity.playerSpeed) / GamePanel.tileSize);
            int rowTop = (int) (playerFeedXLeft + 1) / GamePanel.tileSize;
            int rowBottom = (int) (playerFeedXRight - 1) / GamePanel.tileSize;
            collisionSetter(entity, col, col, rowTop, rowBottom);
        } else if (KeyEnum.WEST.getValue()) {
            final double playerLeftArmX = entity.playerPositionX;
            final double playerLeftArmYTop = entity.playerPositionY;
            final double playerLeftArmYBottom = entity.playerPositionY + entity.rectanglePlayer.height;

            int row = (int) ((playerLeftArmX - entity.playerSpeed) / GamePanel.tileSize);
            int colLeft = (int) (playerLeftArmYTop + 1) / GamePanel.tileSize;
            int colRight = (int) (playerLeftArmYBottom - 1) / GamePanel.tileSize;
            collisionSetter(entity, colLeft, colRight, row, row);
        } else if (KeyEnum.EAST.getValue()) {
            final double playerRightArmX = entity.playerPositionX + entity.rectanglePlayer.width;
            final double playerRightArmYTop = entity.playerPositionY;
            final double playerRightArmYBottom = entity.playerPositionY + entity.rectanglePlayer.height;

            int row = (int) ((playerRightArmX + entity.playerSpeed) / GamePanel.tileSize);
            int colLeft = (int) (playerRightArmYTop + 1) / GamePanel.tileSize;
            int colRight = (int) (playerRightArmYBottom - 1) / GamePanel.tileSize;
            collisionSetter(entity, colLeft, colRight, row, row);
        }

    }

    private void collisionSetter(Entity entity, int colLeft, int colRight, int rowTop, int rowBottom) {
        final int tileNum1 = gamePanel.tileManager.mapTileNum[colLeft][rowTop];
        final int tileNum2 = gamePanel.tileManager.mapTileNum[colRight][rowBottom];
        if (TileImage.tile[tileNum1].collision || TileImage.tile[tileNum2].collision) {
            entity.collisionOn = true;
        } else if (TileImage.tile[tileNum1].slowCollision || TileImage.tile[tileNum2].slowCollision) {
            entity.collisionSlow = true;
        } else if (TileImage.tile[tileNum1].exitCollision || TileImage.tile[tileNum2].exitCollision) {
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
