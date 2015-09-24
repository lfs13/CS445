public class LinkedListPlus<T> extends A4LList<T> {

	public LinkedListPlus() {
		super();
	}

	public LinkedListPlus(LinkedListPlus<T> oldList) {
		clear();
		Node old = oldList.firstNode;
		firstNode = new Node(oldList.firstNode.getData());
		Node curr = firstNode;
		
		for (int i = 1; i <= oldList.numberOfEntries; i++) {
			if(i!=oldList.numberOfEntries){
			Node next = new Node(old.next.getData());
			curr.setNextNode(next);
			curr = curr.next;
			old = old.next;
			}
			numberOfEntries++;

		}


	}

	public void leftShift(int num) {
		if (num <= 0)
			return;

		if (num >= numberOfEntries)
			clear();
		else {
			Node curr = firstNode;
			for (int i = 1; i < numberOfEntries; i++) {
				if (i == num) {
					firstNode = curr.next;
					numberOfEntries -= num;
					break;
				} else
					curr = curr.next;

			}
		}

	}

	// Shift the contents of the list num places to the left (assume the
	// beginning is the leftmost node), removing the leftmost num nodes. For
	// example, if a list has 8 nodes in it (numbered from 1 to 8), a leftShift
	// of 3 would shift out nodes 1, 2 and 3 and the old node 4 would now be
	// node 1. If num <= 0 leftShift should do nothing and if num >= the length
	// of the list, the result should be an empty list.
	public void rightShift(int num) {
		if (num <= 0)
			return;

		if (num >= numberOfEntries)
			clear();

		else {
			Node curr = firstNode;
			for (int i = 1; i < numberOfEntries; i++) {
				if (i == numberOfEntries - num) {
					curr.setNextNode(null);
					numberOfEntries -= num;
					break;
				} else
					curr = curr.next;

			}

		}
	}

	// Same idea as leftShift above, but in the opposite direction. For example,
	// if a list has 8 nodes in it (numbered from 1 to 8) a rightShift of 3
	// would shift out nodes 8, 7 and 6 and the old node 5 would now be the last
	// node in the list. If num <= 0 rightShift should do nothing and if num >=
	// the length of the list, the result should be an empty list.
	public void leftRotate(int num) {
		num = num % numberOfEntries;

		if (num < 0)
			rightRotate(Math.abs(num));

		else {
			Node curr = firstNode;
			Node newFirst = new Node(null);

			for (int i = 1; i <= numberOfEntries; i++) {
				if (i == num) {
					Node temp = curr.next;
					newFirst = temp;
					curr.setNextNode(null);
					curr = temp;

				} else if (i == numberOfEntries) {
					curr.setNextNode(firstNode);
					firstNode = newFirst;
				} else {
					curr = curr.next;
				}

			}
		}
	}

	// In this method you will still shift the contents of the list num places
	// to the left, but rather than removing nodes from the list you will simply
	// change their ordering in a cyclic way. For example, if a list has 8 nodes
	// in it (numbered from 1 to 8), a leftRotate of 3 would shift nodes 1, 2
	// and 3 to the end of the array, so that the old node 4 would now be node
	// 1, and the old nodes 1, 2 and 3 would now be nodes 6, 7 and 8 (in that
	// order). The rotation should work modulo the length of the list, so, for
	// example, if the list is length 8 then a leftRotate of 10 should be
	// equivalent to a leftRotate of 2. If num < 0, the rotation should still be
	// done but it will in fact be a right rotation rather than a left rotation.
	public void rightRotate(int num) {
		num = num % numberOfEntries;

		if (num < 0)
			leftRotate(Math.abs(num));
		else {
			Node curr = firstNode;
			Node newFirst = new Node(null);

			for (int i = 1; i <= numberOfEntries; i++) {
				if (i == (numberOfEntries - num)) {
					Node temp = curr.next;
					newFirst = temp;
					curr.setNextNode(null);
					curr = temp;

				} else if (i == numberOfEntries) {
					curr.setNextNode(firstNode);
					firstNode = newFirst;
				} else
					curr = curr.next;

			}
		}
	}

	// Same idea as leftRotate above, but in the opposite direction. For
	// example, if a list has 8 nodes in it (numbered from 1 to 8), a
	// rightRotate of 3 would shift nodes 8, 7 and 6 to the beginning of the
	// array, so that the old node 8 would now be node 3, the old node 7 would
	// now be node 2 and the old node 6 would now be node 1. The behavior for
	// num > the length of the list and for num < 0 should be analogous to that
	// described above for leftRotate.
	public void reverse() {

		Node curr = firstNode;
		for (int i = 1; i <= numberOfEntries; i++) {
			if (i == 1) {
				curr = curr.next;
			} else {
				Node temp = curr.next;
				curr.setNextNode(firstNode);
				firstNode = curr;
				curr = temp;

			}

		}

	}

	// This method should reverse the nodes in the list.

	public String toString() {
		StringBuilder sb = new StringBuilder("");
		Node n = firstNode;
		for (int i = 1; i <= numberOfEntries; i++) {
			// System.out.println(n.getData());
			sb.append(n.getData() + " ");
			n = n.next;
		}
		return sb.toString();

	}

}
