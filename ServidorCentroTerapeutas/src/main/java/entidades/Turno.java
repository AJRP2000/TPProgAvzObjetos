package entidades;

import exceptions.ExcepcionEstado;

import estados.*;

public class Turno {
	
	int idTurno;
	Paciente paciente;
	Terapeuta terapeuta;	
	String fecha;	
	State estado;

	
	public Turno() {}
	
	public void crearTurno() {
		this.estado = EstadoCreado.getInstancia();
	}
	
	public void editarTurno(Paciente paciente, Terapeuta terapeuta, String fecha) throws ExcepcionEstado {
		estado.editar(this, paciente, terapeuta, fecha);
	}
	
	public void cancelarTurno() throws ExcepcionEstado {
		estado.cancelar(this);
	}
	
	public void confirmarTurno() throws ExcepcionEstado {
		estado.confirmar(this);
	}
	
	public void setInformacion(Paciente paciente, Terapeuta terapeuta, String fecha) {
		this.paciente=paciente;
		this.terapeuta=terapeuta;
		this.fecha=fecha;
	}

	/*getters and setters */
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Terapeuta getTerapeuta() {
		return terapeuta;
	}

	public void setTerapeuta(Terapeuta terapeuta) {
		this.terapeuta = terapeuta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public State getEstado() {
		return estado;
	}

	public void setEstado(State estado) {
		this.estado = estado;
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
}
