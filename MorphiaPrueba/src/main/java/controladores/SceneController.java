package controladores;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Torneo;

public class SceneController {
	private static SceneController instancia = null; 
    private Stage main;

    private SceneController(Stage main) {
        this.main = main;
    }
    
    public static SceneController getInstance() {
    	if (instancia == null) return null;
    	else return instancia;
    }
    
    public static SceneController getInstance(Stage main) {
    	instancia = new SceneController(main);
    	return instancia;
    }
    
    //activadores
    public void activatePrincipal(){
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Principal.fxml"));
    	Parent root;
		try {
			root = loader.load();
			main.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    public void activateAgregarPartidos(Torneo t){
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/AgregarPartidos.fxml"));
    	Parent root;
    	
		try {
			root = loader.load();
			AgregarPartidosController con = (AgregarPartidosController)loader.getController();
			con.setTorneo(t);
			main.setScene( new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void activateGenerarPlanillasPartidos() {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/GenerarPlanillasPartidos.fxml"));
    	Parent root;
		try {
			root = loader.load();
			main.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
      
}
