package gui;


import player.KeyEnum;
import tools.Messages;

import java.awt.*;

public class GameScreens {

    private final GamePanel gamePanel;
    private Graphics2D graphics2D;

    public GameScreens(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        graphics2D.setColor(Color.WHITE);

        if (KeyEnum.ESC.getValue()) {
            drawPauseScreen();
        }

        if (gamePanel.end && gamePanel.mapCounter < gamePanel.maxMapCounter + 1) {
            drawLevelScreen();
        }

        if (gamePanel.end && gamePanel.mapCounter > gamePanel.maxMapCounter) {
            drawEndScreen();
        }

    }

    private void drawPauseScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 80F));
        final String text = Messages.getString("Pause");
        final String level = Messages.getString("Level") + gamePanel.mapCounter;
        final String keys = Messages.getString("Schluesssel") + gamePanel.player.hasKey;

        final int x = gamePanel.screenWidth / 2 - 200;
        final int y = gamePanel.screenHeight / 2;

        graphics2D.drawString(text, x, y - 50);
        graphics2D.drawString(level, x, y + 100);
        graphics2D.drawString(keys, x, y + 200);
    }

    private void drawLevelScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 80F));
        final String text = Messages.getString("Level") + (gamePanel.mapCounter);
        final String next = Messages.getString("EnterWeiter");

        final int x = gamePanel.screenWidth / 2 - 200;
        final int y = gamePanel.screenHeight / 2;

        graphics2D.drawString(text, x, y);
        graphics2D.drawString(next, 30, y + 100);
    }

    private void drawEndScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 80F));
        final String text = Messages.getString("Ende");

        final int x = gamePanel.screenWidth / 2 - 200;
        final int y = gamePanel.screenHeight / 2;

        gamePanel.closeGame = true;

        graphics2D.drawString(text, x, y);
    }
}

