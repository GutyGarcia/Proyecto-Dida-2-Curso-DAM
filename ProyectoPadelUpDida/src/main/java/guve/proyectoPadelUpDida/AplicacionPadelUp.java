package guve.proyectoPadelUpDida;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AplicacionPadelUp extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Parent contenedor = new FXMLLoader().load(getClass().getResource("pantallaPrincipalEquipos.fxml"));
		primaryStage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
		primaryStage.setTitle("PadelUp");
		
		Scene escena = new Scene(contenedor, 698, 630);
		escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
		primaryStage.setResizable(false);
		primaryStage.setScene(escena);
		primaryStage.show();
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
