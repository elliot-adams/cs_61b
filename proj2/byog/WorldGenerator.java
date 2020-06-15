package byog;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class WorldGenerator {
    public static final int MAXROOM = 36;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    public static void addCorner(TETile[][] world, Posit corner) {
        //initially fills 9 coordinates with walls
        //for example, if corner posit is (3,10), fills 3x3 square with 2,9 as bottom right
        for (int i = corner.xPos() - 1; i <= corner.xPos() + 1; i += 1) {
            for (int j = corner.yPos() - 1; j <= corner.yPos() + 1; j += 1) {
                world[i][j] = Tileset.WALL;
            }
        }
        //overrides corner itself and sets as floor
        world[corner.xPos()][corner.yPos()] = Tileset.FLOOR;
    }

    public static ArrayList<Room> drawRoom(TETile[][] tiles,
                                           int roomNumber, WorldGenerateParam wgp) {
        Random rand = new Random(wgp.seed() + 10); //object with new pseudo random sequence
        int maxW = 4;
        int maxH = 5;

        ArrayList<Room> roomList = new ArrayList<>();
        for (int i = 0; i < roomNumber; i += 1) {
            int roomWidth = rand.nextInt(maxW) + 3; //random size within bounds (could just add 3 to maxW and maxH?)
            int roomHeight = rand.nextInt(maxH) + 3;
            int roomPx = rand.nextInt(WIDTH - roomWidth); //random position that can't be out of bounds of matrix
            int roomPy = rand.nextInt(HEIGHT - roomHeight); //note position is bottom left of room
            Posit startP = new Posit(roomPx, roomPy); //create room's position object
            Room.addRoom(tiles, startP, roomWidth, roomHeight); //actually adds tiles to matrix
            Room newRoom = new Room(startP, roomWidth, roomHeight); //creates object so we can store its details (independently of matrix)
            roomList.addLast(newRoom); //this way can keep track of all rooms / locations in an array list
        }
        return roomList;
    }

    public static Hallway drawLWay(TETile[][] tiles, Room r1, Room r2, WorldGenerateParam wgp) {
        //given two rooms, connect with L shaped hallway from left / lower to right / higher
        //Switch bc randomly choosing which way the L is flipped; return the Hallway object
        Random rand = new Random(wgp.seed() + 100); //object with new pseudo random sequence

        Posit p1 = Room.innerRand(r1, wgp); //random floor spot within room one
        Posit p2 = Room.innerRand(r2, wgp); //random floor spot within room two
        //System.out.println("add one："+p2.xPos+", "+p1.xPos);
        int key = rand.nextInt(2);
        switch (key) {
            case 0: {  //draw horizontal hallway first
                Posit horizontalStart = Posit.smallerX(p1, p2); //selects left most room as horizontal start
                Posit horCornerPt = Hallway.addHorizontalHallway(tiles, horizontalStart,
                        Math.abs(p2.xPos() - p1.xPos())); //last param is width,returns right endpoint
                Posit verticalStart = Posit.smallerY(horCornerPt, Posit.largerX(p1, p2)); //start at lower of hor hall end or other room
                Hallway.addVerticalHallway(tiles, verticalStart, Math.abs(p2.yPos() - p1.yPos())); //last param is width
                addCorner(tiles, horCornerPt); //override to floor at intersection (note haven't filled rest of halls with floor yet)
                return new Hallway(horCornerPt, horizontalStart, Posit.largerX(p1, p2), 0);
                //corner, start, end (rightmost room), key (horizontal then vert hall, left room to right room)
            }
            case 1: { //draw vertical way first, otherwise same as above
                Posit verticalStart = Posit.smallerY(p1, p2); //selects lower room as vertical start
                Posit verCornerPt = Hallway.addVerticalHallway(tiles, verticalStart,
                        Math.abs(p2.yPos() - p1.yPos())); //returns top endpoint
                Posit horizontalStart = Posit.smallerX(verCornerPt, Posit.largerY(p1, p2)); //start at leftmost of vert hall end or other room
                Hallway.addHorizontalHallway(tiles, horizontalStart,
                        Math.abs(p2.xPos() - p1.xPos()));
                addCorner(tiles, verCornerPt); //override to floor at intersection (note haven't filled rest of halls with floor yet)
                return new Hallway(verCornerPt, verticalStart, Posit.largerY(p1, p2), 1);
                //corner, start, end (higher room), key (vert then horizontal hall, bottom room to top room)
            }
            default: {
                return null;
            }
        }
    }

    public static Posit fillPlayer(TETile[][] world,
                                   ArrayList<Room> roomList, WorldGenerateParam wgp) {
        //place player in random spot in random room
        Random rand = new Random(wgp.seed() + 1234); //object with new pseudo random sequence
        Posit p = Room.innerRand(roomList.get(roomList.size()
                - rand.nextInt(roomList.size() - 1) - 1), wgp); //get random room w/in room list (index is size - random int - 1), then get rand pos in room
        if (p.xPos() == LockDoor.chooseLockedDoor(roomList, wgp).xPos()) {
            //if random position is in same column as locked door, reset so p is in another room
            p = Room.innerRand(roomList.get(rand.nextInt(roomList.size() - 3) + 3), wgp);
        }
        world[p.xPos()][p.yPos()] = Tileset.PLAYER; //set to @ player tile
        return p;
    }

    public static CrazyWorld generate(WorldGenerateParam wgp) {
        long se = wgp.seed();
        Random rand = new Random(se + 1584); //object with new pseudo random sequence

        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT]; //create tile matrix object
        // initialize tiles to nothing
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                randomTiles[x][y] = Tileset.NOTHING;
            }
        }

        int roomNumber = rand.nextInt(MAXROOM) + 8; //rand room number (at least 8 rooms)
        ArrayList<Room> roomList = drawRoom(randomTiles, roomNumber, wgp); //draw all rooms and store in room list
        roomList = Room.sortRoomList(roomList); //sort rooms from upper right most position to bottom left most position

        ArrayList<Hallway> hallwayList = new ArrayList<>();
        for (int i = 0; i < roomList.size() - 1; i += 1) {
            hallwayList.addLast(drawLWay(randomTiles, roomList.get(i), roomList.get(i + 1), wgp));
            //for each room draw an l shaped hall connecting to next room and store hallway object in list
        }
        Room.fillRoomList(randomTiles, roomList); //fill floors of rooms
        Hallway.fillLHallwayList(randomTiles, hallwayList); //fill floors of hallways
        Posit lockedDoor = LockDoor.fillLockedDoor(randomTiles, roomList, wgp); //randomly select and fill locked door
        Posit player = fillPlayer(randomTiles, roomList, wgp);
        Flower.fillFlower(randomTiles, hallwayList, wgp);
        Sand.fillSand(randomTiles, roomList, wgp);
        CrazyWorld cw = new CrazyWorld(lockedDoor, player, randomTiles);
        return cw;
    }
}
