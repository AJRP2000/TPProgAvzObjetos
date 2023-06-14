package handlers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.TerapeutaDAO;
import dao.TurnoDAO;
import entidades.*;
import exceptions.*;
import implementacionDAO.*;

public class TurnoHandler implements ITurnoHandler{

	private static TerapeutaDAO terapeutaDAO = new TerapeutaImplementacionDAO();
	private static TurnoDAO turnoDAO = new TurnoImplementacionDAO();
	
	@Override
	public void altaTurno(Turno turno) throws Exception {
		Terapeuta terapeuta = getTerapeuta(turno.getFecha());
		checkHorario(turno.getFecha());
		turno.setTerapeuta(terapeuta);
		
		checkearTurnosSemana(turno);
		checkearTurnoDisponible(turno);
		turnoDAO.altaTurno(turno);
	}

	@Override
	public void editarTurno(Turno turno) throws Exception {
		Terapeuta terapeuta = getTerapeuta(turno.getFecha());
		checkHorario(turno.getFecha());
		turno.setTerapeuta(terapeuta);
		Turno turnoPrevio = turnoDAO.consultaTurno(turno);
		if(turnoPrevio == null)
			throw new ExcepcionTerapeuta("El turno ingresado no existe");
		
		if(turnoPrevio.getEstado().toString().equals("Cancelado"))
			throw new ExcepcionTerapeuta("No se puede editar un turno cancelado");
		
		if(turno.getPaciente().getDni()!=turnoPrevio.getPaciente().getDni())
			throw new ExcepcionTerapeuta("Este turno no es para el paciente ingresado");
		
		checkearTurnosSemana(turno);
		checkearTurnoDisponible(turno);
		
		turnoDAO.editarTurno(turno);
	}

	@Override
	public void bajaTurno(Turno turno) throws Exception {
		
		Turno turnoLogeado = turnoDAO.consultaTurno(turno);
		if(turnoLogeado == null)
			throw new ExcepcionTerapeuta("El turno ingresado no existe");
		
		if(turno.getPaciente().getDni()!=turnoLogeado.getPaciente().getDni() && turno.getPaciente().getDni()!=0)
			throw new ExcepcionTerapeuta("Este turno no es para el paciente logeado");
		
		if(turnoLogeado.getEstado().toString().equals("Cancelado"))
			throw new ExcepcionTerapeuta("Este turno ya esta cancelado");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   
		
		Instant fechaTurno = formatter.parse(turnoLogeado.getFecha()).toInstant().truncatedTo(ChronoUnit.DAYS);
		Instant fechaHoy =  (new Date()).toInstant().truncatedTo(ChronoUnit.DAYS);  
		
		if(fechaTurno.equals(fechaHoy)) 
			throw new ExcepcionTerapeuta("No se puede cancelar un turno el mismo dia del turno");
		
		
		turnoDAO.bajaTurno(turno);
	}

	@Override
	public List<String> mostrarDisponibilidad(String fecha) throws Exception {		
		List<String> listaTurnosXDia = this.getListaHorarios();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(formatter.parse(fecha));
		String fechaInicio = formatter.format(calendario.getTime()) + " 00:00:00";
		calendario.add(Calendar.DAY_OF_MONTH, 1);
		String fechaFin = formatter.format(calendario.getTime()) + " 00:00:00";
		List<String> listaTurnos = turnoDAO.getHorariosDisponibles(fechaInicio, fechaFin);
		if(listaTurnos!=null) {
			for(String turnoOcupado: listaTurnos) {
				turnoOcupado = extraerFormatoHora(turnoOcupado);
				if(listaTurnosXDia.contains(turnoOcupado) ){
					listaTurnosXDia.remove(turnoOcupado);
				}
				
			}
		}
		if(listaTurnosXDia.size()==0)
			listaTurnosXDia=null;
		
		return listaTurnosXDia;
	}

	@Override
	public Turno consultarTurno(Turno turno) throws Exception {
		Turno turnoLogeado = turnoDAO.consultaTurno(turno);
		if(turnoLogeado == null)
			throw new ExcepcionTerapeuta("El turno ingresado no existe");
		
		if(turno.getPaciente().getDni()!=turnoLogeado.getPaciente().getDni() && turno.getPaciente().getDni()!=0)
			throw new ExcepcionTerapeuta("Este turno no es para el paciente logeado");
		
		return turnoLogeado;
	}

	@Override
	public List<Turno> consultarTurnosPaciente(Paciente paciente) throws Exception {
		List<Turno> listaTurnos = turnoDAO.consultaTurnosXPaciente(paciente);
		return listaTurnos;
	}
	
	//====================Metodos Privados===============================
	
	
	
	private boolean checkearTurnoDisponible(Turno turno) throws Exception {
		String fechaTurno = turno.getFecha();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(formatter.parse(turno.getFecha()));
		String fechaInicio = formatter.format(calendario.getTime()) + " 00:00:00";
		calendario.add(Calendar.DAY_OF_MONTH, 1);
		String fechaFin = formatter.format(calendario.getTime()) + " 00:00:00";
		String horaTurno = extraerFormatoHora(fechaTurno);
		List<String> listaTurnos = turnoDAO.getHorariosDisponibles(fechaInicio, fechaFin);
		if(listaTurnos!=null) {
			for(String turnoOcupado: listaTurnos) {
				turnoOcupado = extraerFormatoHora(turnoOcupado);
				if(horaTurno.equals(turnoOcupado) ){
					throw new ExcepcionTerapeuta("El turno no se encuentra disponible");
				}
				
			}
		}
		
		return true;
	}
	
	private boolean checkearTurnosSemana(Turno turno) throws Exception {
		Paciente paciente = turno.getPaciente();
		int contador = 1;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS");   
		
		Date fechaTurno = null;
		Date fechaHoy =  new Date();  
		
		Calendar calendarioTurno = Calendar.getInstance();
		calendarioTurno.setTime(formatter.parse(turno.getFecha()));
		int semanaTurno = calendarioTurno.get(Calendar.WEEK_OF_YEAR);
		
		Calendar calendarioRecordTurno = Calendar.getInstance();
		int semanaRecordTurno;
		
		List<Turno> listaTurnos = turnoDAO.consultaTurnosXPaciente(paciente);
		if(listaTurnos!=null) {
			for(Turno recordTurno: listaTurnos) {
				fechaTurno = formatter.parse(recordTurno.getFecha());
				calendarioRecordTurno.setTime(formatter.parse(recordTurno.getFecha()));
				semanaRecordTurno  = calendarioRecordTurno.get(Calendar.WEEK_OF_YEAR);
				if(fechaTurno.compareTo(fechaHoy) >= 0 && semanaRecordTurno == semanaTurno ) {
					contador++;
					if(contador > 2) {
						throw new ExcepcionTerapeuta("Solo se pueden reservar 2 turnos por semana");
					}
				}
			}
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private boolean checkHorario(String fecha) throws ExcepcionTerapeuta {
		Timestamp fechaTimestamp = Timestamp.valueOf(fecha);
		int horaFecha = fechaTimestamp.getHours();
		int minutosFecha = fechaTimestamp.getMinutes();
		
		if(horaFecha > 20 || horaFecha < 6) {
			throw new ExcepcionTerapeuta("El horario ingresado no esta entre las horas de trabajo del centro");
		}
			
		if(minutosFecha != 30 && minutosFecha != 0) {
			throw new ExcepcionTerapeuta("La hora ingresada solo puede ser a los 0 minutos o a los 30 minutos.");
		}
		
		return true;
	}

	@SuppressWarnings("deprecation")
	private Terapeuta getTerapeuta(String fecha) throws ExcepcionTerapeuta {
		Terapeuta resultado = null;
		Timestamp fechaTimestamp = Timestamp.valueOf(fecha);
		int horaFecha = fechaTimestamp.getHours();
		if(horaFecha>=6 && horaFecha < 12) {
			resultado = terapeutaDAO.getTerapeutaTurno("Manana");
		} else {
			if(horaFecha >= 12 && horaFecha < 17) {
				resultado = terapeutaDAO.getTerapeutaTurno("Tarde");
			}
			else {
				if(horaFecha >=17 && horaFecha <= 20) {
					resultado = terapeutaDAO.getTerapeutaTurno("Noche");
				}
				else {
					throw new ExcepcionTerapeuta("El horario ingresado no esta entre las horas de trabajo");
				}
			}
		}
		
		return resultado;
	}
	
	private List<String> getListaHorarios(){
		List<String> timeList = new ArrayList<>();

        String startTime = "06:00:00";
        String endTime = "20:00:00";

        timeList.add(startTime);

        while (!startTime.equals(endTime)) {
            String[] timeParts = startTime.split("\\:");
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            int seconds = Integer.parseInt(timeParts[2]);

            if (minutes < 30) {
                minutes += 30;
            } else {
                hours++;
                minutes = 0;
            }

            if (hours < 10) {
                timeList.add("0" + hours + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            } else {
                timeList.add(hours + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            startTime = timeList.get(timeList.size() - 1);
        }

        return timeList;
	}
	
	public String extraerFormatoHora(String dateTimeString) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss");
        String timeString = null;
        try {
            Date dateTime = inputFormat.parse(dateTimeString);
            timeString = outputFormat.format(dateTime);
        } catch (Exception e) {
            throw new Exception("Error parsing date: " + e.getMessage());
        }
        return timeString;

	}
	
}
