import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    Player player;

    public KeyHandler(Player player) {
        this.player = player;
    }

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

    public void runDirection2(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            if (player.getPosition().getY() > 0) {
                if (MapCreator.getMap()[player.getPosition().y - 1][player.getPosition().x].collusionDetected(MapCreator.getMap()[player.getPosition().y - 1][player.getPosition().x].getHitBox(), player.getHitBox())) {
                    player.getPosition().translate(0, -1);
                }
            }
        }
        if (key == KeyEvent.VK_D) {
            if (player.getPosition().getX() < 25) {
                if (MapCreator.getMap()[player.getPosition().y][player.getPosition().x + 1].collusionDetected(MapCreator.getMap()[player.getPosition().y][player.getPosition().x + 1].getHitBox(), player.getHitBox())) {
                    player.getPosition().translate(1, 0);
                }
            }
        }
        if (key == KeyEvent.VK_S) {
            if (player.getPosition().getY() < 40) {
                if (MapCreator.getMap()[player.getPosition().y + 1][player.getPosition().x].collusionDetected(MapCreator.getMap()[player.getPosition().y + 1][player.getPosition().x].getHitBox(), player.getHitBox())) {
                    player.getPosition().translate(0, 1);
                }
            }
        }
        if (key == KeyEvent.VK_A) {
            if (player.getPosition().getX() > 0) {
                if (MapCreator.getMap()[player.getPosition().y][player.getPosition().x - 1].collusionDetected(MapCreator.getMap()[player.getPosition().y][player.getPosition().x - 1].getHitBox(), player.getHitBox())) {
                    player.getPosition().translate(-1, 0);
                }
            }
        }
    }
}
