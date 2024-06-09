package tile;

import gui.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static gui.GamePanel.tileSize;

public class TileManager {

    public GamePanel gamePanel;

    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];

        new TileImage().loadTileImageMap();


        loadMap(loadMapByNumber(0));
    }

    public String loadMapByNumber(int mapCount) {
            return "/maps/map"+ mapCount +".txt";
    }

    public void loadMap(String map) {
        try {
            InputStream is = getClass().getResourceAsStream(map);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            String line;

            while ((line = br.readLine()) != null && col < gamePanel.maxScreenCol) {
                String[] numbers = line.split("");
                for (int row = 0; row < numbers.length && row < gamePanel.maxScreenRow; row++) {
                    int num = Integer.parseInt(numbers[row]);
                    mapTileNum[col][row] = num;
                }
                col++;
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics graphics2D) {
        for (int col = 0; col < gamePanel.maxScreenCol; col++) {
            for (int row = 0; row < gamePanel.maxScreenRow; row++) {
                int tileNum = mapTileNum[col][row];
                graphics2D.drawImage(TileImage.tile[tileNum].image, row * tileSize, col * tileSize, tileSize, tileSize, null);
            }
        }
    }

}
