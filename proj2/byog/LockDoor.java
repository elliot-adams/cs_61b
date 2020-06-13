package byog;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class LockDoor {

    public static Posit fillLockedDoor(TETile[][] world,
                                       ArrayList<Room> roomList, WorldGenerateParam wgp) {
        //choose random room with y coordinate <=3 and then choose random spot within the room; then set to locked door
        //and return position
        Posit p = chooseLockedDoor(roomList, wgp);
        world[p.xPos()][p.yPos()] = Tileset.LOCKED_DOOR;
        return p;
    }

    public static Posit chooseLockedDoor(ArrayList<Room> roomList, WorldGenerateParam wgp) {
        Random rand = new Random(wgp.seed());
        Posit door;
        if (!filterRoomList(roomList).isEmpty()) { //check if there rooms with y coordinate <=3
            //filter room list for "low" rooms and then choose a random one; then choose random spot within as locked door
            door = setLockedDoor(filterRoomList(roomList).
                    get(rand.nextInt(filterRoomList(roomList).size())), wgp);
        } else { //if there are no rooms with y <=3, then choose the lowest and leftmost one (see room class)
            door = setLockedDoor(roomList.get(Room.smallestRoom(roomList)), wgp);
        }
        return door; //return position of locked door
    }

    private static Posit setLockedDoor(Room r, WorldGenerateParam wgp) {
        //giving room and returning random spot in the room (-2 +1 so not on the wall)
        Random rand = new Random(wgp.seed());
        return new Posit(r.posit().xPos() + rand.nextInt(r.width() - 2) + 1,
                r.posit().yPos());
    }

    private static ArrayList<Room> filterRoomList(ArrayList<Room> roomList) {
        //returns new RoomList of rooms with y coordinate <=3
        ArrayList<Room> filtered = new ArrayList<>();
        for (int i = 0; i < roomList.size(); i += 1) {
            if (roomList.get(i).posit().yPos() <= 3) {
                filtered.addLast(roomList.get(i));
            }
        }
        return filtered;
    }
}