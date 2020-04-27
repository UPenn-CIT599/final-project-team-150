import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Handles FileInput
 */
public class FileInput {
    private static HashMap<String, Boolean> map = new HashMap<>();
    static {
        map.put("Atlanta.tsp", true);
        map.put("Berlin.tsp", true);
        map.put("Boston.tsp", true);
        map.put("Champaign.tsp", true);
        map.put("Cincinnati.tsp", true);
        map.put("Denver.tsp", true);
        map.put("NYC.tsp", true);
        map.put("Philadelphia.tsp", true);
        map.put("Roanoke.tsp", true);
        map.put("SanFrancisco.tsp", true);
        map.put("Toronto.tsp", true);
        map.put("UKansasState.tsp", true);
        map.put("UMissouri.tsp", true);

    }
    /**
     * Create a new City Object from a tsp file
     * @param inputFile filepath of the tsp file
     * @return City Object
     * @throws IOException
     */
    public static City readFile(String inputFile) throws IOException {
        // Open the file and read the number of vertices/edges
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String firstLine = br.readLine();
        String name = firstLine.split(":")[1].trim();
        int size = 0;
        while (true) {
            String line = br.readLine();
            if (line.split(":")[0].equals("DIMENSION")) {
                size = Integer.parseInt(line.split(":")[1].trim());
            }
            if (line.equals("NODE_COORD_SECTION")) {
                break;
            }
        }

        City city = new City(name, size);
        while (true) {
            String line = br.readLine();
            if (line.trim().equals("EOF")) {
                break;
            }
            int num = Integer.parseInt(line.trim().split(" ")[0]);
            double x = Double.parseDouble(line.trim().split(" ")[1]);
            double y = Double.parseDouble(line.trim().split(" ")[2]);
            city.setCoordinate(num, x, y);
        }

        br.close();
        city.setDistance();
        return city;
    }

    /**
     * Get the hashmap of filenames
     * @return hashmap of the filenames
     */
    public static HashMap<String, Boolean> getMap() {
        return map;
    }
}