package apcs.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author JMWaller
 * @date Feb 17, 2016
 * @hour 3rd Hour
 * @class AP Computer Science
 *
 */
public class Tree implements BSTree {

	TreeNode root;

	/**
	 * Constructs a new tree with nothing as the root
	 */
	public Tree() {
		root = null;
	}

	/**
	 * Constructs a new tree with the given Comparable as the root
	 * 
	 * @param o
	 *            The root of the constructed tree
	 */
	public Tree(Comparable o) {
		root = new TreeNode(o);
	}

	/**
	 * Adds the given Comparable to the tree
	 * 
	 * @param o
	 *            The new object to be added to the tree
	 */
	public void add(Comparable o) {
		if (root == null) {
			root = new TreeNode(o);
			return;
		}
		TreeNode temp = root;
		boolean done = false;
		while (!done) {
			int comp = temp.getValue().compareTo(o);
			if (comp > 0) {
				if (temp.getLeft() == null) {
					temp.setLeft(new TreeNode(o));
					done = !done;
				} else
					temp = temp.getLeft();
			} else {
				if (temp.getRight() == null) {
					temp.setRight(new TreeNode(o));
					done = !done;
				} else
					temp = temp.getRight();
			}
		}
	}

	/**
	 * Returns true if the given Comparable is inside the tree
	 * 
	 * @param s
	 *            The object to be searched for
	 */
	public boolean search(Comparable s) {
		TreeNode temp = root;
		while (temp != null) {
			if (temp.getValue() == s) {
				return true;
			}
			if (temp.getValue().compareTo(s) < 0) {
				temp = temp.getRight();
			} else if (temp.getValue().compareTo(s) > 0) {
				temp = temp.getLeft();
			}
		}
		return false;
	}

	/**
	 * Returns the tallest leaf of the tree
	 * 
	 * @return The tallest leaf's height
	 */
	public int height() {
		if (root == null)
			return 0;
		else if (root.getLeft() != null && root.getRight() != null) {
			return Math.max(height(root.getLeft(), 1), height(root.getRight(), 1));
		} else if (root.getLeft() == null) {
			System.out.println("In max");
			return height(root.getRight(), 1);
		} else if (root.getRight() == null) {
			return height(root.getLeft(), 1);
		} else {
			return -1;
		}
	}

	/**
	 * Private helper method to return the height recusively
	 * 
	 * @param x
	 * @param i
	 * @return
	 */
	private int height(TreeNode x, int i) {
		if (x == null)
			return i;
		else if (x.getLeft() != null && x.getRight() != null) {
			return Math.max(height(x.getLeft(), i + 1), height(x.getRight(), i + 1));
		} else if (x.getLeft() == null) {
			return height(x.getRight(), i + 1);
		} else if (x.getRight() == null) {
			return height(x.getLeft(), i + 1);
		} else {
			return -1;
		}
	}

	/**
	 * @return The PreOrder String representation of the tree
	 */
	public String toStringPreOrder() {
		return root.toPreOrderString();
	}

	/**
	 * @return The InOrder String representation of the tree
	 */
	public String toStringInOrder() {
		return root.toInOrderString();
	}

	public String toStringLevelAOrder() {
		String a = "";
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()) {
			TreeNode tempNode = q.poll();
			a+=tempNode.getValue().toString();
			if(tempNode.getLeft()!=null)
				q.add(tempNode.getLeft());
			if(tempNode.getRight() != null)
				q.add(tempNode.getRight());
		}
		return a;
	}
	
	public String toStringLevelOrder() {
		String a = "";
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()) {
			TreeNode temp = q.poll();
			a+=temp.getValue().toString();
			if(temp.getLeft()!=null)
				q.add(temp.getLeft());
			if(temp.getRight()!=null)
				q.add(temp.getRight());
		}
		return a;
	}
	
	
	
	

	/**
	 * Removes the given comparable from the tree
	 * 
	 * @param x
	 */
	public void remove(Comparable c) {
		if (!search(c))
			return;
		TreeNode temp = root;
		TreeNode parent = root;
		int comp = 1;
		while (comp != 0) {
			comp = temp.getValue().compareTo(c);
			if (comp > 0) {
				temp = temp.getLeft();
				if (temp.getValue().compareTo(c) != 0) {
					parent = temp;
				}
			} else if (comp < 0) {
				temp = temp.getRight();
				if (temp.getValue().compareTo(c) != 0) {
					parent = temp;
				}
			}
		}
		// If node has no children
		if (temp.getLeft() == null && temp.getRight() == null) {
			if (parent.getRight().getValue().compareTo(c) == 0) {
				parent.setRight(null);
			}
			if (parent.getLeft().getValue().compareTo(c) == 0) {
				parent.setLeft(null);
			}
			return;
		} else if (temp.getLeft() != null && temp.getRight() == null) {
			TreeNode otherTemp = temp.getLeft();
			temp.setValue(temp.getLeft().getValue());
			temp.setRight(otherTemp.getLeft());
			return;
		} else if (temp.getLeft() == null && temp.getRight() != null) {
			TreeNode otherTemp = temp.getRight();
			temp.setValue(temp.getRight().getValue());
			temp.setRight(otherTemp.getRight());
			return;
		} else { // Has two children
			// If the lowest of the higher is temp.getRight()
			if (temp.getRight().getLeft() == null) {
				TreeNode tempNode = null;
				tempNode = temp.getRight().getRight();
				temp.setValue(temp.getRight().getValue());
				temp.setRight(tempNode);
				return;
			} else {
				TreeNode otherTemp = temp.getRight();
				while (otherTemp.getLeft() != null) {
					otherTemp = otherTemp.getLeft();
				}
				temp.setValue(otherTemp.getValue());
				temp.getRight().setLeft(null);
				return;
			}

		}
	}

}
