package guve.proyectoPadelUpDida;

public class Equipo {
	
	private int idEquipo;
	private String nombre;
	
	public Equipo(int idEquipo, String nombre) {
		this.idEquipo = idEquipo;
		this.nombre = nombre;
	}

	public Equipo(String nombre) {
		this.nombre = nombre;
	}
	
	public Equipo() {
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
