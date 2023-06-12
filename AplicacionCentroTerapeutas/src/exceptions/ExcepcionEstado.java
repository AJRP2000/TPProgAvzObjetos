package exceptions;

public class ExcepcionEstado extends Exception {

	public ExcepcionEstado(String errorMessage) {
        super(errorMessage);
    }
	
	public ExcepcionEstado() {
		super();
	}
}
