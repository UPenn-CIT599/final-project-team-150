import java.io.IOException;
import java.util.Scanner;

/**
 * Entry of the program
 */
public class Main {

	/**
	 * Inner class for preseting parameters
	 */
	private static class Parameter {
		private int algorithm;
		private String fileName;
		private int cutOffTime;
		public Parameter(int algorithm, String fileName, int cutOffTime) {
			this.algorithm = algorithm;
			this.fileName = fileName;
			this.cutOffTime = cutOffTime;
		}

		public int getAlgorithm() {
			return algorithm;
		}

		public String getFileName() {
			return fileName;
		}

		public int getCutOffTime() {
			return cutOffTime;
		}
	}

	/**
	 * Method for determining algorithm parameters
	 * @return set of parameters designated by user
	 */
	private static Parameter start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("<---Advanced algorithms for Travelling Salesman Problem--->");
		System.out.println("1. Branch and Bound");
		System.out.println("2. Farthest Insertion");
		System.out.println("3. Simulated Annealing");
		System.out.println("4. Iterated Local Search");
		System.out.print("Please select an algorithm [1 - 4]: ");
		String algorithm = scanner.nextLine();
		while (!algorithm.equals("1")
				&& !algorithm.equals("2")
				&& !algorithm.equals("3")
				&& !algorithm.equals("4")) {
			System.out.print("Invalid input, please select a correct number [1 - 4]: ");
			algorithm = scanner.nextLine();
		}
		System.out.print("Please input a city (e.g. Atlanta.tsp): ");
		String city = scanner.nextLine();
		System.out.print("Please input a cut-off time [1 - 600 (seconds)]: ");
		String cutOff = scanner.nextLine();
		scanner.close();
		return new Parameter(Integer.parseInt(algorithm), city, Integer.parseInt(cutOff));

	}

	/**
	 * Main method
	 * @param args arguments
	 * @throws IOException
	 */
    public static void main(String[] args) throws IOException {
		Parameter start = Main.start();
		String traceFile, solutionFile;
        City city = FileInput.readFile("src/DATA/" + start.getFileName());
		switch (start.getAlgorithm()) {
			case 1: // Branch And Bound
				BranchAndBound bb = new BranchAndBound(city.getNum(),city);
				bb.branchBound(start.getFileName(), start.getCutOffTime());
				break;
			case 2: // Farthest Insert

				break;
			case 3: // Simulated Annealing

				break;
			case 4: // Iterated Local Research

				break;
		}
		System.out.println("<---Travelling Salesman Problem Solved--->");
		System.out.println("Program Ends!");
    }
}