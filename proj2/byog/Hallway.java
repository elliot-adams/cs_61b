package byog;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Hallway {
    Posit corner; //for L shaped halls
    Posit start;
    Posit end;
    int key; //for L shaped halls, 0 (horizontal then vert hall, right room to left room) or 1 (vert then horizontal hall, draw from bottom room to top room)

    public Hallway(Posit c, Posit s, Posit e, int k) {
        corner = c;
        start = s;
        end = e;
        key = k;
    }

    public static Posit addVerticalHallway(TETile[][] world, Posit p, int h) {
        //draws from bottom to top, returns position of the top end of the hall
        for (int y = 0; y < h; y += 1) {
            world[p.xPos() - 1][p.yPos() + y] = Tileset.WALL; //left wall
            world[p.xPos() + 1][p.yPos() + y] = Tileset.WALL; //right wall
        }
        return new Posit(p.xPos(), p.yPos() + h);
    }

    public static Posit addHorizontalHallway(TETile[][] world, Posit p, int w) {
        //draws from left to right, returns position of the right end of the hall
        for (int x = 0; x < w; x += 1) {
            //System.out.println("world: "+world.length+" w: "+w+" p.x: "+p.xPos+" p.y: "+p.yPos);
            world[p.xPos() + x][p.yPos() - 1] = Tileset.WALL; //bottom wall
            world[p.xPos() + x][p.yPos() + 1] = Tileset.WALL; //top wall
        }
        return new Posit(p.xPos() + w, p.yPos());
    }

    private static void fillVerticalHallway(TETile[][] world, Posit p, int h) {
        //draws hall floor from bottom to top (note private)
        for (int y = 0; y < h; y += 1) {
            world[p.xPos()][p.yPos() + y] = Tileset.FLOOR;
        }
    }

    private static void fillHorizontalHallway(TETile[][] world, Posit p, int w) {
        //draws hall floor from bottom to top (note private)
        for (int x = 0; x < w; x += 1) {
            world[p.xPos() + x][p.yPos()] = Tileset.FLOOR;
        }
    }

    private static void fillLHallway(TETile[][] world, Hallway hw) {
        switch (hw.key) {
            case 0: {
                //horizontal then vertical hall (from L to R)
                //fill horizontal portion
                fillHorizontalHallway(world, hw.start, hw.corner.xPos() - hw.start.xPos() + 1);
                //fill vertical portion, start at bottom whether that's the corner or the end, (could be going up or down)
                fillVerticalHallway(world, Posit.smallerY(hw.corner, hw.end),
                        Math.abs(hw.corner.yPos() - hw.end.yPos()) + 1);
                break;
            }
            case 1: {
                fillVerticalHallway(world, hw.start, hw.corner.yPos() - hw.start.yPos() + 1);
                fillHorizontalHallway(world, Posit.smallerX(hw.corner, hw.end),
                        Math.abs(hw.corner.xPos() - hw.end.xPos()) + 1);
                break;
            }
            default: break;
        }
    }

    public static void fillLHallwayList(TETile[][] world, ArrayList<Hallway> hallwayList) {
        for (int i = 0; i < hallwayList.size(); i += 1) {
            fillLHallway(world, hallwayList.get(i));
        }
    }
}