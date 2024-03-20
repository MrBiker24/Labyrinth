package tile;

import Items.Key;

public class Door_old extends Block_old {
    private boolean locked, open;
    private int lockPickingLevel;
    private Key key;

    public Door_old(int lockPickingLevel, boolean rotated){
        super("door", new BlockProperties_old(rotated));

        //loadTexture(getImagePath(rotated));
        setLockPickingLevel(lockPickingLevel);
    }

    private String getImagePath(boolean flag) {
        String path = "resources/images/door.png";

        if (flag) path = "resources/images/doorRotated.png";

        return path;
    }

    public int getLockPickingLevel() {
        return lockPickingLevel;
    }

    private void setLockPickingLevel(int lockPickingLevel) {
        if (lockPickingLevel < 0) throw new IllegalArgumentException("Lock picking level cannot be less than 0!");

        this.lockPickingLevel = lockPickingLevel;
    }

    public boolean isOpen() {
        return open;
    }

    public void close() {
        open = false;
    }

    public void open() {
        open = true;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean lock(Key key) {
        if (isOpen() || isLocked()) return false;

        this.key = key;

        return locked = true;
    }

    public boolean unlock(Key key) {
        //if (!key.getName().equalsIgnoreCase(this.key.getName())) return false;

        locked = false;

        return true;
    }
}