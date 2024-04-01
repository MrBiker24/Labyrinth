package generator;

public enum KeyEnumGenerator {
    ZERO(false),
    ONE(false),
    TWO(false),
    THREE(false),
    FOUR(false),
    FIVE(true),
    SIX(false);


    private boolean value;

    KeyEnumGenerator(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void resetAllValues(){
        KeyEnumGenerator.ZERO.setValue(false);
        KeyEnumGenerator.ONE.setValue(false);
        KeyEnumGenerator.TWO.setValue(false);
        KeyEnumGenerator.THREE.setValue(false);
        KeyEnumGenerator.FOUR.setValue(false);
        KeyEnumGenerator.FIVE.setValue(false);
        KeyEnumGenerator.SIX.setValue(false);
    }
}
