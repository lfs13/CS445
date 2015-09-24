// CS 0445 Spring 2014
// Assig1A driver program.  This program must work as is with your
// MyDB class.  Look carefully at all of the method calls so that
// you create your MyDB methods correctly.  For example, note the
// constructor calls and the toString() method calls.
public class Assig1A {
	public static void main(String[] args) {
		// Testing constructor
		SimpleDB<Integer> theDB = new MyDB<Integer>(4);

		// Testing addItem and resizing
		for (int i = 0; i < 10; i++) {
			Integer newItem = new Integer(2 * i);
			theDB.addItem(newItem);
			System.out.println(newItem + " added to DB");
		}
		theDB.addItem(new Integer(2)); // duplicate
		System.out.println("2 added to DB");
		System.out.println();

		// Testing the SaveRestore interface
		((SaveRestore) theDB).saveToFile("A1A.out");
		System.out.println("The DB has been saved");
		theDB.clear();
		System.out.println("The DB has been cleared");
		System.out.println("The DB has " + theDB.size() + " items");
		boolean empty = theDB.isEmpty();
		if (empty)
			System.out.println("The DB is empty");

		((SaveRestore) theDB).restoreFromFile("A1A.out");

		System.out.println("DB restored");
		System.out.println("The DB has " + theDB.size() + " items");
		System.out.println();

		// Testing findItem
		int[] testItems = { 6, 3, 7, 2, 14, 15, 10, 2, 6 };
		for (int x : testItems) {
			Integer key = new Integer(x);
			Integer oldItem = theDB.findItem(key);
			if (oldItem != null)
				System.out.println(oldItem + " was found in the DB");
			else
				System.out.println(key + " was not found");
		}
		System.out.println();

		// Testing removeItem
		for (int x : testItems) {
			Integer key = new Integer(x);
			Integer oldItem = theDB.removeItem(key);
			if (oldItem != null)
				System.out.println(oldItem + " was removed from the DB");
			else
				System.out.println(key + " was not found");
		}
		System.out.println();

		System.out.println(theDB.toString());
		int sz = theDB.size();
		System.out.println("There are " + sz + " items in the DB");
		System.out.println();

		// This code will test the Reverser and Sorter interfaces.
		String[] data = { "Fezzik", "Inigo", "Humperdinck", "Roberts",
				"Buttercup", "Vizzini", "Rugen" };
		SimpleDB<String> newDB = new MyDB<String>(10);
		Reverser R = (Reverser) newDB;
		Sorter S = (Sorter) newDB;
		for (int i = 0; i < data.length; i++) {
			newDB.addItem(new String(data[i]));
		}
		System.out.println(newDB.toString());
		System.out.println("Reversing the data...");
		R.reverse();
		System.out.println(newDB.toString());
		System.out.println("Sorting the data...");
		S.sort();
		System.out.println(newDB.toString());
		String bogus = newDB.removeItem("Humperdinck");
		bogus = newDB.removeItem("Rugen");
		newDB.addItem(new String("Max"));
		System.out.println(newDB.toString());
		System.out.println("Reversing the data...");
		R.reverse();
		System.out.println(newDB.toString());
		System.out.println("Sorting the data...");
		S.sort();
		System.out.println(newDB.toString());
	}
}