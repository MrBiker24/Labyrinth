package generator;

import tile.Tile;
import tile.TileNum;
import tools.FolderContent;
import tools.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class LabyrinthGenerator extends JFrame implements Runnable {
    private static final int tileSize = 32;
    private static final int maxScreenCol = 40;
    private static final int maxScreenRow = 25;
    private static final int screenWidth = (tileSize * maxScreenCol) + 20;
    private static final int screenHeight = (tileSize * maxScreenRow) + 80;

    private int[][] maze;
    public Tile[] tile;

    public JLabel tileNumber;

    private int doorCount, endCount = 1;

    public LabyrinthGenerator() {
        setTitle("Labyrinth Generator");
        setSize(screenWidth, screenHeight);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getTileImage();

        KeyHandlerGenerator keyHandlerGenerator = new KeyHandlerGenerator(this);
        this.addKeyListener(keyHandlerGenerator);
        this.setFocusable(true);
        this.requestFocus();

        JPanel buttonPanel = new JPanel();
        JButton generateButton = new JButton("Generate New Labyrinth");
        generateButton.addActionListener(e -> {
            //generateMaze();
            setInitialMaze();
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

        //generateMaze();
        setInitialMaze();
        repaint();

        mazePanel.setDoubleBuffered(true);
        mazePanel.addKeyListener(keyHandlerGenerator);
        mazePanel.setFocusable(true);
        mazePanel.requestFocus();

        add(buttonPanel, BorderLayout.NORTH);
        add(mazePanel, BorderLayout.CENTER);
    }

    private void generateMaze() {
        maze = new int[maxScreenRow][maxScreenCol];

        for (int i = 0; i < maxScreenRow; i++) {
            for (int j = 0; j < maxScreenCol; j++) {
                if (i == 0 || i == maxScreenRow - 1 || j == 0 || j == maxScreenCol - 1) {
                    maze[i][j] = 5;
                } else if (i % 2 == 0 && j % 2 == 0) {
                    maze[i][j] = 5;
                } else {
                    double randomValue = Math.random();
                    if (randomValue < 0.5) {
                        maze[i][j] = 5;
                    } else if (randomValue < 0.7) {
                        maze[i][j] = 1;
                    } else if (randomValue < 0.85) {
                        maze[i][j] = 2;
                    } else {
                        maze[i][j] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                maze[i + 1][j + 1] = 1;
            }
        }

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

        int doorCount = 0;
        while (doorCount < 10) {
            int row = (int) (Math.random() * (maxScreenRow - 2)) + 1;
            int col = (int) (Math.random() * (maxScreenCol - 2)) + 1;

            if (maze[row][col] == 1) {
                maze[row][col] = (Math.random() < 0.5) ? 3 : 4;
                doorCount++;
            }
        }

        int exitRow = (int) (Math.random() * (maxScreenRow - 2)) + 1;
        int exitCol = (int) (Math.random() * (maxScreenCol - 2)) + 1;
        maze[exitRow][exitCol] = 6;
    }

    private void getTileImage() {
        tile = new Tile[7];

        tile[TileNum.GRAS.getValue()] = new Tile();
        tile[TileNum.GRAS.getValue()].image = ImageUtils.loadImage("/tiles/gras.png");

        tile[TileNum.WAY.getValue()] = new Tile();
        tile[TileNum.WAY.getValue()].image = ImageUtils.loadImage("/tiles/way.png");

        tile[TileNum.WATER.getValue()] = new Tile();
        tile[TileNum.WATER.getValue()].image = ImageUtils.loadImage("/tiles/water.png");

        tile[TileNum.DOOR.getValue()] = new Tile();
        tile[TileNum.DOOR.getValue()].image = ImageUtils.loadImage("/tiles/way_door.png");

        tile[TileNum.DOORROTATED.getValue()] = new Tile();
        tile[TileNum.DOORROTATED.getValue()].image = ImageUtils.loadImage("/tiles/way_doorRotated.png");

        tile[TileNum.WALL.getValue()] = new Tile();
        tile[TileNum.WALL.getValue()].image = ImageUtils.loadImage("/tiles/wall.png");

        tile[TileNum.EXIT.getValue()] = new Tile();
        tile[6].image = ImageUtils.loadImage("/tiles/exit.png");

    }

    private void drawMaze(Graphics g) {
        for (int i = 0; i < maxScreenRow; i++) {
            for (int j = 0; j < maxScreenCol; j++) {
                BufferedImage image = null;
                switch (maze[i][j]) {
                    case 0 -> image = tile[0].image;
                    case 1 -> image = tile[1].image;
                    case 2 -> image = tile[2].image;
                    case 3 -> image = tile[3].image;
                    case 4 -> image = tile[4].image;
                    case 5 -> image = tile[5].image;
                    case 6 -> image = tile[6].image;
                }
                g.drawImage(image, j * tileSize, i * tileSize, tileSize, tileSize, null);
            }
        }
    }

    private void toggleCell(int row, int col) {
        if (maze[row][col] == 6) {
            endCount = 0;
        } else if (maze[row][col] == 3 || maze[row][col] == 4) {
            doorCount -= 1;
        }
        if (!notEditable(row, col) && maze != null && row < maxScreenRow && col < maxScreenCol) {
            if (KeyEnumGenerator.ZERO.getValue()) {
                maze[row][col] = 0;
            } else if (KeyEnumGenerator.ONE.getValue()) {
                maze[row][col] = 1;
            } else if (KeyEnumGenerator.TWO.getValue()) {
                maze[row][col] = 2;
            } else if (KeyEnumGenerator.THREE.getValue()) {
                if (doorCount < 10) {
                    maze[row][col] = 3;
                    doorCount += 1;
                }
            } else if (KeyEnumGenerator.FOUR.getValue()) {
                if (doorCount < 10) {
                    maze[row][col] = 4;
                    doorCount += 1;
                }
            } else if (KeyEnumGenerator.FIVE.getValue()) {
                maze[row][col] = 5;
            } else if (KeyEnumGenerator.SIX.getValue()) {
                if (endCount < 1) {
                    maze[row][col] = 6;
                    endCount = 1;
                }
            }
        }
    }

    private void saveMazeToFile() {
        int mapNumber = FolderContent.countFolderContents();
        String defaultFileName = "map" + mapNumber;

        JFileChooser fileChooser = new JFileChooser(FolderContent.defaultDirectory);
        fileChooser.setSelectedFile(new File(defaultFileName)); // Setzen des vordefinierten Dateinamens

        fileChooser.setDialogTitle("Save Maze to File");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(fileToSave + ".txt")) {
                for (int i = 0; i < maxScreenRow; i++) {
                    for (int j = 0; j < maxScreenCol; j++) {
                        writer.print(maze[i][j]);
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


    private void setInitialMaze() {
        maze = new int[maxScreenRow][maxScreenCol];

        for (int i = 0; i < maxScreenRow; i++) {
            for (int j = 0; j < maxScreenCol; j++) {
                maze[i][j] = 5;
            }
        }

        int startRow = 1;
        int startCol = 1;
        maze[startRow][startCol] = 1; // Weg

        buildMaze(startRow, startCol);
        maze[0][0] = 1;
        maze[0][1] = 1;
        maze[0][2] = 1;
        maze[1][0] = 1;
        maze[1][1] = 1;
        maze[1][2] = 1;
        maze[2][0] = 1;
        maze[2][1] = 1;
        maze[2][2] = 1;

        Random random = new Random();
        maze[random.nextInt(25)][random.nextInt(40)] = 6;
    }

    private void buildMaze(int row, int col) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        shuffleArray(directions);

        for (int[] dir : directions) {
            int newRow = row + dir[0] * 2;
            int newCol = col + dir[1] * 2;

            if (newRow > 0 && newRow < maxScreenRow - 1 && newCol > 0 && newCol < maxScreenCol - 1 && maze[newRow][newCol] == 5) {
                maze[row + dir[0]][col + dir[1]] = 1;
                maze[newRow][newCol] = 1;

                buildMaze(newRow, newCol);
            }
        }
    }

    private void shuffleArray(int[][] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int[] temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private boolean notEditable(int i, int j) {
        if (i == 0 && j == 0) {
            return true;
        } else if (i == 0 && j == 1) {
            return true;
        } else if (i == 0 && j == 2) {
            return true;
        } else if (i == 1 && j == 0) {
            return true;
        } else if (i == 1 && j == 1) {
            return true;
        } else if (i == 1 && j == 2) {
            return true;
        } else if (i == 2 && j == 0) {
            return true;
        } else if (i == 2 && j == 1) {
            return true;
        } else if (i == 2 && j == 2) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LabyrinthGenerator().setVisible(true));
    }

    @Override
    public void run() {
        while (true) {

            repaint();

            try {
                Thread.sleep((1000 / (120)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
