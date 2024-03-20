package tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class MapCreator_old {

    private static Block_old[][] map;

    public MapCreator_old(BufferedImage img, int rows, int columns, int tileSize) {
        createTiles(img, rows, columns, tileSize);
    }

    private void createTiles(BufferedImage img, int rows, int columns, int tileSize) {

        map = new Block_old[rows][columns];
        try {
            Random random = new Random();
            List<String> lines = switch (random.nextInt(1)) {
                case 0 -> Files.readAllLines(Paths.get("resources/maps/test.map.txt"));
                case 1 -> Files.readAllLines(Paths.get("resources/maps/map1.txt"));
                case 2 -> Files.readAllLines(Paths.get("resources/maps/map2.txt"));
                default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(1));
            };


            for (int i = 0; i < rows; i++) {
                String text = lines.get(i);
                System.out.println(text);

                for (int j = 0; j < columns; j++) {
                    Block_old block = null;

                    switch (Character.getNumericValue(text.charAt(j))) {
                        case 0 -> {
                            block = new Block_old("wall_block");
                            //block.loadTexture("resources/images/background.png");
                        }
                        case 1 -> {
                            block = new Block_old("way_block");
                            //block.loadTexture("resources/images/way.png");
                        }
                        case 2 -> {
                            block = new Block_old("water_block");
                            //block.loadTexture("resources/images/water.png");
                        }
                        case 3 -> block = new Door_old(1, false);
                        case 4 -> block = new Door_old(1, true);
                        default ->
                                throw new IllegalStateException("Unexpected value: " + Character.getNumericValue(text.charAt(j)));
                    }

                    //block.setPosition(new Point(i, j));

                    map[i][j] = block;
                }
            }
        } catch (IOException | IllegalStateException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                final BufferedImage tile = null; //= map[j][i].getImage();

                if (tile == null) continue;

                for (int x = 0; x < tile.getWidth(); x++) {
                    for (int y = 0; y < tile.getHeight(); y++) {
                        img.setRGB(x + i * tileSize, y + j * tileSize, tile.getRGB(x, y));
                    }
                }
            }
        }
    }

    public static Block_old[][] getMap() {
        return map;
    }
}