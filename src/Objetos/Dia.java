package Objetos;

import org.json.JSONException;
import org.json.JSONObject;


public class Dia {

	private int id;
	private String ingles;
	private String espanol;
	
	
	public Dia() {
		super();
		this.id = 0;
		this.ingles = "";
		this.espanol = "";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getIngles() {
		return ingles;
	}


	public void setIngles(String ingles) {
		this.ingles = ingles;
	}


	public String getEspanol() {
		return espanol;
	}


	public void setEspanol(String espanol) {
		this.espanol = espanol;
	}
	
	
	public void fromJson(JSONObject json) {
		
		try {
			
			this.setId(json.getInt("ID"));
			this.setIngles(json.getString("Ingles"));
			this.setEspanol(json.getString("Espanol"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public String toString() {
		return "Dia [ID=" + id + ", ingles=" + ingles + ", espanol=" + espanol + "]";
	}
	
	
}
