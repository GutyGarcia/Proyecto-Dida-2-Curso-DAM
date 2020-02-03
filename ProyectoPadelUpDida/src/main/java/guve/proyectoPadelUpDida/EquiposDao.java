package guve.proyectoPadelUpDida;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EquiposDao {

	
	public static final String URL_CONEXION = "jdbc:mysql://localhost/padelUp_Dida?serverTimezone=Europe/Madrid";
	public static final String USUARIO_BDD = "root";
	public static final String PASSWORD_BDD = "root";
	Jugador jugador;
	
	public EquiposDao() {
		crearTablaSiNoExiste();		
	}
	
	private void crearTablaSiNoExiste() {
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			Statement statement = conexionBD.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Equipos" + 
					"(IdEquipo INTEGER NOT NULL auto_increment PRIMARY KEY, " +  
					"Nombre VARCHAR(45) NOT NULL)";
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	public void bajaEquipo(Equipo equipo) {
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			Statement statement = conexionBD.createStatement();
			String sql = "DELETE FROM equipos WHERE IdEquipo=" + equipo.getIdEquipo();
			statement.executeUpdate(sql);
		}catch(Exception e) {
			throw new RuntimeException("Ocurrio un error al guardar la informacion: " + e.getMessage());
		}
	}
	
	public List<Equipo> consultarTodos(){
		List<Equipo> equipos = new ArrayList<>();
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			Statement statement = conexionBD.createStatement();
			String sql = "SELECT * FROM equipos ORDER BY IdEquipo";
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Equipo equipo = new Equipo();
				equipo.setNombre(resultSet.getString("Nombre"));
				equipo.setIdEquipo(resultSet.getInt("IdEquipo"));
				equipos.add(equipo);
			}
		}catch(Exception e) {
			throw new RuntimeException("Ocurrio un error al guardar la informacion: " + e.getMessage());
		}
		return equipos;
	}
	public void altaEquipo(Equipo equipo) {
		try(Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
			Statement statement = conexionBD.createStatement();
			String sql = "INSERT INTO Equipos (Nombre) VALUES ('" + equipo.getNombre() + "')";
			statement.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
