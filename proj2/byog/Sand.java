package byog;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Sand {
    private Posit posit;

    public Sand(Posit p) {
        posit = p;
    }

    public static void fillSand(TETile[][] world,
                                ArrayList<Room> roomList, WorldGenerateParam wgp) {
        int sands = 5;
        Random rand = new Random(wgp.seed() + 150); //object with new pseudo random sequence
        for (int i = 0; i < sands; i += 1) {
            int location = rand.nextInt(roomList.size() - 1); //choose random room
            Posit p = setSand(world, roomList.get(location), wgp); //get random position within room
            world[p.xPos()][p.yPos()] = Tileset.SAND;
        }
    }

    public static Posit setSand(TETile[][] world, Room r, WorldGenerateParam wgp) {
        Random rand = new Random(wgp.seed() + 195);
        return new Posit(r.posit().xPos() + rand.nextInt(r.width() - 2) + 1,
                r.posit().yPos() + rand.nextInt(r.height() - 2) + 1); //random position within room
                //need it to be at least one over and one up (hence the -2 +1 combo
    }
}