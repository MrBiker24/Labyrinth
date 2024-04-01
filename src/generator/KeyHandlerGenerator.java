package generator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerGenerator implements KeyListener {

    private final LabyrinthGenerator labyrinthGenerator;

    public KeyHandlerGenerator(LabyrinthGenerator labyrinthGenerator) {
        this.labyrinthGenerator = labyrinthGenerator;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        setDirection(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setDirection(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void setDirection(final int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_0 -> {
                KeyEnumGenerator.ZERO.resetAllValues();
                KeyEnumGenerator.ZERO.setValue(true);
                this.labyrinthGenerator.tileNumber.setText("0");
            }
            case KeyEvent.VK_1 -> {
                KeyEnumGenerator.ONE.resetAllValues();
                KeyEnumGenerator.ONE.setValue(true);
                this.labyrinthGenerator.tileNumber.setText("1");
            }
            case KeyEvent.VK_2 -> {
                KeyEnumGenerator.TWO.resetAllValues();
                KeyEnumGenerator.TWO.setValue(true);
                this.labyrinthGenerator.tileNumber.setText("2");
            }
            case KeyEvent.VK_3 -> {
                KeyEnumGenerator.THREE.resetAllValues();
                KeyEnumGenerator.THREE.setValue(true);
                this.labyrinthGenerator.tileNumber.setText("3");
            }
            case KeyEvent.VK_4 -> {
                KeyEnumGenerator.FOUR.resetAllValues();
                KeyEnumGenerator.FOUR.setValue(true);
                this.labyrinthGenerator.tileNumber.setText("4");
            }
            case KeyEvent.VK_5 -> {
                KeyEnumGenerator.FIVE.resetAllValues();
                KeyEnumGenerator.FIVE.setValue(true);
                this.labyrinthGenerator.tileNumber.setText("5");
            }
            case KeyEvent.VK_6 -> {
                KeyEnumGenerator.SIX.resetAllValues();
                KeyEnumGenerator.SIX.setValue(true);
                this.labyrinthGenerator.tileNumber.setText("6");
            }
        }
    }
}
