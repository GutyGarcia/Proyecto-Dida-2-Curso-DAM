package guve.proyectoPadelUpDida;

public class Jugador {

	private int idJugador;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String apodo;
	private String telefono;
	private String posicion;
	private int puntos;
	private Equipo equipo;

	public Jugador(int idJugador, String nombre, String primerApellido, String segundoApellido, String apodo,
			String telefono, String posicion, int puntos, Equipo equipo) {
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.apodo = apodo;
		this.telefono = telefono;
		this.posicion = posicion;
		this.puntos = puntos;
		this.equipo = equipo;

	}

	public Jugador(int idJugador, String nombre, String primerApellido, String segundoApellido, String apodo,
			String telefono, String posicion, int puntos) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.apodo = apodo;
		this.telefono = telefono;
		this.posicion = posicion;
		this.puntos = puntos;
	}

	public Jugador() {
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
}
