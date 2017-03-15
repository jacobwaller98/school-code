package apcs.tree;
/**
 * 
 * @author JMWaller
 * @date   Feb 17, 2016
 * @hour   3rd Hour
 * @class  AP Computer Science
 *
 */
public interface BSTree {
	
	void add(Comparable<Object> c);
	
	boolean search(Comparable<Object> c);
	
	int height(); // returns the maximum length of any leaf in the tree
	
	String toStringPreOrder(); // prints the Tree using Pre-Order traversal
	
	String toStringInOrder(); // prints the Tree elements using In-Order traversal
	
	void remove(Comparable<Object> c);

}
