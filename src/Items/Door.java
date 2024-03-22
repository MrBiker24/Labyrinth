package Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends Item {

    public Door() {
        super(32, 5);
        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/door.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        coolision = true;
    }


}

