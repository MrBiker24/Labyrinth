package Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class DoorRotated extends Item {

    public DoorRotated() {
        super(5,32);
        name = "DoorRotated";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/doorRotated.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        coolision = true;
    }


}
