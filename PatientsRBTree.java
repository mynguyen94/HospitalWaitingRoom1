/*Name: My Nguyen
 Class: CS 146
 Due Date: 12/15/17
 Description: This is the main interface to run the program
 			  by calling the object of RBTreeMenu
 */
package hospitalEmergencyRoom;

import MenuOptions.RBTreeMenu;

public class PatientsRBTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RBTreeMenu menu = new RBTreeMenu(); //call a new object of RBTreeMenu
		menu.Menu();

	}
}// end class PatientsRBTree
