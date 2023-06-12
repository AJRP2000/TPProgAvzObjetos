package handlers;

import dao.AdminDAO;
import dao.PacienteDAO;
import entidades.Admin;
import entidades.Paciente;
import exceptions.ExcepcionTerapeuta;
import implementacionDAO.AdminImplementacionDAO;
import implementacionDAO.PacienteImplementacionDAO;

public class LogInHandler implements ILogInHandler {
	
	private static PacienteDAO pacienteDAO = new PacienteImplementacionDAO();
	private static AdminDAO adminDAO = new AdminImplementacionDAO();
	
	public Paciente logInPaciente(Paciente paciente) throws ExcepcionTerapeuta {
		Paciente resultado = null;
		try {
			resultado = pacienteDAO.logInPaciente(paciente);
		} catch (ExcepcionTerapeuta e) {
			e.printStackTrace();
			throw new ExcepcionTerapeuta(e.getMessage());
		}
		
		return resultado;
	}
	
	public Admin logInAdmin(Admin admin) throws ExcepcionTerapeuta {
		Admin resultado = null;
		try {
			resultado = adminDAO.logInAdmin(admin);
		} catch (ExcepcionTerapeuta e) {
			e.printStackTrace();
			throw new ExcepcionTerapeuta(e.getMessage());
		}
		
		return resultado;
	}
}
