package apcs.quiz;

public class StringManip {
	String line = "Hello _my name is Jacob_ What is yours? Is your name _ Jacob _ _?";

	public static void main(String a[]) {
		new StringManip();
	}

	public StringManip() {
		System.out.println(findString("a", 0));
		System.out.println(countStrings("a"));
		System.out.println(convertItalics());
	}

	public int findString(String str, int start) {
		char[] cs = line.toCharArray();
		if (start > cs.length - 1)
			return -1;
		for (int x = start; x < cs.length; x++) {
			if (str.equals(cs[x] + "")) {
				if ((x == 0 && cs[x + 1] != cs[x]) || (x == cs.length - 1 && cs[x - 1] != cs[x])
						|| x != 0 && (cs[x - 1] != cs[x] && cs[x] != cs[x + 1]))
					return x;
			}
		}
		return -1;
	}
	
	public int countStrings(String str) {
		char[] cs = line.toCharArray();
		int n = 0, num = 0;
		while(findString(str,num) != -1) {
			n++;
			
			num = findString(str,num)+1;
		}
		return n;
	}
	
	public String convertItalics() {
		String temp = line;
		if(countStrings("_") == 0 || countStrings("_") % 2 != 0) 
			return temp;
		int amt = 0;
		while(countStrings("_")-amt != 0) {
			temp = temp.replaceFirst("_", "<i>");
			temp = temp.replaceFirst("_", "</i>");	
			amt += 2;
		}
		return temp;
	}
}
