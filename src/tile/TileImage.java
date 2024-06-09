package tile;

import tools.ImageUtils;

public class TileImage {

    public static Tile[] tile = new Tile[8];

    private void loadTileImage() {

        tile[TileNum.GRAS.getValue()] = new Tile();
        tile[TileNum.GRAS.getValue()].image = ImageUtils.loadImage("/tiles/gras.png");
        tile[TileNum.GRAS.getValue()].slowCollision = true;

        tile[TileNum.WAY.getValue()] = new Tile();
        tile[TileNum.WAY.getValue()].image = ImageUtils.loadImage("/tiles/way.png");

        tile[TileNum.WATER.getValue()] = new Tile();
        tile[TileNum.WATER.getValue()].image = ImageUtils.loadImage("/tiles/water.png");
        tile[TileNum.WATER.getValue()].slowCollision = true;

        tile[TileNum.DOOR.getValue()] = new Tile();
        tile[TileNum.DOOR.getValue()].image = ImageUtils.loadImage("/tiles/way.png");

        tile[TileNum.DOORROTATED.getValue()] = new Tile();
        tile[TileNum.DOORROTATED.getValue()].image = ImageUtils.loadImage("/tiles/way.png");

        tile[TileNum.WALL.getValue()] = new Tile();
        tile[TileNum.WALL.getValue()].image = ImageUtils.loadImage("/tiles/wall.png");
        tile[TileNum.WALL.getValue()].collision = true;

        tile[TileNum.EXIT.getValue()] = new Tile();
        tile[6].image = ImageUtils.loadImage("/tiles/exit.png");
        tile[TileNum.EXIT.getValue()].exitCollision = true;
    }

    public void loadTileImageMap() {

        loadTileImage();

        tile[TileNum.DOOR.getValue()] = new Tile();
        tile[TileNum.DOOR.getValue()].image = ImageUtils.loadImage("/tiles/way.png");

        tile[TileNum.DOORROTATED.getValue()] = new Tile();
        tile[TileNum.DOORROTATED.getValue()].image = ImageUtils.loadImage("/tiles/way.png");

    }

    public void loadTileImageGenerator() {

        loadTileImage();

        tile[TileNum.DOOR.getValue()] = new Tile();
        tile[TileNum.DOOR.getValue()].image = ImageUtils.loadImage("/tiles/way_door.png");

        tile[TileNum.DOORROTATED.getValue()] = new Tile();
        tile[TileNum.DOORROTATED.getValue()].image = ImageUtils.loadImage("/tiles/way_doorRotated.png");

    }
}
