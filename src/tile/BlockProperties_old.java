package tile;

public final class BlockProperties_old {
    private boolean rotated;

    public BlockProperties_old(boolean rotated) {
        rotate(rotated);
    }

    public boolean isRotated() {
        return rotated;
    }

    private void rotate(boolean flag) {
        rotated = flag;
    }
}