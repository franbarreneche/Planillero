package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AgregarPartidosController implements Initializable{
	@FXML
    private TextField texto_matchday;

    @FXML
    private ComboBox<?> combo_sede;

    @FXML
    private Button boton_cargar;

    @FXML
    private VBox panel_partidos;

    @FXML
    private Button boton_agregar_uno;

    @FXML
    private DatePicker box_dia;

    @FXML
    private Button boton_cancelar;
    
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

    @FXML
    void agregarUnPartido(ActionEvent event) {

    }

    @FXML
    void cargarPartidos(ActionEvent event) {

    }

	
}
