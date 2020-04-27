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
		System.out.println("|------------------------------------------------------------------------------|");
		System.out.println("|-------------Advanced algorithms for Travelling Salesman Problem--------------|");
		System.out.println("|------------------------------------------------------------------------------|");
		System.out.println("ALGORITHM [1] -> Branch   and    Bound");
		System.out.println("ALGORITHM [2] -> Farthest    Insertion");
		System.out.println("ALGORITHM [3] -> Simulated   Annealing");
		System.out.println("ALGORITHM [4] -> Iterated Local Search");
		
		String algorithm, city;
		int cutOff;

		do {
			System.out.print("Please select an algorithm [1 - 4]: ");
			algorithm = scanner.nextLine();
		} while (!algorithm.equals("1")
		&& !algorithm.equals("2")
		&& !algorithm.equals("3")
		&& !algorithm.equals("4"));

		do {
			System.out.print("Please input a city (e.g. Atlanta.tsp): ");
			city = scanner.nextLine();
		} while (!FileInput.getMap().containsKey(city));

		do {
			System.out.print("Please input a cut-off time [1 - 600 (seconds)]: ");
			cutOff = scanner.nextInt();
		} while (cutOff < 1 || cutOff > 600);

		scanner.close();
		return new Parameter(Integer.parseInt(algorithm), city, cutOff);
	}

	/**
	 * Main method
	 * @param args arguments
	 * @throws IOException
	 */
    public static void main(String[] args) throws IOException {
		Parameter info = Main.start();
		System.out.println("PROGRAM RUNNING...");
		City city = FileInput.readFile("DATA/" + info.getFileName());
		switch (info.getAlgorithm()) {
			case 1: // Branch And Bound
				BranchAndBound bb = new BranchAndBound(city);
				bb.programStarts(info.getFileName(), info.getCutOffTime());
				break;
			case 2: // Farthest Insert
				FarthestInsert fi = new FarthestInsert(city);
				fi.programStarts(info.getFileName(), info.getCutOffTime());
				break;
			case 3: // Simulated Annealing
				SimulatedAnnealing sa = new SimulatedAnnealing(city);
				sa.programStarts(info.getFileName(), info.getCutOffTime());
				break;
			case 4: // Iterated Local Research
				Iteratedlocalsearch ils = new Iteratedlocalsearch(city);
				ils.programStarts(info.getFileName(), info.getCutOffTime());
				break;
		}
    }
}