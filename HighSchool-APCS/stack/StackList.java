package apcs.stack;

public class StackList extends LinkedList implements Stack {

	public StackList() {
		super();
	}

	public StackList(Object o) {
		super(o);
	}

	public Object push(Object o) {
		addBack(o);
		return o;
	}

	public Object pop() {
		Object a = getBack();
		removeBack();
		return a;
	}

	public Object peek() {
		return getFront();
	}

	public int search(Object o) {
		return super.search(o);
	}

}
