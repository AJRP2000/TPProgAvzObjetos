package com.TerapeutaBackEnd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import entidades.Paciente;
import entidades.Turno;
import factory.JSONToObjectFactory;
import factory.ObjectToJSONFactory;
import handlers.ITurnoHandler;
import handlers.TurnoHandler;

@Controller
public class TurnoController {
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un cliente o un error.
	@GetMapping(value = "/altaTurno", 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity altaTurno(@RequestParam(name="turno") String turno) {
		try {
			ITurnoHandler turnoHandler = new TurnoHandler();
			JSONToObjectFactory factory = JSONToObjectFactory.getInstance();
			Turno turnoObjeto = factory.convertTurno(turno);
			turnoHandler.altaTurno(turnoObjeto);
			return ResponseEntity.accepted().body("Turno creado con exito");
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
	}
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un cliente o un error.
	@GetMapping(value = "/editarTurno", 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity editarTurno(@RequestParam(name="turno") String turno){
		try {
			ITurnoHandler turnoHandler = new TurnoHandler();
			JSONToObjectFactory factory = JSONToObjectFactory.getInstance();
			Turno turnoObjeto = factory.convertTurno(turno);
			turnoHandler.editarTurno(turnoObjeto);
			return ResponseEntity.accepted().body("Turno editado con exito");
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
	}
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un cliente o un error.
	@GetMapping(value = "/bajaTurno", 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity bajaTurno(@RequestParam(name="turno") String turno) {
		try {
			ITurnoHandler turnoHandler = new TurnoHandler();
			JSONToObjectFactory factory = JSONToObjectFactory.getInstance();
			Turno turnoObjeto = factory.convertTurno(turno);
			turnoHandler.bajaTurno(turnoObjeto);
			return ResponseEntity.accepted().body("Turno dado de baja con exito");
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
	}
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un cliente o un error.
	@GetMapping(value = "/mostrarDisponibilidad", 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity mostrarDisponibilidad(@RequestParam(name="fecha") String fecha){
		try {
			ITurnoHandler turnoHandler = new TurnoHandler();
			List<String> listaHorarios = turnoHandler.mostrarDisponibilidad(fecha);
			return ResponseEntity.accepted().body(listaHorarios);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
	}
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un cliente o un error.
	@GetMapping(value = "/consultarTurno", 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity consultarTurno(@RequestParam(name="turno") String turno){
		try {
			ITurnoHandler turnoHandler = new TurnoHandler();
			JSONToObjectFactory factory = JSONToObjectFactory.getInstance();
			Turno turnoObjeto = factory.convertTurno(turno);
			Turno turnoresultado = turnoHandler.consultarTurno(turnoObjeto);
			
			List<Turno> listaTurnos = new ArrayList<Turno>();
			listaTurnos.add(turnoresultado);
			ObjectToJSONFactory factory2 = ObjectToJSONFactory.getInstance();			
			String respuesta = factory2.convertListaTurnos(listaTurnos);
			return ResponseEntity.accepted().body(respuesta);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
	}
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un cliente o un error.
	@GetMapping(value = "/consultarTurnosPaciente", 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity consultarTurnosPaciente(@RequestParam(name="paciente") String paciente) {
		try {
			ITurnoHandler turnoHandler = new TurnoHandler();
			JSONToObjectFactory factory = JSONToObjectFactory.getInstance();
			Paciente pacienteObjeto = factory.convertPacienteAlterno(paciente);
			List<Turno> listaTurnos = turnoHandler.consultarTurnosPaciente(pacienteObjeto);
			ObjectToJSONFactory factory2 = ObjectToJSONFactory.getInstance();			
			String respuesta = factory2.convertListaTurnos(listaTurnos);
			return ResponseEntity.accepted().body(respuesta);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
	}
}
