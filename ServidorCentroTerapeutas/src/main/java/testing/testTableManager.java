package testing;

import java.sql.Timestamp;

import dao.*;
import entidades.*;
import estados.EstadoCancelado;
import estados.EstadoConfirmado;
import factory.UsuarioFactory;
import implementacionDAO.*;
import jdbc.TableManager;

public class testTableManager {
	public static void main(String [] args) {
		TableManager tm = new TableManager();
		AdminDAO adminDAO = new AdminImplementacionDAO();
		PacienteDAO pacienteDAO = new PacienteImplementacionDAO();
		TerapeutaDAO terapeutaDAO = new TerapeutaImplementacionDAO();
		TurnoDAO turnoDAO = new TurnoImplementacionDAO();
		
		Timestamp tiempo = new Timestamp(System.currentTimeMillis());
		System.out.println(tiempo.toString());
		
		//Paso 1: Borramos todas las tablas
		tm.dropTablaUsuarios();
		tm.dropTablaTerapeutas();
		tm.dropTablaTurnos();
		tm.dropTablaPacientes();
		System.out.println("Borrado");
		
		//Paso 2: Creamos todas las tablas
		tm.createTablaUsuarios();
		tm.createTablaTerapeutas();
		tm.createTablaPacientes();
		tm.createTablaTurnos();
		
		insertarPacientes(pacienteDAO);
		insertarAdmins(adminDAO);
		insertarTerapeutas(terapeutaDAO); 
		insertarTurnos(turnoDAO);
		
		System.out.println("Creado");
		tiempo = new Timestamp(System.currentTimeMillis());
		System.out.println(tiempo.toString());
	}
	
	//Funcion que insertara los datos de los pacientes para testear
	static void insertarPacientes(PacienteDAO pacienteDAO) {
		Paciente paciente1 = UsuarioFactory.crearPaciente(123, "Paciente 1", "contrasena1", "Patologia1");
		Paciente paciente2 = UsuarioFactory.crearPaciente(456, "Paciente 2", "contrasena2", "Patologia2");
		Paciente paciente3 = UsuarioFactory.crearPaciente(789, "Paciente 3", "contrasena3", "Patologia3");
		
		try {
			pacienteDAO.addUsuario(paciente1);
			pacienteDAO.addUsuario(paciente2);
			pacienteDAO.addUsuario(paciente3);
		}catch(Exception e) {
			System.out.println("Error creando Pacientes");
		}
	} 
	
	//Funcion que insertara los Admins para testear
	static void insertarAdmins(AdminDAO adminDAO) {
		Admin admin1 = UsuarioFactory.crearAdmin(321, "Admin 1", "contrasena1");
		Admin admin2 = UsuarioFactory.crearAdmin(654, "Admin 2", "contrasena2");
		
		try {
			adminDAO.addAdmin(admin1);
			adminDAO.addAdmin(admin2);
		}catch(Exception e) {
			System.out.println("Error creando admins");
		}
	} 
	
	//Funcion que insertara a los terapeutas de los 3 turnos
	static void insertarTerapeutas(TerapeutaDAO terapeutaDAO) {
		Terapeuta terapeuta1 = new Terapeuta(0, "Terapeuta Manana", "Manana");
		Terapeuta terapeuta2 = new Terapeuta(1, "Terapeuta Tarde", "Tarde");
		Terapeuta terapeuta3 = new Terapeuta(2, "Terapeuta Noche", "Noche");
		
		try {
			terapeutaDAO.addTerapeuta(terapeuta1);
			terapeutaDAO.addTerapeuta(terapeuta2);
			terapeutaDAO.addTerapeuta(terapeuta3);
		}catch(Exception e) {
			System.out.println("Error creando Terapeutas");
		}
	} 
	
	//Funcion que insertara los turnos para testear
	static void insertarTurnos(TurnoDAO turnoDAO) {
		
		Paciente paciente2 = UsuarioFactory.crearPaciente(456, "Paciente 2", "contrasena2", "Patologia2");
		Paciente paciente3 = UsuarioFactory.crearPaciente(789, "Paciente 3", "contrasena3", "Patologia3");
		
		Terapeuta terapeuta1 = new Terapeuta(1, "Terapeuta Manana", "Manana");
		Terapeuta terapeuta2 = new Terapeuta(2, "Terapeuta Tarde", "Tarde");
		Terapeuta terapeuta3 = new Terapeuta(3, "Terapeuta Noche", "Noche");
		
		
		Turno turno1 = new Turno();
		turno1.setPaciente(paciente2);
		turno1.setTerapeuta(terapeuta2);
		turno1.setFecha("2023-06-14 14:00:00");
		turno1.setEstado(EstadoConfirmado.getInstancia());
		
		Turno turno2 = new Turno();
		turno2.setPaciente(paciente3);
		turno2.setTerapeuta(terapeuta1);
		turno2.setFecha("2023-06-14 08:30:00");
		turno2.setEstado(EstadoConfirmado.getInstancia());
		
		Turno turno3 = new Turno();
		turno3.setPaciente(paciente3);
		turno3.setTerapeuta(terapeuta3);
		turno3.setFecha("2023-06-14 18:00:00");
		turno3.setEstado(EstadoConfirmado.getInstancia());
		
		Turno turno4 = new Turno();
		turno4.setPaciente(paciente3);
		turno4.setTerapeuta(terapeuta1);
		turno4.setFecha("2023-06-14 09:30:00");
		turno4.setEstado(EstadoCancelado.getInstancia());
		
		
		try {
			turnoDAO.altaTurno(turno1);
			turnoDAO.altaTurno(turno2);
			turnoDAO.altaTurno(turno3);
			turnoDAO.altaTurno(turno4);
		}catch(Exception e) {
			System.out.println("Error creando turnos");
		}
	}
}
