package mediator;

import javax.swing.JScrollPane;

import clasesVista.JPanelABMCTurno;
import entidades.Paciente;
import entidades.Turno;

public interface TurnoMediator {
	
	void panelAltaTurno();
	
	void panelBajaTurno();
	
	void panelEditarTurno();
	
	void panelConsultarTurno();
	
	void altaTurno(Turno turno) throws Exception;
	
	void bajaTurno(Turno turno) throws Exception;
	
	void editarTurno(Turno turno) throws Exception;
	
	void consultarTurno(Turno turno) throws Exception;
	
	void consultarTurnosPaciente(Paciente paciente) throws Exception;
	
	void mostrarDisponibilidad(String diaSeleccionado);
	
	void popUpFailure(String texto);
	
	void borrarPantalla();
	
	void mensajeError(Exception error);
	
	void descargarTabla(JScrollPane tabla);

}
