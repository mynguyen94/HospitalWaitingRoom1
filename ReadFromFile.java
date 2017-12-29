/*Name: My Nguyen
 Class: CS 146
 Due Date: 12/15/17
 Description: In the class ReadFromFile
 				1. randomNumber(): Generate an array of random number between 1 and 30
 				2. readFile(): read 20 names of patient from a file call NameOfPatients
 				3. buildPatientsName(): set the information including name and priority code
 				for each patient object
 */
package patientsInformation;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class ReadFromFile {
	
	//generate a random number for each patient
	public Integer[] numArray = new Integer[30];

	public void randomNumber() {

		for (int i = 0; i < 30; i++) {
			numArray[i] = i + 1;
		}

		// shuffle the array
		Collections.shuffle(Arrays.asList(numArray));
	}

	// read the first 20 patients' names from the file
	// build an array object of all the patients
	public AllPatientsInfo readFile() {

		int count = 0;
		AllPatientsInfo info = new AllPatientsInfo();

		try {
			// open the file
			FileReader file = new FileReader("NameOfPatients");
			BufferedReader buff = new BufferedReader(file);

			boolean eof = false;

			while (!eof) {
				// read one line at a time
				String line = buff.readLine();
				count++;

				if (line == null) {
					eof = true;
				} else {
					// display the data from the file
					// System.out.printf(line);
					// System.out.printf(" ");

					if (count == 0)
						;
					else if (count > 0) {
						buildPatientsName(count - 1, info, line);
						// System.out.print(patientNumber);
						// info.getPatients(count-1).print();
					}
				}
			} // end while

			buff.close();

		} catch (IOException e) {
			// display error message when text file cannot be opened
			System.out.println("Error-- " + e.toString());
		}

		return info;
	}// end readFile

	// build information for each patient
	public void buildPatientsName(int index, AllPatientsInfo info, String name) {

		PatientInfo patient = new PatientInfo();

		// set name for each patient
		patient.setName(name);

		// set the number for each patient
		patient.setNumber(numArray[index]);

		// insert the patient info into BST
		info.setPatients(patient, index);

	}// end buildPatientsInfo

}
