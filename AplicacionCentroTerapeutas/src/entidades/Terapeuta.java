package entidades;

public class Terapeuta {
	
	int idTerapeuta;
	String nombre;
	String horario;

	public Terapeuta(int idTerapeuta, String nombre, String horario) {
		this.idTerapeuta=idTerapeuta;
		this.nombre=nombre;
		this.horario=horario;
	}
	
	public Terapeuta() {}

	public int getIdTerapeuta() {
		return idTerapeuta;
	}
	public void setIdTerapeuta(int idTerapeuta) {
		this.idTerapeuta = idTerapeuta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
}
