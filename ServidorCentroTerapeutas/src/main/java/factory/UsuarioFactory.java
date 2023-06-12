package factory;

import entidades.Admin;
import entidades.Paciente;

public class UsuarioFactory {
	
	public static Paciente crearPaciente(int dni, String nombre, String contrasena, String patologia) {
		Paciente paciente = new Paciente(dni,nombre,contrasena, patologia);
		return paciente;
	}
	
	public static Admin crearAdmin(int dni, String nombre, String contrasena) {
		Admin admin = new Admin(dni,nombre,contrasena);
		return admin;
	}

}
