package tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;

public class ImageUtils {

    public static BufferedImage loadImage(String pathname) {
        BufferedImage img = null;

        if (pathname.isEmpty()) {
            return null;
        } else {
            try {
                InputStream is = Objects.requireNonNull(ImageUtils.class.getResourceAsStream(pathname));
                img = ImageIO.read(is);
                is.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return img;
    }
}
