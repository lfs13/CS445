import java.io.*;
import java.util.*;

public class Assig2 {
	private int date;
	private int current_boxes = 0;
	private File file;
	private Scanner input;
	private StackInterface<Spud> stack;
	private int lastboxes, totalboxes, lastmoves, totalmoves;
	private double lastpotcost, potcost, lastlabcost, labcost;

	public static void main(String[] args) {
		Assig2 A2 = new Assig2(args[0]);
	}

	public Assig2(String fileName) {
		file = new File(fileName);
		stack = new LinkedStack<Spud>();
		readFile(file);
	}

	public void readFile(File f) {
		try {
			System.out.println("Starting simulation on day 0");
			System.out.println();
			input = new Scanner(f);

			while (input.hasNextLine()) {

				String s = input.nextLine();
				// System.out.println(s);

				if (s.equalsIgnoreCase("receive")) {
					int i = (int) new Integer(
							Integer.parseInt(input.nextLine()));
					System.out.println("Processing shipment of " + i
							+ " boxes of potatoes");
					receive(i);

					System.out.println("Added " + lastboxes
							+ " boxes to the stack");
					System.out.println();
				}

				if (s.equalsIgnoreCase("use")) {
					int i = (int) new Integer(
							Integer.parseInt(input.nextLine()));
					System.out.println(i + " boxes of potatoes are needed");
					use(i);

				}

				if (s.equalsIgnoreCase("display")) {
					System.out.println("Here are your boxes (top to bottom)");
					display();
				}

				if (s.equalsIgnoreCase("skip")) {
					skip();
				}

				if (s.equalsIgnoreCase("report")) {
					report();

				}

				if (s.equalsIgnoreCase("quit")) {
					quit();

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void receive(int i) {
		int moves = 0;
		int count = 0;
		double cost = 0;
		int expired = 0;

		Spud[] temp = new Spud[i + current_boxes];

		for (int j = 0; j < i; j++) {
			String[] broken = input.nextLine().split("\\:");
			String s = broken[0];
			int d = Integer.parseInt(broken[1]);
			double c = Double.parseDouble(broken[2]);
			temp[j] = (new Spud(s, d, c));
			cost += temp[j].getCost();
			moves++;

		}

		if (!stack.isEmpty())
			for (int k = 0; k < current_boxes; k++) {
				Spud t = stack.pop();
				temp[i + k] = t;
				moves++;

			}

		Arrays.sort(temp);

		for (int j = 0; j < i + current_boxes; j++) {
			if (!expired(temp[j])) {
				stack.push(temp[j]);
				moves++;
				count++;
			} else {
				expired++;
				moves--;
				cost -= temp[j].getCost();
				System.out.println("BOX EXPIRED: " + temp[j].toString());
			}

		}

		
		lastboxes = i-expired;
		totalboxes += lastboxes;
		current_boxes = lastboxes;
		// //
		lastpotcost = cost;
		potcost += lastpotcost;
		// //
		lastmoves = moves;
		totalmoves += lastmoves;
		// //
		lastlabcost = (double) moves;
		labcost += lastlabcost;

	}

	/*
	 * USE METHOD pops off number of boxes based on parameter if not enough it
	 * will say so
	 */
	public void use(int count) {
		if (count > current_boxes)
			System.out.println("Not enough boxes!");
		else {
			for (int i = 0; i < count; i++) {
				Spud t = stack.pop();
				System.out.println(t.toString());
				current_boxes--;
			}
		}
		System.out.println();

	}

	public void display() {
		Spud[] temp = new Spud[current_boxes];
		for (int i = 0; i < current_boxes; i++) {
			Spud t = stack.pop();
			temp[i] = t;
			System.out.println(temp[i].toString());
		}
		for (int i = temp.length - 1; i >= 0; i--) {
			stack.push(temp[i]);
		}
		System.out.println();

	}

	public void skip() {
		date++;
		System.out.println("The current day is now Day " + date);
		Spud[] temp = new Spud[current_boxes];
		for (int i = 0; i < current_boxes; i++) {
			Spud t = stack.pop();
			temp[i] = t;
		}
		for (int i = temp.length - 1; i >= 0; i--) {
			if (expired(temp[i])) {
				System.out.println("BOX EXPIRED: " + temp[i].toString());
				current_boxes--;
			} else
				stack.push(temp[i]);
		}
		System.out.println();

	}

	public void report() {
		System.out.println("Spuds Spuds and Spuds Semi-Irregular Report:");
		System.out.println("        Most Recent Shipment:");
		System.out.println("                Boxes: " + lastboxes);
		System.out.println("                Potato cost: " + lastpotcost);
		System.out.println("                Labor (moves): " + lastmoves);
		System.out.println("                Labor cost: " + lastlabcost);
		System.out.println("                ----------------------");
		System.out.println("                Total: "
				+ (lastpotcost + lastlabcost));
		System.out.println();
		System.out.println("        Overall Expenses:");
		System.out.println("                Boxes: " + totalboxes);
		System.out.println("                Potato cost: " + potcost);
		System.out.println("                Labor (moves): " + totalmoves);
		System.out.println("                Labor cost: " + labcost);
		System.out.println("                ----------------------");
		System.out
				.println("                Total cost: " + (potcost + labcost));
		System.out.println();

	}

	public void quit() {
		System.out.println("End of simulation");
		System.exit(0);
	}

	public boolean expired(Spud t) {
		if (t.getDate() < date)
			return true;
		else
			return false;

	}
}
