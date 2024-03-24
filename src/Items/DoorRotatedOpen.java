package Items;

import gui.GamePanel;
import tools.ImageUtils;

public class DoorRotatedOpen extends Item {

    public DoorRotatedOpen() {
        super(GamePanel.tileSize, GamePanel.tileSize);
        name = "DoorRotatedOpen";
        image = ImageUtils.loadImage("/items/doorRotatedOpen.png");

        coolision = false;
    }


}
