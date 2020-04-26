/**
 * Class for locations with index and x, y coordinates
 */
public class Location {
    private int number;
    private double x;
    private double y;

    /**
     * Constructor
     * @param num index of the location
     * @param x coordinate
     * @param y coordinate
     */
    public Location(int num, double x, double y){
        this.number = num;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter
     * @return x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getter
     * @return y coordinate
     */
    public double getY() {
        return this.y;
    }

}