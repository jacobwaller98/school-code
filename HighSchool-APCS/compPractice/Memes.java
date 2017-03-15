package apcs.compPractice;

import java.util.Scanner;

public class Memes {

	public static void main(String args[]){
		while(null==null){
			Scanner s = new Scanner(System.in);
			int lolcats = 10;
			int alots = 0;
			while(null==null){
				System.out.println("Enter number of lolcats (-1 to quit):");
				int i1 = s.nextInt();
				System.out.println("Enter number of alots (-1 to quit):");
				int i2 = s.nextInt();
				if(i1==-1 && i2==-1)
					System.exit(0);
				if((alots%2==0 && lolcats %2==0)||(alots%2!=0 && lolcats %2!=0)){
					lolcats+=i1;
					alots+=i2;
				}
				else{
					lolcats/=2;
					alots/=2;
				}
				if(alots%2!=0 && lolcats %2!=0){
					lolcats--; alots--;
				}
				
				if(alots>=30)
					alots = 15;
				if((lolcats+alots)>50){
					System.out.println("Meme density exceeded! Everyone can haz cheeseburgers!");
					break;
				}
				System.out.println("You have "+lolcats+" lolcats.");
				System.out.println("You have "+alots+" alots.");
			}
		}
	}
}
