package player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public Rectangle rectanglePlayer = new Rectangle();
    public double playerPositionX, playerPositionY;
    public int playerSpeed;

    public BufferedImage stand, run1, run2;

    public int runCount = 0;

    public boolean collisionOn = false;
    public boolean collisionSlow = false;
    public boolean collisionExit = false;
    public boolean collisionDoor = false;
    public boolean collisionKey = false;


}