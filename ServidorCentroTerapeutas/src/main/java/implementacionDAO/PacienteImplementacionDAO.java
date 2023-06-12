package implementacionDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.PacienteDAO;
import entidades.Paciente;
import exceptions.ExcepcionTerapeuta;
import factory.UsuarioFactory;
import jdbc.DBManager;

public class PacienteImplementacionDAO implements PacienteDAO {

	@Override
	public Paciente logInPaciente(Paciente paciente) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = ""
					+ " SELECT u.*, p.Patologia"
					+ " FROM Pacientes p"
					+ " INNER JOIN Usuarios u"
					+ " ON u.DNI  = p.DNI"
					+ " WHERE u.DNI = " + paciente.getDni() +" AND u.Contrasena ='" + paciente.getContrasena() +"' AND Rol ='Paciente'";
		Paciente resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) { 
				resultado = UsuarioFactory.crearPaciente(
						rs.getInt("DNI"), 
						rs.getString("Nombre"), 
						rs.getString("Contrasena"), 
						rs.getString("Patologia"));
			}
			
		} catch (SQLException e) {
			throw new ExcepcionTerapeuta("No se pudo encontrar el paciente",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e1) {}
		}
		
		return resultado;
	}

	@Override
	public void addUsuario(Paciente paciente) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = " "
				+ " INSERT INTO Pacientes (DNI, Patologia) VALUES ( "
				+ " '" + paciente.getDni() + "', "
				+ " '" + paciente.getPatologia() + "' ) "
				+ " INSERT INTO Usuarios (DNI, Nombre, Contrasena, Rol) values ( "
				+ " " + paciente.getDni() + ", "
				+ " '" + paciente.getNombre() + "' , "
				+ " '" + paciente.getContrasena() +"',"
				+ " 'Paciente' )" ;
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new ExcepcionTerapeuta("No se pudo crear el paciente",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}

	}

	@Override
	public void deleteUsuario(Paciente paciente) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "DELETE FROM Pacientes WHERE "
				+ "DNI = " + paciente.getDni() + " "
				+ "DELETE FROM Usuarios WHERE "
				+ "DNI = " + paciente.getDni();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
			c.rollback();
			} catch (SQLException e1) {}
			throw new ExcepcionTerapeuta("No se pudo borrar el Paciente",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}

	}

}
