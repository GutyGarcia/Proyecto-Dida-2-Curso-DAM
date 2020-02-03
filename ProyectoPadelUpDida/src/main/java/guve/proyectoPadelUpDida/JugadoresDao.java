package guve.proyectoPadelUpDida;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JugadoresDao {
	
	public static final String URL_CONEXION = "jdbc:mysql://localhost/padelUp_Dida?serverTimezone=Europe/Madrid";
	public static final String USUARIO_BDD = "root";
	public static final String PASSWORD_BDD = "root";
	Equipo equipo;
	
	public JugadoresDao() {
		crearTablaSiNoExiste();
	}
	
	private void crearTablaSiNoExiste() {
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			Statement statement = conexionBD.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Jugadores" +
					"(idJugador INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
					"nombre VARCHAR(20) NOT NULL, " +
					"primerApellido VARCHAR(20) NOT NULL, " +
					"segundoApellido VARCHAR(20) NULL, " +
					"apodo VARCHAR(10) NULL," +
					"telefono VARCHAR(12) NULL, " +
					"posicion ENUM('Derecha','Revés','Ambas') NOT NULL, " +
					"puntos INT NOT NULL, " +
					"idEquipo INT NOT NULL, " +
					"FOREIGN KEY (idEquipo) REFERENCES PadelUp_Dida.Equipos (idEquipo))";
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	public void bajaJugador(Jugador jugador) {
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			Statement statement = conexionBD.createStatement();
			String sql = "DELETE FROM jugadores WHERE IdJugador=" + jugador.getIdJugador();
			statement.executeUpdate(sql);
		}catch(Exception e) {
			throw new RuntimeException("Ocurrio un error al guardar la informacion: " + e.getMessage());
		}
	}
	public void altaJugador(Jugador jugador) {
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			PreparedStatement statement = conexionBD.prepareStatement("INSERT INTO jugadores (Nombre, PrimerApellido, SegundoApellido, "
					+ "Apodo, Telefono, Posicion, Puntos, IdEquipo)" +
					"VALUES (?,?,?,?,?,?,?,?)");
			statement.setString(1, jugador.getNombre());
			statement.setString(2, jugador.getPrimerApellido());
			statement.setString(3, jugador.getSegundoApellido());			
			statement.setString(4, jugador.getApodo());
			statement.setString(5, jugador.getTelefono());
			statement.setString(6, jugador.getPosicion());
			statement.setInt(7, jugador.getPuntos());
			statement.setInt(8, jugador.getEquipo().getIdEquipo());	
			statement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void modificarJugador(Jugador jugador) {
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			PreparedStatement statement = conexionBD.prepareStatement("UPDATE jugadores (Nombre, PrimerApellido, SegundoApellido, " 
									+ "Apodo, Telefono, Posicion, Puntos, IdEquipo)" + 
									"VALUES (?,?,?,?,?,?,?,?,?)");
			statement.setString(1, jugador.getNombre());
			statement.setString(2, jugador.getPrimerApellido());
			statement.setString(3, jugador.getSegundoApellido());			
			statement.setString(4, jugador.getApodo());
			statement.setString(5, jugador.getTelefono());
			statement.setString(6, jugador.getPosicion());
			statement.setInt(7, jugador.getPuntos());
			statement.setInt(8, jugador.getEquipo().getIdEquipo());	
			statement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Jugador> consultarTodos(){
		List<Jugador> jugadores = new ArrayList<>();
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			Statement statement = conexionBD.createStatement();
			String sql = "SELECT * FROM jugadores ORDER BY IdEquipo";
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Jugador jugador = new Jugador();
				Equipo equipo = new Equipo();
				jugador.setIdJugador(resultSet.getInt("IdJugador"));
				jugador.setNombre(resultSet.getString("Nombre"));
				jugador.setPrimerApellido(resultSet.getString("PrimerApellido"));
				jugador.setSegundoApellido(resultSet.getString("SegundoApellido"));
				jugador.setApodo(resultSet.getString("Apodo"));
				jugador.setTelefono(resultSet.getString("Telefono"));
				jugador.setPosicion(resultSet.getString("posicion"));
				jugador.setPuntos(resultSet.getInt("puntos"));
				equipo.setIdEquipo(resultSet.getInt("IdEquipo"));
				equipo.setNombre(resultSet.getString("Nombre"));
			}
		}catch(Exception e) {
			throw new RuntimeException("Ocurrio un error al guardar la informacion: " + e.getMessage());
		}
		return jugadores;
	}
	
	public List<Jugador> consultarTodosPorEquipo(int IdEquipo){
		List<Jugador> jugadores = new ArrayList<>();
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			Statement statement = conexionBD.createStatement(); 
			String sql = "SELECT * FROM Jugadores WHERE IdEquipo = " + IdEquipo + " ORDER BY IdJugador";
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Jugador jugador = new Jugador();
				Equipo equipo = new Equipo();
				jugador.setIdJugador(resultSet.getInt("IdJugador"));
				jugador.setNombre(resultSet.getString("Nombre"));
				jugador.setPrimerApellido(resultSet.getString("PrimerApellido"));
				jugador.setSegundoApellido(resultSet.getString("SegundoApellido"));
				jugador.setApodo(resultSet.getString("Apodo"));
				jugador.setTelefono(resultSet.getString("Telefono"));
				jugador.setPosicion(resultSet.getString("Posicion"));
				jugador.setPuntos(resultSet.getInt("Puntos"));
				equipo.setIdEquipo(resultSet.getInt("IdEquipo"));
				jugadores.add(jugador);
			}
		}catch(Exception e) {
			throw new RuntimeException("Ocurrio un error al guardar la informacion: " + e.getMessage());
		}
		return jugadores;
	}
	
	public Jugador consultarJugadorPorId(int IdJugador){
		Jugador jugador = new Jugador();
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			Statement statement = conexionBD.createStatement(); 
			String sql = "SELECT * FROM jugadores INNER JOIN Usuarios USING(IdUsuario) INNER JOIN Equipos USING (IdEquipo) WHERE IdJugador = " + IdJugador;
			ResultSet resultSet = statement.executeQuery(sql);
			jugador.setIdJugador(resultSet.getInt("IdJugador"));
			jugador.setNombre(resultSet.getString("Nombre"));
			jugador.setPrimerApellido(resultSet.getString("PrimerApellido"));
			jugador.setSegundoApellido(resultSet.getString("SegundoApellido"));
			jugador.setApodo(resultSet.getString("Apodo"));
			jugador.setTelefono(resultSet.getString("Telefono"));
			jugador.setPosicion(resultSet.getString("posicion"));
			jugador.setPuntos(resultSet.getInt("puntos"));
			jugador.getEquipo().setIdEquipo(resultSet.getInt("IdEquipo"));	
		}catch(Exception e) {
			throw new RuntimeException("Ocurrio un error al guardar la informacion: " + e.getMessage());
		}
		return jugador;
	}
}
