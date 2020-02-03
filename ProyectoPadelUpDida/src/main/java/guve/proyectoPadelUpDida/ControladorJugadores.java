package guve.proyectoPadelUpDida;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControladorJugadores {

	@FXML
	AnchorPane altaJugador;
	@FXML
	private TextField textFieldNombre;
	@FXML
	private TextField textFieldApellido1;
	@FXML
	private TextField textFieldApellido2;
	@FXML
	private TextField textFieldApodo;
	@FXML
	private TextField textFieldTelefono;
	@FXML
	private TextField textFieldPuntos;	
	@FXML
	ComboBox<String> comboBoxPosicion;
	@FXML 
	private Button botonCancelar, botonAceptar;
	
	private int idEquipo;
	
	JugadoresDao jugadoresDao = new JugadoresDao();
	ControladorEquipos controlEquipos;
	
	@FXML
	public void botonAceptar(ActionEvent event){
	
		Jugador jugador = new Jugador();
		Equipo equipo = new Equipo();
		equipo.setIdEquipo(idEquipo);;
		jugador.setNombre(textFieldNombre.getText());
		jugador.setPrimerApellido(textFieldApellido1.getText());
		jugador.setSegundoApellido(textFieldApellido2.getText());
		jugador.setApodo(textFieldApodo.getText());
		jugador.setTelefono(textFieldTelefono.getText());
		jugador.setPosicion(comboBoxPosicion.getValue());
		jugador.setPuntos(Integer.parseInt(textFieldPuntos.getText()));		
		jugador.setEquipo(equipo);
		jugadoresDao.altaJugador(jugador);
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}	
	@FXML
	public void botonCancelar(ActionEvent event){
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}

	public ComboBox<String> getComboBoxPosicion() {
		return comboBoxPosicion;
	}
	public void setComboBoxPosicion(ComboBox<String> comboBoxPosicion) {
		this.comboBoxPosicion = comboBoxPosicion;
	}
	public TextField getTextFieldNombre() {
		return textFieldNombre;
	}
	public void setTextFieldNombre(TextField textFieldNombre) {
		this.textFieldNombre = textFieldNombre;
	}
	public TextField getTextFieldApellido1() {
		return textFieldApellido1;
	}
	public void setTextFieldApellido1(TextField textFieldApellido1) {
		this.textFieldApellido1 = textFieldApellido1;
	}
	public TextField getTextFieldPuntos() {
		return textFieldPuntos;
	}
	public void setTextFieldPuntos(TextField textFieldPuntos) {
		this.textFieldPuntos = textFieldPuntos;
	}
	public Button getBotonAceptar() {
		return botonAceptar;
	}
	public void setBotonAceptar(Button botonAceptar) {
		this.botonAceptar = botonAceptar;
	}
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
}
