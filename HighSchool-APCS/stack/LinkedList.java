package apcs.stack;

public class LinkedList {

	private ListNode head;
	private LinkedList next;

	/**
	 * Creates a linked list with no contents
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * Creates a LinkedList with the given object as the head
	 * 
	 * @param o
	 */
	public LinkedList(Object o) {
		head = new ListNode(null, o, null);
	}

	/**
	 * Returns true if the linked list has no contents
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Returns the Object at the front of the LinkedList
	 * 
	 * @return
	 */
	public Object getFront() {
		return head.getValue();
	}

	/**
	 * Returns the final Object in the LinkedList
	 * @return
	 */
	public Object getBack() {
		try {
			if (next.head.getValue() == null) {
				return head.getValue();
			} else {
				if (next.next == null)
					return next.head.getValue();
				return next.getBack();
			}
		} catch (NullPointerException e) {
			if(head == null)
				return null;
			return head.getValue();
		}
	}

	/**
	 * Adds an object to the front of the linked list
	 * 
	 * @param o
	 */
	public void addFront(Object o) {
		try {
			if (head.getValue() != null) {
				head = new ListNode(null, o, head);

			} else {
				head = new ListNode(null, o, null);
			}
		} catch (Exception e) {
			head = new ListNode(null, o, null);
		}
	}

	/**
	 * Adds an object to the very back of the LinkedList
	 * 
	 * @param o
	 */
	public void addBack(Object o) {
		if (head == null) {
			head = new ListNode(null, o, null);
			return;
		}
		LinkedList tmp = this;
		while (tmp.next != null)
			tmp = tmp.next;
		tmp.next = new LinkedList(o);
	}

	/**
	 * 
	 * @param o
	 *            The object to search for.
	 * @return Returns the node number of the object starting with 0. Returns -1
	 *         if the object isn't there
	 */
	public int search(Object o) {
		if (head.getValue().equals(o))
			return 0;
		return next.search(o, 1);
	}

	/**
	 * Private helper method with an extra int parameter to recursively search
	 * for the given object
	 * 
	 * @param o
	 * @param i
	 * @return
	 */
	private int search(Object o, int i) {
		if (head.getValue().equals(o))
			return i;
		else {
			if (next == null)
				return -1;
			return next.search(o, i + 1);
		}
	}

	/**
	 * Removes the front Object in the Linked list and moves everything forward
	 * to compensate
	 */
	public void removeFront() {
		head = new ListNode(null, next.head, null);
		next = next.next;
	}

	/**
	 * Will remove the last element in the LinkedList
	 */
	public void removeBack() {
		if (head == null) {
			return;
		}
		LinkedList tmp = this;
		try {
			while (tmp.next.head != null) {
				tmp = tmp.next;
			}
		} catch (Exception e) {
			tmp.head = null;
			return;
		}
		tmp.head = null;

	}

	/**
	 * Returns the String representation of this
	 */
	@Override
	public String toString() {
		if (head == null)
			return null;
		String a = head.toString();
		if (next != null) {
			if (next.toString() == null)
				return a;
			a += (", " + next.toString());
		}
		return a;
	}
}
