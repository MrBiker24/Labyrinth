package Items;

import main.GamePanel;
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

        for (int i = 1; i < 7; i++) {
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

        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {

            int tileNumber = gamePanel.tileManager.mapTileNum[col][row];

            if(tileNumber == TileNum.DOOR.getValue()){
                gamePanel.doors[index] = new Door();
                gamePanel.doors[index].rectangleItem.x = col * gamePanel.tileSize;
                gamePanel.doors[index].rectangleItem.y = row * gamePanel.tileSize;
                index++;
            }

            if(tileNumber == TileNum.DOORROTATED.getValue()){
                gamePanel.doors[index] = new DoorRotated();
                gamePanel.doors[index].rectangleItem.x = col * gamePanel.tileSize;
                gamePanel.doors[index].rectangleItem.y = row * gamePanel.tileSize;
                index++;
            }

            col++;
            if (col == gamePanel.maxScreenCol) {
                col = 0;
                row++;
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
        int y = (position.y + 10) / gamePanel.tileSize;
        int x = (position.x + 10) / gamePanel.tileSize;
        int tester = gamePanel.tileManager.mapTileNum[x][y];
        boolean test = tester != TileNum.WAY.getValue();
        return test;
    }

}
