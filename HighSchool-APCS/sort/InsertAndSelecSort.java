package apcs.sort;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class InsertAndSelecSort {

	private static ArrayList<Integer> al = new ArrayList<Integer>();
	private static Integer[] ai = new Integer[100];
	public PrintWriter writer;

	public static void main(String[] args) {
		genLists();
		new InsertAndSelecSort();
	}

	public InsertAndSelecSort() {
		try {
			writer = new PrintWriter("data.txt", "UTF-8");
			print(al, ai, "The original BLANK is:");
			insertionSort(al);
			selectionSort(ai);
			print(al, ai, "The sorted BLANK is:");
			writer.close();
		} catch (Exception e) {
		}

	}

	/**
	 * Returns the array list converted to a String object
	 * @return
	 */
	public String alToString() {
		String s = "";
		for (int x = 0; x < al.size(); x++) {
			s += al.get(x) + ", ";
			if (x % 10 == 9) {
				s += "\n";
			}
		}
		return s;
	}

	/**
	 * Returns the array converted to a String object
	 * @return
	 */
	public String aiToString() {
		String s = "";
		for (int x = 0; x < ai.length; x++) {
			s += ai[x] + ", ";
			if (x % 10 == 9) {
				s += "\n";
			}
		}
		return s;
	}

	public static void genLists() {
		for (int x = 0; x < 100; x++) {
			al.add((int) (Math.random() * 100));
		}

		for (int x = 0; x < 100; x++) {
			ai[x] = ((int) (Math.random() * 100));
		}
	}

	/**
	 * Sorts the given arraylist using the insertionSort algorithm
	 * @param a
	 */
	public void insertionSort(ArrayList<Integer> a) {
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		sorted.add(a.get(0));
		a.remove(0);
		for (int x = 0; x < a.size(); x++) {
			Integer temp = a.get(x);
			for (int y = 0; y < sorted.size(); y++) {
				try {
					if (temp > sorted.get(y) &&
							temp < sorted.get(y + 1)) {
						sorted.add(y + 1, temp);
						break;
					} else if (temp < sorted.get(y)) {
						sorted.add(y, temp);
						break;
					}
				} catch (Exception e) {
					if (temp < sorted.get(y)) {
						sorted.add(y, temp);
						break;
					} else {
						sorted.add(temp);
					}
				}
			}
		}
		al = sorted;
	}

	/**
	 * Uses the Selection Sort Algorithm to sort the array
	 *
	 * @param a
	 */
	public void selectionSort(Comparable<Integer> a[]) {
		/*
		 * finds the smallest & moves to bottom then second smallest and
		 * moves it to the next one and so on
		 */
		// Stores two variables for later use
		int min;
		Comparable<Integer> temp;
		// Runs Selection sort for the entire array
		for (int index = 0; index < a.length - 1; index++) {
			// Makes sure that the next minimum is right where it needs to

			min = index;
			// Only looks at things it needs to look at
			for (int scan = index + 1; scan < a.length; scan++)
				// Finds the smallest value left in the unsorted portion
				if (a[scan].compareTo((Integer) a[min]) < 0)
					min = scan;
			// Swap the values
			temp = a[min];
			a[min] = a[index];
			a[index] = temp;
		}
	}

	public void print(ArrayList<Integer> a, Comparable<Integer> b[],
										String msg) throws Exception {
		writer.println(msg.replace("BLANK", "array"));
		writer.println(aiToString());
		writer.println(msg.replace("BLANK", "list"));
		writer.println(alToString());
	}

}
