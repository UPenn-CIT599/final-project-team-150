import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Handles FileInput
 */
public class FileInput {
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
}