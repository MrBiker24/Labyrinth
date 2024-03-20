/*package player;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Player_old extends Entity {
    private String displayName;
    public BufferedImage img;
    public KeyHandler keyHandler;

    int playerPositionX = 1;
    int playerPositionY = 1;
    int playerSpeed = 1;

    public Player_old(CharacterBuilder builder) {
        super(builder.getName());
        setDisplayName(builder.getDisplayName());
        keyHandler = new KeyHandler(this);
    }


    public void update(){
        if (Direction.NORTH.getValue()) {
            playerPositionY -= playerSpeed;
        } else if (Direction.SOUTH.getValue()) {
            playerPositionY += playerSpeed;
        } else if (Direction.WEST.getValue()) {
            playerPositionX -= playerSpeed;
        } else if (Direction.EAST.getValue()) {
            playerPositionX += playerSpeed;
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLUE);
        graphics2D.fillRect(playerPositionX, playerPositionY, tileSize, tileSize);
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                img,
                getPosition().x * GamePanel.tileSize,
                getPosition().y * GamePanel.tileSize,
                observer
        );
    }


    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the display name of the character.
     *
     * @param displayName Specified display name.
     *
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    public void setImg(BufferedImage img) {
        this.img = img;
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
}*/