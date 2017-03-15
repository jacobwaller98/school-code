package apcs.stack;

public class ListTest {

	public static void main(String[] args) {
		new ListTest();
	}

	public ListTest() {
		StackList l = new StackList();
		for (int x = 0; x < 10; x++) {
			l.addBack(x);
		}
		System.out.println(l);
		System.out.println(l.search(3));
		System.out.println(l);
		System.out.println(l.pop());
		System.out.println(l);
		
		
	}

}
