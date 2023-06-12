package dao;

import entidades.Paciente;
import exceptions.ExcepcionTerapeuta;

public interface PacienteDAO {
	
	Paciente logInPaciente(Paciente paciente) throws ExcepcionTerapeuta;
	
	void addUsuario(Paciente paciente) throws ExcepcionTerapeuta;
	
	void deleteUsuario(Paciente paciente) throws ExcepcionTerapeuta;

}
