/**
 * Jacob Waller
 * 1/25/16
 */

package apcs.stack;

public class Driver {

	public static void main(String ar[]) {
		new Driver();
	}
	
	public Driver() {
		QueueList q = new QueueList();
		StackList s = new StackList();
		String[] as = {"Bill" , "Fred", "Joe", "Henrietta", "Mr. M", "Noman"};
		System.out.println("Added in this order: ");
		for(int x = 0; x < as.length; x++) {
			System.out.print(as[x] + ", ");
			q.enqueue(as[x]);
			s.push(as[x]);
		}
		System.out.println("\n");
		System.out.println("stack: " + s);
		System.out.println("queue: " + q);
		System.out.println("stack.isEmpty() " + s.isEmpty());
		System.out.println("queue.isEmpty() " + q.isEmpty());
		System.out.println("stack.peek() " + s.peek());
		System.out.println("queue.getFront() " + q.getFront());
		System.out.println("queue.getBack() " + q.getBack());
		System.out.println("stack: " + s);
		System.out.println("queue: " + q);
		System.out.println("stack.pop() " + s.pop());
		System.out.println("queue.dequeue() " + q.dequeue());
	}

}
