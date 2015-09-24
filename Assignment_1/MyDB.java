import java.io.*;
import java.util.Arrays;

public class MyDB<T> implements SimpleDB<T>, Reverser, Sorter, SaveRestore {

	private T[] db;
	private int count = 0;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	private FileInputStream fis;
	private ObjectInputStream ois;
	private File f;

	public MyDB(int size) {
		db = (T[]) new Object[size];
	}

	// --------------------SIMPLEDB------------------------//
	@Override
	public boolean addItem(T item) {
		if (count < length()) {
			db[count] = item;
			count++;
		} else {
			T[] temp = (T[]) new Object[length() * 2];
			for (int i = 0; i < count; i++) {
				temp[i] = db[i];
			}
			db = temp;
			db[count] = item;
			count++;
		}
		return false;
	}

	@Override
	public T removeItem(T item) {
		int index;
		T temp;
		for (int i = 0; i < count; i++) {
			if (db[i].equals(item)) {
				temp = db[i];
				index = i;
				count--;
				for (int c = index; c < count; c++) {
					db[c] = db[c + 1];
					db[c + 1] = null;
				}
				return temp;
			}
		}
		return null;
	}

	@Override
	public T findItem(T item) {
		for (int i = 0; i < count; i++) {
			if (db[i].equals(item))
				return db[i];
		}
		return null;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < length(); i++) {
			if (db[i] != null)
				return false;
		}
		return true;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public void clear() {
		for (int i = 0; i < count; i++) {
			db[i] = null;
		}
		count = 0;
	}

	public int length() {
		return db.length;
	}

	// ----------------------------------------------------//

	@Override
	public void sort() {
		T[] temp = (T[]) new Object[count];
		for (int i = 0; i < count; i++) {
			temp[i] = db[i];
		}
		Arrays.sort(temp);
		for (int i = 0; i < count; i++) {
			db[i] = temp[i];
		}

	}

	@Override
	public void reverse() {
		T[] temp = (T[]) new Object[count];
		for (int i = 0; i < count; i++) {
			temp[i] = db[count - (i + 1)];
		}
		for (int i = 0; i < count; i++) {
			db[i] = temp[i];
		}

	}

	// ----------------------------------------------------//

	public boolean saveToFile(String fileName) {

		f = new File(fileName);

		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			for (int i = 0; i < count; i++) {
				oos.writeObject(db[i]);
			}
		} catch (IOException e) {
			System.out.println("Something's wrong");
			return false;
		}

		return true;
	}

	public boolean restoreFromFile(String fileName) {
		f = new File(fileName);

		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			boolean end = false;
			try {
				while (end == false) {
					try {
						addItem((T) ois.readObject());
					} catch (EOFException a) {
						end = true;
					}
				}
			} catch (ClassNotFoundException c) {
				return false;
			}
		} catch (IOException e) {
			System.out.println("File "+fileName+" was not found");
			System.out.println();
			return false;
		}
		System.out.println(fileName+" was restored");
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Here is the DB:\n\n");
		for (int i = 0; i < count; i++) {
			sb.append(db[i] + " \n");
		}
		return sb.toString();
	}

}
