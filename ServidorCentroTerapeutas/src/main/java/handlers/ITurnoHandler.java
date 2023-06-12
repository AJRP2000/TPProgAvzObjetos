package handlers;

import java.util.List;

import entidades.*;

public interface ITurnoHandler {

	public void altaTurno(Turno turno) throws Exception;
	
	public void editarTurno(Turno turno) throws Exception;
	
	public void bajaTurno(Turno turno) throws Exception;
	
	public List<String> mostrarDisponibilidad(String fecha) throws Exception;
	
	public Turno consultarTurno(Turno turno) throws Exception;
	
	public List<Turno> consultarTurnosPaciente(Paciente paciente) throws Exception;
	
}
