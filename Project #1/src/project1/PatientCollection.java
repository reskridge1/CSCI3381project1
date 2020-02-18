//Reginald Eskridge
package project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PatientCollection implements PatientCollectionADT{
	private ArrayList<Patient> list;
	private ArrayList<String> ids;
	
	public PatientCollection() {
		list = new ArrayList<Patient>();
		ids = new ArrayList<String>();
	}
	
	public PatientCollection(String fn) { //Overloaded constructor for file reader testing
		this();
		readFile(fn);
	}
	
	
	public Patient getPatient(String id) {
		for(Patient patient: list) { //For each patient in the list check to see if
			if(list.contains(id)) //the list contains the id
				return patient;
		}
		return null;
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
		if(list.contains(id)) {
			Patient patient = new Patient(result, "unknown", id);
			list.add(patient);
		}
		
	}

	public ArrayList<String> getIds() {
		return ids; //Returns ArrayList containing ids
	}

	
	public String addPatientsFromFile(String fileName) {
		BufferedReader lineReader = null;
		
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			
			while((line = lineReader.readLine())!=null) {
				String[] tokens = line.split(",");
				
				String id = tokens[0]+",";
				ids.add(id);
						
					
				String pr1 = tokens[3696];
				String pr2 = tokens[3257];
				double p1 = Double.parseDouble(pr1);
				double p2 = Double.parseDouble(pr2);
					
				ArrayList<Double> proteins = new ArrayList<Double>();
				
				proteins.add(p1);
				proteins.add(p2);
					
				String prediction = Predictor.predict(p1,p2);
					
				Patient patient = new Patient("unknown", prediction, id, proteins);
				list.add(patient);
				
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while((line = lineReader.readLine())!=null) {
					String[] tokens = line.split(",");
					
					String id = tokens[0]+",";
					ids.add(id);
						
						
					String pr1 = tokens[3696];
					String pr2 = tokens[3257];
					double p1 = Double.parseDouble(pr1);
					double p2 = Double.parseDouble(pr2);
						
					ArrayList<Double> proteins = new ArrayList<Double>();
					
					proteins.add(p1);
					proteins.add(p2);
						
						
					String prediction = Predictor.predict(p1,p2);
					Patient patient = new Patient("unknown", prediction, id, proteins);
					list.add(patient);
				
				}			
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} 
		}finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e2) {
					System.err.println("could not close BufferedReader");
				}
		}			
		return list.toString();
	}
	
	private void readFile(String fileName) {
		BufferedReader lineReader = null;
		
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			
			while((line = lineReader.readLine())!=null) {
				String[] tokens = line.split(",");
				
				String response = tokens[0]+",";
				String prediction = tokens[1]+",";
				String id = tokens[2]+",";
				Patient patient = new Patient(response,prediction,id);
				
				String pr1 = tokens[3697];
				String pr2 = tokens[3258];
				double p1 = Double.parseDouble(pr1);
				double p2 = Double.parseDouble(pr2);
				
				ArrayList<Double> proteins = new ArrayList<Double>();
				
				proteins.add(p1);
				proteins.add(p2);
				
				patient.setP(proteins);
				
				list.add(patient);
				
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while((line = lineReader.readLine())!=null) {
					String[] tokens = line.split(",");
					
					String response = tokens[0]+",";
					String prediction = tokens[1]+",";
					String id = tokens[2]+",";
					Patient patient = new Patient(response,prediction,id);
					
					
					
					String pr1 = tokens[3697];
					String pr2 = tokens[3258];
					double p1 = Double.parseDouble(pr1);
					double p2 = Double.parseDouble(pr2);
					
					ArrayList<Double> proteins = new ArrayList<Double>();
				
					proteins.add(p1);
					proteins.add(p2);
					
					patient.setP(proteins);
					
					list.add(patient);
					
				}			
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} 
		}finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e2) {
					System.err.println("could not close BufferedReader");
				}
		}			
	}
	
	public void writeFile(String fn) {
		try {
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);
			
			for (int i = 0; i < list.size(); i++) {
				myOutfile.write(list.get(i).toString() +",");
				myOutfile.newLine();
			}
			
			myOutfile.flush();
			myOutfile.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}
	
}