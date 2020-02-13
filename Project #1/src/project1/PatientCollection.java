package project1;

import java.util.ArrayList;

public class PatientCollection implements PatientCollectionADT{
	private String file;
	private ArrayList<Patient> list;
	
	public PatientCollection(String fn) {
		file = fn;
		list = new ArrayList<Patient>();
	}
	
	public Patient getPatient(String id) {
		for(Patient patient: list) { //For each patient in the list check to see
			if(list.contains(id)) { //if the list contains the id
				return patient; 
			}
		}
		return null; //returns null if the id is not in the collection
	}

	public Patient removePatient(String id) {
		for (Patient patient : list) { //For each patient in the list check to see if
			if(list.contains(id)) { //the list contains the id then remove
				list.remove(patient);
				return patient;
			}
		}
		return null; //returns null if the id is not in the collection
	}

	public void setResultForPatient(String id, String result) {
		
		
	}

	public ArrayList<String> getIds() {
		
		return null;
	}

	
	public String addPatientsFromFile(String fileName) {
		
		return null;
	}
	
	public void readFile() {
		
	}
	
	public void writeFile() {
		
	}
}