import java.io.*;
import java.util.*;
import TreePackage.*;

public class Assig5 {
	private static File in;
	private static Scanner input;
	private BinaryNode<Character> root;
	private static int leaves = 0;
	private String[] table;
	private ArrayList<Character> alchars = new ArrayList<Character>();
	private Object[] chars;
	private String stringchars;
	private Object[] unordered;

	public static void main(String[] args) throws IOException {
		Assig5 A5 = new Assig5(args[0]);
	}

	public Assig5(String s) throws IOException {
		in = new File(s);
		input = new Scanner(in);

		root = readTree(input.nextLine());
		chars = alchars.toArray();
		unordered = alchars.toArray();
		Arrays.sort(chars);
		StringBuilder a = new StringBuilder("");
		for (int i = 0; i < leaves; i++) {
			a.append(chars[i]);
		}
		stringchars = a.toString();
		System.out.println("The huffman tree has been restored");
		System.out.println();

		StringBuilder sb = new StringBuilder("");
		table = new String[26];
		makeTable(sb, root, table);

		boolean run = true;
		while (run == true) {
			Scanner b = new Scanner(System.in);
			System.out.println("Please choose from the following:"
					+ "\n1) Encode a text string"
					+ "\n2) Decode a Huffman string" + "\n3) Quit");
			int choice = b.nextInt();
			while (choice < 1 || choice > 3) {
				System.out.println("Please Enter a Valid Choice");
				System.out.println("1) Encode a text string"
						+ "\n2) Decode a Huffman string" + "\n3) Quit");
				choice = b.nextInt();
			}
			if (choice == 1) {
				encode();
			} else if (choice == 2) {
				decode();
			} else {
				System.exit(0);
			}

		}

	}

	public BinaryNode readTree(String s) {
		if (s.equals("I")) {
			BinaryNode<Character> interior = new BinaryNode<Character>('\0');
			if (input.hasNextLine())
				interior.setLeftChild(readTree(input.nextLine()));
			else
				return null;
			if (input.hasNextLine())
				interior.setRightChild(readTree(input.nextLine()));
			else
				return null;
			return interior;
		} else {
			BinaryNode<Character> leaf = new BinaryNode<Character>(s.charAt(2));
			leaves++;
			alchars.add(leaf.getData());
			return leaf;
		}

	}

	public void makeTable(StringBuilder sb, BinaryNodeInterface<Character> bn,
			String[] table) {
		if (bn.getData() == '\0') {
			makeTable(sb.append(0), bn.getLeftChild(), table);
			sb.deleteCharAt(sb.length() - 1);
			makeTable(sb.append(1), bn.getRightChild(), table);
			sb.deleteCharAt(sb.length() - 1);
		} else {
			table[bn.getData() - 'A'] = sb.toString();
		}

	}

	public void encode() {

		Scanner encoder = new Scanner(System.in);

		System.out
				.print("Please enter a string from the following characters:\n"
						+ stringchars);
		System.out.println();

		String enter = encoder.nextLine().toUpperCase();
		StringBuilder huffmansb = new StringBuilder("");
		System.out.println("Huffman String:");
		for (int i = 0; i < enter.length(); i++) {
			for (int j = 0; j < unordered.length; j++) {
				if (((Object) (enter.charAt(i))).equals(unordered[j])) {
					huffmansb.append((table[(Character) unordered[j] - 'A'])
							+ "\n");
				}

			}

		}
		String huffman = huffmansb.toString();
		System.out.println(huffman);

	}

	public void decode() {
		Scanner decoder = new Scanner(System.in);
		System.out.println("Here is the encoding table:");
		for(int i=0;i<leaves;i++){
			System.out.println(chars[i]+":"+ table[i]); 
		}
		System.out
				.println("Please enter a Huffman string (one line, no spaces)");
		String bits = decoder.nextLine();
		System.out.println(decursive(bits).toString());
		

	}

	public StringBuilder decursive(String s) {
		BinaryNodeInterface<Character> curr = root;
		StringBuilder huff = new StringBuilder("");

		for (int i = 0; i <s.length(); i++) {
			if (s.charAt(i) == '0') {
				curr = curr.getLeftChild();
				if (curr.getLeftChild()==null) {
					huff.append(curr.getData());
					curr = root;
				} 
					
			} else {
				curr = curr.getRightChild();
				if (curr.getRightChild() == null) {
					huff.append(curr.getData());
					curr = root;
				}
					
			}

		}
		return huff;
	}
}
