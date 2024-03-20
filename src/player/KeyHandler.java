package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        setDirection(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setDirection(e.getKeyCode(), false);
    }

    private void setDirection(final int keyCode, boolean runOrStop) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                Direction.NORTH.setValue(runOrStop);
                break;
            case KeyEvent.VK_S:
                Direction.SOUTH.setValue(runOrStop);
                break;
            case KeyEvent.VK_D:
                Direction.EAST.setValue(runOrStop);
                break;
            case KeyEvent.VK_A:
                Direction.WEST.setValue(runOrStop);
                break;
        }
    }
}
