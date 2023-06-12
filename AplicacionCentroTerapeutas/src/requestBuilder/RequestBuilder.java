package requestBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import entidades.*;
import exceptions.ExcepcionTerapeuta;
import factory.JSONToObjectFactory;
import factory.ObjectToJSONFactory;

public class RequestBuilder {
			
	private static final String path = "http://localhost:8080/";
	
	public static Paciente requestLogInPaciente(Paciente usuario) throws Exception {
		
		Paciente resultado = null;
		StringBuilder result = new StringBuilder();
		String parametros = "?dni=" + usuario.getDni()+"&contrasena=" + usuario.getContrasena();
		URL url = new URL(path + "logInPaciente" + parametros);
		//System.out.println(path + "logInPaciente" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
		    JSONToObjectFactory jsonToObjectFactory = JSONToObjectFactory.getInstance();
		    resultado = jsonToObjectFactory.convertPaciente(result.toString());
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new ExcepcionTerapeuta (result.toString());
	    }
	    
	    return resultado;
	}
	
	public static Admin requestLogInAdmin(Admin usuario) throws Exception {
		Admin resultado = null;
		StringBuilder result = new StringBuilder();
		String parametros = "?dni=" + usuario.getDni()+"&contrasena=" + usuario.getContrasena();
		URL url = new URL(path + "logInAdmin" + parametros);
		//System.out.println(path + "logIn" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
		    JSONToObjectFactory jsonToObjectFactory = JSONToObjectFactory.getInstance();
		    resultado = jsonToObjectFactory.convertAdmin(result.toString());
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new ExcepcionTerapeuta (result.toString());
	    }
	    
	    return resultado;
	}

	public static String altaTurno(Turno turno) throws Exception {
		String resultado = null;
		
		ObjectToJSONFactory factory = ObjectToJSONFactory.getInstance();
		String turnoJson = factory.convertTurno(turno);
		
		StringBuilder result = new StringBuilder();
		String parametros = "?turno=" + turnoJson;
		URL url = new URL(path + "altaTurno" + parametros);
		//System.out.println(path + "logIn" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
			    resultado = result.toString();
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new ExcepcionTerapeuta (result.toString());
	    }
		
		
		return resultado;
	}
	
	public static String editarTurno(Turno turno) throws Exception {
		String resultado = null;
		
		ObjectToJSONFactory factory = ObjectToJSONFactory.getInstance();
		String turnoJson = factory.convertTurno(turno);
		
		StringBuilder result = new StringBuilder();
		String parametros = "?turno=" + turnoJson;
		URL url = new URL(path + "editarTurno" + parametros);
		//System.out.println(path + "logIn" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
			    resultado = result.toString();
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new ExcepcionTerapeuta (result.toString());
	    }
		
		
		return resultado;
	}

	public static String bajaTurno(Turno turno) throws Exception {
		String resultado = null;
		
		ObjectToJSONFactory factory = ObjectToJSONFactory.getInstance();
		String turnoJson = factory.convertTurno(turno);
		
		StringBuilder result = new StringBuilder();
		String parametros = "?turno=" + turnoJson;
		URL url = new URL(path + "bajaTurno" + parametros);
		//System.out.println(path + "logIn" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
			    resultado = result.toString();
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new ExcepcionTerapeuta (result.toString());
	    }
		
		
		return resultado;
	}
	
	public static List<String> mostrarDisponibilidad(String fecha) throws Exception {
		List<String> resultado = null;
		fecha = fecha.replace(" ", "+");
		StringBuilder result = new StringBuilder();
		String parametros = "?fecha=" + fecha;
		URL url = new URL(path + "mostrarDisponibilidad" + parametros);
		//System.out.println(path + "logIn" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
		    	JSONToObjectFactory jsonToObjectFactory = JSONToObjectFactory.getInstance();
			    resultado = jsonToObjectFactory.convertListaFechas(result.toString());
			}
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new ExcepcionTerapeuta (result.toString());
	    }
		
		
		return resultado;
	}
	
	public static List<Turno> consultarTurno(Turno turno) throws Exception {
		List<Turno> resultado = null;
		
		ObjectToJSONFactory factory = ObjectToJSONFactory.getInstance();
		String turnoJson = factory.convertTurno(turno);
		
		StringBuilder result = new StringBuilder();
		String parametros = "?turno=" + turnoJson;
		URL url = new URL(path + "consultarTurno" + parametros);
		//System.out.println(path + "logIn" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
		    	JSONToObjectFactory jsonToObjectFactory = JSONToObjectFactory.getInstance();
			    resultado = jsonToObjectFactory.convertListaTurnos(result.toString());
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new ExcepcionTerapeuta (result.toString());
	    }
				
		return resultado;
	}
	
	
	public static List<Turno> consultarTurnosPaciente(Paciente paciente) throws Exception {
		List<Turno> resultado = null;
		
		ObjectToJSONFactory factory = ObjectToJSONFactory.getInstance();
		String turnoJson = factory.convertPaciente(paciente);
		
		StringBuilder result = new StringBuilder();
		String parametros = "?paciente=" + turnoJson;
		URL url = new URL(path + "consultarTurnosPaciente" + parametros);
		//System.out.println(path + "logIn" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
		    	JSONToObjectFactory jsonToObjectFactory = JSONToObjectFactory.getInstance();
			    resultado = jsonToObjectFactory.convertListaTurnos(result.toString());
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new ExcepcionTerapeuta (result.toString());
	    }
				
		return resultado;
	}
	
}
