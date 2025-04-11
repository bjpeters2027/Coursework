public class Coord {
    /** The legal name of the animal */
    String name;
    /** its x coord from a top-down view of the rescue */
    int x;
    /** its y coord from a top-down view of the rescue */
    int y;

    /**
     * A constructor for initializing a coord DTO
     * @param name the legal name of the animal
     * @param x the x coord from a top-down view of the rescue
     * @param y the y coord from a top-down view of the rescue
     */
    public Coord(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

}
