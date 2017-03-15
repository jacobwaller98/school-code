package apcs.tree;
/**
 * 
 * @author JMWaller
 * @date   Feb 17, 2016
 * @hour   3rd Hour
 * @class  AP Computer Science
 *
 */
public class Driver {

	Tree t = new Tree();

	public static void main(String args[]) {
		new Driver();
	}

	public Driver() {
		//Adds all the letters
		t.add("L");
		t.add("D");
		t.add("A");
		t.add("F");
		t.add("B");
		t.add("R");
		t.add("M");
		t.add("U");
		t.add("T");
		t.add("V");
		System.out.println("Height of the tree: " + t.height());
		TreeNode root = t.root.clone();
		Tree x = new Tree();
		x.root = root;
		System.out.println(x.toStringLevelOrder());
		System.out.println(t.toStringLevelOrder());
		
	}
}
		/*
		 	::OUTPUT::
		 	
		    Height of the tree: 4
			Is "B" in the tree? true
			Removed Nothing
			toStringInOrder(): A, B, D, F, L, M, R, T, U, V
			toStringPreOrder(): L, D, A, B, F, R, M, U, T, V
			Removed "A"
			toStringInOrder(): B, D, F, L, M, R, T, U, V
			toStringPreOrder(): L, D, B, F, R, M, U, T, V
			Removed "B"
			toStringInOrder(): D, F, L, M, R, T, U, V
			toStringPreOrder(): L, D, F, R, M, U, T, V
			Removed "U"
			toStringInOrder(): D, F, L, M, R, T, V
			toStringPreOrder(): L, D, F, R, M, V, T
			Removed "R"
			toStringInOrder(): D, F, L, M, T, V
			toStringPreOrder(): L, D, F, T, M, V
			Removed "L"
			toStringInOrder(): D, F, M, T, V
			toStringPreOrder(): M, D, F, T, V
			Is "B" in the tree? false
			Height of the tree: 3
			*/
