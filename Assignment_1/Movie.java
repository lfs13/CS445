import java.io.*;
import java.sql.*;

public class Movie implements Comparable, Serializable {
	private String title;
	private String date;
	private int minutes, seconds;

	// main constructor
	public Movie(String t, String d, int m, int s) {
		title = t;
		date = d;
		minutes = m;
		seconds = s;
	}

	// constructor for temporary testing (find,remove)
	public Movie(String t) {
		title = t;
	}

	public boolean equals(Object obj) {
		Movie temp = (Movie) obj;
		if (temp.title.equalsIgnoreCase(title))
			return true;
		else
			return false;
	}

	public int compareTo(Object arg0) {
		Movie temp = (Movie) arg0;
		return this.title.toLowerCase().compareTo(temp.title.toLowerCase());
	}

	public String toString() {
		return ("Title: " + title + "\nRelease: " + date + "\nLength: "
				+ minutes + ":" + seconds + "\n");
	}

}
