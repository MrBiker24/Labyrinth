package tile;

import gui.GamePanel;
import tools.ImageUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    public GamePanel gamePanel;
    public Tile[] tile;

    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tile = new Tile[10];
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];

        getTileImage();

        //loadMap(loadRandomMap());
        loadMap(loadMapByNumber(0));
    }

    /*private String loadRandomMap() {
        Random random = new Random();
        return switch (random.nextInt(5)) {
            case 0 -> "/maps/map.txt";
            case 1 -> "/maps/map1.txt";
            case 2 -> "/maps/map2.txt";
            case 3 -> "/maps/map3.txt";
            case 4 -> "/maps/map4.txt";
            default -> "/maps/map.txt";
        };
    }*/

    public String loadMapByNumber(int mapCount) {
        return switch (mapCount) {
            case 0 -> "/maps/map.txt";
            case 1 -> "/maps/map1.txt";
            case 2 -> "/maps/map2.txt";
            default -> "/maps/map.txt";
        };
    }

    private void getTileImage() {

        tile[TileNum.GRAS.getValue()] = new Tile();
        tile[TileNum.GRAS.getValue()].image = ImageUtils.loadImage("/tiles/gras.png");
        tile[TileNum.GRAS.getValue()].slowCollision = true;

        tile[TileNum.WAY.getValue()] = new Tile();
        tile[TileNum.WAY.getValue()].image = ImageUtils.loadImage("/tiles/way.png");

        tile[TileNum.WATER.getValue()] = new Tile();
        tile[TileNum.WATER.getValue()].image = ImageUtils.loadImage("/tiles/water.png");
        tile[TileNum.WATER.getValue()].slowCollision = true;

        tile[TileNum.DOOR.getValue()] = new Tile();
        tile[TileNum.DOOR.getValue()].image = ImageUtils.loadImage("/tiles/way.png");

        tile[TileNum.DOORROTATED.getValue()] = new Tile();
        tile[TileNum.DOORROTATED.getValue()].image = ImageUtils.loadImage("/tiles/way.png");

        tile[TileNum.WALL.getValue()] = new Tile();
        tile[TileNum.WALL.getValue()].image = ImageUtils.loadImage("/tiles/wall.png");
        tile[TileNum.WALL.getValue()].collision = true;

        tile[TileNum.EXIT.getValue()] = new Tile();
        tile[6].image = ImageUtils.loadImage("/tiles/exit.png");
        tile[TileNum.EXIT.getValue()].exitCollision = true;

        //tile[TileNum.DOOROPEN.getValue()] = new Tile();
        //tile[TileNum.DOOROPEN.getValue()].image = ImageUtils.loadImage("/doorOpen.png");

        //tile[TileNum.DOORROTATEDOPEN.getValue()] = new Tile();
        //tile[TileNum.DOORROTATEDOPEN.getValue()].image = ImageUtils.loadImage("/doorRotatedOpen.png");

    }

    public void loadMap(String map) {
        try {
            InputStream is = getClass().getResourceAsStream(map);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader((is)));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {

                String line = br.readLine();

                while (col < gamePanel.maxScreenCol) {

                    String[] numbers = line.split("");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gamePanel.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D graphics2D) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            graphics2D.drawImage(tile[tileNum].image, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
            col++;
            x += GamePanel.tileSize;

            if (col == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += GamePanel.tileSize;
            }
        }


    }

}
