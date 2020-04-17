import java.util.*;
import java.io.PrintWriter;
import java.io.IOException;

public class SimulatedAnnealing extends Algorithm{
    double[][] distances;
    public SimulatedAnnealing(City city) {
        super();
        distances = city.getDistances();
    }

    @Override
    public void programStarts(String fileName, int cutOffTime) throws IOException{
        double temperature = 10000;
        double coolRate = 0.01;
        int seed = 0;
        String traceFile = fileName.split("\\.")[0] + "_" + "SA" + "_" + cutOffTime + "_trace.txt";
        String solutionFile = fileName.split("\\.")[0] + "_" + "SA" + "_" + cutOffTime + "_solution.txt";

        List<Integer> path = generateRandomPath(distances, seed);
        List<Long> traceList = new ArrayList<>();
        List<Integer> bestRoute = new ArrayList<>();
        int num = distances.length;
        int num_iterate = 0;
        double best_distance = Double.POSITIVE_INFINITY;
        Random rand = new Random(seed);
        PrintWriter output2 = new PrintWriter(solutionFile);

        // The initial total distance of path
        double distance = getDistance(distances, path);
        double init = distance;
        // System.out.println("The initial total distance of path is: " + distance);

        // Setup the termination time
        long starttime = System.currentTimeMillis();
        long terminTime = starttime + (long)cutOffTime * 1000;

        while( System.currentTimeMillis() < terminTime){

            // 1000 times run for constant temperature
            for(int i=0; i<1000; i++){

                //When temperature is low, reset the temperature
                if(temperature < 0.1){
                    temperature = 10000;
                    path = generateRandomPath(distances, seed);
                }

                // Tracking the number of iteration
                num_iterate ++;

                // Randomly generate two index for swapping
                int first_index = rand.nextInt(num);
                int second_index = rand.nextInt(num);
                while(first_index == second_index){
                    second_index = rand.nextInt(num);
                }

                //Swap two nodes we choose
                int temp = path.get(first_index);
                path.set(first_index, path.get(second_index));
                path.set(second_index, temp);

                // Calculate the new distance after swapping
                double newdistance = getDistance(distances, path);

                if(acceptProb(distance, newdistance, temperature) < rand.nextDouble()){
                    temp = path.get(first_index);
                    path.set(first_index, path.get(second_index));
                    path.set(second_index, temp);
                    continue;
                }

                // traceList.add((long)newdistance);
                distance = newdistance;

                if(distance < best_distance){
                    best_distance = distance;
                    traceList.add((long)distance);
                    output2.println((double)(System.currentTimeMillis()-starttime)/1000 + " " + Math.round(distance));
                    bestRoute = path;
                }
            }

            temperature *= 1.0 - coolRate;
        }

        bestRoute.add(bestRoute.get(0));
        PrintWriter output1 = new PrintWriter(traceFile);
        output1.println(Math.round(best_distance));
        for(Integer i : bestRoute){
            output1.print(bestRoute.get(i) + " ");
        }

        output1.close();

        output2.close();

        // System.out.println("The total number of iterations is: " + num_iterate);
        // System.out.println("The initial total distance of path is: " + init);
        // System.out.println("The final distance is: " + distance);
        // System.out.println("The best distance is: " + best_distance);

//        AnnealResult result = new AnnealResult(path, traceList);
//        return result;
        programEnds();
    }

    public List<Integer> generateRandomPath(double[][] graph, long seed){
        int len = graph.length;
        List<Integer> randomPath = new ArrayList<>();
        for(int i=0; i<len; i++){
            randomPath.add(i);
        }

        Random rand = new Random(seed);
        Collections.shuffle(randomPath, rand);
        return randomPath;
    }

    public double acceptProb(double distance, double newdistance, double temperature){

        if (newdistance < distance) {
            return 1.0;
        }
        return Math.exp((distance - newdistance) / temperature);
    }


    public double getDistance(double[][] graph, List<Integer> path){
        double distance = 0;
        for(int i=0; i<path.size()-1; i++){
            distance += graph[path.get(i)][path.get(i+1)];
        }
        distance += graph[path.get(path.size()-1)][path.get(0)];
        return distance;
    }
}