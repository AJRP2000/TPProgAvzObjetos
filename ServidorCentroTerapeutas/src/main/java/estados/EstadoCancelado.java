package estados;
import entidades.Paciente;
import entidades.Terapeuta;
import entidades.Turno;
import exceptions.ExcepcionEstado;

public class EstadoCancelado implements State {
	
	
private static EstadoCancelado instancia;
	
	private EstadoCancelado() {}
	
	//Singleton
	public static State getInstancia() {
		if(instancia == null)
			instancia = new EstadoCancelado();
		
		return instancia;
	}

	@Override
	public void editar(Turno turno, Paciente paciente, Terapeuta terapeuta, String fecha) throws ExcepcionEstado {
		throw new ExcepcionEstado("No se puede editar un turno cancelado");
	}

	@Override
	public void confirmar(Turno turno) throws ExcepcionEstado {
		throw new ExcepcionEstado("No se puede confirmar un turno cancelado");
	}

	@Override
	public void cancelar(Turno turno) throws ExcepcionEstado {
		throw new ExcepcionEstado("El turno ya esta cancelado");
	}

	@Override
	public String toString() {
		return "Cancelado";
	}
	
}
