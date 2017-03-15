/*
 * Jacob Waller
 */
package apcs.hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WordFreq {
	private Map m;

	public static void main(String arg[]) {
		new WordFreq();
	}

	/**
	 * Count the amount of words in the file
	 */
	public WordFreq() {
		m = new TreeMap();
		loadMap(m);
		System.out.println(m); // print the values in the map
	}

	/**
	 * Will load a map of m
	 * 
	 * @param m
	 */
	public void loadMap(Map m) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("inFile3.txt"));
		} catch (FileNotFoundException e) {
		}
		while (scanner.hasNext()) {
			String word = scanner.next();
			Integer i = (Integer) m.get(word);
			if (i == null) {
				m.put(word, new Integer(1));
			} else {
				m.put(word, new Integer(i.intValue() + 1));
			}
		}
	}
}
