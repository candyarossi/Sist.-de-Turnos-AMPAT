package Objetos;

import org.json.JSONException;
import org.json.JSONObject;


public class Tipo {

	private String tipo; // Transferencias, Altas o Bajas.
	private int duracion; // En minutos. Transferencias = 1h -> 60m. Altas/Bajas = 40m.
	
	
	public Tipo() {
		super();
		this.tipo = "";
		this.duracion = 0;
	}
	
	
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getDuracion() {
		return duracion;
	}


	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	public void fromJson(JSONObject json) {
		
		try {
			
			this.setTipo(json.getString("Tipo"));
			this.setDuracion(json.getInt("Duracion"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public String toString() {
		return "Tipo [tipo=" + tipo + ", duracion=" + duracion + "]";
	}
	
	
}
