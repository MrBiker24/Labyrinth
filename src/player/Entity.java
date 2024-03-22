package player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public Rectangle rectanglePlayer = new Rectangle();
    public double playerPositionX, playerPositionY, playerSpeed;

    public BufferedImage up, down, left, right;

    public boolean collisionOn = false;
    public boolean collisionSlow = false;
    public boolean collisionExit = false;
    public boolean collisionDoor = false;
    public boolean collisionKey = false;

public Entity(){
    this.rectanglePlayer.height = 22;
    this.rectanglePlayer.width = 18;
}


}