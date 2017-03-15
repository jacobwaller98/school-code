package apcs.stack;

public class ListNode {
	private Object node;
	private ListNode before;
	private ListNode after;

	/**
	 * Creates a new ListNode
	 * @param b
	 * @param node
	 * @param a
	 */
	public ListNode(ListNode b, Object node, ListNode a) {
		this.node = node;
		before = b;
		after = a;
	}
	
	/**
	 * Returns this value of the current node
	 * @return
	 */
	public Object getValue() {
		return node;
	}
	
	/**
	 * Returns the next node
	 * @return
	 */
	public Object getAfter() {
		return after.getValue();
	}
	
	/**
	 * returns the Previous node's value
	 * @return
	 */
	public Object getBefore() {
		return before.getValue();
	}
	
	/**
	 * Set the next Node to a new node
	 * @param a
	 */
	public void setAfter(ListNode a) {
		after = a;
	}
	
	/**
	 * Set's the previous node
	 * @param b
	 */
	public void setBefore(ListNode b) {
		before = b;
	}

	/**
	 * Creates a new node that has the value of null;
	 */
	public ListNode() {
		node = null;
	}

	/**
	 * Returns the objects string
	 */
	public String toString() {
		if(node == null)
			return null;
		return node.toString();
	}

}
