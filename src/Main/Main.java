package Main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import Json.ArchivosJson;
import Objetos.Dia;
import Objetos.Horario;
import Objetos.Sistema;
import Objetos.Tipo;
import Objetos.Turno;


public class Main {

	public static void main(String[] args) {
		
		
		/*Sistema sistema = new Sistema();
		
		sistema.agregar("Juancito", LocalDate.parse("2020-03-10"), "Baja");
		
		sistema.agregar("Juancito", LocalDate.parse("2020-03-10"), "Transferencia");
		
		sistema.agregar("Juancito", LocalDate.parse("2020-03-10"), "Alta");
		
		sistema.agregar("Juancito", LocalDate.parse("2020-03-10"), "Transferencia");
		
		//sistema.agregar("Juancito", LocalDate.parse("2021-03-10"), "Alta");

		sistema.agregar("Juancito", LocalDate.parse("2020-10-14"), "Transferencia");

		//sistema.listar();
		
		//LocalTime proximo = sistema.proximoTurnoDelDia(LocalDate.parse("2021-03-10"));
		
		//System.out.println("Proximo turno del dia 10-03: " + proximo.toString());
	
		String completo = sistema.diaCompleto(LocalDate.parse("2021-10-14"));
	
		System.out.println("Esta el 10-03 lleno? " + completo);
		
		
		/*JSONArray json = sistema.toJson();
		
		System.out.println(json.toString());
		
		System.out.println("\n------------------------\n");
		
		ArchivosJson.grabarTurnos(json); 
		
		JSONArray contenido = ArchivosJson.leerTurnos();
		
		//System.out.println(contenido);
		
		//System.out.println("\n------------------------\n");
		
		
		//sistema.listar();
		
		//System.out.println("\n------------------------\n");
		
		//sistema.eliminarMesesAnteriores();
		
		//sistema.listar();
	
		
		Sistema sistema2 = new Sistema();
		
		sistema2.fromJson(contenido);

		sistema2.listar();
		
		//sistema2.eliminarMesesAnteriores();
		
	    //sistema2.listar();
		
		
		/*ArrayList<Dia> dias = ArchivosJson.leerDias();
		ArrayList<Tipo> tipos = ArchivosJson.leerTipos();
		Horario horario = ArchivosJson.leerHorario();
		
		
		for(Dia aux : dias) {
			
			System.out.println(aux.toString());
			
		}
		
		System.out.println("\n------------------------\n");
		
		
		for(Tipo aux : tipos) {
			
			System.out.println(aux.toString());
			
		}
		
		System.out.println("\n------------------------\n");
		
		System.out.println(horario.toString());*/
	
	}

}
