package Items;

import gui.GamePanel;
import tile.TileNum;

import java.awt.*;
import java.util.Random;

public class ItemSetter {

    GamePanel gamePanel;

    public ItemSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void populateItems() {
        Random rand = new Random();
        Point position;

        gamePanel.items[0] = new Key();
        gamePanel.items[0].rectangleItem.x = 25;
        gamePanel.items[0].rectangleItem.y = 25;

        for (int i = 1; i < 10; i++) {
            position = generatePoint(rand);
            gamePanel.items[i] = new Key();
            gamePanel.items[i].rectangleItem.x = position.x;
            gamePanel.items[i].rectangleItem.y = position.y;
        }
    }

    public void populateDoors() {

        int col = 0;
        int row = 0;
        int index = 0;

        while (row < gamePanel.maxScreenRow && col < gamePanel.maxScreenCol) {

            int tileNumber = gamePanel.tileManager.mapTileNum[col][row];

            if (tileNumber == TileNum.DOOR.getValue()) {
                gamePanel.doors[index] = new Door();
                gamePanel.doors[index].rectangleItem.x = row * GamePanel.tileSize;
                gamePanel.doors[index].rectangleItem.y = col * GamePanel.tileSize;
                index++;
            }

            if (tileNumber == TileNum.DOORROTATED.getValue()) {
                gamePanel.doors[index] = new DoorRotated();
                gamePanel.doors[index].rectangleItem.x = row * GamePanel.tileSize;
                gamePanel.doors[index].rectangleItem.y = col * GamePanel.tileSize;
                index++;
            }

            row++;
            if (row == gamePanel.maxScreenRow) {
                row = 0;
                col++;
            }
        }
    }

    private Point generatePoint(Random random) {
        Point position;

        do {
            position = new Point(random.nextInt(gamePanel.screenWidth - 10), random.nextInt(gamePanel.screenHeight - 10));
        } while (isTrue(position));

        return position;
    }

    private boolean isTrue(Point position) {
        int y = (position.y) / GamePanel.tileSize;
        int x = (position.x) / GamePanel.tileSize;
        int tileNumber = gamePanel.tileManager.mapTileNum[y][x];
        return tileNumber != TileNum.WAY.getValue();
    }

}
