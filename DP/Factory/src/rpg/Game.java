package rpg;

public class Game {

    public static Map createMap(String type, int width, int height) {
        if (type.equalsIgnoreCase("city")) {
            return new CityMap(width, height);
        } else if (type.equalsIgnoreCase("wilderness")) {
            return new WildernessMap(width, height);
        } else {
            throw new IllegalArgumentException("Unknown map type");
        }
    }

    public static void main(String[] args) {
        Map map = createMap("wilderness", 10, 5);
        map.display();
    }
}