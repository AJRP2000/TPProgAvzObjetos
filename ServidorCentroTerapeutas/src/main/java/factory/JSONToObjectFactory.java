package factory;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import entidades.*;
import estados.*;

public class JSONToObjectFactory {
	
	private static JSONToObjectFactory instancia;
	private static JSONParser parser;
	
	private JSONToObjectFactory() {
		parser = new JSONParser();
	}
	
	public static JSONToObjectFactory getInstance() {
		if(instancia==null)
			instancia = new JSONToObjectFactory();
		return instancia;
	}
	
	public Admin convertAdmin(String jsonString) throws ParseException {
		jsonString = jsonString.replace("+", " ");
		jsonString = jsonString.replace("%5B", "[");
		jsonString = jsonString.replace("%5D", "]");
		jsonString = jsonString.replace("%7B", "{");
		jsonString = jsonString.replace("%7D", "}");
		jsonString = jsonString.replace("%22", "\"");
	    JSONObject json = (JSONObject) parser.parse(jsonString.toString());
	    Admin resultado = UsuarioFactory.crearAdmin(((Long)json.get("dni")).intValue(), 
	    		json.get("nombre").toString(), 
	    		json.get("contrasena").toString());
	    return resultado;
	}
	
	public Paciente convertPaciente(String jsonString) throws ParseException {
		jsonString = jsonString.replace("+", " ");
		jsonString = jsonString.replace("%5B", "[");
		jsonString = jsonString.replace("%5D", "]");
		jsonString = jsonString.replace("%7B", "{");
		jsonString = jsonString.replace("%7D", "}");
		jsonString = jsonString.replace("%22", "\"");
	    JSONObject json = (JSONObject) parser.parse(jsonString.toString());
	    String dniPaciente = json.get("dni").toString();
	    Paciente resultado = UsuarioFactory.crearPaciente(Integer.valueOf(dniPaciente) , 
	    		json.get("nombre").toString(), 
	    		json.get("contrasena").toString(),
	    		json.get("patologia").toString());
	    return resultado;
	}
	
	public Terapeuta convertTerapeuta(String jsonString) throws ParseException {
		jsonString = jsonString.replace("+", " ");
		jsonString = jsonString.replace("%5B", "[");
		jsonString = jsonString.replace("%5D", "]");
		jsonString = jsonString.replace("%7B", "{");
		jsonString = jsonString.replace("%7D", "}");
		jsonString = jsonString.replace("%22", "\"");
		JSONObject json = (JSONObject) parser.parse(jsonString.toString());
	    Terapeuta resultado = new Terapeuta(((Long)json.get("idTerapeuta")).intValue(), 
	    		json.get("nombre").toString(), 
	    		json.get("horario").toString());
	    		
	    return resultado;
	}
	
	public Turno convertTurno(String jsonString) throws ParseException {
		jsonString = jsonString.replace("+", " ");
		jsonString = jsonString.replace("%5B", "[");
		jsonString = jsonString.replace("%5D", "]");
		jsonString = jsonString.replace("%7B", "{");
		jsonString = jsonString.replace("%7D", "}");
		jsonString = jsonString.replace("%22", "\"");
		
		JSONObject json = (JSONObject) parser.parse(jsonString.toString());
	    Turno resultado = new Turno();
	    String idTurno = json.get("idTurno").toString();
	    resultado.setIdTurno(Integer.valueOf(idTurno));
	    String jsonPaciente = json.get("paciente").toString();
	    Paciente paciente = this.convertPacienteAlterno(jsonPaciente);
	    resultado.setPaciente(paciente);
	    
	    Terapeuta terapeuta = new Terapeuta();
	    resultado.setTerapeuta(terapeuta);
	    
	    resultado.setFecha(json.get("fecha").toString());
	    
	    State estado = EstadoConfirmado.getInstancia();


	    resultado.setEstado(estado);
	    
	    return resultado;
	}
	
	public List<String> convertListaFechas(String jsonString) throws ParseException {
		jsonString = jsonString.replace("+", " ");
		jsonString = jsonString.replace("%5B", "[");
		jsonString = jsonString.replace("%5D", "]");
		jsonString = jsonString.replace("%7B", "{");
		jsonString = jsonString.replace("%7D", "}");
		jsonString = jsonString.replace("%22", "\"");
		List<String> resultado = new ArrayList<String>();
		JSONArray jsonArray = (JSONArray) parser.parse(jsonString.toString());
		for(int i = 0; i<jsonArray.size(); i++) {
			resultado.add(((JSONObject)jsonArray.get(i)).toString());
		}
		return resultado;
	}
	
	public List<Turno> convertListaTurnos(String jsonString) throws ParseException {
		jsonString = jsonString.replace("+", " ");
		jsonString = jsonString.replace("%5B", "[");
		jsonString = jsonString.replace("%5D", "]");
		jsonString = jsonString.replace("%7B", "{");
		jsonString = jsonString.replace("%7D", "}");
		jsonString = jsonString.replace("%22", "\"");
		List<Turno> resultado = new ArrayList<Turno>();
		JSONArray jsonArray = (JSONArray) parser.parse(jsonString.toString());
		for(int i = 0; i<jsonArray.size(); i++) {
			resultado.add(convertTurno( ( ( (JSONObject) jsonArray.get(i) ) ).toString()));
		}
		return resultado;
	}
	
	
	public Paciente convertPacienteAlterno(String jsonString) throws ParseException {
	    JSONObject json = (JSONObject) parser.parse(jsonString.toString());
	    String dniPaciente = json.get("dni").toString();
	    Paciente resultado = UsuarioFactory.crearPaciente(Integer.valueOf(dniPaciente) , 
	    		"", 
	    		"",
	    		"");
	    return resultado;
	}
	
}
