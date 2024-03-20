package tile;

public class Block_old {
    private BlockProperties_old blockProperties;

    public Block_old(String name, BlockProperties_old blockProperties) {

        setBlockProperties(blockProperties);
    }

    public Block_old(String name) {
        this(name, new BlockProperties_old(false));
    }

    public BlockProperties_old getBlockProperties() {
        return blockProperties;
    }

    protected void setBlockProperties(BlockProperties_old blockProperties) {
        if (blockProperties == null) throw new IllegalArgumentException("tile.Block properties cannot be null!");

        this.blockProperties = blockProperties;
    }
}