package Json;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Objetos.Dia;
import Objetos.Horario;
import Objetos.Tipo;


public class ArchivosJson {

	
			public static void grabarTurnos(JSONArray array)
			{
				try {
					FileWriter file = new FileWriter("data/turnos.json");
					
					file.write(array.toString());
					file.flush();
					file.close();

				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			
			public static JSONArray leerTurnos() 
			{
				JSONArray contenido = new JSONArray();
				try 
				{
					String string = new String(Files.readAllBytes(Paths.get("data/turnos.json")));
					contenido = new JSONArray(string);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
				return contenido;
			}
			
			
			public static ArrayList<Dia> leerDias() {
				
				ArrayList<Dia> dias = new ArrayList<Dia>();
				
				JSONArray contenido = new JSONArray();
				try 
				{
					String string = new String(Files.readAllBytes(Paths.get("config/dias.json")));
					contenido = new JSONArray(string);
					
					for(int i=0; i<contenido.length(); i++) {
						
						JSONObject json = (JSONObject) contenido.get(i);
						Dia dia = new Dia();
						dia.fromJson(json);
						dias.add(dia);
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
				
				return dias;
			}
			
			
			public static ArrayList<Tipo> leerTipos() {
				
				ArrayList<Tipo> tipos = new ArrayList<Tipo>();
				
				JSONArray contenido = new JSONArray();
				try 
				{
					String string = new String(Files.readAllBytes(Paths.get("config/tipos.json")));
					contenido = new JSONArray(string);
					
					for(int i=0; i<contenido.length(); i++) {
						
						JSONObject json = (JSONObject) contenido.get(i);
						Tipo tipo = new Tipo();
						tipo.fromJson(json);
						tipos.add(tipo);
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
				
				return tipos;
			}
			
			
			public static Horario leerHorario() {
				
				Horario horario = new Horario();
				
				JSONObject contenido = new JSONObject();
				
				try 
				{
					String string = new String(Files.readAllBytes(Paths.get("config/horario.json")));
					contenido = new JSONObject(string);
					horario.fromJson(contenido);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
				
				return horario;
			}
			
			
			public static void grabarTXT(String turno)
			{
				try {
					FileWriter file = new FileWriter("data/turno.txt");
					
					file.write(turno);
					file.flush();
					file.close();

				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			
			public static void abrirTXT() {
				
				File txt = new File("data/turno.txt");
				try {
					Desktop.getDesktop().open(txt);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
				
}
