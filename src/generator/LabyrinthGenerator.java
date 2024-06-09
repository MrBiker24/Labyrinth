package generator;

import tile.TileImage;
import tools.FolderContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class LabyrinthGenerator extends JFrame implements Runnable {
    private static final int tileSize = 32;
    private static final int maxScreenCol = 25; //höhe oben nach unten
    private static final int maxScreenRow = 40; //Breite links nach rechts
    private static final int screenWidth = (tileSize * maxScreenRow) + 20;
    private static final int screenHeight = (tileSize * maxScreenCol) + 80;

    private int[][] tileMap;

    public JLabel tileNumber;

    private int doorCount, endCount = 1;

    private JPanel tilePanel;

    private JPanel buttonPanel;


    public LabyrinthGenerator() {
        setTitle("Labyrinth Generator");
        setSize(screenWidth, screenHeight);

        new TileImage().loadTileImageGenerator();

        KeyHandlerGenerator keyHandlerGenerator = new KeyHandlerGenerator(this);
        this.addKeyListener(keyHandlerGenerator);
        this.setFocusable(true);
        this.requestFocus();

        initTilePanel();
        initButtonPanel();

        setInitialTiles();
        repaint();

        tilePanel.addKeyListener(keyHandlerGenerator);
        tilePanel.setFocusable(true);
        tilePanel.requestFocus();

        add(buttonPanel, BorderLayout.NORTH);
        add(tilePanel, BorderLayout.CENTER);
    }

    private void initTilePanel() {
        tilePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (tileMap != null) {
                    draw(g);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(screenWidth, screenHeight);
            }
        };

        tilePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getY() / tileSize;
                int row = e.getX() / tileSize;
                toggleCell(col, row);
                repaint();
            }
        });
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        JButton generateButton = new JButton("Generate New Labyrinth");
        generateButton.addActionListener(e -> {
            setInitialTiles();
            repaint();
            tilePanel.requestFocus();
        });
        buttonPanel.add(generateButton);

        JButton saveButton = new JButton("Save Map");
        saveButton.addActionListener(e -> {
            saveTilesToFile();
            tilePanel.requestFocus();
        });
        buttonPanel.add(saveButton);

        tileNumber = new JLabel("5 Wand");
        buttonPanel.add(tileNumber);
    }


    private void draw(Graphics graphics2D) {
        for (int col = 0; col < maxScreenCol; col++) {
            for (int row = 0; row < maxScreenRow; row++) {
                int tileNum = tileMap[col][row];
                graphics2D.drawImage(TileImage.tile[tileNum].image, row * tileSize, col * tileSize, tileSize, tileSize, null);
            }
        }
    }

    private void toggleCell(int col, int row) {
        if (row >= maxScreenRow || col >= maxScreenCol || tileMap == null || notEditable(col, row)) {
            return;
        }

        switch (tileMap[col][row]) {
            case 3, 4:
                doorCount--;
                break;
            case 6:
                endCount = 0;
                break;
        }

        if (KeyEnumGenerator.ZERO.getValue()) {
            tileMap[col][row] = 0;
        } else if (KeyEnumGenerator.ONE.getValue()) {
            tileMap[col][row] = 1;
        } else if (KeyEnumGenerator.TWO.getValue()) {
            tileMap[col][row] = 2;
        } else if (KeyEnumGenerator.THREE.getValue() && doorCount < 10) {
            tileMap[col][row] = 3;
            doorCount++;
        } else if (KeyEnumGenerator.FOUR.getValue() && doorCount < 10) {
            tileMap[col][row] = 4;
            doorCount++;
        } else if (KeyEnumGenerator.FIVE.getValue()) {
            tileMap[col][row] = 5;
        } else if (KeyEnumGenerator.SIX.getValue() && endCount < 1) {
            tileMap[col][row] = 6;
            endCount = 1;
        }
    }

    private void saveTilesToFile() {
        int mapNumber = FolderContent.countFolderContents();
        String defaultFileName = "map" + mapNumber;

        JFileChooser fileChooser = new JFileChooser(FolderContent.defaultDirectory);
        fileChooser.setSelectedFile(new File(defaultFileName));

        fileChooser.setDialogTitle("Save Map to File");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            final File fileToSave = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(fileToSave + ".txt")) {
                for (int col = 0; col < maxScreenRow; col++) {
                    for (int row = 0; row < maxScreenCol; row++) {
                        writer.print(tileMap[col][row]);
                    }
                    writer.println();
                }
                writer.flush();
                JOptionPane.showMessageDialog(this, "Map saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving map: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void setInitialTiles() {
        tileMap = new int[maxScreenCol][maxScreenRow];

        for (int col = 0; col < maxScreenCol; col++) {
            for (int row = 0; row < maxScreenRow; row++) {
                tileMap[col][row] = 5;
            }
        }

        int startRow = 1;
        int startCol = 1;
        tileMap[startRow][startCol] = 1;
        buildTile(startCol, startRow);

        for (int col = 0; col <= 2; col++) {
            for (int row = 0; row <= 2; row++) {
                tileMap[col][row] = 1;
            }
        }

        Random random = new Random();
        tileMap[random.nextInt(25)][random.nextInt(40)] = 6;
    }

    private void buildTile(int col, int row) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        shuffleDirections(directions);

        for (int[] dir : directions) {
            int newRow = row + dir[0] * 2;
            int newCol = col + dir[1] * 2;

            if (newRow > 0 && newRow < maxScreenRow - 1 && newCol > 0 && newCol < maxScreenCol - 1 && tileMap[newCol][newRow] == 5) {
                tileMap[col + dir[0]][row + dir[1]] = 1;
                tileMap[newCol][newRow] = 1;

                buildTile(newCol, newRow);
            }
        }
    }

    private void shuffleDirections(int[][] directions) {
        Random rnd = new Random();
        for (int i = directions.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int[] temp = directions[index];
            directions[index] = directions[i];
            directions[i] = temp;
        }
    }

    private boolean notEditable(int col, int row) {
        return (col >= 0 && col <= 2 && row >= 0 && row <= 2);
    }

    @Override
    public void run() {
        int delay = 1000 / 120;
        Timer timer = new Timer(delay, e -> repaint());
        timer.start();
    }


}
