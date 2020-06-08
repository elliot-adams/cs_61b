package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Room {

    public static void draw_room(TETile[][] world, int[] position, int height, int width) {
        fill_in(world, position, height, width);
    }

    public static int [] draw_border() {
        return null;
    }

    public static void fill_in(TETile[][] world, int [] position, int height, int width) {

    }
}
