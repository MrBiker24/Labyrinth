package Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class DoorOpen extends Item {

    public DoorOpen() {
        super(32, 32);
        name = "DoorOpen";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/doorOpen.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        coolision = false;
    }


}

