package Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class DoorRotatedOpen extends Item {

    public DoorRotatedOpen() {
        super(32,32);
        name = "DoorRotatedOpen";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/doorRotatedOpen.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        coolision = false;
    }


}
