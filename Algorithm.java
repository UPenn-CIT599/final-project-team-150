import java.io.IOException;

/**
 * Super class for all the algorithms
 */
public abstract class Algorithm {
    public Algorithm() {}

    /**
     * The main program of the algorithm
     * @param fileName the tsp file that is going to implement the algorithm on
     * @param cutOffTime the cut-off time set by the user
     * @throws IOException
     */
    public abstract void programStarts(String fileName, int cutOffTime) throws IOException;

    /**
     * Wrapping up after the algorithm finishes
     */
    public void programEnds() {
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("|---------------------------------PROGRAM ENDS---------------------------------|");
        System.out.println("|------------------------------------------------------------------------------|");
    };
}
