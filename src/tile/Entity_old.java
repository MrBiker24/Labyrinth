package tile;

import java.awt.*;

public class Entity_old extends Tile {
    private Rectangle hitBox;
    private boolean canCollide = true;

    public Entity_old(String name, boolean canCollide) {
        //super(name);

        collidable(canCollide);

        //hitBox = new Rectangle(getPosition().x, getPosition().y, 32, 32);
    }

    public Entity_old(String name) {
        this(name, true);
    }

    protected void collidable(boolean flag) {
        canCollide = flag;
    }

    public boolean canCollide() {
        return canCollide;
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public boolean collusionDetected(Rectangle a, Rectangle b) {
        return canCollide && !a.intersects(b);
    }
}