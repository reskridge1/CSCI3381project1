//Reginald Eskridge
package project1;

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PatientCollection ptCol = new PatientCollection();
		
		String fn = "test.csv";
		String fromFile = "./project1/data.csv";
		//String fromFile = "./project1/newdata.csv";
				
		ptCol.addPatientsFromFile(fromFile);
		ptCol.writeFile(fn);
		
		PatientCollection ptCol2 = new PatientCollection(fromFile);
		ptCol2.writeFile(fn);
		
		
		String prediction = Predictor.predict(20.9,20.1); //Tests predictor
		
		ArrayList<Double> p = new ArrayList<Double>();
		
		p.add(20.9786);
		p.add(22.0139);
		
		Patient pat = ptCol.getPatient("1");
		ArrayList<String> thing = new ArrayList<String>();
		thing = ptCol.getIds(); //Tests the getIds method
//		pat.setId("1");
//		pat.setP(p);
//		pat.setResult("unknown");
//		pat.setPred(prediction);
		System.out.println("Test: " +pat);
		
		Patient patient = new Patient("unknown", prediction, "1", p);
		
		System.out.println(patient); //Tests toString method
		
		
	}

}
