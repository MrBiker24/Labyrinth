package Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Key extends Item {

    public Key() {
        super(16,16);
        name = "Key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/key_32x32.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        coolision = true;
    }

}