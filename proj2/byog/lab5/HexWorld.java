package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    //pseudo random number generator - gives you identical sequence of numbers for same seed every time
    private static final Random RANDOM = new Random(SEED);

    private static void addHexagon(TETile[][] matrix, int size, int x, int y) {
        //check if there's no room to draw the hexagon; should really toss some sort of error here
        //if (y < 2 * size || x < 2 * size) {
           // return;
        //}
        TETile tileType = randomTile();
        int rowNumber = size * 2;
        int currentRow = 0;

        while(currentRow < rowNumber) {
            int rowLength = getRowLength(rowNumber,currentRow);
            drawRow(matrix,x,y,tileType,rowLength);

            currentRow ++;
            if (currentRow < (rowNumber / 2)) {
                x --;
            } else if (currentRow == rowNumber / 2) {
                //no change to x
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

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexTiles = new TETile[WIDTH][HEIGHT];

        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++){
                hexTiles[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(hexTiles,5, 23, 27);

        ter.renderFrame(hexTiles);
    }
}
