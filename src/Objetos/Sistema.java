package Objetos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Json.ArchivosJson;
import Objetos.Turno;


public class Sistema {
	
	private HashMap<String, ArrayList> turnos;
	

	public Sistema() {
		super();
		this.turnos = new HashMap<String, ArrayList>();
	}
	

	public boolean agregar(String nombre, LocalDate dia, String tipo) {
		
		boolean agregado = false;
		ArrayList<Turno> turnosDelDia = new ArrayList();
		
		if(turnos.containsKey(dia.toString())) {
		
			turnosDelDia = (ArrayList<Turno>) turnos.get(dia.toString());
			LocalTime proximo = proximoTurnoDelDia(dia);
			turnosDelDia = (ArrayList<Turno>) crearTurno(nombre, turnosDelDia, proximo, tipo, dia);
			turnos.put(dia.toString(), turnosDelDia);
		
		} else {
			
			LocalTime proximo = proximoTurnoDelDia(dia);
			turnosDelDia = (ArrayList<Turno>) crearTurno(nombre, turnosDelDia, proximo, tipo, dia);
			turnos.put(dia.toString(), turnosDelDia);
		
		}
		
		return agregado;	
	}
	

	public void listar() {
		
		Iterator<Entry<String, ArrayList>> it = turnos.entrySet().iterator();
		
		while(it.hasNext()) {
			
			Map.Entry<String, ArrayList> me = (Map.Entry<String, ArrayList>) it.next();
			ArrayList<Turno> turnosDelDia = me.getValue();
			System.out.println("\n----------------------------------");
			System.out.println("DIA " + me.getKey() + "\n");
			
			for(Turno aux : turnosDelDia) {
				
				System.out.println(aux.toString());
			}
		}
		
	}
	
	
	public ArrayList<Turno> crearTurno(String nombre, ArrayList<Turno> turnosDelDia, LocalTime proximo, String tipo, LocalDate fecha) {
		
		Turno turno = new Turno();
		
		if(turnosDelDia.isEmpty()) {
			
			turno.setId(1);
			turno.setNombre(nombre);
			turno.setFecha(fecha);
			turno.setDia();
			turno.setTipo(tipo);
			turno.setDuracion(tipo);
			turno.setHora(proximo);
			
			turnosDelDia.add(turno);
			
		} else {
			
		Turno turnoMayor = turnosDelDia.get(0);;
			
		for(Turno aux : turnosDelDia) {
			
			LocalTime mayor = turnoMayor.getHora();
			
			if(mayor.compareTo(aux.getHora()) < 0) {
				turnoMayor = aux;
			}
		}
		
		turno.setId(turnoMayor.getId() + 1);
		turno.setNombre(nombre);
		turno.setFecha(fecha);
		turno.setDia();
		turno.setTipo(tipo);
		turno.setDuracion(tipo);
		turno.setHora(proximo);
		
		turnosDelDia.add(turno);
		
		}
		
		return turnosDelDia;
	}
	
	
	public LocalTime proximoTurnoDelDia(LocalDate fecha) {
		
		ArrayList<Turno> turnosDelDia = new ArrayList<Turno>();
		
		Horario horario = ArchivosJson.leerHorario();
		
		LocalTime proximo = horario.getInicio();
		
		if(turnos.containsKey(fecha.toString())) {
			
			turnosDelDia = (ArrayList<Turno>) turnos.get(fecha.toString());
			
			Turno turnoMayor = turnosDelDia.get(0);;
			
			for(Turno aux : turnosDelDia) {
				
				LocalTime mayor = turnoMayor.getHora();
				
				if(mayor.compareTo(aux.getHora()) < 0) {
					turnoMayor = aux;
				}
			}
			
			proximo = turnoMayor.getHora().plusMinutes(turnoMayor.getDuracion());
		} 
		
		return proximo;
	}
	
	
	public String diaCompleto(LocalDate fecha) {
		
		String estaCompleto = "vacio";
		
		Horario horario = ArchivosJson.leerHorario();
		
		ArrayList<Turno> turnosDelDia = new ArrayList<Turno>();
		
		if(turnos.containsKey(fecha.toString())) {
			
			turnosDelDia = (ArrayList<Turno>) turnos.get(fecha.toString());
			
			Turno turnoMayor = turnosDelDia.get(0);
			
			for(Turno aux : turnosDelDia) {
				
				LocalTime mayor = turnoMayor.getHora();
				
				if(mayor.compareTo(aux.getHora()) < 0) {
					turnoMayor = aux;
				}
			}
			
			LocalTime finUltTurno = turnoMayor.getHora().plusMinutes(turnoMayor.getDuracion());
		
			if(finUltTurno.compareTo(horario.getFin()) >= 0) {
				
				estaCompleto = "lleno";
				
			} else if (finUltTurno.compareTo(horario.getInicio()) > 0) {
				
				estaCompleto = "medio";
			}
			
		}
		
		return estaCompleto;
	}
	
	
	public JSONArray toJson() {
		
		JSONArray turnosjson = new JSONArray();
		
		Iterator<Entry<String, ArrayList>> it = turnos.entrySet().iterator();
		
		while(it.hasNext()) {
			
			Map.Entry<String, ArrayList> me = (Map.Entry<String, ArrayList>) it.next();
			ArrayList<Turno> turnosDelDia = me.getValue();
			
			for(Turno aux : turnosDelDia) {
				
				turnosjson.put(aux.toJson());
			}
		}
		
		return turnosjson;
	}
	
	
	public void eliminarMesesAnteriores() {
		
		ArrayList<String> aEliminar = new ArrayList<String>();
		
		Iterator<Entry<String, ArrayList>> it = turnos.entrySet().iterator();
		
		while(it.hasNext()) {
			
			Map.Entry<String, ArrayList> me = (Map.Entry<String, ArrayList>) it.next();
			
			LocalDate diferencia = LocalDate.now().minusMonths(3);
			
			if(LocalDate.parse(me.getKey()).compareTo(diferencia) < 0) {
				aEliminar.add(me.getKey());
			}
		}
		
		for(String aux : aEliminar) {
			
			turnos.remove(aux);
		}
	}
	
	
	public void fromJson(JSONArray json) {
		
		//System.out.println(json.length());
		
		for(int i=0; i<json.length(); i++) {
			
			JSONObject turnoJson;
			
			try {
				
				ArrayList<Turno> turnosDelDia = new ArrayList();
				
				turnoJson = (JSONObject) json.get(i);
				
				Turno turno = new Turno();
				
				turno.fromJson(turnoJson);
				
				//System.out.println(turno.toString());
				
				if(turnos.containsKey(turno.getFecha().toString())) {
				
					turnosDelDia = (ArrayList<Turno>) turnos.get(turno.getFecha().toString());
					turnosDelDia.add(turno);
					turnos.put(turno.getFecha().toString(), turnosDelDia);
				
				} else {
					
					turnosDelDia.add(turno);
					turnos.put(turno.getFecha().toString(), turnosDelDia);
				
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public StringBuilder getTurnosDelDia(LocalDate fecha) {
		
		ArrayList<Turno> turnosDelDia = (ArrayList<Turno>) turnos.get(fecha.toString());
		StringBuilder sbTurnos = new StringBuilder();
		
		for(Turno aux : turnosDelDia) {
			
			sbTurnos.append("Hora: " + aux.getHora() + "  -  Tipo de turno: " + aux.getTipo() + "  -  A nombre de: " + aux.getNombre() + "\n");
		}
		return sbTurnos;
	}
	
	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}

}




