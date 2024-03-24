package gui;

import javax.swing.*;
import java.awt.*;

public class LabyrinthFrame {
    public static final String START_PANEL = "start panel";
    public static final String GAME_PANEL = "game panel";
    public static CardLayout cardLayout;

    public LabyrinthFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Labyrinth");

        cardLayout = new CardLayout();

        JPanel mainPanel = new JPanel();

        GamePanel game = new GamePanel();
        StartPanel start = new StartPanel(game, mainPanel, frame);


        mainPanel.setLayout(cardLayout);
        mainPanel.add(start, START_PANEL);
        mainPanel.add(game, GAME_PANEL);

        cardLayout.show(mainPanel, START_PANEL);

        frame.pack();

        frame.add(mainPanel);

        frame.setSize(500, 500);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
