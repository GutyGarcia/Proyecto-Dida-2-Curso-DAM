package guve.proyectoPadelUpDida;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ControladorEquipos implements Initializable{	
	@FXML
	TableView<Jugador> tablaJugadores;	
	@FXML
	ComboBox<Equipo> comboEquipos; 
	@FXML
	Button altaJugador,bajaJugador,bajaEquipo,consultarJugadores ;
	
	EquiposDao equiposDao;
	JugadoresDao jugadoresDao;
	Jugador jugador;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		equiposDao = new EquiposDao();
		jugadoresDao = new JugadoresDao();		
		altaJugador.disableProperty().bind(comboEquipos.valueProperty().isNull());
		bajaEquipo.disableProperty().bind(comboEquipos.valueProperty().isNull());
		consultarJugadores.disableProperty().bind(comboEquipos.valueProperty().isNull());
		bajaJugador.disableProperty().bind(Bindings.isEmpty(tablaJugadores.getSelectionModel().getSelectedItems()));
		cargarEquipos();
		configurarTamanioColumnas();
		
	}	
	private void configurarTamanioColumnas() {		
		tablaJugadores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ObservableList<TableColumn<Jugador, ?>> columnas = tablaJugadores.getColumns();
		columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 10);
		columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 30);
		columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 30);	
		columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 30);
	}
	@FXML
	private void pantallaAltaJugador (ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("altaJugador.fxml"));
		Parent contenedor = fxmlLoader.load();
		Stage stage = new Stage();
		int idEquipo = comboEquipos.getValue().getIdEquipo();
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
		stage.setTitle("PadelUp");
		ControladorJugadores controladorJugadores = fxmlLoader.<ControladorJugadores>getController();
		
		controladorJugadores.getTextFieldNombre().textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\sa-zA-Z*")) {
	        	controladorJugadores.getTextFieldNombre().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
	        }
	    });
		
		controladorJugadores.getTextFieldApellido1().textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\sa-zA-Z*")) {
	        	controladorJugadores.getTextFieldApellido1().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
	        }
	    });
		
		controladorJugadores.getTextFieldPuntos().textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	controladorJugadores.getTextFieldPuntos().setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		controladorJugadores.setIdEquipo(idEquipo);
		Scene escena = new Scene(contenedor, 392, 409);
		escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
		stage.setResizable(false);
		stage.setScene(escena);
		stage.initModality(Modality.APPLICATION_MODAL);
		BooleanBinding isTextFieldEmpty = Bindings.isEmpty(controladorJugadores.getTextFieldNombre().textProperty())
        		.or(Bindings.isEmpty(controladorJugadores.getTextFieldApellido1().textProperty()))
        		.or(Bindings.isEmpty(controladorJugadores.getTextFieldPuntos().textProperty()))
        		.or(controladorJugadores.getComboBoxPosicion().valueProperty().isNull());        
        controladorJugadores.getBotonAceptar().disableProperty().bind(isTextFieldEmpty);
        stage.showAndWait();
      
        
        consultarJugadores();
	}
	@FXML
	private void  pantallaAltaEquipo (ActionEvent event) throws IOException {
		Parent contenedor = new FXMLLoader().load(getClass().getResource("altaEquipo.fxml"));
		Stage stage = new Stage();
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
		stage.setTitle("PadelUp");
		Scene escena = new Scene(contenedor, 452, 151);
		escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
		stage.setResizable(false);
		stage.setScene(escena);
		stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        cargarEquipos();        
	}
	
	 protected void cargarEquipos() {
		ObservableList<Equipo> equipos = FXCollections.observableArrayList();
		List<Equipo> listaEquipos = equiposDao.consultarTodos();
		equipos.addAll(listaEquipos);
		comboEquipos.setItems(equipos);
		comboEquipos.setConverter(new StringConverter<Equipo>() {
			
			@Override
			public String toString(Equipo object) {
				return object.getNombre();
			}			
			@Override
			public Equipo fromString(String string) {
				return comboEquipos.getItems().stream().filter(ap -> 
	            ap.getNombre().equals(string)).findFirst().orElse(null);
			}
		});
	}
	 @FXML
	 protected void consultarJugadores() {
		ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
		List<Jugador> jugadoresEncontrados = jugadoresDao.consultarTodosPorEquipo(comboEquipos.getSelectionModel().getSelectedItem().getIdEquipo());
		jugadores.addAll(jugadoresEncontrados);
		tablaJugadores.setItems(jugadores);		
	}
	public void bajaEquipo() {
		Equipo equipo = comboEquipos.getSelectionModel().getSelectedItem();		
		Alert avisoBajaEquipo = new Alert(AlertType.CONFIRMATION);		
		avisoBajaEquipo.setTitle("Confirmar la baja");
		avisoBajaEquipo.setHeaderText("Desea dar de baja al equipo ");
		avisoBajaEquipo.setContentText("Va usted a borrar al equipo " + equipo.getNombre() + ". ¿Está usted seguro?");	
		
		DialogPane dialogo1 = avisoBajaEquipo.getDialogPane();
		dialogo1.getStylesheets().add( getClass().getResource("/estilos/Estilos.css").toExternalForm());
		dialogo1.getStyleClass().add("myDialog");
		
		Image image = new Image("@../../imagenes/logoAzul.png");
		ImageView imageView = new ImageView(image);
		avisoBajaEquipo.setGraphic(imageView);
		Stage stage = new Stage();	
		stage = (Stage) avisoBajaEquipo.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
		
		Optional<ButtonType> resultado = avisoBajaEquipo.showAndWait();
		if(resultado.get() == ButtonType.OK){
			if(jugadoresDao.consultarTodosPorEquipo(equipo.getIdEquipo()).isEmpty()){
				equiposDao.bajaEquipo(equipo);		
				cargarEquipos();
			}
			else {
				Alert errorBajaEquipo = new Alert(AlertType.WARNING);				
				errorBajaEquipo.setContentText("No es posible la baja del equipo mientras tenga jugadores en activo");
				DialogPane dialogo2 = errorBajaEquipo.getDialogPane();
				dialogo2.getStylesheets().add( getClass().getResource("/estilos/Estilos.css").toExternalForm());
				dialogo2.getStyleClass().add("myDialog");
				Image imagenError = new Image("@../../imagenes/logoAzul.png");
				ImageView imageViewError = new ImageView(imagenError);
				errorBajaEquipo.setGraphic(imageViewError);	
				Stage stage2 = new Stage();	
				stage2 = (Stage) errorBajaEquipo.getDialogPane().getScene().getWindow();
				stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
				errorBajaEquipo.showAndWait();
			}
		}else {
			
		}		
	}	
	
	@FXML
	private void bajaJugador(ActionEvent event) {
		Jugador jugador = tablaJugadores.getSelectionModel().getSelectedItem();	
		Alert avisoBajaJugador = new Alert(AlertType.CONFIRMATION);
		
		avisoBajaJugador.setTitle("Confirmar la baja");
		avisoBajaJugador.setHeaderText("Desea dar de baja al jugador ");
		avisoBajaJugador.setContentText("Va usted a borrar al jugador " + jugador.getNombre() + " " + jugador.getPrimerApellido() + ". ¿Esta usted seguro?");
		DialogPane dialogo3 = avisoBajaJugador.getDialogPane();
		dialogo3.getStylesheets().add( getClass().getResource("/estilos/Estilos.css").toExternalForm());
		dialogo3.getStyleClass().add("myDialog");
		Image image = new Image("@../../imagenes/logoAzul.PNG");
		ImageView imageView = new ImageView(image);
		avisoBajaJugador.setGraphic(imageView);
		Stage stage = new Stage();
		stage = (Stage) avisoBajaJugador.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
		
		Optional<ButtonType> resultado = avisoBajaJugador.showAndWait();
		if(resultado.get() == ButtonType.OK){
			jugadoresDao.bajaJugador(jugador);
			consultarJugadores();
		}else {}		
	}
}
