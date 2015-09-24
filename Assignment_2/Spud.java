public class Spud implements Comparable {
	private String supplier;
	private int date;
	private double cost;

	public Spud(String s, int d, double c) {
		supplier = s;
		date = d;
		cost = c;
	}

	@Override
	public int compareTo(Object arg0) {
		Spud temp = (Spud) arg0;

		if (temp.date < date)
			return -1;
		if (temp.date > date)
			return 1;
		else if (temp.date == date) {
			if (temp.cost < cost)
				return -1;
			if (temp.cost > cost)
				return 1;
		}
		return 0;

	}

	public String getSupplier() {
		return supplier;
	}

	public int getDate() {
		return date;

	}

	public double getCost() {
		return cost;
	}

	public String toString() {
		return ("Supplier: " + supplier + " Expires: " + date + " Cost: " + cost);

	}

}
