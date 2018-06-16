package controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.PartidoDaoMorphia;
import dao.TorneoDaoMorphia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Equipo;
import modelo.Partido;
import modelo.Torneo;

public class AgregarPartidosController implements Initializable{
	@FXML
    private TextField texto_matchday;

    @FXML
    private ComboBox<String> combo_sede;

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
    
    private Torneo torneo;
    
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		
		combo_sede.setItems(FXCollections.observableArrayList("Palermo","KDT"));
		
		
	}

    @FXML
    public void agregarUnPartido(ActionEvent event) {
    	HBox panelPartido = new HBox();panelPartido.setAlignment(Pos.CENTER);panelPartido.setSpacing(10);
    	ComboBox<Equipo> local = new ComboBox<Equipo>();
    	local.setItems(FXCollections.observableList(torneo.getEquipos()));
    	ComboBox<Equipo> visitante = new ComboBox<Equipo>();
    	visitante.setItems(FXCollections.observableList(torneo.getEquipos()));
    	
    	panelPartido.getChildren().add(local);
    	panelPartido.getChildren().add(new Label(" vs "));
    	panelPartido.getChildren().add(visitante);
    	TextField horario = new TextField("20:40"); horario.setPrefWidth(60);
    	panelPartido.getChildren().add(horario);
    	
    	
    	this.panel_partidos.getChildren().add(panelPartido);
    }

    @FXML
    public void cargarPartidos(ActionEvent event) {
    	for(Node n : panel_partidos.getChildren()) {
    		HBox panel = (HBox)n;
    		//agarrar local
    		ComboBox<Equipo> Clocal = (ComboBox)panel.getChildren().get(0);
    		Equipo local = Clocal.getValue();
    		//agarrar visitante
    		ComboBox<Equipo> Cvisitante = (ComboBox)panel.getChildren().get(2);
    		Equipo visitante = Cvisitante.getValue();
    		//agarrar Hora
    		TextField Thora = (TextField)panel.getChildren().get(3);
    		String hora = Thora.getText();
    		
    		
    		Partido p = new Partido(local,visitante,hora,this.box_dia.getValue().toString(),this.texto_matchday.getText());
    		PartidoDaoMorphia daoP = new PartidoDaoMorphia();
    		daoP.agregarPartido(p);
    		this.torneo.agregarPartido(p);
    		TorneoDaoMorphia daoT = new TorneoDaoMorphia();
    		daoT.agregarTorneo(torneo);  		    		
    	}
    	//mensaje de exito
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Carga Exitosa");
		alert.setHeaderText(null);
		alert.setContentText("El/los partidos se han cargado exitosamente");
		alert.showAndWait();
		
		//volver al principio
		SceneController.getInstance().activatePrincipal();
    }
    
    public void setTorneo(Torneo t) {
    	this.torneo = t;
    }

	
}
