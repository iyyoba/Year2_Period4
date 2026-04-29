package rpg;

public class CityMap extends Map {

    public CityMap(int width, int height) {
        super(width, height);
    }

    @Override
    protected Tile createTile() {
        int choice = random.nextInt(3);
        switch (choice) {
            case 0: return new RoadTile();
            case 1: return new ForestTile();
            default: return new BuildingTile();
        }
    }
}