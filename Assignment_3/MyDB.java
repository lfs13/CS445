   /*\\\\\\\\\\\\\\
  /////////////////
 //LOUIS/SEEFELD//
/////////////////
\\\\\\\\\\\\\\*/


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

	// --------------------ADD-ITEM------------------------//
	public boolean addItem(T item) {
		if (count < length()) {
			db[count] = item;
			count++;
		} else {
			T[] temp = (T[]) new Object[length() * 2];
			temp = recAdd(temp, 0);
			db = temp;
			db[count] = item;
			count++;
		}
		return false;
	}

	public T[] recAdd(T[] t, int n) {
		if (n >= count)
			return t;
		else {
			t[n] = db[n];
			return recAdd(t, n + 1);
		}
	}

	// ---------------------------------------------------//

	// ----------------REMOVE-----------------------------//
	public T removeItem(T item) {
		T temp = recRemove(item, 0);

		if (temp == null) {
			return temp;
		} else {
			recCheck(0);
			return temp;
		}

	}

	public T recRemove(T item, int n) {
		if (count == 0)
			return null;
		if (n == count)
			return null;
		else if (db[n].equals(item)) {
			T temp = db[n];
			db[n] = null;
			return temp;
		} else
			return recRemove(item, n + 1);
	}

	public boolean recCheck(int n) {
		if (n >= count)
			return false;
		if (db[n] == null) {
			return recShift(n);
		}
		return recCheck(n + 1);

	}

	public boolean recShift(int n) {
		if (n == (count - 1)) {
			db[n] = null;
			count--;
			return true;
		} else {
			db[n] = db[n + 1];
			return recShift(n + 1);
		}

	}

	// ---------------------------------------------------//

	/*------------FIND-ITEM------------*/

	public T findItem(T item) {
		return recFind(item, 0);
	}

	public T recFind(T item, int n) {
		if (n == count)
			return null;
		if (db[n].equals(item))
			return db[n];
		else
			return recFind(item, n + 1);

	}

	/*--------------------------------*/
	public boolean isFull() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEmpty() {
		if (count > 0)
			return false;

		return true;
	}

	public int size() {
		return count;
	}

	// --------------------CLEAR------------------//
	public void clear() {
		recClear(0);
	}

	public boolean recClear(int n) {
		if (n >= count) {
			count = 0;
			return false;
		} else {
			db[n] = null;
			return recClear(n + 1);
		}

	}

	// --------------------------------------------//

	public int length() {
		return db.length;
	}

	// ---------------------SORT-------------------------------//

	public void sort() {
		T[] temp = (T[]) new Object[count];
		temp = recSort(temp, 0);
		Arrays.sort(temp);
		refill(temp, 0);

	}

	public T[] recSort(T[] temp, int n) {
		if (n >= count)
			return temp;
		else {
			temp[n] = db[n];
			return recSort(temp, n + 1);
		}

	}

	public T[] refill(T[] temp, int n) {
		if (n >= count)
			return temp;
		else {
			db[n] = temp[n];
			return refill(temp, n + 1);
		}

	}

	// -------------------------------------------------------//

	// ---------------------REVERSE---------------------------//

	public void reverse() {
		T[] temp = (T[]) new Object[count];
		temp = recReverse(temp, 0);
		refill(temp, 0);

	}

	public T[] recReverse(T[] temp, int n) {
		if (n >= count)
			return temp;
		else {
			temp[n] = db[count - (n + 1)];
			return recReverse(temp, n + 1);
		}

	}

	// ----------------------------------------------------//

	// ------------------SAVE-FILE-------------------------//

	public boolean saveToFile(String fileName) {

		f = new File(fileName);

		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			recSave(0, fos, oos);
		} catch (IOException e) {
			System.out.println("Something's wrong");
			return false;
		}

		return true;
	}

	public boolean recSave(int n, FileOutputStream fos, ObjectOutputStream oos)
			throws IOException {
		if (n >= count)
			return false;

		else {
			oos.writeObject(db[n]);
			return recSave(n + 1, fos, oos);
		}

	}

	// -------------------RESTORE FILE---------------------------------//

	public boolean restoreFromFile(String fileName) {
		f = new File(fileName);

		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			boolean end = false;
			try {
				recRestore(fis, ois, end);
			} catch (ClassNotFoundException c) {
				return false;
			}
		} catch (IOException e) {
			System.out.println("File " + fileName + " was not found");
			System.out.println();
			return false;
		}
		return true;
	}

	public boolean recRestore(FileInputStream fos, ObjectInputStream ois,
			boolean b) throws IOException, ClassNotFoundException {
		boolean end = b;

		if (end == false) {
			try {
				addItem((T) ois.readObject());
				return recRestore(fos, ois, end);
			} catch (EOFException a) {
				end = true;
				return recRestore(fos, ois, end);

			}
		} else
			return false;
	}

	// -------------------------MODE-------------------------//

	public void showMode() {
		T mode;
		int freq;
		Object [] h = new Object[2];
		int a1 = count/2;
		int a2 = count-a1;
		T [] temp = (T[]) new Object[count];
		temp = splitFill(temp,0,count);
		Arrays.sort(temp);
			
		if(count==1){
			mode=db[0];
			freq=1;
			System.out.println("The mode is: "+mode+" The frequency is: "+freq);
		}
		h=valueGuy(temp,temp[0],0,h);
		System.out.println("The mode is:   Data: "+h[0]+"   Frequency: "+h[1]);


	}
	
	public Object[] valueGuy(T[] temp,T value, int n, Object [] holder){
		
		if(n>=temp.length)
			return holder;

		if(n==0){
			Integer f= new Integer(modeCount(temp, temp[n], 0, 0));
			holder[0]=temp[n];
			holder[1]=f;
			return valueGuy(temp,temp[n],n+1,holder);
		}
		if(value.equals(temp[n])){
			return valueGuy(temp,temp[n],n+1,holder);
		}
		else{
			Integer f= new Integer(modeCount(temp, temp[n], 0, 0));
			if((f.compareTo((Integer) holder[1]))==0){
				holder[0]=firstTest(holder[0],temp[n],0);
				holder[1]=f;
			}if((f.compareTo((Integer) holder[1]))>0){
				holder[0]=temp[n];
				holder[1]=f;
			}
			return valueGuy(temp,temp[n],n+1,holder);
			
		}
		
	}
	public Object firstTest(Object a, Object b, int n){
		if(n>=count)
			return null;
		else if(db[n].equals(a))
			return a;
		else if(db[n].equals(b))
			return b;
		else
			return firstTest(a, b, n+1);
	}
	
	public int modeCount(T [] t, T value, int c, int n){
		if(n>=t.length){
			return c;
		}
		else{
			if(t[n].equals(value))
				c++;
			return modeCount(t,value, c, n+1);
			
		}
	}
	
	public T[] splitFill(T[] t, int n, int max){
		if(n>=max)
			return t;
		else{
			t[n]=db[n];
			return splitFill(t,n+1,max);
		}
	}

	// ----------------------------------------------------//

	// -------------IS-SORTED------------------//
	public boolean isSorted() {

		return recCheckSort(0);

	}

	public boolean recCheckSort(int n) {
		if (n >= count) {
			return true;
		}
		if (n == 0) {
			return recCheckSort(n + 1);
		} else {
			if ((((Comparable) db[n - 1]).compareTo(((Comparable) db[n]))) >= 1) {
				return false;
			} else {
				return recCheckSort(n + 1);
			}

		}

	}

	// ----------------------------------------------------//

	// -----------------------TO-STRING--------------------//

	// The public method is not recursive. Note that it creates a
	// StringBuilder then uses a recursive method to fill it.

	public String toString() {
		StringBuilder temp = new StringBuilder();
		temp.append("Here is the DB: \n");
		return toStringRec(temp, 0);
	}

	// This recursive method returns the actual String version of the
	// data in the MyDB. Note the parameters. The ind parameter is used
	// to determine the position in the MyDB and the b parameter is used
	// to store the data as it is processed. The recursive case is shown
	// first.

	private String toStringRec(StringBuilder b, int ind) {
		if (ind < count) {
			b.append(db[ind]);
			b.append(" ");
			return toStringRec(b, ind + 1);
		} else
			return b.toString();
	}
	// ----------------------------------------------------//

}
