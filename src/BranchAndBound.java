import java.util.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.lang.*;

public class BranchAndBound extends Algorithm{
    private int nums;
    private int[] finalPath;
    private boolean[] visited;
    private double finalCost;
    private List<List<Double>> output;
    private City city;

    /**
     * Constructor for Branch and Bound algorithm
     * @param city city to implement algorithm
     */
    public BranchAndBound(City city) {
        super();
        this.nums = city.getNum();
        this.finalPath = new int[city.getNum()+1];
        this.visited = new boolean[city.getNum()];
        this.finalCost = Double.MAX_VALUE;
        this.city = city;
        this.output = new ArrayList<>();
    }

    /**
     * Method for updating the path
     * @param path previous path
     */
    private void convertToFinal(int[] path) {
        for (int i = 0; i < this.nums; i++) {
            this.finalPath[i] = path[i];
        }
        this.finalPath[this.nums] = path[0];
    }


    /**
     * Method for finding the minimum edge cost
     * @param i location
     * @return the minimum edge cost
     */
    public double firstMin(int i) {
        double min = Double.MAX_VALUE;
        for (int j = 0;j < this.nums;j++) {
            if (this.city.getDistances()[i][j] < min && j !=i) min = this.city.getDistances()[i][j];
        }
        return min;
    }

    /**
     * Method for finding the second minimum edge cost
     * @param i location
     * @return the second minimum edge cost
     */
    public double secondMin(int i) {
        double firstmin = Double.MAX_VALUE;
        double secondmin = Double.MAX_VALUE;
        for (int j = 0;j < this.nums;j++) {
            if (i == j) continue;
            if (this.city.getDistances()[i][j] <= firstmin ) {
                secondmin = firstmin;
                firstmin = this.city.getDistances()[i][j];
            }
            else if (this.city.getDistances()[i][j] <= secondmin && this.city.getDistances()[i][j] != firstmin) secondmin = this.city.getDistances()[i][j];
        }
        return secondmin;
    }

    /**
     * Reset the visiting status to false
     */
    private void resetVisit() {
        Arrays.fill(this.visited,false);
    }

    /**
     * Recursion body of the Branch and Bound algorithm
     * @param currBound current bound
     * @param currCost current cost
     * @param level current level
     * @param currpath current path
     * @param start starting time
     * @param outfile1 trace file
     * @param outfile2 solution file
     * @param cut_off cut off time
     * @throws IOException
     */
    private void recursion(double currBound,double currCost,int level,int[] currpath,long start, String outfile1, String outfile2, int cut_off) throws IOException {
        if (level == this.nums && this.city.getDistances()[currpath[level-1]][currpath[0]] != 0.0) {
            double tempCurrCost = currCost + this.city.getDistances()[currpath[level-1]][currpath[0]];
            if (tempCurrCost < this.finalCost) {
                convertToFinal(currpath);
                this.finalCost = tempCurrCost;
                if ((double)(System.currentTimeMillis()- start) / 1000 > cut_off) {
                    PrintWriter output1 = new PrintWriter(outfile1);
                    for (int i = 0; i < output.size(); i++) {
                        output1.println(output.get(i).get(0) + "," + Math.round(output.get(i).get(1)));
                    }
                    output1.close();
                    PrintWriter output2 = new PrintWriter(outfile2);
                    int[] path = this.finalPath;
                    output2.println((int)this.finalCost);
                    for (int i = path.length - 1; i >= 0; i--) {
                        if (i == 0) {
                            output2.printf("%d", path[i]);
                        } else {
                            output2.printf("%d,", path[i]);
                        }
                    }
                    output2.close();
                    programEnds();
                    System.exit(0);
                }


                List<Double> temp = new ArrayList<>();
                temp.add((double) (System.currentTimeMillis() - start) / 1000);
                temp.add(this.finalCost);

                output.add(temp);
            }
            return;
        }
        for (int i = 0; i < this.nums; i++) {
            if(this.city.getDistances()[currpath[level-1]][i] != 0.0 && !this.visited[i]) {
                double temp = currBound;
                currCost += this.city.getDistances()[currpath[level-1]][i];
                if(level == 1) {
                    currBound = (firstMin(currpath[level-1]) + firstMin(i))/2;
                }
                else {
                    currBound = (secondMin(currpath[level-1]) + firstMin(i))/2;
                }
                if (currBound + currCost < this.finalCost) {
                    currpath[level] = i;
                    this.visited[i] = true;
                    recursion(currBound, currCost, level + 1, currpath, start, outfile1, outfile2, cut_off);
                }
                currCost -= this.city.getDistances()[currpath[level - 1]][i];
                currBound = temp;
                resetVisit();
                for (int j = 0;j < level; j++) this.visited[currpath[j]] = true;
            }
        }
    }

    /**
     * Method for implementing the Branch and Bound algorithm on the city and outputing trace file and solution file
     * @param fileName given file name
     * @param cutOffTime given cut off time
     * @throws IOException
     */
    @Override
    public void programStarts(String fileName, int cutOffTime) throws IOException {
        String traceFile = fileName.split("\\.")[0] + "_" + "BB" + "_" + cutOffTime + "_trace.txt";
        String solutionFile = fileName.split("\\.")[0] + "_" + "BB" + "_" + cutOffTime + "_solution.txt";
        long start = System.currentTimeMillis();
        int[] currPath = new int[this.nums];
        double currBound = 0.0;
        for (int i = 0;i < this.nums;i++) currPath[i] = -1;
        resetVisit();
        for (int i = 0; i < this.nums; i++) currBound += firstMin(i) + secondMin(i);
        currBound = currBound/2;
        this.visited[0] = true;
        currPath[0] = 0;
        recursion(currBound, 0.0, 1, currPath, start, traceFile, solutionFile, cutOffTime);
        PrintWriter output1 = new PrintWriter(traceFile);
        for (List<Double> doubles : output) output1.println(doubles.get(0) + "," + Math.round(doubles.get(1)));
        output1.close();
        PrintWriter output2 = new PrintWriter(solutionFile);
        int[] path = this.finalPath;
        output2.println((int) this.finalCost);
        for (int i = path.length - 1; i >= 0; i--) {
            if (i == 0) output2.printf("%d", path[i]);
            else output2.printf("%d,", path[i]);
        }
        output2.close();
        programEnds();
    }
}