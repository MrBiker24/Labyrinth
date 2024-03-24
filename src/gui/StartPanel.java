package gui;

import tools.ImageUtils;

import javax.swing.*;
import java.awt.*;

import static gui.LabyrinthFrame.GAME_PANEL;

public class StartPanel extends JPanel {

    private final GamePanel gamePanel;
    private final JPanel mainPanel;
    private final JFrame frame;
    private int scale = 2;

    public StartPanel(GamePanel gamePanel, JPanel mainPanel, JFrame frame) {

        this.gamePanel = gamePanel;
        this.mainPanel = mainPanel;
        this.frame = frame;
        initialize();

    }

    private void initialize() {
        this.setSize(500, 500);
        this.setLayout(null);

        JButton grafic1 = new JButton("640 * 400");
        grafic1.setBounds(50, 100, 100, 50);
        //grafic1.setIcon(new ImageIcon(Objects.requireNonNull(ImageUtils.loadImage("/btn_100x50.png"))));
        grafic1.addActionListener(e -> this.scale = 1);
        this.add(grafic1);

        JButton grafic2 = new JButton("1280 * 800");
        grafic2.setBounds(200, 100, 100, 50);
        //grafic2.setIcon(new ImageIcon(Objects.requireNonNull(ImageUtils.loadImage("/btn_100x50.png"))));
        grafic2.addActionListener(e -> this.scale = 2);
        this.add(grafic2);

        JButton grafic3 = new JButton("1920 * 1200");
        grafic3.setBounds(350, 100, 100, 50);
        //grafic3.setIcon(new ImageIcon(Objects.requireNonNull(ImageUtils.loadImage("/btn_100x50.png"))));
        grafic3.addActionListener(e -> this.scale = 3);
        this.add(grafic3);

        JButton start = new JButton("Start");
        start.setBounds(200, 350, 100, 50);
        //start.setIcon(new ImageIcon(Objects.requireNonNull(ImageUtils.loadImage("/btn_100x50.png"))));
        start.addActionListener(e -> startGame());
        this.add(start);

        JLabel lbl_info = new JLabel("Ziel des Spiels ist es aus allen Labyrinthen zu entkommen");
        lbl_info.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_info.setBounds(50, 250, 400, 50);
        this.add(lbl_info);

        JLabel lbl_infoControl = new JLabel("Der Spieler wird mit W, A, S , D bewegt und Pausiert wird mis ESC");
        lbl_infoControl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_infoControl.setBounds(50, 300, 400, 50);
        this.add(lbl_infoControl);

    }

    public void startGame() {
        LabyrinthFrame.cardLayout.show(mainPanel, GAME_PANEL);

        gamePanel.setupGame(scale);

        frame.setSize((gamePanel.screenWidth + (GamePanel.tileSize / 2)), (gamePanel.screenHeight + GamePanel.tileSize));
        frame.setResizable(false);

        gamePanel.startGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image backgroundImage = ImageUtils.loadImage("/background_info.png");

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
