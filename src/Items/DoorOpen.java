package Items;

import gui.GamePanel;
import tools.ImageUtils;

public class DoorOpen extends Item {

    public DoorOpen() {
        super(GamePanel.tileSize, GamePanel.tileSize);
        name = "DoorOpen";
        image = ImageUtils.loadImage("/items/doorOpen.png");

        coolision = false;
    }


}

