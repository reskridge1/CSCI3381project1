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
		readFile();
	}
	
	public Patient getPatient(String id) {
		int index = list.indexOf(new Patient(id));
		if (index<0)
			return null;
		else
			return list.get(index);
	}

	public Patient removePatient(String id) {
		Patient toRemove = new Patient(id);
		int index  = list.indexOf(toRemove);
		if (index >= 0) {
			Patient toReturn = list.remove(index);
			list.remove(toRemove);
			ids.remove(id);
			return toReturn;
		}
		else {
			return null;
		}
	}

	public void setResultForPatient(String id, String result) {
		Patient pat = getPatient(id);
		if (pat!=null) {
			pat.setResult(result);
		}
	}

	public ArrayList<String> getIds() {
		return ids; //Returns ArrayList containing ids
	}

	
	public String addPatientsFromFile(String fileName) {
		// format
				//id,protein1,protein2, ... , protein4776
				String errors = "";
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(fileName));
					String line;
					int lineNum = 1;
					while ((line = br.readLine()) != null) {
						System.out.println(line.substring(0,30));
						String[] tokens = line.split(",");
						int index = line.indexOf(',');
						String values = line.substring(index+1);
						String prediction = Predictor.predict(Double.parseDouble(tokens[3697]), Double.parseDouble(tokens[3258]));
						
						double p1 = Double.parseDouble(tokens[3697]);
						double p2 = Double.parseDouble(tokens[3258]);
						
						ArrayList<Double> doubles = new ArrayList<Double>();
						doubles.add(p1);
						doubles.add(p2);
						
						Patient toAdd = new Patient("unknown",prediction,tokens[0],doubles);
						if (ids.contains(tokens[0])) {
							errors += "line # "+lineNum+" "+tokens[0]+" is not a unique identifier\n";
						}
						else if (tokens.length!=4777) {
							errors += "line # "+lineNum+" not the correct number of values\n";
						}
						else {
							list.add(toAdd);
							ids.add(tokens[0]);
							;				}
						lineNum++;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
				return errors;
	}
	
	private void readFile() {
		BufferedReader lineReader = null;
		
		try {
			FileReader fr = new FileReader("./project1/data.csv");
			lineReader = new BufferedReader(fr);
			String line;
			
			while((line = lineReader.readLine())!=null) {
				String[] tokens = line.split(",");
				
				String response = tokens[0]+",";
				String prediction = tokens[1]+",";
				String id = tokens[2]+",";
				
				ArrayList<Double> proteins = new ArrayList<Double>();
				String pr1 = tokens[3697];
				String pr2 = tokens[3258];
				double p1 = Double.parseDouble(pr1);
				double p2 = Double.parseDouble(pr2);
				proteins.add(p1);
				proteins.add(p2);
				
				Patient patient = new Patient(response,prediction,id,proteins);
				
				list.add(patient);
				ids.add(tokens[2]);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				lineReader.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeFile(String fn) {
		try {
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);
			
			for (Patient pat: list) {
				myOutfile.write(pat.getResult()+","+pat.getPred()+","+pat.getId()+","+pat.getP());
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
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		for (Patient patient : list) {
			sb.append(patient+"\n");
		}
		return sb.toString();
	}
	
}