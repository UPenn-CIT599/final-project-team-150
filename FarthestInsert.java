import java.util.*;
import java.lang.Math;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Farthest Insertion algorithm
 */
public class FarthestInsert extends Algorithm{
    double[][] distances;

    /**
     * Constructor for Farthest Insertion algorithm
     * @param city city to implement algorithm
     */
    public FarthestInsert(City city) {
        super();
        distances = city.getDistances();
    }

    /**
     * Return the farthest node
     * @param tempRow temporary rows
     * @param onPath whether the node is onthe path or not
     * @return the maximum index
     */
    public int FarthestNode(double[] tempRow, boolean[] onPath) {

        int maxIndex = 0;
        double max = Double.NEGATIVE_INFINITY;

        for (int i=0; i < tempRow.length; i++)
        {
            if (tempRow[i]>max && !onPath[i]) {
                max = tempRow[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Select a node
     * @param path current path
     * @param matrix distances between locations
     * @return the selected node
     */
    public int SelectNode(List<Integer> path, double[][] matrix) {
        int dim = matrix.length;
        boolean[] onPath = new boolean[dim];
        double max = Double.NEGATIVE_INFINITY;
        int to = 0, from;

        //initialize all nodes to be not on path
        for (int i=0; i<dim; i++) {
            onPath[i] = false;
        }

        //set node on path to be true
        for (Integer integer : path) {
            onPath[integer] = true;
        }

        for (Integer integer : path) {
            double[] tempRow = matrix[integer];
            int tempMax = FarthestNode(tempRow, onPath);
            if (tempRow[tempMax] > max) {
                max = tempRow[tempMax];
                to = tempMax;
                from = integer;
            }
        }
        return to;
    }

    /**
     * Insert a node
     * @param path current path
     * @param matrix distances between locations
     * @param k the index of the selected node
     * @return insertion position
     */
    public int InsertNode(List<Integer> path, double[][] matrix, int k) {

        double min = Double.POSITIVE_INFINITY;
        int from, to;
        int insert = path.get(0);
        int dim = path.size()-1;
        for (int i=0; i<dim; i++) {
            from = path.get(i);
            to = path.get(i+1);
            double dist = matrix[from][k]+matrix[k][to] - matrix[from][to];
            if (dist < min) {
                min = dist;
                insert = i+1;
            }
        }
        return insert;
    }

    /**
     * Method for implementing the Farthest Insertion algorithm on the city and outputing trace file and solution file
     * @param fileName the tsp file that is going to execute the algorithm on
     * @param cutOffTime the cut-off time set by the user
     * @throws IOException
     */
    @Override
    public void programStarts(String fileName, int cutOffTime) throws IOException {
        String traceFile = fileName.split("\\.")[0] + "_" + "FI" + "_" + cutOffTime + "_trace.txt";
        String solutionFile = fileName.split("\\.")[0] + "_" + "FI" + "_" + cutOffTime + "_solution.txt";
        List<Integer> path = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        path.add(0);
        double[] row = distances[0];

        //Find the farthest node to node 0
        int node = 0;
        double max = Double.NEGATIVE_INFINITY;
        for(int i=1; i<row.length; i++){
            if(row[i] > max){
                node = i;
                max = row[i];
            }
        }
        path.add(node);
        path.add(0);

        int choice, insert_position;
        while(path.size() < distances.length+1){
            choice = SelectNode(path, distances);
            insert_position = InsertNode(path, distances, choice);
            path.add(insert_position, choice);
        }

        long endTime = System.currentTimeMillis();
        double totalTime = ((double)endTime - (double)startTime)/1000;

        //Get the farthest distance
        double distance = 0.0;
        for(int i=0; i<path.size()-1; i++) {
            distance += distances[path.get(i)][path.get(i+1)];
        }

        PrintWriter output2 = new PrintWriter(traceFile);
        PrintWriter output1 = new PrintWriter(solutionFile);

        output2.println("|------------------------------------TRACES------------------------------------|");
        output2.printf("%.3f seconds, total distance = %d\n", totalTime, Math.round(distance));

        output1.println("|------------------------------------RESULT------------------------------------|");
        System.out.println("|------------------------------------RESULT------------------------------------|");
        output1.println("Total distance: " + Math.round(distance));
        System.out.println("Total distance: " + Math.round(distance));

        //Get the route of path
        for(int i = path.size() - 2; i >= 0; i--){
            if (i == 0) {
                output1.printf("Location[%02d]", path.get(i));
                System.out.printf("Location[%02d]\n", path.get(i));
            }
            else if (i % 5 == 0) {
                output1.printf("Location[%02d] -> \n", path.get(i));
                System.out.printf("Location[%02d] -> \n", path.get(i));
            }
            else {
                output1.printf("Location[%02d] -> ", path.get(i));
                System.out.printf("Location[%02d] -> ", path.get(i));
            }
        }
//        output1.println(path.get(0));

        output1.close();
        output2.close();
        programEnds();
    }
}