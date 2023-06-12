package estados;
import exceptions.ExcepcionEstado;
import entidades.Paciente;
import entidades.Terapeuta;
import entidades.Turno;

public class EstadoConfirmado implements State {
	
	
private static EstadoConfirmado instancia;
	
	private EstadoConfirmado() {}
	
	//Singleton
	public static State getInstancia() {
		if(instancia == null)
			instancia = new EstadoConfirmado();
		
		return instancia;
	}

	@Override
	public void editar(Turno turno, Paciente paciente, Terapeuta terapeuta, String fecha) {
		turno.setInformacion(paciente, terapeuta, fecha);
		turno.setEstado(EstadoEditado.getInstancia());
	}

	@Override
	public void confirmar(Turno turno) throws ExcepcionEstado {
		throw new ExcepcionEstado("El turno ya esta confirmado");
	}

	@Override
	public void cancelar(Turno turno) {
		turno.setEstado(EstadoCancelado.getInstancia());
	}
	
	@Override
	public String toString() {
		return "Confirmado";
	}

}
