package implementacionDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.TerapeutaDAO;
import entidades.Terapeuta;
import exceptions.ExcepcionTerapeuta;
import jdbc.DBManager;

public class TerapeutaImplementacionDAO implements TerapeutaDAO {

	@Override
	public Terapeuta getTerapeutaTurno(String turno) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = ""
					+ " SELECT *"
					+ " FROM Terapeutas"
					+ " WHERE Horario = '" + turno +"'";
		Terapeuta resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) { 
				resultado = new Terapeuta();
				resultado.setIdTerapeuta(rs.getInt("IdTerapeuta"));
				resultado.setNombre(rs.getString("Nombre"));
				resultado.setHorario(turno);			
				}
			
		} catch (SQLException e) {
			throw new ExcepcionTerapeuta("No se pudo encontrar el Terapeuta",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e1) {}
		}
		
		return resultado;
	}

	@Override
	public Terapeuta getTerapeuta(Terapeuta terapeuta) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = ""
					+ " SELECT *"
					+ " FROM Terapeutas"
					+ " WHERE IdTerapeuta = " + terapeuta.getIdTerapeuta();
		Terapeuta resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) { 
				resultado = new Terapeuta();
				resultado.setIdTerapeuta(rs.getInt("IdTerapeuta"));
				resultado.setNombre(rs.getString("Nombre"));		
				resultado.setHorario(rs.getString("Horario"));
				
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
	public void addTerapeuta(Terapeuta terapeuta) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = " "
				+ " INSERT INTO Terapeutas (Nombre, Horario) values ( "
				+ " '" + terapeuta.getNombre() + "' , "
				+ " '" + terapeuta.getHorario() +"')" ;
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new ExcepcionTerapeuta("No se pudo crear el Terapeuta",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
	}

	@Override
	public void deleteTerapeuta(Terapeuta terapeuta) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "DELETE FROM Terapeutas WHERE "
				+ "IdTerapeuta = " + terapeuta.getIdTerapeuta();
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
