package entidades;

public class Admin extends Usuario {

	public Admin() {}
	
	public Admin(int dni, String nombre, String contrasena) {
		this.setDni(dni);
		this.setNombre(nombre);
		this.setContrasena(contrasena);
	}
	
	@Override
	public boolean isAdmin() {
		return true;
	}

}
