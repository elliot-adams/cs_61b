package byog.lab5;
//import org.junit.Test;
//import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    private static final long SEED = 2873123;
    //pseudo random number generator - gives you identical sequence of numbers for same seed
    private static final Random RANDOM = new Random(SEED);

    private static void addHexagon(TETile[][] matrix, int size, int x, int y) {
        TETile tileType = randomTile();
        int rowNumber = size * 2;
        int currentRow = 0;

        while (currentRow < rowNumber) {
            int rowLength = getRowLength(rowNumber, currentRow);
            drawRow(matrix, x, y, tileType, rowLength);

            currentRow++;
            if (currentRow < (rowNumber / 2)) {
                x--;
            } else if (currentRow == rowNumber / 2) {
                x = x; //only added for style checker
            } else {
                x++;
            }
            y--;
        }

    }

    private static int getRowLength(int rowNumber, int row) {
        if (row < rowNumber / 2) {
            return rowNumber / 2 + row * 2;
        }
        return rowNumber / 2 + (rowNumber - row - 1) * 2;
    }

    private static void drawRow(TETile[][] matrix, int x, int y, TETile type, int rowLength) {
        for (int i = x; i < x + rowLength; i++) {
            matrix[i][y] = type;
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.WATER;
            default: return Tileset.MOUNTAIN;
        }
    }

    private static void tesselate(TETile[][] matrix) {
        //need at least 30x30 grid
        //specific to five columns of size 3 hexagons

        int size = 3;
        //height minus the height of size 3 hexagon times 3 (since starting with column of three)
        int y = matrix[0].length - (matrix[0].length - size * 2 * 3) / 2 - 1;
        int x = size;
        int[] position = new int[] {x, y};

        hexColumn(matrix, position, 3);
        position = topRight(position, size);

        hexColumn(matrix, position, 4);
        position = topRight(position, size);

        hexColumn(matrix, position, 5);
        position = bottomRight(position, size);

        hexColumn(matrix, position, 4);
        position = bottomRight(position, size);

        hexColumn(matrix, position, 3);

    }

    private static void hexColumn(TETile[][] matrix, int [] position, int hexes) {
        int size = 3;
        int x = position[0];
        int y = position[1];

        for (int i = 0; i < hexes; i++) {
            addHexagon(matrix, size, x, y);
            y = y - size * 2;
        }

    }

    private static int[] topRight(int [] position, int size) {
        position[0] += size * 2 - 1;
        position[1] += size;
        return position;
    }

    private static int[] bottomRight(int [] position, int size) {
        position[0] += size * 2 - 1;
        position[1] -= size;
        return position;
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexTiles = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                hexTiles[x][y] = Tileset.NOTHING;
            }
        }
        //addHexagon(hexTiles, 5, 23, 27);
        tesselate(hexTiles);

        ter.renderFrame(hexTiles);
    }
}
