package entidades;

public class Paciente extends Usuario {

	private String patologia;
	
	public Paciente() {}
	
	public Paciente(int dni, String nombre, String contrasena, String patologia) {
		this.setDni(dni);
		this.setNombre(nombre);
		this.setContrasena(contrasena);
		this.setPatologia(patologia);
	}
	
	@Override
	public boolean isAdmin() {
		return false;
	}

	
	/* getters and setters */
	public String getPatologia() {
		return patologia;
	}

	public void setPatologia(String patologia) {
		this.patologia = patologia;
	}

}
