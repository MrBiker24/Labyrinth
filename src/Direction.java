public enum Direction {
    NORTH(false),
    SOUTH(false),
    EAST(false),
    WEST(false);


    private boolean value;

    Direction(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
