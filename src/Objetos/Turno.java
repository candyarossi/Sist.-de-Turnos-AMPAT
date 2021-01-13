package Objetos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import Json.ArchivosJson;


public class Turno {
  
	private int id;
	private String nombre;
	private String dia;
	private LocalDate fecha; // De Lunes a Viernes.
	private LocalTime hora; // De 9 a 12 am.
	private String tipo; // Transferencias, Altas o Bajas.
	private int duracion; // En minutos. Transferencias = 1h -> 60m. Altas/Bajas = 40m.
	
	
	public Turno() {
		super();
		this.id = 0;
		this.nombre = "";
		this.dia = "";
		this.fecha = LocalDate.parse("2021-01-01");
		this.hora = LocalTime.parse("09:00");
		this.tipo = "";
		this.duracion = 0;
	}
	
	
	public Turno(int id, String nombre, LocalDate fecha, LocalTime hora, String tipo, int duracion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.setDia();
		this.hora = hora;
		this.tipo = tipo;
		this.duracion = duracion;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getDia() {
		return dia;
	}
	

	public void setDia() {
		
		ArrayList<Dia> dias = ArchivosJson.leerDias();
		
		int dia = this.getFecha().getDayOfWeek().getValue();
		
		for(Dia aux : dias) {
			
			if(aux.getId() == dia) {
				
				this.dia = aux.getEspanol();
			}
		}
	}

	
	public LocalDate getFecha() {
		return fecha;
	}
	
	
	public String getFecha2() {
		return fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear();
	}

	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	
	public LocalTime getHora() {
		return hora;
	}

	
	public void setHora(LocalTime hora) {
		this.hora = hora;
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

	
	public void setDuracion(String tipo) {
		
		ArrayList<Tipo> tipos = ArchivosJson.leerTipos();
		
		for(Tipo aux : tipos) {
			
			if(aux.getTipo().compareTo(tipo) == 0) {
				
				this.duracion = aux.getDuracion();
			}
		}
	}

	
	@Override
	public String toString() {
		return "TURNO " + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "-" + id 
				+ "\n\nNombre: " + nombre + "\nFecha: " + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear()
				+ "\nDia: " + dia + "\nHora: " + hora + "\nTipo: " + tipo + "\nDuracion: " + duracion + "\n";
	}
	
	
	public JSONObject toJson() {
		
		JSONObject jsonObj = new JSONObject();
		
		try 
		{
			jsonObj.put("ID", id);
			jsonObj.put("Dia", dia);
			jsonObj.put("Nombre", nombre);
			jsonObj.put("Fecha", fecha);
			jsonObj.put("Hora", hora);
			jsonObj.put("Tipo", tipo);
			jsonObj.put("Duracion", duracion);
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return jsonObj;
	}

	
	@Override
	public int hashCode() {
		return 1;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turno other = (Turno) obj;
		if (duracion != other.duracion)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	
	public void fromJson(JSONObject json) {
		
		try {
			
			this.setId(json.getInt("ID"));
			this.setNombre(json.getString("Nombre"));
			this.setFecha(LocalDate.parse(json.getString("Fecha")));
			this.setDia();
			this.setHora(LocalTime.parse(json.getString("Hora")));
			this.setTipo(json.getString("Tipo"));
			this.setDuracion(json.getString("Tipo"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
}






