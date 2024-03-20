package tile;

import main.GamePanel;
import player.Direction;
import player.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    public GamePanel gamePanel;
    public Tile[] tile;

    public TileManager(GamePanel gamePanel){
     this.gamePanel = gamePanel;

     tile = new Tile[10];

     getTileImage();
    }

    private void getTileImage() {

        try{
            tile[0] = new Tile();
            tile[0].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gras.png")));

            tile[1] = new Tile();
            tile[1].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/wall.png")));

            tile[2] = new Tile();
            tile[2].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/water.png")));

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D graphics2D) {

        graphics2D.drawImage(tile[0].image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);

    }

}
