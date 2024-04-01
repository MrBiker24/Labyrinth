package gui;

import tools.ImageUtils;
import tools.Messages;
import tools.RoundedBorder;

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

        JButton grafic1 = new JButton(Messages.getString("640_400"));
        JButton grafic2 = new JButton(Messages.getString("1280_800"));
        JButton grafic3 = new JButton(Messages.getString("1920_1200"));

        grafic1.setBounds(50, 100, 100, 50);
        grafic1.setFont(new Font("Arial", Font.BOLD, 15));
        grafic1.setOpaque(false);
        grafic1.setBackground(new Color(0, 100, 0, 64));
        grafic1.setForeground(Color.WHITE);
        grafic1.setBorder(new RoundedBorder(10));
        grafic1.addActionListener(e -> {
            grafic1.setForeground(Color.BLACK);
            grafic2.setForeground(Color.WHITE);
            grafic3.setForeground(Color.WHITE);

            this.scale = 1;
        });
        this.add(grafic1);


        grafic2.setBounds(200, 100, 100, 50);
        grafic2.setFont(new Font("Arial", Font.BOLD, 15));
        grafic2.setOpaque(false);
        grafic2.setBackground(new Color(0, 100, 0, 64));
        grafic2.setForeground(Color.BLACK);
        grafic2.setBorder(new RoundedBorder(10));
        grafic2.addActionListener(e -> {
            grafic1.setForeground(Color.WHITE);
            grafic2.setForeground(Color.BLACK);
            grafic3.setForeground(Color.WHITE);
            this.scale = 2;
        });
        this.add(grafic2);

        grafic3.setBounds(350, 100, 110, 50);
        grafic3.setFont(new Font("Arial", Font.BOLD, 15));
        grafic3.setOpaque(false);
        grafic3.setBackground(new Color(0, 100, 0, 64));
        grafic3.setForeground(Color.WHITE);
        grafic3.setBorder(new RoundedBorder(10));
        grafic3.addActionListener(e -> {
            grafic1.setForeground(Color.WHITE);
            grafic2.setForeground(Color.WHITE);
            grafic3.setForeground(Color.BLACK);
            this.scale = 1;
        });
        this.add(grafic3);

        /*JButton grafic4 = new JButton("Vollbild");
        grafic4.setBounds(350, 200, 100, 50);
        grafic4.addActionListener(e -> this.scale = 999);
        this.add(grafic4);*/

        JButton start = new JButton(Messages.getString("Start"));
        start.setBounds(200, 350, 100, 50);
        start.setFont(new Font("Arial", Font.BOLD, 15));
        start.setOpaque(false);
        start.setBackground(new Color(0, 100, 0, 64));
        start.setForeground(Color.WHITE);
        start.setBorder(new RoundedBorder(10));
        start.addActionListener(e -> startGame());
        this.add(start);

        JTextArea txtA_info = new JTextArea();
        txtA_info.setText(Messages.getString("Info1"));
        txtA_info.append(Messages.getString("Info2"));
        txtA_info.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtA_info.setFont(new Font("Arial", Font.BOLD, 20));
        txtA_info.setForeground(Color.WHITE);
        txtA_info.setBackground(new Color(0, 0, 0, 0));
        txtA_info.setBounds(50, 200, 400, 50);
        this.add(txtA_info);

        JTextArea txtA_infoControl = new JTextArea();
        txtA_infoControl.setText(Messages.getString("InfoControl1"));
        txtA_infoControl.append(Messages.getString("InfoControl2"));
        txtA_infoControl.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtA_infoControl.setFont(new Font("Arial", Font.BOLD, 20));
        txtA_infoControl.setForeground(Color.WHITE);
        txtA_infoControl.setBackground(new Color(0, 0, 0, 0));
        txtA_infoControl.setBounds(50, 260, 400, 50);
        this.add(txtA_infoControl);

    }

    public void startGame() {
        LabyrinthFrame.cardLayout.show(mainPanel, GAME_PANEL);
        gamePanel.setupGame(scale);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        if (gamePanel.screenWidth == screenSize.width && gamePanel.screenHeight == screenSize.height) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            frame.setSize((gamePanel.screenWidth + (GamePanel.tileSize / 2)), (gamePanel.screenHeight + GamePanel.tileSize));
        }
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

