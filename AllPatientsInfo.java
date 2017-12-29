/*Name: My Nguyen
 Class: CS 146
 Due Date: 12/15/17
 Description: This class contains an array object of all patients
 */
package patientsInformation;

public class AllPatientsInfo {
	
	public PatientInfo patients[] = new PatientInfo[20]; //an array of patients
	
	//getter
	public PatientInfo getPatients(int i){
		return patients[i];
	}
	
	//setter
	public void setPatients(PatientInfo info, int i){
		this.patients[i] = info;
	}

}
