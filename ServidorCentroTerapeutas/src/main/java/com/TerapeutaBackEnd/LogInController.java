package com.TerapeutaBackEnd;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import entidades.*;
import factory.UsuarioFactory;
import handlers.ILogInHandler;
import handlers.LogInHandler;

@Controller
public class LogInController {

	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un cliente o un error.
	@GetMapping(value = "/logInPaciente", 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity logInPaciente(@RequestParam(name="dni") String dni,
								@RequestParam(name="contrasena") String contrasena) {
		try {
			ILogInHandler logInHandler = new LogInHandler();
			Paciente usuarioIngresado = UsuarioFactory.crearPaciente(Integer.parseInt(dni),"nombre",contrasena, "patologia");
			Paciente paciente = logInHandler.logInPaciente(usuarioIngresado);
			return ResponseEntity.accepted().body(paciente);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un cliente o un error.
	@GetMapping(value = "/logInAdmin", 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity logInAdmin(@RequestParam(name="dni") String dni,
								@RequestParam(name="contrasena") String contrasena) {
		try {
			ILogInHandler logInHandler = new LogInHandler();
			Admin usuarioIngresado = UsuarioFactory.crearAdmin(Integer.parseInt(dni),"nombre",contrasena);
			Admin paciente = logInHandler.logInAdmin(usuarioIngresado);
			return ResponseEntity.accepted().body(paciente);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
