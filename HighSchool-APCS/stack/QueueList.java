package apcs.stack;

public class QueueList extends LinkedList implements Queue {

	public QueueList() {
		super();
	}
	
	public QueueList(Object o) {
		super(o);
	}
	
	@Override
	public boolean isEmpty() {
		super.isEmpty();
		return false;
	}

	@Override
	public Object dequeue() {
		Object a = getFront();
		//System.out.println(a + " FROM QUEUE LIST " + getFront());
		removeFront();
		return a;
	}

	@Override
	public Object enqueue(Object o) {
		addBack(o);
		return o;
	}

	@Override
	public Object getBack() {
		return super.getBack();
		
	}

	@Override
	public Object getFront() {
		return super.getFront();
	}

}
