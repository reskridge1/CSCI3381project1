package project1;

import java.util.ArrayList;

public class Patient {
	private String result;
	private String prediction;
	private String id;
	private ArrayList<Double> p;
	
	public Patient() {
		result = "result";
		prediction = "unknown";
		id = "not set";
		p = new ArrayList<Double>();
	}

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
	
	public String toString() {
		return result +" "+ prediction +" "+ id;
		
	}
}