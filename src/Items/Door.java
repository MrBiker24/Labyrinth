package Items;

import gui.GamePanel;
import tools.ImageUtils;

public class Door extends Item {

    public Door() {
        super(GamePanel.tileSize, GamePanel.tileSize / 6);
        name = "Door";
        image = ImageUtils.loadImage("/items/door.png");

        coolision = true;
    }


}

