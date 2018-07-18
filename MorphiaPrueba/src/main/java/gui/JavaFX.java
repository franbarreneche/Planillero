package gui;

import controladores.SceneController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFX extends Application{
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		/*try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("E.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        Stage primaryStage = new Stage();
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
		
		Stage primaryStage = new Stage(); primaryStage.setTitle("Planillero Master");        
        SceneController controladorescenas = SceneController.getInstance(primaryStage);
		primaryStage.show();
		controladorescenas.activatePrincipal();
	}
	
} 
