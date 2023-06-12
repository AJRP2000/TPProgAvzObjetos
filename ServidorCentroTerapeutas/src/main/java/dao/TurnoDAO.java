package dao;

import java.util.List;

import entidades.Paciente;
import entidades.Turno;
import exceptions.ExcepcionTerapeuta;

public interface TurnoDAO {
	
	void altaTurno(Turno turno) throws ExcepcionTerapeuta;
	
	void bajaTurno(Turno turno) throws ExcepcionTerapeuta;
	
	Turno consultaTurno(Turno turno) throws ExcepcionTerapeuta;
	
	Turno editarTurno(Turno turno) throws ExcepcionTerapeuta;
	
	List<Turno> consultaTurnosXPaciente(Paciente paciente) throws ExcepcionTerapeuta;
	
	List<String> getHorariosDisponibles(String fechaInicio, String fechaFin) throws ExcepcionTerapeuta;
	
}
