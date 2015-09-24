// CS 0445 Spring 2014
// Test program for your LinkedListPlus class -- for full credit you CANNOT MODIFY
// this code in ANY WAY.

// This program should execute without error and produce output identical to
// the output shown on the Web site.  If your output does not match mine, think
// carefully about what your operations are doing and trace them to find the
// problem.

// If your output does not match mine, or if you must change this file to get
// your code to work, you will lose credit, but you can still get PARTIAL
// credit for your work, so be sure to turn something in no matter what!

import java.util.*;

public class LLPTest {
	static final String[] data = { "Tori", "Sarah", "Kate", "Aimee", "Shirley",
			"Chrissy", "Debbie", "Alanis", "Courtney", "Sinead" };

	public static void main(String[] args) {
		// Testing constructor
		LinkedListPlus<Integer> L1 = new LinkedListPlus<Integer>();
		for (int i = 0; i < 10; i++) {
			L1.add(new Integer(i));
		}
		System.out.println("L1: " + L1.toString());

		// Testing copy constructor
		LinkedListPlus<Integer> L2 = new LinkedListPlus<Integer>(L1);
		System.out.println("L2: " + L2.toString());

		System.out.println("L1.leftShift(2)");
		// Testing shift and rotate methods
		L1.leftShift(2);
		System.out.println("\tL1: " + L1.toString());

		System.out.println("L2.rightShift(3)");
		L2.rightShift(3);
		System.out.println("\tL2: " + L2.toString());
			
		System.out.println("L1.leftRotate(4)");
		L1.leftRotate(4);
		System.out.println("\tL1: " + L1.toString());
		
		System.out.println("L2.rightRotate(2)");
		L2.rightRotate(2);
		System.out.println("\tL2: " + L2.toString());

		System.out.println("Reversing L1");
		L1.reverse();
		System.out.println("\tL1: " + L1.toString());
		System.out.println("Reversing L1 again");
		L1.reverse();
		System.out.println("\tL1: " + L1.toString());
		System.out.println();

		// Note that shifting is not limited to the length of the list. If the
		// shift is greater than the length of the list it should "wrap" as many
		// times as necessary. However, do not shift unnecessarily -- think
		// about
		// how you can do this very efficiently. Also note that negative shifts
		// work as expected!
		LinkedListPlus<String> L3 = new LinkedListPlus<String>();
		for (int i = 0; i < data.length; i++)
			L3.add(data[i]);
		System.out.println("L3: " + L3.toString());
		System.out.println("L3.leftRotate(14)");
		L3.leftRotate(14);
		System.out.println("\tL3: " + L3.toString());
		System.out.println("L3.rightRotate(23)");
		L3.rightRotate(23);
		System.out.println("\tL3: " + L3.toString());
		System.out.println("L3.rightRotate(-3)");
		L3.rightRotate(-3);
		System.out.println("\tL3: " + L3.toString());
		System.out.println("L3.leftRotate(-4)");
		L3.leftRotate(-4);
		System.out.println("\tL3: " + L3.toString());
	}
}