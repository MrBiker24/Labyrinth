package generator;

import player.KeyHandler;
import tools.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class LabyrinthGenerator extends JFrame {
    private static final int tileSize = 32;
    private static final int maxScreenCol = 40;
    private static final int maxScreenRow = 25;
    private static final int screenWidth = (tileSize * maxScreenCol) + 20;
    private static final int screenHeight = (tileSize * maxScreenRow) + 80;

    private int[][] maze;

    private final KeyHandlerGenerator keyHandlerGenerator;

    public JLabel tileNumber;

    public LabyrinthGenerator() {
        keyHandlerGenerator = new KeyHandlerGenerator(this);
        setTitle("Labyrinth Generator");
        setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        JButton generateButton = new JButton("Generate Labyrinth");
        generateButton.addActionListener(e -> {
            generateMaze();
            repaint();
        });
        buttonPanel.add(generateButton);

        JButton saveButton = new JButton("Save Maze");
        saveButton.addActionListener(e -> saveMazeToFile());
        buttonPanel.add(saveButton);

        tileNumber = new JLabel("5");
        buttonPanel.add(tileNumber);

        JPanel mazePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (maze != null) {
                    drawMaze(g);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(screenWidth, screenHeight);
            }
        };
        mazePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / tileSize;
                int col = e.getX() / tileSize;
                toggleCell(row, col);
                repaint();
            }
        });

        add(buttonPanel, BorderLayout.NORTH);
        add(mazePanel, BorderLayout.CENTER);
    }

    private void generateMaze() {
        maze = new int[maxScreenRow][maxScreenCol];

        // Fill maze with random values based on specified rules
        for (int i = 0; i < maxScreenRow; i++) {
            for (int j = 0; j < maxScreenCol; j++) {
                if (i == 0 || i == maxScreenRow - 1 || j == 0 || j == maxScreenCol - 1) {
                    maze[i][j] = 5; // Boundary walls
                } else if (i % 2 == 0 && j % 2 == 0) {
                    maze[i][j] = 5; // Internal walls
                } else {
                    double randomValue = Math.random();
                    if (randomValue < 0.5) { // Increase the wall percentage to 50%
                        maze[i][j] = 5; // Wall
                    } else if (randomValue < 0.7) {
                        maze[i][j] = 1; // Path
                    } else if (randomValue < 0.85) {
                        maze[i][j] = 2; // Water
                    } else {
                        maze[i][j] = 0; // Grass
                    }
                }
            }
        }

        // Start room
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                maze[i + 1][j + 1] = 1;
            }
        }

        // Connect walls
        for (int i = 1; i < maxScreenRow - 1; i++) {
            for (int j = 1; j < maxScreenCol - 1; j++) {
                if (maze[i][j] == 5) {
                    int wallCount = 0;
                    if (maze[i - 1][j] == 5) wallCount++;
                    if (maze[i + 1][j] == 5) wallCount++;
                    if (maze[i][j - 1] == 5) wallCount++;
                    if (maze[i][j + 1] == 5) wallCount++;

                    if (wallCount >= 3) {
                        maze[i][j] = 5;
                    } else {
                        maze[i][j] = 1;
                    }
                }
            }
        }

        // Add doors
        int doorCount = 0;
        while (doorCount < 10) {
            int row = (int) (Math.random() * (maxScreenRow - 2)) + 1;
            int col = (int) (Math.random() * (maxScreenCol - 2)) + 1;

            if (maze[row][col] == 1) {
                maze[row][col] = (Math.random() < 0.5) ? 3 : 4;
                doorCount++;
            }
        }

        // Add exit
        int exitRow = (int) (Math.random() * (maxScreenRow - 2)) + 1;
        int exitCol = (int) (Math.random() * (maxScreenCol - 2)) + 1;
        maze[exitRow][exitCol] = 6;
    }

    private void drawMaze(Graphics g) {
        for (int i = 0; i < maxScreenRow; i++) {
            for (int j = 0; j < maxScreenCol; j++) {
                Color color;
                BufferedImage image = null;
                switch (maze[i][j]) {
                    case 0 -> {
                        color = Color.GREEN; // Grass
                        image = ImageUtils.loadImage("/tiles/gras.png");
                    }
                    case 1 -> {
                        color = Color.WHITE; // Way
                        image = ImageUtils.loadImage("/tiles/way.png");
                    }
                    case 2 -> {
                        color = Color.BLUE; // Water
                        image = ImageUtils.loadImage("/tiles/water.png");
                    }
                    case 3 -> {
                        color = Color.RED; // Vertical or Horizontal door
                        image = ImageUtils.loadImage("/items/door.png");
                    }
                    case 4 -> {
                        color = Color.RED; // Vertical or Horizontal door
                        image = ImageUtils.loadImage("/items/doorRotated.png");
                    }
                    case 5 -> {
                        color = Color.GRAY; // Wall
                        image = ImageUtils.loadImage("/tiles/wall.png");
                    }
                    case 6 -> {
                        color = Color.YELLOW; // Exit
                        image = ImageUtils.loadImage("/tiles/exit.png");
                    }
                }
                //g.setColor(color);
                g.drawImage(image, j * tileSize, i * tileSize, tileSize, tileSize, null);

                //g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
    }

    private void toggleCell(int row, int col) {
        if (maze != null && row >= 0 && row < maxScreenRow && col >= 0 && col < maxScreenCol) {
            if (KeyEnumGenerator.ZERO.getValue()){
                maze[row][col] = 0;
            }else if (KeyEnumGenerator.ONE.getValue()){
                maze[row][col] = 1;
            }else if (KeyEnumGenerator.TWO.getValue()){
                maze[row][col] = 2;
            }else if (KeyEnumGenerator.THREE.getValue()){
                maze[row][col] = 3;
            }else if (KeyEnumGenerator.FOUR.getValue()){
                maze[row][col] = 4;
            }else if (KeyEnumGenerator.FIVE.getValue()){
                maze[row][col] = 5;
            }else if (KeyEnumGenerator.SIX.getValue()){
                maze[row][col] = 6;
            }


        }
    }

    private void saveMazeToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Maze to File");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(fileToSave)) {
                for (int i = 0; i < maxScreenRow; i++) {
                    for (int j = 0; j < maxScreenCol; j++) {
                        writer.print(maze[i][j]);
                        if (j < maxScreenCol - 1) {
                            writer.print(",");
                        }
                    }
                    writer.println();
                }
                writer.flush();
                JOptionPane.showMessageDialog(this, "Maze saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving maze: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LabyrinthGenerator().setVisible(true));
    }
}
