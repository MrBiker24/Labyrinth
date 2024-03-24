package Items;

import gui.GamePanel;
import tools.ImageUtils;

public class Key extends Item {

    public Key() {
        super(GamePanel.tileSize, GamePanel.tileSize);
        name = "Key";
        image = ImageUtils.loadImage("/items/key_32x32.png");

        coolision = true;
    }

}