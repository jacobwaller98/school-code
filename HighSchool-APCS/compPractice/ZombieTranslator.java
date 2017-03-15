package apcs.compPractice;

import java.util.ArrayList;
import java.util.Scanner;

public class ZombieTranslator {

	public static void main(String a[]) {
		new ZombieTranslator();
	}

	public ZombieTranslator() {
		boolean done = false;
		while (!done) {
			System.out.println("HEllo");
			Scanner a = new Scanner(System.in);
			String phrase = a.nextLine();
			System.out.println(phrase);
			ArrayList<String> letters = new ArrayList<String>();
			
			for (int x = 0; x < phrase.length()-1; x++) {
				letters.add("" +phrase.charAt(x));
			}
			
			for (int x = 0; x < letters.size(); x++) {
				System.out.println(letters.get(x));
				if (letters.get(x).equals("e") || letters.get(x).equals("i") || letters.get(x).equals("o")
						|| letters.get(x).equals("u") || letters.get(x).equals("E") || letters.get(x).equals("I")
						|| letters.get(x).equals("O") || letters.get(x).equals("U")) {
					letters.set(x, "r");
				}
				if (!letters.get(x).equals("z") || !letters.get(x).equals("h") || !letters.get(x).equals("r")
						|| !letters.get(x).equals("g") || !letters.get(x).equals("b") || !letters.get(x).equals("m")
						|| !letters.get(x).equals("n") || !letters.get(x).equals("a") || !letters.get(x).equals(".")
						|| !letters.get(x).equals("!") || !letters.get(x).equals("-") || !letters.get(x).equals("?")
						|| !letters.get(x).equals(" ") || !letters.get(x).equals("Z") || !letters.get(x).equals("H")
						|| !letters.get(x).equals("R") || !letters.get(x).equals("G") || !letters.get(x).equals("B")
						|| !letters.get(x).equals("M") || !letters.get(x).equals("N") || !letters.get(x).equals("A")) {
					letters.remove(x);
				}
				
				if(letters.get(x).equals("r") && letters.get(x+1).equals(" ")) {
					letters.set(x, "rh");
				}
				
				if(letters.get(x).equals(" ") && letters.get(x+1).equalsIgnoreCase("a") && letters.get(x+2).equals(" ")){
					letters.set(x+1, "hra");
				}
			
			}
			String h = "";
			for(int x = 0; x < letters.size();x++) {
				h += letters.get(x);
			}
			System.out.println(h);

		}
	}

}
