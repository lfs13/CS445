// CS 0445 Spring 2014
// Assig3 driver program.  This program must work as is with your
// MyDB class.   Note that this program is primarily testing the additional
// methods showMode() and isSorted().  Use Assig1A.java (the same program from
// Assignment 1) to test the other recursive methods.
public class Assig3 {
	
	public static <T> void testSort(MyDB<T> db) {
		if (db.isSorted())
			System.out.println("The data is sorted");
		else
			System.out.println("The data is not sorted");
	}

	public static void main(String[] args) {
		int[] data1 = { 20, 50, 50, 30, 20, 20, 80, 30, 50, 10, 30, 30, 80, 60,
				50, 20, 30, 30, 70, 60 };
		MyDB<Integer> theDB1 = new MyDB<Integer>(data1.length);

		for (int i = 0; i < data1.length; i++) {
			Integer newItem = new Integer(data1[i]);
			theDB1.addItem(newItem);
		}
		System.out.println(theDB1.toString());
		theDB1.showMode();
		testSort(theDB1);
		System.out.println("Sorting the data...");
		theDB1.sort();
		System.out.println(theDB1.toString());
		testSort(theDB1);
		theDB1.showMode();
		System.out.println();
		//----------------------------------------------------------------//
		String[] data2 = { "Bart", "Gert", "Gert", "Mort", "Mort", "Mort",
				"Marge", "Marge", "Marge", "Marge", "Herb", "Herb", "Herb",
				"Herb", "Herb" };
		MyDB<String> theDB2 = new MyDB<String>(data2.length);
		for (int i = 0; i < data2.length; i++) {
			theDB2.addItem(data2[i]);
		}
		System.out.println(theDB2.toString());
		theDB2.showMode();
		testSort(theDB2);
		System.out.println("Sorting the data...");
		theDB2.sort();
		System.out.println(theDB2.toString());
		testSort(theDB2);
		theDB2.showMode();
		System.out.println();

		int[] data3 = { 10, 10, 10, 10, 20, 30, 40, 40, 40, 40, 40, 50, 50, 50,
				60, 70, 80, 90 };
		MyDB<Integer> theDB3 = new MyDB<Integer>(data1.length);

		for (int i = 0; i < data3.length; i++) {
			Integer newItem = new Integer(data3[i]);
			theDB3.addItem(newItem);
		}
		System.out.println(theDB3.toString());
		theDB3.showMode();
		testSort(theDB3);
		System.out.println("Sorting the data...");
		theDB3.sort();
		System.out.println(theDB3.toString());
		testSort(theDB3);
		theDB3.showMode();
		System.out.println();

		// Note the output for this example. The mode shown differs before and
		// after the sorting. This is because the mode is a tie and it shows the
		// first occurring result. In the initial data "Herb" was found 4 times
		// and
		// is output as the mode. After sorting, "Fritz" is first and is thus
		// found
		// as the mode.
		String[] data4 = { "Herb", "Marge", "Fritz", "Herb", "Marge", "Fritz",
				"Herb", "Marge", "Fritz", "Herb", "Marge", "Fritz" };
		MyDB<String> theDB4 = new MyDB<String>(data4.length);
		for (int i = 0; i < data4.length; i++) {
			theDB4.addItem(data4[i]);
		}
		System.out.println(theDB4.toString());
		theDB4.showMode();
		testSort(theDB4);
		System.out.println("Sorting the data...");
		theDB4.sort();
		System.out.println(theDB4.toString());
		testSort(theDB4);
		theDB4.showMode();
	}
}