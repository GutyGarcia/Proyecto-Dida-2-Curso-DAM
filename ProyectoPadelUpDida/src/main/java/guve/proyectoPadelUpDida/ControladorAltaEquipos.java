package guve.proyectoPadelUpDida;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAltaEquipos {
	
	@FXML
	TextField textFieldNombreEquipo;
	@FXML 
	Button botonCancelar, botonAceptar;
	
	EquiposDao equipoDao = new EquiposDao();
	ControladorEquipos controlEquipos;
	
	@FXML
	public void botonAceptar(ActionEvent event) {
		Equipo equipo = new Equipo();
		equipo.setNombre(textFieldNombreEquipo.getText());
		equipoDao.altaEquipo(equipo);
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
}




