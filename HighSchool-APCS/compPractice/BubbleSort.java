package apcs.compPractice;

import java.util.Arrays;

public class BubbleSort {
	
	int[] u = new int[1000];
	public static void main(String[] args) {
		new BubbleSort();

	}
	
	public BubbleSort() {
		randArray();
		bubbleSort();
		System.out.println(Arrays.toString(u));
	}
	
	public void randArray() {
		for(int y = 0; y < u.length; y++) {
			u[y] = (int)( Math.random() *100);
		}
	}
	
	public void bubbleSort() {
		for(int x = 0; x < u.length; x++) {
			for(int y = 0; y < u.length-1; y++) {
				if(u[y] > u[y+1]) {
					int temp = u[y];
					u[y] = u[y+1];
					u[y+1] = temp;
				}
			}
		}
	}

}
