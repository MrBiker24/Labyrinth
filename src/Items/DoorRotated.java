package Items;

import gui.GamePanel;
import tools.ImageUtils;

public class DoorRotated extends Item {

    public DoorRotated() {
        super(GamePanel.tileSize / 6, GamePanel.tileSize);
        name = "DoorRotated";
        image = ImageUtils.loadImage("/items/doorRotated.png");

        coolision = true;
    }


}
