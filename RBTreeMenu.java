/*Name: My Nguyen
 Class: CS 146
 Due Date: 12/15/17
 Description: This is the main menu of choices. There are for options.
 			  Base on the choice, the user can see the current list of patients,
 			  insert a new patient, search for a new patient, or delete a patient
 			  from the list (or from the red black tree)
 */
package MenuOptions;

import java.util.Scanner;
import RedBlackTree.RBTree;
import patientsInformation.AllPatientsInfo;
import patientsInformation.PatientInfo;
import patientsInformation.ReadFromFile;

public class RBTreeMenu {
	
	Scanner input = new Scanner(System.in);
	
	public void Menu(){
		
		char repeat;   		//holds the user's answer if they want to continue the program
		String name;		//holds the name of a patient
		int priorityNumber; //holds the priority code of a patient
		int index = 21;		//since the first 20 patient get the first 20 numbers in the array
							//random numbers, the next one is inserted in will get the random
							//number starts at index 21
		
		ReadFromFile file = new ReadFromFile();   //create a new object of ReadFromFile
		file.randomNumber(); // generate a random number for each patient

		AllPatientsInfo info = file.readFile(); //store information of all patients in an array
		RBTree rbt = new RBTree();  //create an object of binary search tree
		
		//insert the first 20 patients into the binary search tree
		for (int i = 0; i < 20; i++) {
			//info.getPatients(i).print();
			rbt.RBTInsert(rbt, info.getPatients(i));	
		}
		
		System.out.printf("WELCOME TO THE HOSPITAL EMERGENCY ROOM!\n");
		
		// the user must choose one of the option from this main menu
		do {
			System.out.printf("\nPlease chose one of the following options\n");
			System.out.printf("-----------------------------------------\n");
			System.out.printf("1. See the curent list of patients\n");
			System.out.printf("2. Insert a new patient\n");
			System.out.printf("3. Search for a patient's name by the priority code\n");
			System.out.printf("4. Delete a patient\n");

			//prompt the user to input one choice between 1 and 4
			System.out.printf("\nYour choice: ");
			int choice = input.nextInt();
			System.out.printf("\n");

			//if the user choice is other than the number between 1 and 4
			//ask them to enter again
			while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
				System.out.printf("Invalid choice. Please enter again(from 1 to 4 only): ");
				choice = input.nextInt();
				System.out.printf("\n");
			}

			// perform different function based on the user's choice using switch statement
			switch (choice) {

			case 1:
				System.out.printf("DISPLAY!\n");
				// print out the red black tree in order			
				printPatientList();
				rbt.inorderPrint();
				break;
			case 2:
				// print out the red black tree in order			
				printPatientList();
				rbt.inorderPrint();
				// print out the patient with most priority and then delete that person
				System.out.printf("\n=> " + "Currently, we are helping patient: ");
				rbt.printMaximumPatient();
				System.out.printf("\n");
				rbt.RBDelete(rbt, rbt.RBTMaximum());
				System.out.printf("This patient is removed from the list.\n\n");
				System.out.printf("INSERTION!\n");
				System.out.printf("You can add a new patient now!\n\n");

				// insert a new patient
				// only need to enter the name
				// the priority number is set from the array contains random
				// numbers
				PatientInfo patient = new PatientInfo(); // create an object of
															// a patient
				name = nameInput();			//get patient's name from user's input
				patient.setName(name);		//set the patient's name
				patient.setNumber(file.numArray[index]); //set the information for that patient

				rbt.RBTInsert(rbt, patient); 	//insert the new patient into RB Tree

				//display the change after inserting
				printPatientList();
				rbt.inorderPrint();

				index++; //increase the index of the random number array
				break;
			case 3:
				//display the current list of patient
				printPatientList();
				rbt.inorderPrint();
				System.out.printf("\n");
				System.out.printf("SEARCH!\n");
				//prompt the user for the priority code of the patient that they want to look for
				priorityNumber = priorityCodeInput();
				//call the search function in RBTree class to search up that patient
				//if the priority code does not exist, print an error message
				if(rbt.RBTSearch(priorityNumber) == rbt.nil){
					System.out.printf("The priority code does not exist! ");
					System.out.printf("The patient is not in the list\n");
				}
				//if the code does exist, display the corresponding information of the patient
				else{
					System.out.printf("The patient is found: ");
					rbt.printRespondPatient(priorityNumber);
				}	
				break;
			case 4:
				//display the current list of patient
				printPatientList();
				rbt.inorderPrint();
				System.out.printf("\n");
				System.out.printf("DELETION!\n");
				//get the priority code that the user wants to delete
				priorityNumber = priorityCodeInput();
				//search up the priority code first to see if the code exist
				//id it does not, display an error message
				if (rbt.RBTSearch(priorityNumber) == rbt.nil) {
					System.out.printf("The priority code does not exist! ");
					System.out.printf("\nNo Deletion!\n");
				} 
				//if it does, delete the patient with that code from the list
				else {
					System.out.printf("The patient will be removed which is: ");
					rbt.printRespondPatient(priorityNumber);
					rbt.RBDelete(rbt, priorityNumber);
					System.out.printf("\nDeletion is successful!\n");
					printPatientList();
					rbt.inorderPrint();
				}

			}// end switch

			//prompt the user if they want to select another option from the main menu
			System.out.printf("\nDo you want to choose another option? ");
			repeat = input.next().charAt(0);

		} while (repeat == 'y' || repeat == 'Y'); //allow the user to choose another choice
												  //when they enter y or Y
												  //Otherwise, stop the program
		input.close();
	}
	
	//get the input name of a patient from the user
	public String nameInput(){
		System.out.printf("Please enter name of the patient\n");
		System.out.printf("Name: ");
		String name = input.next();		
		 
		 return name;
	}
	
	//get the input priority code of a patient from the user
	public int priorityCodeInput(){
		System.out.printf("Please enter priority code of the patient\n");
		System.out.printf("Number: ");
		int number = input.nextInt();
		
		return number;
	}
	
	//print the little headline before printing the list of patients
	public void printPatientList(){
		System.out.printf("--------------------\n");
		System.out.printf("* List of patients *\n");
		System.out.printf("--------------------\n");
	}

}
