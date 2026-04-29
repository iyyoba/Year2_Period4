package rpg;

public class WildernessMap extends Map {

    public WildernessMap(int width, int height) {
        super(width, height);
    }

    @Override
    protected Tile createTile() {
        int choice = random.nextInt(3);
        switch (choice) {
            case 0: return new SwampTile();
            case 1: return new WaterTile();
            default: return new ForestTile();
        }
    }
}