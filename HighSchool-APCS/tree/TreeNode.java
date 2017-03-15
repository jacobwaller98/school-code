package apcs.tree;
/**
 * 
 * @author JMWaller
 * @date   Feb 17, 2016
 * @hour   3rd Hour
 * @class  AP Computer Science
 *
 */
public class TreeNode {

	Comparable head;
	TreeNode left;
	TreeNode right;

	public TreeNode(Comparable c) {
		head = c;
	}
	
	public TreeNode(Comparable c, TreeNode left, TreeNode right){
		head = c;
		this.left = left;
		this.right = right;
	}

	public TreeNode getLeft() {
		return left;
	}

	public TreeNode getRight() {
		return right;
	}

	public Comparable getValue() {
		return head;
	}

	public void setValue(Comparable c) {
		head = c;
	}

	public void setLeft(TreeNode a) {
		left = a;
	}

	public void setRight(TreeNode a) {
		right = a;
	}

	
	public String toInOrderString() {
		if (left != null && right != null) {
			return left.toInOrderString() + ", " + head.toString() + ", " + right.toInOrderString();
		} else if (left == null && right != null) {
			return head.toString() + ", " + right.toInOrderString();
		} else if (right == null && left != null) {
			return left.toInOrderString() + ", " + head.toString();
		} else {
			return head.toString();
		}
	}
	
	public String toPreOrderString() {
		if (right != null && left != null) {
			return head.toString() + ", " + left.toPreOrderString() + ", " + right.toPreOrderString();
		} else if (left == null && right != null) {
			return head.toString() + ", " + right.toPreOrderString();
		} else if (right == null && left != null) {
			return head.toString() + ", " + left.toPreOrderString();
		} else {
			return head.toString();
		}
	}
	
	public TreeNode clone() {
		if(head == null) {
			return null;
		}
		else {
			return clone(this);
		}
	}
	
	private TreeNode clone(TreeNode root) {
		return new TreeNode(getValue(),getLeft().clone(), getRight().clone());
	}
}
