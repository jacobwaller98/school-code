package apcs.compPractice;

import java.util.Scanner;

public class CountVowels {
	
	public static void main(String a[]) {
		new CountVowels();
	}
	
	public CountVowels() {
		Scanner a = new Scanner(System.in);
		String phrase = a.nextLine();
		char[] b = phrase.toLowerCase().toCharArray();
		int vowels = 0;
		int con = 0;
		for(int x = 0; x < b.length; x++) {
			if(b[x] == 'a' || b[x] == 'e' || b[x] == 'i' || b[x] == 'o' || b[x] == 'u')
				vowels++;
			else
				con++;
		}
		System.out.println("there are " + vowels + " vowels");
		System.out.println("There are " + con + " cons");
		
	}

}
