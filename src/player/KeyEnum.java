package player;

public enum KeyEnum {
    NORTH(false),
    SOUTH(false),
    EAST(false),
    WEST(false),

    ESC(false),
    ENTER(false);


    private boolean value;

    KeyEnum(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
