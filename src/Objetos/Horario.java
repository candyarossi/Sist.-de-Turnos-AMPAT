package Objetos;

import java.time.LocalTime;
import org.json.JSONException;
import org.json.JSONObject;


public class Horario {
	
	private LocalTime inicio; // 09:00
	private LocalTime fin; // 12:00
	
	
	public Horario() {
		super();
		this.inicio = LocalTime.parse("00:00");
		this.fin = LocalTime.parse("00:00");
	}
	
	
	public LocalTime getInicio() {
		return inicio;
	}


	public void setInicio(LocalTime inicio) {
		this.inicio = inicio;
	}


	public LocalTime getFin() {
		return fin;
	}


	public void setFin(LocalTime fin) {
		this.fin = fin;
	}

	
	public void fromJson(JSONObject json) {
		
		try {
			
			this.setInicio(LocalTime.parse(json.getString("Inicio")));
			this.setFin(LocalTime.parse(json.getString("Fin")));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public String toString() {
		return "Horario [inicio=" + inicio + ", fin=" + fin + "]";
	}
	
	
}
