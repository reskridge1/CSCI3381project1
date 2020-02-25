//Reginald Eskridge
package project1;

import java.util.ArrayList;

public class Patient {
	private String result;
	private String prediction;
	private String id;
	private ArrayList<Double> p;
	
	public Patient() {
		result = "unknown";
		prediction = "unknown";
		id = "unknown";
		p = new ArrayList<Double>();
	}
	
	public Patient(String r, String pred, String i) { //Overloaded constructor sets private data members to parameters
		this();
		result = r;
		prediction = pred;
		id = i;
	}
	
	public Patient(String r, String pred, String i, ArrayList<Double> proteins) { //Overloaded constructor with ArrayList for the proteins added
		this(r,pred,i);
		p = proteins;
	}

	public Patient(String id) {
		// TODO Auto-generated constructor stub
		this();
		this.id = id;
	}

	//Getters and setters follow
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPred() {
		return prediction;
	}

	public void setPred(String pred) {
		this.prediction = pred;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Double> getP() {
		return p;
	}

	public void setP(ArrayList<Double> p) {
		this.p = p;
	}
	
	public String toString() { //String representation of patient object
		return result +" "+ prediction +" "+ id +" "+ p.get(0).toString() + " "+p.get(1).toString();
		
	}
	
	public boolean equals(Patient rhs) {
		if((rhs.getId()).equals(this.getId())) //Compares the ids of this and rhs to test if they are equal
			return true;
		return false;
	}
}