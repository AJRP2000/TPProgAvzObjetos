package jdbc;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

	public void createTablaUsuarios() {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "CREATE TABLE Usuarios (DNI INT PRIMARY KEY, "
				+ "Nombre VARCHAR(50), "
				+ "Contrasena VARCHAR(50), "
				+ "Rol VARCHAR(50))";
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createTablaPacientes() {
		DBManager dbm= DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "CREATE TABLE Pacientes (DNI INT PRIMARY KEY, " 
				+ "Patologia VARCHAR(100))";
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createTablaTurnos() {
		DBManager dbm= DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "CREATE TABLE Turnos (IdTurno INTEGER IDENTITY PRIMARY KEY, " 
				+ "DniPaciente INT, "
				+ "IdTerapeuta INT, "
				+ "Fecha TIMESTAMP, "
				+ "Estado VARCHAR(20))";
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createTablaTerapeutas() {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "CREATE TABLE Terapeutas (IdTerapeuta INTEGER IDENTITY PRIMARY KEY, "
				+ "Nombre VARCHAR(50),"
				+ "Horario VARCHAR(50))";
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void dropTabla(String nombreTabla) {
		DBManager dbm= DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "DROP TABLE " + nombreTabla;
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void dropTablaUsuarios() {
		dropTabla("Usuarios");
	}
	
	public void dropTablaPacientes() {
		dropTabla("Pacientes");
	}
	
	public void dropTablaTurnos() {
		dropTabla("Turnos");
	}
	
	public void dropTablaTerapeutas() {
		dropTabla("Terapeutas");
	}
}
