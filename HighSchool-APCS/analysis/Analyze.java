/**
 * Author Jacob
 * Date 11/6/15
 */

package apcs.analysis;

import java.io.*;

public class Analyze {
	int length = 10000000;
	
	static long systemTime;
	static int typicalArray[];
	static int randArray[];
	
	static long genRandTime;
	static long genOrderedTime;
	static long bubbleSortTime;
	static long binarySearchTime;

	/*
	 * This runs the entire program
	 */
	public static void main(String a[]) {
		//Runs the random array generator
		
		long initTime = System.currentTimeMillis();
		//new Analyze("");
		long afterTime = System.currentTimeMillis();
		long orderDifference = afterTime - initTime;
		System.out.println("Create random array in: " + orderDifference +" ms.");
		
		initTime = System.currentTimeMillis();
		new Analyze(1);
		afterTime = System.currentTimeMillis();
		long randDifference = afterTime - initTime;
		System.out.println("Create ordered array in: " + randDifference+ " ms.");
		
		initTime = System.currentTimeMillis();
		bubbleSortRand();
		afterTime = System.currentTimeMillis();
		long sortDifference = afterTime - initTime;
		System.out.println("Sorted the random array by Bubble sort in: " + sortDifference + " ms.");
		
		int x = getTarget();
		initTime = System.currentTimeMillis();
		binSearch(x);
		afterTime = System.currentTimeMillis();
		long searchDifference = afterTime - initTime;
		System.out.println("Find " + getTarget() + " in: " + searchDifference + " ms.");
	}
	
	/**
	 * Constructor that generates the random array
	 * 
	 */
	public Analyze(int x) {
		generateOrderedArray(length);
	}
	
	/**
	 * Constructor that generates the random array
	 */
	public Analyze(String x) {
		generateRandArray(length);
	}
	
	/**
	 * Generates the ordered array
	 */
	public static void generateOrderedArray(int u) {
		typicalArray = new int[u];
		for(int x = 0; x < u; x++) {
			typicalArray[x] = x;
		}
	}
	
	/**
	 * Generates the random array
	 */
	public static void generateRandArray(int u) {
		randArray = new int[u];
		for(int x = 0; x < u; x++) {
			randArray[x] = (int)(Math.random()*randArray.length);
		}
	}
	
	/**
	 * Bubble sorts the random array
	 */
	public static void bubbleSortRand() {
		for(int y = 0; y < randArray.length; y++) {
			for(int x = 0; x < randArray.length-1; x++) {
				if(randArray[x] < randArray[x+1]) {
					int temp = randArray[x];
					randArray[x] = randArray[x+1];
					randArray[x+1] = temp;
				}
			}
		}
	}
	
	/**
	 * Searches using the binary search method
	 */
	public static boolean binSearch(int num) {
		num = getTarget();
		int firstInd = 0;
		int lastInd = typicalArray.length - 1;
		while (firstInd <= lastInd) {
			int mid = firstInd + (lastInd - firstInd) / 2;
			if (num < typicalArray[mid]) {
				lastInd = mid - 1;
			} else if (num > typicalArray[mid]) {
				firstInd = mid + 1;
			} else {
				return true;
			}
		}
		System.out.println("Not Found");
		return false;
	}
	
	/**
	 * Finds the int in the data.txt file
	 */
	public static int getTarget() {
		// The name of the file to open.
        String fileName = "data.txt";
        String myNum = "";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                myNum = line;
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return Integer.parseInt(myNum);
        
	}
	
	
	/**
	 * What "n" did you choose for the problem and what is O(n)? 100000 for random 100000for pre-sorted
	 * What was the time the constructor took to run? t1 = 14 ms. for random 3 ms. for pre-sorted
	 * Run the simulation again using using 10 times the number of elements in question 1
	 * What was the time the constructor took to run? t2 = 56 ms. for random 10ms. for pre-sorted
	 * What is t2 / t1 and how does it relate to O(n) from question (i)? ___________________for random _________________for pre-sorted	
	 * Random 56/14    = 4
	 * Pre-Sorted 10/3 = 3.33
	 * It is linear
	 * What "n" did you choose for the problem and what is O(n)? I chose 1000 for n :: O(n) = n^2
	 * What was the time the sort took to run? t1 = 19 ms.
	 * What was the time the sort took to run? t2 = 307 ms.
	 * What is t2 / t1 and how does it relate to O(n) from question (i)? 
	 * 307/19 = 16.2 It relates because when you multiply by 10, it takes about 100 times the time
	 * What "n" did you choose for the problem and what is O(n)? 10000000 o(n) = log(n) base two
	 * What was the time the search took to run? t1 about a millisecond	
	 * What was the time the search took to run? t2 about a millisecond
	 * 1/1 = 1
	 * Well the algorithm is really good. It only has to loop about 26 times for any given number
	 * for the larger n
	 * 
	 * Based upon the above answers, did the time correspond with the "Big-Oh" calculations for each of the three methods? 
	 * constructor? yes sorting? Yes searching? I'm not sure
	 * 
	 * Clarify any discrepencies between O(n) theory and what you found in practice:
	 * with the sorting, it's too close for my computer to determine.				
	 * 
	 */
	
	
	

}
