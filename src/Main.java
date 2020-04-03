import java.io.IOException;
import java.util.Scanner;

public class Main {
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
    public static void main(String[] args) throws IOException {
		Parameter start = Main.start();
		String traceFile, solutionFile;
        City city = FileIO.readFile("src/DATA/" + start.getFileName());
		switch (start.getAlgorithm()) {
			case 1:
				traceFile = start.getFileName().split("\\.")[0] + "_" + "BB" + "_" + Integer.toString(start.getCutOffTime()) + ".trace";
				solutionFile = start.getFileName().split("\\.")[0] + "_" + "BB" + "_" + Integer.toString(start.getCutOffTime()) + ".sol";
				// BranchAndBound
				break;
			case 2:
				traceFile = start.getFileName().split("\\.")[0] + "_" + "FI" + "_" + Integer.toString(start.getCutOffTime()) + ".trace";
				solutionFile = start.getFileName().split("\\.")[0] + "_" + "FI" + "_" + Integer.toString(start.getCutOffTime()) + ".sol";
				// FarthestInsert
				break;
			case 3:
				traceFile = start.getFileName().split("\\.")[0] + "_" + "SA" + "_" + Integer.toString(start.getCutOffTime()) + ".trace";
				solutionFile = start.getFileName().split("\\.")[0] + "_" + "SA" + "_" + Integer.toString(start.getCutOffTime()) + ".sol";
				// Simulated Annealing
				break;
			case 4:
				traceFile = start.getFileName().split("\\.")[0] + "_" + "LS" + "_" + Integer.toString(start.getCutOffTime()) + ".trace";
				solutionFile = start.getFileName().split("\\.")[0] + "_" + "LS" + "_" + Integer.toString(start.getCutOffTime()) + ".sol";
				// Iterated Local Research
				break;
		}
    }
}