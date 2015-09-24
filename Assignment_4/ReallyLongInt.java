// CS 0445 Spring 2014 Assignment 4
// This is a partial implementation of the ReallyLongInt class.  You need to
// complete the implementations of the remaining methods.  Also, for this class
// to work, you must complete the implementation of the LinkedListPlus class.
// See additional comments below.

import java.util.*;

public class ReallyLongInt extends LinkedListPlus<Integer> implements
		Comparable<ReallyLongInt> {
	// Default constructor
	public ReallyLongInt() {

	}

	// Note that we are adding the digits here in the FRONT. This is more
	// efficient
	// (no traversal is necessary) and results in the LEAST significant digit
	// first
	// in the list.
	public ReallyLongInt(String s) {
		super();
		char c;
		int digit;
		// Iterate through the String, getting each character and converting it
		// into
		// an int. Then make an Integer and add at the front of the list.
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (('0' <= c) && (c <= '9')) {
				digit = c - '0';
				this.add(1, new Integer(digit));
			}
		}
	}

	// Simple call to super to copy the nodes from the argument ReallyLongInt
	// into a
	// new one.
	public ReallyLongInt(ReallyLongInt rightOp) {
		super(rightOp);
	}

	// Method to put digits of number into a String. Since the numbers are
	// stored "backward" (least significant digit first) we first reverse the
	// number, then traverse it to add the digits to a StringBuilder, then
	// reverse it again. This seems like a lot of work, but accessing the list
	// from back to front using repeated calls to the getEntry() method is
	// actually
	// much more work -- we will discuss this in lecture after break.
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (numberOfEntries > 0) {
			this.reverse();
			Node curr = firstNode;
			for (int n = 1; n <= numberOfEntries; n++) {
				sb.append(curr.data);
				curr = curr.next;
			}
			this.reverse();
		}
		return sb.toString();
	}

	// You must implement the methods below. See the descriptions in the
	// assignment
	// sheet.
	//DID NOT USE getEntry()//
	public ReallyLongInt add(ReallyLongInt rightOp) {
		Node leftcurr = firstNode;
		Node rightcurr = rightOp.firstNode;
		int carry = 0;
		ReallyLongInt temp = new ReallyLongInt();

		// same digits--------------------------------------------------//
		if (numberOfEntries == rightOp.numberOfEntries) {
			for (int i = 1; i <= numberOfEntries; i++) {
				int sum = leftcurr.getData() + rightcurr.getData() + carry;
				int place = sum % 10;
				carry = sum / 10;
				temp.add(place);
				if (i != numberOfEntries) {
					leftcurr = leftcurr.next;
					rightcurr = rightcurr.next;
				}
			}
			if (carry != 0)
				temp.add(carry);
		}

		// right has more digits----------------------------------------//
		if (numberOfEntries < rightOp.numberOfEntries) {

			// adding with 2 digits
			for (int i = 1; i <= numberOfEntries; i++) {
				int sum = leftcurr.getData() + rightcurr.getData() + carry;
				int place = sum % 10;
				carry = sum / 10;
				temp.add(place);
				leftcurr = leftcurr.next;
				rightcurr = rightcurr.next;
			}
			// adding with 1 digit
			for (int i = 1; i <= (rightOp.numberOfEntries - numberOfEntries); i++) {
				int sum = rightcurr.getData() + carry;
				int place = sum % 10;
				carry = sum / 10;
				temp.add(place);
				rightcurr = rightcurr.next;

			}
			if (carry != 0)
				temp.add(carry);
		}

		// left has more digits-----------------------------------------//
		if (numberOfEntries > rightOp.numberOfEntries) {

			// adding with 2 digits
			for (int i = 1; i <= rightOp.numberOfEntries; i++) {
				int sum = leftcurr.getData() + rightcurr.getData() + carry;
				int place = sum % 10;
				carry = sum / 10;
				temp.add(place);
				leftcurr = leftcurr.next;
				rightcurr = rightcurr.next;

			}
			// adding with 1 digit
			for (int i = 1; i <= (numberOfEntries - rightOp.numberOfEntries); i++) {
				int sum = leftcurr.getData() + carry;
				int place = sum % 10;
				carry = sum / 10;
				temp.add(place);
				leftcurr = leftcurr.next;

			}
			if (carry != 0)
				temp.add(carry);
		}
		return temp;
	}
	
	//DID NOT USE getEntry()//
	public int compareTo(ReallyLongInt rOp) {
		Integer current = new Integer(numberOfEntries);
		Integer other = new Integer(rOp.numberOfEntries);
		if (!((current.compareTo(other)) == 0)) {
			return current.compareTo(other);
		} else {
			Node curfirst = firstNode;
			Node otherfirst = rOp.firstNode;

			for (int i = 1; i <= numberOfEntries; i++) {
				if ((curfirst.getData().compareTo(otherfirst.getData())) < 0)
					return -1;
				if ((curfirst.getData().compareTo(otherfirst.getData())) > 0)
					return 1;
				curfirst = curfirst.next;
				otherfirst = otherfirst.next;
			}
			return 0;
		}
	}

	public boolean equals(Object rightOp) {
		ReallyLongInt send = (ReallyLongInt) rightOp;
		if (this.compareTo(send) == 0)
			return true;

		return false;
	}

	public void multTenToThe(int num) {
		for (int i = 0; i < num; i++) {
			Node temp = new Node(0);
			temp.setNextNode(firstNode);
			firstNode = temp;
			numberOfEntries++;
		}

	}

	public void divTenToThe(int num) {
		Node curr = firstNode;
		for (int i = 1; i <= num; i++) {
			if (i == num) {
				firstNode = curr.next;
				curr.setNextNode(null);
				numberOfEntries -= num;
				break;
			}
			curr = curr.next;

		}

	}
}