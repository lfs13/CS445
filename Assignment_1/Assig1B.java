//LOUIE SEEFELD//
//CS0445////////

import java.io.*;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Assig1B {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		MyDB<Movie> theDB = new MyDB<Movie>(1);
		boolean quit = false;

		while (quit == false) {
			int choice = 0;
			boolean choice_check = false;
			while (choice_check == false) {
				try {
					System.out.println("Please choose an option:");
					System.out.println("1) Restore the Movies from a file"
							+ "\n2) Add a new Movie"
							+ "\n3) Search for a Movie" + "\n4) Delete a Movie"
							+ "\n5) Show Movies in sorted order"
							+ "\n6) Show Movies in reverse sorted order"
							+ "\n7) Save Movies back to file"
							+ "\n8) Quit the program");

					choice = input.nextInt();
					choice_check = true;
				} catch (InputMismatchException i) {
					System.out.println("Please enter a valid integer");
					System.out.println();
					input.nextLine();
				}
			}
			switch (choice) {
			case 1:
				// restore
				input.nextLine();
				System.out.println("Enter name of file to restore:");
				String fileName = input.nextLine();
				theDB.restoreFromFile(fileName);
				System.out.println();
				break;

			case 2:
				// add
				input.nextLine();
				boolean check1 = false;
				boolean check2 = false;
				int mins = 0;
				int secs = 0;
				System.out.println("Enter movie title:");
				String title = (String) input.nextLine();
				Movie p = new Movie(title);
				if (theDB.findItem(p) != null) {
					System.out.println(title + " is already in the DB\n");
					break;
				}
				System.out.println("Enter date of release (mm/dd/yyyy)");
				String date = input.nextLine();
				while (check1 == false) {
					try {
						System.out.println("Length (minutes):");
						mins = input.nextInt();
						check1 = true;
					} catch (InputMismatchException i) {
						System.out.println("Please enter a valid integer");
						input.nextLine();
					}
				}
				while (check2 == false) {
					try {
						input.nextLine();
						System.out.println("Length (seconds)");
						secs = input.nextInt();
						check2 = true;
					} catch (InputMismatchException i) {
						System.out.println("Please enter a valid integer");
					}
				}

				Movie newMovie = new Movie(title, date, mins, secs);
				theDB.addItem(newMovie);
				break;

			case 3:
				// find
				input.nextLine();
				System.out.println("Enter title of movie to find:");
				String find = input.nextLine();
				Movie f = new Movie(find);
				if ((theDB.findItem(f)) == null)
					System.out.println("Could not find movie.");
				else {
					System.out.println("Here is your movie:\n");
					System.out.println(theDB.findItem(f));
				}
				System.out.println();
				break;
			case 4:
				// remove
				input.nextLine();
				System.out.println("Enter title of movie to delete:");
				String remove = input.nextLine();
				Movie r = new Movie(remove);
				if ((theDB.findItem(r)) == null)
					System.out.println("Could not find movie.\n");
				else {
					System.out.println(remove + " was removed.\n");
					theDB.removeItem(r);
				}
				break;
			case 5:
				// sort
				theDB.sort();
				System.out.println(theDB.toString());
				break;
			case 6:
				// reverse
				theDB.reverse();
				System.out.println(theDB.toString());
				break;
			case 7:
				// save
				input.nextLine();
				System.out.println("Enter name of file to save to:");
				String saveFile = input.nextLine();
				// if (theDB.saveToFile(saveFile) == false)
				// System.out.println("No File");
				theDB.saveToFile(saveFile);
				System.out.println();
				break;
			case 8:
				quit = true;

			}
		}
		System.out.println("CYA BITCH");
	}
}
