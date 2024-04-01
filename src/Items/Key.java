package Items;

import gui.GamePanel;
import tools.ImageUtils;

public class Key extends Item {

    public Key() {
        super(8 * GamePanel.scale, 8 * GamePanel.scale);
        name = "Key";
        image = ImageUtils.loadImage("/items/key_32x32.png");

        coolision = true;
    }

}