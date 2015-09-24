// CS 0445 Spring 2014
// Assignment 1 Sorter interface
// This interface should be trivial to implement, since you can just
// use a version of the Arrays.sort() method on the underlying array
// to do the actual sorting. Be careful to sort the logical array (only
// the actual items) rather than the entire physical array. For this
// method it is assumed that the underlying data type is Comparable, but
// you are not required to enforce that fact in the MyDB class header.

public interface Sorter
{
	public void sort();
}