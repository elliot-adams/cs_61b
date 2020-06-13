package byog;

import byog.TileEngine.TETile;

//import java.io.Serializable;

public class CrazyWorld /*implements Serializable*/ {
    private Posit lockedDoor;
    private Posit player;
    private TETile[][] world;

    public CrazyWorld(Posit l, Posit p, TETile[][] w) {
        lockedDoor = l;
        player = p;
        world = w;
    }

    public Posit lockedDoor() {
        return lockedDoor;
    }

    public Posit player() {
        return player;
    }

    public TETile[][] world() {
        return world;
    }
}