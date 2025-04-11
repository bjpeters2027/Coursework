/**
 * A 2D coord
 */
public class Coord2D {
    /** The x part of the Coordinate */
    public int x;
    /** The y part of the Coordinate */
    public int y;
    /**
     * A simple 2D coordinate constructor
     * @param x the x part of the coordinate
     * @param y the y part of the coordinate
     */
    public Coord2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    //////////// IGNORE THE BELOW CODE FOR NOW. WE COVER THIS IN HW3, BUT IT IS
   // JUST TO MAKE DEBUGGING EASIER FOR YOU
    @Override
    public String toString(){
        return String.format("(%d,%d)",this.x, this.y);
    }
    @Override
    public boolean equals(Object o){
        if(! (o instanceof Coord2D)){
            return false;
        }
        Coord2D co = (Coord2D) o;
        return this.x == co.x && this.y == co.y;
    }
}

