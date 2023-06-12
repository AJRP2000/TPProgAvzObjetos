package implementacionDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.TurnoDAO;
import entidades.Paciente;
import entidades.Terapeuta;
import entidades.Turno;
import estados.EstadoCancelado;
import estados.EstadoConfirmado;
import estados.EstadoCreado;
import estados.EstadoEditado;
import estados.State;
import exceptions.ExcepcionTerapeuta;
import jdbc.DBManager;

public class TurnoImplementacionDAO implements TurnoDAO {

	@Override
	public void altaTurno(Turno turno) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = " "
				+ " INSERT INTO Turnos (DniPaciente, IdTerapeuta, Fecha, Estado) values ( "
				+ " " + turno.getPaciente().getDni() + ", "
				+ " " + turno.getTerapeuta().getIdTerapeuta() + ", "
				+ " '" + turno.getFecha() +"', "
				+ " '" + turno.getEstado().toString() + "')" ;
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new ExcepcionTerapeuta("No se pudo crear el Turno",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
	}

	@Override
	public void bajaTurno(Turno turno) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = " "
				+ " UPDATE Turnos "
				+ " SET Estado = '" + EstadoCancelado.getInstancia().toString() + "' "
				+ " WHERE IdTurno = " + turno.getIdTurno();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new ExcepcionTerapeuta("No se pudo actualizar el Turno",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
	}

	@Override
	public Turno consultaTurno(Turno turno) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = ""
					+ " SELECT *"
					+ " FROM Turnos"
					+ " WHERE IdTurno = " + turno.getIdTurno();
		Turno resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) { 
				resultado = new Turno();
				resultado.setIdTurno(rs.getInt("IdTurno"));
				
				Paciente paciente = new Paciente();
				paciente.setDni(rs.getInt("DniPaciente"));
				resultado.setPaciente(paciente);
				
				Terapeuta terapeuta = new Terapeuta();
				terapeuta.setIdTerapeuta(rs.getInt("IdTerapeuta"));
				resultado.setTerapeuta(terapeuta);
				
				resultado.setFecha(rs.getString("Fecha"));
				
				String estadoString = rs.getString("estado");
			    State estado = null;
			    if(estadoString.equals("Creado")) {
			    	estado = EstadoCreado.getInstancia();
			    }
			    if(estadoString.equals("Editado")) {
			    	estado = EstadoEditado.getInstancia();
			    }
			    if(estadoString.equals("Confirmado")) {
			    	estado = EstadoConfirmado.getInstancia();
			    }
			    if(estadoString.equals("Cancelado")) {
			    	estado = EstadoCancelado.getInstancia();
			    }
			    resultado.setEstado(estado);
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
	public Turno editarTurno(Turno turno) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = " "
				+ " UPDATE Turnos "
				+ " SET DniPaciente = " + turno.getPaciente().getDni()
				+ " , IdTerapeuta = " + turno.getTerapeuta().getIdTerapeuta()
				+ " , Fecha = '" + turno.getFecha() +"' "
				+ " , Estado =  '" + turno.getEstado().toString() + "' "
				+ " WHERE IdTurno = " + turno.getIdTurno();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new ExcepcionTerapeuta("No se pudo editar el Turno",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
		
		return turno;
	}

	@Override
	public List<Turno> consultaTurnosXPaciente(Paciente pacienteId) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = ""
					+ " SELECT *"
					+ " FROM Turnos"
					+ " WHERE DniPaciente = " + pacienteId.getDni() + " AND Estado !='"+ EstadoCancelado.getInstancia().toString() + "' ";
		List<Turno> resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			resultado = new ArrayList<Turno>();
			while(rs.next()) { 
				Turno turno = new Turno();
				turno.setIdTurno(rs.getInt("IdTurno"));
				
				Paciente paciente = new Paciente();
				paciente.setDni(rs.getInt("DniPaciente"));
				turno.setPaciente(paciente);
				
				Terapeuta terapeuta = new Terapeuta();
				terapeuta.setIdTerapeuta(rs.getInt("IdTerapeuta"));
				turno.setTerapeuta(terapeuta);
				
				turno.setFecha(rs.getString("Fecha"));
				
				String estadoString = rs.getString("estado");
			    State estado = null;
			    if(estadoString.equals("Creado")) {
			    	estado = EstadoCreado.getInstancia();
			    }
			    if(estadoString.equals("Editado")) {
			    	estado = EstadoEditado.getInstancia();
			    }
			    if(estadoString.equals("Confirmado")) {
			    	estado = EstadoConfirmado.getInstancia();
			    }
			    if(estadoString.equals("Cancelado")) {
			    	estado = EstadoCancelado.getInstancia();
			    }
			    turno.setEstado(estado);
			    
			    resultado.add(turno);
			}
			
		} catch (SQLException e) {
			throw new ExcepcionTerapeuta("No se pudieron encontrar turnos para el paciente seleccionado",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e1) {}
		}
		
		if(resultado.size()==0)
			resultado = null;
		
		return resultado;
	}

	@Override
	public List<String> getHorariosDisponibles(String fechaInicio, String fechaFin) throws ExcepcionTerapeuta {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = ""
					+ " SELECT Fecha"
					+ " FROM Turnos"
					+ " WHERE Fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' "
					+ " AND Estado != '"+  EstadoCancelado.getInstancia().toString()  +"' ";
		List<String> resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			resultado = new ArrayList<String>();
			while(rs.next()) {
			    resultado.add(rs.getString("Fecha"));
			}
			
		} catch (SQLException e) {
			throw new ExcepcionTerapeuta("Ocurrio un error obteniendo las fechas disponibles",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e1) {}
		}
		
		if(resultado.size()==0)
			resultado = null;
		
		return resultado;
	}

}
