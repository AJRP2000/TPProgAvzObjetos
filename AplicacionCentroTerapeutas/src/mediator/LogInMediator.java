package mediator;

import entidades.Usuario;

public interface LogInMediator {

	public void logInPaciente(Usuario usuario);
	
	public void logInAdmin(Usuario usuario);
}
