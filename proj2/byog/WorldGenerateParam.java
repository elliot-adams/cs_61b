package byog;

public class WorldGenerateParam {
    private int width;
    private int height;
    private long seed;

    public WorldGenerateParam() {
        width = 80;
        height = 30;
        seed = 587667;
    }

    public WorldGenerateParam(int w, int h, long seed) {
        this.width = w;
        this.height = h;
        this.seed = seed;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public long seed() {
        return seed;
    }
    public WorldGenerateParam setWorldGenerateParam(int w, int h, long s) {
        //return new wgp with width, height, seed specified
        //not static so need to call on an existing wgp, but don't need to construct a new one
        //so basically just saves you writing out statement to create a new wgp
        WorldGenerateParam wgp = new WorldGenerateParam();
        wgp.width = w;
        wgp.height = h;
        wgp.seed = s;
        return wgp;
    }
}