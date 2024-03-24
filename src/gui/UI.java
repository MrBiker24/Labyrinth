package gui;


import player.KeyEnum;

import java.awt.*;

public class UI {

    private final GamePanel gamePanel;
    private Graphics2D graphics2D;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        graphics2D.setColor(Color.WHITE);

        if (KeyEnum.ESC.getValue()) {
            drawPauseScreen();
        }

        if (gamePanel.end) {
            drawLevelScreen();
        }

        if (gamePanel.end && gamePanel.mapCounter > 2) {
            drawEndScreen();
        }

    }

    private void drawPauseScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 80F));
        final String text = "Pause";
        final String level = "Level " + gamePanel.mapCounter;
        final String keys = "Schlüsssel: " + gamePanel.player.hasKey;

        final int x = gamePanel.screenWidth / 2 - 200;
        final int y = gamePanel.screenHeight / 2;

        graphics2D.drawString(text, x, y - 50);
        graphics2D.drawString(level, x, y + 100);
        graphics2D.drawString(keys, x, y + 200);
    }

    private void drawLevelScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 80F));
        final String text = "Level " + (gamePanel.mapCounter);
        final String next = "Press ENTER to continue";

        final int x = gamePanel.screenWidth / 2 - 200;
        final int y = gamePanel.screenHeight / 2;

        graphics2D.drawString(text, x, y);
        graphics2D.drawString(next, x - 200, y + 100);
    }

    private void drawEndScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 80F));
        final String text = "Ende";

        final int x = gamePanel.screenWidth / 2 - 200;
        final int y = gamePanel.screenHeight / 2;

        graphics2D.drawString(text, x, y);
    }

    /*public void drawDialogeScreen() {
        int x = GamePanel.tileSize * 2;
        int y = GamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - (GamePanel.tileSize * 4);
        int height = GamePanel.tileSize * 5;

        drawSubWindow(x, y, width, height);

    }

    private void drawSubWindow(int x, int y, int width, int height) {

        Color color = new Color(0, 0, 0);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);
    }*/


}

