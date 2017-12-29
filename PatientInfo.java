/*Name: My Nguyen
 Class: CS 146
 Due Date: 12/15/17
 Description: This class consists information of each patient:
			  the name and the priority code
 */
package patientsInformation;

public class PatientInfo {
	
	private String patientName;
	private int orderNumber;
	
	//constructor
	public PatientInfo (){
		patientName = " ";
		orderNumber = 0;
	}
	
	//getter
	public String getName(){
		return patientName;
	}
	public int getNumber(){
		return orderNumber;
	}
	
	//setter
	public void setName(String name){
		this.patientName = name;
	}
	public void setNumber(int number){
		this.orderNumber = number;
	}
	
	//display information for each patient
	public void print(){
			System.out.printf(getName() + "  " + getNumber());
	}

}
