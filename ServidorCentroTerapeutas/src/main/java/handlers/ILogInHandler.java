package handlers;

import entidades.Admin;
import entidades.Paciente;
import exceptions.ExcepcionTerapeuta;

public interface ILogInHandler {

	public Paciente logInPaciente(Paciente paciente) throws ExcepcionTerapeuta;
	
	public Admin logInAdmin(Admin admin) throws ExcepcionTerapeuta;
	
}
