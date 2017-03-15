/*
Author Jacob Waller
Date 12/7/15

*/
package apcs.sort;

import java.io.PrintWriter;

public class Sorts {

	int[] array = new int[100];
	private int length;
	public PrintWriter writer;

	public static void main(String a[]) {
		new Sorts();
	}

	public Sorts() {
		try {
			writer = new PrintWriter("data.dat", "UTF-8");
			for (int x = 0; x < 100; x++) {
				array[x] = (int) (Math.random() * 500);
			}

			print(array, "The original array is:");
			quickSort(array);
			print(array, "The sorted array is:");

		} catch (Exception e) {

		}

	}

	public void quickSort(int[] inputArr) {

		if (inputArr == null || inputArr.length == 0) {
			return;
		}
		this.array = inputArr;
		length = inputArr.length;
		quickSort(0, length - 1);
	}

	private void quickSort(int lowerIndex, int higherIndex) {

		int i = lowerIndex;
		int j = higherIndex;
		// calculate pivot number
		int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
		// Divide into two arrays
		while (i <= j) {
			while (array[i] < pivot) {
				i++;
			}
			while (array[j] > pivot) {
				j--;
			}
			if (i <= j) {
				sort(i, j);
				// move index to next position on both sides
				i++;
				j--;
			}
		}
		// recursive
		if (lowerIndex < j)
			quickSort(lowerIndex, j);
		if (i < higherIndex)
			quickSort(i, higherIndex);
	}

	private void sort(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public void print(int[] a, String s) {

		writer.println(s);
		writer.println(getArString(a));
	}

	private String getArString(int[] ai) {
		String s = "";
		for (int x = 0; x < ai.length; x++) {
			s += ai[x] + ", ";
			if (x % 10 == 9) {
				s += "\n";
			}
		}
		return s;
	}

}
