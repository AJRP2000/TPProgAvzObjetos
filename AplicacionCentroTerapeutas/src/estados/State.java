package estados;

import exceptions.ExcepcionEstado;
import entidades.Paciente;
import entidades.Terapeuta;
import entidades.Turno;

public interface State {
	
	void editar(Turno turno, Paciente paciente, Terapeuta terapeuta, String fecha) throws ExcepcionEstado;
	
	void confirmar(Turno turno) throws ExcepcionEstado;
	
	void cancelar(Turno turno) throws ExcepcionEstado;
	
	String toString();
	
}
