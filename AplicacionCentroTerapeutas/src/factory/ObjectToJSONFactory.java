package factory;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import entidades.*;

public class ObjectToJSONFactory {

	private static ObjectToJSONFactory instancia;
	private static Gson gson;
	
	private ObjectToJSONFactory() {
		gson = new Gson();
	}
	
	public static ObjectToJSONFactory getInstance() {
		if(instancia==null)
			instancia = new ObjectToJSONFactory();
		return instancia;
	}
	
	public String convertAdmin(Admin admin) {
		String resultado = gson.toJson(admin);
		resultado = resultado.replace(" ", "+");
		resultado = resultado.replace("[", "%5B");
		resultado = resultado.replace("]", "%5D");
		resultado = resultado.replace("{", "%7B");
		resultado = resultado.replace("}", "%7D");
		resultado = resultado.replace("\"", "%22");
		return resultado;
	}

	public String convertPaciente(Paciente paciente) {
		String resultado = gson.toJson(paciente);
		resultado = resultado.replace(" ", "+");
		resultado = resultado.replace("[", "%5B");
		resultado = resultado.replace("]", "%5D");
		resultado = resultado.replace("{", "%7B");
		resultado = resultado.replace("}", "%7D");
		resultado = resultado.replace("\"", "%22");
		return resultado;
	}

	public String convertTerapeuta(Terapeuta terapeuta) {
		String resultado = gson.toJson(terapeuta);
		resultado = resultado.replace(" ", "+");
		resultado = resultado.replace("[", "%5B");
		resultado = resultado.replace("]", "%5D");
		resultado = resultado.replace("{", "%7B");
		resultado = resultado.replace("}", "%7D");
		resultado = resultado.replace("\"", "%22");
		return resultado;
	}

	public String convertTurno(Turno turno) {
		String resultado = gson.toJson(turno);
		resultado = resultado.replace(" ", "+");
		resultado = resultado.replace("[", "%5B");
		resultado = resultado.replace("]", "%5D");
		resultado = resultado.replace("{", "%7B");
		resultado = resultado.replace("}", "%7D");
		resultado = resultado.replace("\"", "%22");
		return resultado;
	}
	
	public String convertListaTurnos(List<Turno> turnos) {
		JSONArray jsonArray = new JSONArray();
        
        for (Turno turno : turnos) {
            JSONObject json = new JSONObject();
            json.put("idTurno", turno.getIdTurno());
            json.put("paciente", turno.getPaciente());
            json.put("terapeuta", turno.getTerapeuta());
            json.put("fecha", turno.getFecha());
            json.put("estado", turno.getEstado().toString());

            jsonArray.add(json);
        }
        String resultado = jsonArray.toString();
		resultado = resultado.replace(" ", "+");
		resultado = resultado.replace("[", "%5B");
		resultado = resultado.replace("]", "%5D");
		resultado = resultado.replace("{", "%7B");
		resultado = resultado.replace("}", "%7D");
		resultado = resultado.replace("\"", "%22");
		return resultado;
	}

}
