package tile;

public enum TileNum {
    GRAS(0),
    WAY(1),
    WATER(2),
    DOOR(3),
    DOORROTATED(4),
    WALL(5),
    EXIT(6);

    private final int value;

    TileNum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
