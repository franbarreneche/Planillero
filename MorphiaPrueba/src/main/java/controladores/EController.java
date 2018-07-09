package controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import dao.EquipoDao;
import dao.EquipoDaoMorphia;
import dao.JugadorDao;
import dao.JugadorDaoMorphia;
import dao.PartidoDao;
import dao.PartidoDaoMorphia;
import dao.TorneoDao;
import dao.TorneoDaoMorphia;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;
import modelo.Torneo;
import parsers.ExportadorCSV;
import parsers.ExportadorPDF;

public class EController implements Initializable {
	
	   @FXML
	    private Button boton_agregarFecha;

	    @FXML
	    private Button boton_csv;

	    @FXML
	    private Button boton_eliminar;

	    @FXML
	    private Button boton_pdf;
	    
	    @FXML
	    private Button boton_planillas;

	    @FXML
	    private ListView<Equipo> lista_equipos;

	    @FXML
	    private ComboBox<Torneo> combo;

	    @FXML
	    private ListView<Jugador> lista_jugadores;

	    @FXML
	    private ListView<Partido> lista_partidos;
	    
	    @FXML
	    private Button boton_agregarJugador;
	    
	    @FXML
	    private TextField box_nombreJugador;
	    
	    @FXML
	    private Button boton_eliminarJugador;

	    @FXML
	    private Button boton;
	    
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	TorneoDaoMorphia daoT = new TorneoDaoMorphia();
	    	ObservableList<Torneo> torneos = FXCollections.observableList(daoT.getTorneos());
	    	
	    	combo.setItems(torneos);
	    	
	    	this.lista_partidos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		}
	    
	    
	    @FXML
	    void buttonClicked(ActionEvent event) {

	    }

	    @FXML
	    void mostrarJugadores(MouseEvent event) {
	    	Equipo seleccionado = lista_equipos.getSelectionModel().getSelectedItem();
	    	if (seleccionado != null) {
		    	ObservableList<Jugador> jugadores = FXCollections.observableList(seleccionado.getJugadores());
		    	this.lista_jugadores.setItems(jugadores);
	    	}else System.out.println("No selecionaste ningun equipo");
	    }

	    @FXML
	    void generarCSV(ActionEvent event) {
	    	List<Partido> seleccionados = lista_partidos.getSelectionModel().getSelectedItems();
	    	if(seleccionados!=null && !seleccionados.isEmpty()) {
	    		try {
					ExportadorCSV.exportarPartidosCSV(seleccionados, combo.getValue());
					
					//mensaje de exito
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Carga Exitosa");
					alert.setHeaderText(null);
					alert.setContentText("El/los partidos se han cargado exitosamente");
					alert.showAndWait();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}else {
	    		//mensaje error
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Error");
	    		alert.setHeaderText(null);
	    		alert.setContentText("No se ha seleccionado ningún partido.");
	    		alert.showAndWait();
	    	}
	    	
	    }

	    @FXML
	    void generarPDF(ActionEvent event) {
	    	List<Partido> seleccionados = lista_partidos.getSelectionModel().getSelectedItems();
	    	if(seleccionados!=null && !seleccionados.isEmpty()) {
	    		try {
	    			LocalDateTime nom = LocalDateTime.now(); 
					ExportadorPDF.exportarPlanillasPDF(seleccionados, nom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"))+"hs");
					
					//mensaje de exito
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exito");
					alert.setHeaderText(null);
					alert.setContentText("Las planillas se han generado exixtosamente");
					alert.showAndWait();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}else {
	    		//mensaje error
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Error");
	    		alert.setHeaderText(null);
	    		alert.setContentText("No se ha seleccionado ningún partido.");
	    		alert.showAndWait();
	    	}
	    }

	    @FXML
	    void agregarFecha(ActionEvent event) throws IOException {
	    	//Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    	// OR
	    	//Stage stage = (Stage) boton_agregarFecha.getScene().getWindow();
	    	// these two of them return the same stage
	    	// Swap screen
	    	//FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AgregarPartidos.fxml"));
	    	//FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/AgregarPartidos.fxml"));
	    	
	    	//Parent root = loader.load();
	    	
	    	//forma 1
	    	//stage.setScene(new Scene(root));
	    	//forma 2
	    	//boton_agregarFecha.getScene().setRoot(root);
	    	
	    	
	    	//forma nueva
	    	if(combo.getValue()!=null) {
		    	SceneController aux = SceneController.getInstance();
		    	
		    	aux.activateAgregarPartidos(combo.getValue());
	    	}else {
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Error");
	    		alert.setHeaderText(null);
	    		alert.setContentText("No se ha seleccionado ningún torneo.");
	    		alert.showAndWait();
	    	}
	    }
	    
	    @FXML
	    void abrirGenerarPlanillas(ActionEvent event) {
	    	SceneController aux = SceneController.getInstance();
	    	
	    	aux.activateGenerarPlanillasPartidos();
	    }

	    @FXML
	    void eliminarPartidos(ActionEvent event) {
	    	Partido aEliminar = this.lista_partidos.getSelectionModel().getSelectedItem();
	    	if(aEliminar != null) {
	    		Torneo torneo = combo.getValue();
	    		torneo.getPartidos().remove(aEliminar);
	    		PartidoDao	daoP = new PartidoDaoMorphia();
	    		daoP.eliminarPartido(aEliminar);
	    		TorneoDao daoT = new TorneoDaoMorphia();
	    		daoT.agregarTorneo(torneo);
	    		//por ultimo actualizamos la lista de equipos visiblemente
	    		this.lista_partidos.setItems(FXCollections.observableList(torneo.getPartidos()));
	    	}
	    }

	    @FXML
	    void mostrarEquipos(ActionEvent event) {
	    	ObservableList<Equipo> equipos = FXCollections.observableList(combo.getValue().getEquipos());
	    	lista_equipos.setItems(equipos);
	    	//limpiar lista jugadores
	    	lista_jugadores.getItems().clear();

	    	ObservableList<Partido> partidos = FXCollections.observableList(combo.getValue().getPartidos());
	    	lista_partidos.setItems(partidos);
	    }
	    
	    @FXML
	    void agregarJugador(ActionEvent event) {
	    	String nombre = this.box_nombreJugador.getText();
	    	if(nombre != null && !nombre.equals("")) {
	    		JugadorDao daoJ = new JugadorDaoMorphia();
	    		Jugador j = new Jugador(nombre);
	    		daoJ.agregarJugador(j);
	    		Equipo e = this.lista_equipos.getSelectionModel().getSelectedItem();
	    		if(e!=null) {
	    			EquipoDao daoE = new EquipoDaoMorphia();
	    			e.agregarJugador(j);
	    			//lista_jugadores.getItems().add(j);
	    			daoE.agregarEquipo(e);
	    			lista_jugadores.setItems(FXCollections.observableArrayList(e.getJugadores()));
	    		}
	    	}
	    	
	    }
	    
	    @FXML
	    void eliminarJugador(ActionEvent event) {
	    	Jugador aEliminar = this.lista_jugadores.getSelectionModel().getSelectedItem();
	    	if(aEliminar != null) {
	    		Equipo equipo = this.lista_equipos.getSelectionModel().getSelectedItem();
	    		equipo.getJugadores().remove(aEliminar);
	    		JugadorDao	daoJ = new JugadorDaoMorphia();
	    		daoJ.eliminarJugador(aEliminar);
	    		EquipoDao daoE = new EquipoDaoMorphia();
	    		daoE.agregarEquipo(equipo);
	    		//por ultimo actualizamos la lista de jugadores visiblemente
	    		this.lista_jugadores.setItems(FXCollections.observableList(equipo.getJugadores()));
	    	}
	    }
	    
	    		
}
