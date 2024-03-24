package Items;

import gui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    public BufferedImage image;

    public String name;
    public boolean coolision = false;

    public Rectangle rectangleItem = new Rectangle();

    public Item(int height, int width) {
        this.rectangleItem.height = height;
        this.rectangleItem.width = width;
    }

    public void draw(Graphics2D graphics2D, boolean door) {

        if (door) {
            graphics2D.drawImage(image, this.rectangleItem.x, this.rectangleItem.y, GamePanel.tileSize, GamePanel.tileSize, null);
        } else {
            graphics2D.drawImage(image, this.rectangleItem.x, this.rectangleItem.y, GamePanel.tileSize / 2, GamePanel.tileSize / 2, null);

        }
    }
}