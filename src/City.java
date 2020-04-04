import java.util.*;

public class City {
    private final double RRR = 6378.388;
    private final double pi = 3.1415926;
    private String name;
    private int locationNum;
    private List<Location> coordinates;
    private double[][] distances;

    /**
     * Constructor
     * @param name of the city
     * @param locationNum number of locations in the city
     */
    public City(String name, int locationNum) {
        this.name = name;
        this.locationNum = locationNum;
        this.coordinates = new ArrayList<>();
        this.distances = new double[this.locationNum][this.locationNum];
    }

    /**
     * Getter
     * @return city name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter
     * @return number of locations
     */
    public int getNum() {
        return this.locationNum;
    }

    /**
     * Getter
     * @return list of coordinates
     */
    public List<Location> getCoordinate() {
        return this.coordinates;
    }

    /**
     * Getter
     * @return distances between locations
     */
    public double[][] getDistances() {
        return this.distances;
    }

    /**
     * Setter
     * @param name of the city
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter
     * @param num of locations
     */
    public void setNum(int num) {
        this.locationNum = num;
    }

    /**
     * Setter
     * @param number of the location
     * @param x coordinate
     * @param y coordinate
     */
    public void setCoordinate(int number, double x, double y) {
        Location temp = new Location(number, x, y);
        this.coordinates.add(temp);
    }

    /**
     * Calculate the distances between any two locations
     */
    public void setDistance() {
        for (int i = 0;i < this.locationNum;i++) {
            for (int j = i+1;j < this.locationNum;j++) {
                double x1 = this.coordinates.get(i).getX();
                double y1 = this.coordinates.get(i).getY();
                double x2 = this.coordinates.get(j).getX();
                double y2 = this.coordinates.get(j).getY();
                double dis =  Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
                this.distances[i][j] = dis;
                this.distances[j][i] = dis;
            }
        }
    }


}