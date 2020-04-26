import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Iteratedlocalsearch extends Algorithm {
    private int nums;
    private int[] finalPath;
    private double finalCost;
    private int[] currentPath;
    private double currentCost;
    private City c;
    private Random rand;
    private List<List<Double>> output;


    public Iteratedlocalsearch(City city){
        super();
        this.nums = city.getNum();
        this.finalPath = new int[city.getNum()];
        this.finalCost = Double.MAX_VALUE;
        this.currentPath = new int[city.getNum()];
        this.currentCost = Double.MAX_VALUE;
        this.c = city;
        this.rand = new Random(0);
        this.output = new ArrayList<>();

    }

    /////generate initial random path
    public void changePosition(int[] path) {
        for(int index=path.length-1; index>=0; index--) {
            swap(path,this.rand.nextInt(index+1), index);
        }
    }


    private int[] generateRandomPath(double[][] graph){
        int len = graph.length;
        int[] randomPath = new int[len];
        for(int i=0; i<len; i++){
            randomPath[i] = i;
        }
        changePosition(randomPath);
        return randomPath;
    }

    private  double getCost(double[][] graph, int[] path){
        double distance = 0;
        for(int i = 0; i < path.length-1; i++){
            distance += graph[path[i]][path[i+1]];
        }
        distance += graph[path[path.length-1]][path[0]];
        return distance;
    }

    /////exchange parts of current path
    private void addPerturtation(){
        int len = this.finalPath.length;
        int end1 = 1 + Math.abs(this.rand.nextInt()) % (len/3);
        int end2 = end1 + Math.abs(this.rand.nextInt()) % (len/3);
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0;i < end1;i++){
            list.add(this.finalPath[i]);
        }
        for(int i = end2;i < len;i++){
            list.add(this.finalPath[i]);
        }
        for (int i = end1;i < end2;i++){
            list.add(this.finalPath[i]);
        }
        for(int i = 0;i < len;i++){
            this.currentPath[i] = list.pollFirst();
        }


    }



    private int[] swap(int[] currPath, int i, int j){
        int temp = currPath[i];
        currPath[i] = currPath[j];
        currPath[j] = temp;
        return currPath;
    }

    private  double localSearch(int[] path,double cost, double[][] graph, int maxImprove){
        int count = 0;
        int len = path.length;
        int[] currPath = new int[len];
        double temp = 0.0;
        while (count < maxImprove){
            for(int i = 0;i < len-1;i++){
                for(int j = i+1;j < len;j++){
                    for(int k = 0;k < len;k++){
                        currPath[k] = path[k];
                    }
                    //// swap two elements of current path
                    currPath = swap(currPath,i,j);
                    double currentCost = getCost(graph,currPath);
                    if(currentCost < cost){

                        count = 0;
                        for (int k = 0; k < len ; k++) {
                            path[k] = currPath[k];
                        }
                        cost = currentCost;
                        temp = currentCost;
                    }
                }
            }
            count++;
        }
        return temp == 0.0?cost:temp;
    }

    private void perturbation(double[][] graph){
        addPerturtation();
        this.currentCost = getCost(graph,currentPath);
    }
    private void printpath(int[] path){
        for (int i = 0; i < this.nums; i++) {
            System.out.println(path[i]);
        }
    }

    public int[] getFinalPath(){
        return this.finalPath;
    }

    public double getFinalCost(){
        return this.finalCost;
    }

    @Override
    public void programStarts(String fileName, int cutOffTime) throws IOException {
        int maxSearchTimes = 1000000000;
        int maxImprove = 50;
        String traceFile = fileName.split("\\.")[0] + "_" + "ILS" + "_" + cutOffTime + "_trace.txt";
        String solutionFile = fileName.split("\\.")[0] + "_" + "ILS" + "_" + cutOffTime + "_solution.txt";
        long start = System.currentTimeMillis();
        double[][] graph = this.c.getDistances();
        this.finalPath = generateRandomPath(graph);
        this.finalCost = getCost(graph, this.finalPath);
        int len = this.finalPath.length;
        this.finalCost = localSearch(this.finalPath,this.finalCost,graph,maxImprove);

        for (int i = 0; i < maxSearchTimes; i++) {
            perturbation(graph);
            this.currentCost = localSearch(this.currentPath, this.currentCost, graph, maxImprove);
            if ((double)(System.currentTimeMillis()- start) / 1000 > cutOffTime) {
                break;
            }

            if (this.currentCost < this.finalCost) {
                for (int j = 0; j < len; j++) {
                    this.finalPath[j] = this.currentPath[j];
                }
                this.finalCost = this.currentCost;
                List<Double> temp = new ArrayList<>();
                temp.add((double) (System.currentTimeMillis()- start) / 1000);
                temp.add(this.finalCost);
                output.add(temp);
            }
        }
        PrintWriter output1 = new PrintWriter(traceFile);
        output1.println("|------------------------------------TRACES------------------------------------|");
        for (List<Double> doubles : output) {
            output1.printf("%.3f seconds, total distance = %d\n", doubles.get(0), Math.round(doubles.get(1)));
        }
        output1.close();
        PrintWriter output2 = new PrintWriter(solutionFile);
        int[] path = this.finalPath;
        output2.println("|------------------------------------RESULT------------------------------------|");
        System.out.println("|------------------------------------RESULT------------------------------------|");
        output2.println("Total distance: " + (int) this.finalCost);
        System.out.println("Total distance: " + (int) this.finalCost);
        for (int i = path.length - 1; i >= 0; i--) {
            if (i == 0) {
                output2.printf("Location[%02d]", path[i]);
                System.out.printf("Location[%02d]\n", path[i]);
            }
            else if (i % 5 == 0) {
                output2.printf("Location[%02d] -> \n", path[i]);
                System.out.printf("Location[%02d] -> \n", path[i]);
            }
            else {
                output2.printf("Location[%02d] -> ", path[i]);
                System.out.printf("Location[%02d] -> ", path[i]);
            }
        }
        output2.close();
        programEnds();
    }
}
