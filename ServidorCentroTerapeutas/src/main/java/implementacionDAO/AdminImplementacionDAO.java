package implementacionDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.AdminDAO;
import entidades.Admin;
import exceptions.ExcepcionTerapeuta;
import factory.UsuarioFactory;
import jdbc.DBManager;

public class AdminImplementacionDAO implements AdminDAO {

	@Override
	public Admin logInAdmin(Admin admin) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = ""
					+ " SELECT *"
					+ " FROM Usuarios"
					+ " WHERE DNI = " + admin.getDni() +" AND Contrasena ='" + admin.getContrasena() +"' AND Rol = 'Admin'";
		Admin resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) { 
				resultado = UsuarioFactory.crearAdmin(
						rs.getInt("DNI"), 
						rs.getString("Nombre"), 
						rs.getString("Contrasena"));		
				}
			
		} catch (SQLException e) {
			throw new ExcepcionTerapeuta("No se pudo encontrar el admin",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e1) {}
		}
		
		return resultado;
	}

	@Override
	public void addAdmin(Admin admin) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = " "
				+ " INSERT INTO Usuarios (DNI, Nombre, Contrasena, Rol) values ( "
				+ " " + admin.getDni() + ", "
				+ " '" + admin.getNombre() + "' , "
				+ " '" + admin.getContrasena() +"',"
				+ " 'Admin' )" ;
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new ExcepcionTerapeuta("No se pudo crear el Admin",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
	}

	@Override
	public void deleteAdmin(Admin admin) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "DELETE FROM Usuarios WHERE "
				+ "DNI = " + admin.getDni();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
			c.rollback();
			} catch (SQLException e1) {}
			throw new ExcepcionTerapeuta("No se pudo borrar el Admin",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
	}

}
