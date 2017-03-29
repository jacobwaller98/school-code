
public class MyStack {
	Node head = null;
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void push(Object data) {
		Node newHead = new Node(data,head);
		head = newHead;
	}
	
	public Object top() {
		return head.getValue();
	}
	
	public void pop() {
		if(head == null) {
			return;
		} else if(head.next == null) {
			head = null;
		} else {
			head = head.next;
		}
	}
	
}

class Node {
	public Node next;
	private Object value;	
	
	public Node(Object value, Node next) {
		this.value = value;
		this.next = next;
	}
	public Object getValue() { return value; }	
	
}
