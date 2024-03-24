package enviroment;

import gui.GamePanel;

import java.awt.*;

public class EnviromentManager {

    private final GamePanel gamePanel;
    private Lighting lighting;

    public EnviromentManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        lighting = new Lighting(gamePanel);
    }

    public void setup() {
        lighting = new Lighting(gamePanel);
    }

    public void draw(Graphics2D g2) {
        lighting.draw(g2);
    }

}
