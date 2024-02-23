import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Player extends Entity {
    private String displayName;
    public BufferedImage img;

    KeyHandler keyHandler;


    protected Player(CharacterBuilder builder) {
        super(builder.getName());
        setDisplayName(builder.getDisplayName());
        keyHandler = new KeyHandler(this);
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the display name of the character.
     *
     * @param displayName Specified display name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                img,
                getPosition().x * GamePanel.tileSize,
                getPosition().y * GamePanel.tileSize,
                observer
        );
    }

    //prevents player from disappear
    public void tick(int columns, int rows) {
        if (getPosition().x < 0) {
            getPosition().x = 0;
        } else if (getPosition().x >= columns) {
            getPosition().x = columns - 1;
        }
        if (getPosition().y < 0) {
            getPosition().y = 0;
        } else if (getPosition().y >= rows) {
            getPosition().y = rows - 1;
        }
    }
}