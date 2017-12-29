/*Name: My Nguyen
 Class: CS 146
 Due Date: 12/15/17
 Description: 
 */
package RedBlackTree;

import RedBlackTree.RBTNode;
import patientsInformation.PatientInfo;
	
class RBTNode {
	
	PatientInfo info;
	RBTNode left;
	RBTNode right;
	RBTNode parent;
	String color;

	// constructor
	public RBTNode() {
		this.info = new PatientInfo();
		left = null;
		right = null;
		parent = null;
		color = " ";		
	}

	// getters
	public RBTNode getLeft() {
		return left;
	}

	public RBTNode getRight() {
		return right;
	}

	public PatientInfo getInfomration() {
		return info;
	}

	public RBTNode getParent() {
		return parent;
	}
	public String getColor(){
		return color;
	}

	// setters
	public void setLeft(RBTNode node) {
		this.left = node;
	}

	public void setRight(RBTNode node) {
		this.right = node;
	}

	public void setInformation(PatientInfo info) {
		this.info = info;
	}

	public void setParent(RBTNode node) {
		this.parent = node;
	}
	public void setColor(String theColor){
		this.color = theColor;
	}

}

//red-black tree
public class RBTree {
	
	private RBTNode root;
	public RBTNode nil = new RBTNode();

	// constructor
	public RBTree() {
		root = nil;
	}

	// setter
	public void setRoot(RBTNode node){
		this.root = node;
	}

	// getter
	public RBTNode getRoot(){
		return root;
	}

	//The operation leftRotate transforms the configuration of the two nodes
	//on the right into the configuration on the left by changing a constant number of pointers
	public void leftRotate(RBTree Tree, RBTNode node1) {
		RBTNode node2 = node1.right;			
		node1.right = node2.left;
		if (node2.left != nil) {
			node2.left.parent = node1;
		}
		node2.parent = node1.parent;
		if (node1.parent == nil) {
			Tree.root = node2;
		} else if (node1 == node1.parent.left) {
			node1.parent.left = node2;
		} else {
			node1.parent.right = node2;
		}
		node2.left = node1;
		node1.parent = node2;

	}// end leftRotate

	//The operation rightRotate transforms the configuration
	//on the left into the configuration on the right.
	public void rightRotate(RBTree Tree, RBTNode node1) {
		RBTNode node2 = node1.left;
		node1.left = node2.right;
		if (node2.right != nil) {
			node2.right.parent = node1;
		}
		node2.parent = node1.parent;
		if (node1.parent == nil) {
			Tree.root = node2;
		} else if (node1 == node1.parent.right) {
			node1.parent.right = node2;
		} else {
			node1.parent.left = node2;
		}
		node2.right = node1;
		node1.parent = node2;

	}// end rightRotate

	//this class is wrapper function of RBTInsert(RBTree Tree, RBTNode z)
	//It passes the root of the tree as an argument
	public void RBTInsert(RBTree Tree, PatientInfo patient) {
		RBTNode node = new RBTNode();
		node.info = patient;	
		RBTInsert(Tree, node);
	}
	//RBTInsert procedure is written to insert a new patient into the tree
	//as if it were an ordinary binary search tree, and then we color the new
	//patient red. 
	public void RBTInsert(RBTree Tree, RBTNode z) {	
		RBTNode y = nil;
		RBTNode x = Tree.root;
		while(x != nil) {
			y = x;
			if (z.info.getNumber() < x.info.getNumber()) {
				x = x.left;
			} else
				x = x.right;
		}
		z.parent = y;
		if (y == nil)
			Tree.root = z;
		else if (z.info.getNumber() < y.info.getNumber())
			y.left = z;
		else
			y.right = z;
		z.left = nil;
		z.right = nil;
		z.color = "RED";
		RBTInsertFixUp(Tree, z);
	}

	//To guarantee that the red-black properties are preserved, we
	//then call an auxiliary procedure RBTInsertFixUp to recolor nodes and perform
	//rotations. 
	public void RBTInsertFixUp(RBTree Tree, RBTNode z) {
		while (z.parent.color == "RED") {
			if (z.parent == z.parent.parent.left) {
				RBTNode y = z.parent.parent.right;
				if (y.color == "RED") {
					z.parent.color = "BLACK"; 			// case 1
					y.color = "BLACK";					// case 1
					z.parent.parent.color = "RED";		// case 1
					z = z.parent.parent; 				// case 1
				} else if (z == z.parent.right) {
					z = z.parent;						// case 2
					leftRotate(Tree, z); 				// case 2
				} else {
					z.parent.color = "BLACK";  			// case 3
					z.parent.parent.color = "RED";		// case 3
					rightRotate(Tree, z.parent.parent);	// case 3
				}
			} else {
				RBTNode y = z.parent.parent.left;
				if (y.color == "RED") {
					z.parent.color = "BLACK"; 			// case 1
					y.color = "BLACK"; 					// case 1
					z.parent.parent.color = "RED"; 		// case 1
					z = z.parent.parent;	 			// case 1
				} else if (z == z.parent.left) {
					z = z.parent; 						// case 2
					rightRotate(Tree, z); 				// case 2
				} else {
					z.parent.color = "BLACK"; 			// case 3
					z.parent.parent.color = "RED"; 		// case 3
					leftRotate(Tree, z.parent.parent);  // case 3
				}
			}
		} // end while
		Tree.root.color = "BLACK";
	}// end RBTInsertFixUp

	//this class is wrapper function of RBTSearch(RBTree Tree, int priorityCode)
	//It passes the root of the tree as an argument
	public RBTNode RBTSearch(int priorityCode) {
		RBTNode correctNode = new RBTNode();
		correctNode = RBTSearch(root, priorityCode);
		return correctNode;			//return nil if the code does not exist
									//otherwise, return the pointer points at that code
	}// end RBTSearch

	//search a patient by their priority code
	//if the code exists, return the address of the node
	//if it does not, return nil
	public RBTNode RBTSearch(RBTNode node, int priorityCode) {
		while (node != nil && priorityCode != node.info.getNumber()) {
			if (priorityCode < node.info.getNumber()) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return node;
	}// end RBTSearch

	//print the correspond information of the patient with the same given code
	public void printRespondPatient(int priorityCode){
		RBTNode node = RBTSearch(priorityCode);
		node.info.print();
		System.out.printf("  " + node.getColor() + "\n");
	}
	
	// move the subtree around within the binary search tree
	// this is the pseudo-code from the text book
	public void RBTransplant(RBTree Tree, RBTNode u, RBTNode v) {
		if (u.parent == nil)
			Tree.root = v;
		else if (u == u.parent.left)
			u.parent.left = v;
		else
			u.parent.right = v;
		v.parent = u.parent;
	}

	//this class is wrapper function of RBTDelete(RBTree Tree, int priorityCode)
	//It passes the root of the tree as an argument to the RBTSearch to search the code up
	//pass nil if the code is not found; otherwise pass the address of that node as an argument
	public void RBDelete(RBTree Tree, int priorityCode) {

		RBTNode node = new RBTNode();
		// search to see if the patient is in the tree. If it does, delete it
		node = RBTSearch(root, priorityCode);
		RBDelete(Tree, node);
	}
	//The procedure RBDelete is written to delete a patient from the red black tree
	//Some of the lines keep track of a node y that 
	//might cause violations of the red-black properties.
	public void RBDelete(RBTree Tree, RBTNode z) {
		RBTNode y = z;
		String yOriginalColor = y.color;
		RBTNode x = new RBTNode();
		if (z.left == nil) {
			x = z.right;
			RBTransplant(Tree, z, z.right);
		} else if (z.right == nil) {
			x = z.left;
			RBTransplant(Tree, z, z.left);
		} else {
			y = RBTMinimum(z.right);
			yOriginalColor = y.color;
			x = y.right;
			if (y.parent == z)
				x.parent = y;
			else {
				RBTransplant(Tree, y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			RBTransplant(Tree, z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if (yOriginalColor == "BLACK")
			RBTDeleteFixUp(Tree, x);
	}
	
	//this procedure is written to change the color and performs rotations
	//to maintain the property of the red black tree
	private void RBTDeleteFixUp(RBTree Tree, RBTNode x) {
		RBTNode w = new RBTNode();
		while (x != Tree.root && x.color == "BLACK") { // case 0
			if (x == x.parent.left) {
				w = x.parent.right;
				if (w.color == "RED") {
					w.color = "BLACK";					// case 1
					x.parent.color = "RED"; 			// case 1
					leftRotate(Tree, x.parent); 		// case 1
					w = x.parent.right; 				// case 1
				}
				if (w.left.color == "BLACK" && w.right.color == "BLACK") {
					w.color = "RED"; 					// case 2
					x = x.parent; 						// case 2
				} else if (w.right.color == "BLACK") {
					w.left.color = "BLACK"; 			// case 3
					w.color = "RED"; 					// case 3
					rightRotate(Tree, w); 				// case 3
					w = x.parent.right; 				// case 3
				} else {
					w.color = x.parent.color; 			// case 4
					x.parent.color = "BLACK"; 			// case 4
					w.right.color = "BLACK"; 			// case 4
					leftRotate(Tree, x.parent); 		// case 4
					x = Tree.root; 						// case 4
				}
			} else {
				w = x.parent.left;
				if (w.color == "RED") {
					w.color = "BLACK"; 					// case 1
					x.parent.color = "RED"; 			// case 1
					rightRotate(Tree, x.parent); 		// case 1
					w = x.parent.left; 					// case 1
				}
				if (w.right.color == "BLACK" && w.left.color == "BLACK") {
					w.color = "RED"; 					// case 2
					x = x.parent; 						// case 2
				} else if (w.left.color == "BLACK") {
					w.right.color = "BLACK"; 			// case 3
					w.color = "RED"; 					// case 3
					leftRotate(Tree, w); 				// case 3
					w = x.parent.left; 					// case 3
				} else {
					w.color = x.parent.color; 			// case 4
					x.parent.color = "BLACK"; 			// case 4
					w.left.color = "BLACK"; 			// case 4
					rightRotate(Tree, x.parent); 		// case 4
					x = Tree.root; 						// case 4
				}
			}
		} // end while
		x.color = "BLACK";
	}

	//this class is wrapper function of RBTMinimum(RBTNode node)
	//It passes the root of the tree as an argument
	public RBTNode BSTMinimum() {
		return RBTMinimum(root);			//pass the root of the tree as an argument
	}
	//find the patient with the minimum priority code in the red black tree
	public RBTNode RBTMinimum(RBTNode node) {
		while (node.left != nil)
			node = node.left;			//traverse the left of the root to find the patient	
										//with the least priority code
		return node;					//return the pointer points at that patient
	}

	//this class is wrapper function of RBTMaximum(RBTNode node)
	//It passes the root of the tree as an argument
	public RBTNode RBTMaximum() {
		return RBTMaximum(root);		//pass the root of the tree as an argument
	}
	//find the patient with the maximum priority code in the red black tree
	public RBTNode RBTMaximum(RBTNode node) {
		while (node.right != nil)
			node = node.right;			//traverse the left of the root to find the patient	
										//with the least priority code	
		return node;					//return the pointer points at that patient
	}

	//print the patient with the highest priority code
	public void printMaximumPatient() {
		RBTNode node = new RBTNode();
		node = RBTMaximum(); // find the patient with highest priority
		node.info.print(); // print name and priority code of the patient
		System.out.printf("  " + node.getColor()); // print out the color code
													// of that patient
	}
		
	//this class is wrapper function of inorderPrint(RBTNode node)
	//It passes the root of the tree as an argument
	public void inorderPrint() {
		inorderPrint(root);			//pass the root of the tree as an argument
	}
	//print the RBT in correct order (ascending order)
	//Information of each patient includes: name, priority code, and color code
	public void inorderPrint(RBTNode node) {
		if (node != nil) {
			inorderPrint(node.left);				//traverse the left a node
			node.info.print();						//print name + priority code of a patient
			System.out.printf("   " + node.getColor() + "\n");	//print the color code of that patient
			inorderPrint(node.right);				//traverse the right of a node
		}
	}
	
}//end RBTree class
