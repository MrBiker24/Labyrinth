package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            KeyEnum.ESC.setValue(!KeyEnum.ESC.getValue());
        } else {
            setDirection(e.getKeyCode(), true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setDirection(e.getKeyCode(), false);
    }

    private void setDirection(final int keyCode, boolean runOrStop) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                if (!KeyEnum.ESC.getValue()) {
                    KeyEnum.NORTH.setValue(runOrStop);
                }
                break;
            case KeyEvent.VK_S:
                if (!KeyEnum.ESC.getValue()) {
                    KeyEnum.SOUTH.setValue(runOrStop);
                }
                break;
            case KeyEvent.VK_D:
                if (!KeyEnum.ESC.getValue()) {
                    KeyEnum.EAST.setValue(runOrStop);
                }
                break;
            case KeyEvent.VK_A:
                if (!KeyEnum.ESC.getValue()) {
                    KeyEnum.WEST.setValue(runOrStop);
                }
                break;
            case KeyEvent.VK_ENTER:
                KeyEnum.ENTER.setValue(runOrStop);
                break;
        }
    }
}
