package controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.EquipoDaoMorphia;
import dao.JugadorDaoMorphia;
import dao.TorneoDaoMorphia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;
import modelo.Torneo;

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
	    private ListView<Equipo> lista_equipos;

	    @FXML
	    private ComboBox<Torneo> combo;

	    @FXML
	    private ListView<Jugador> lista_jugadores;

	    @FXML
	    private ListView<Partido> lista_partidos;

	    @FXML
	    private Button boton;
	    
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	TorneoDaoMorphia daoT = new TorneoDaoMorphia();
	    	ObservableList<Torneo> torneos = FXCollections.observableList(daoT.getTorneos());
	    	
	    	combo.setItems(torneos);
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

	    }

	    @FXML
	    void generarPDF(ActionEvent event) {

	    }

	    @FXML
	    void agregarFecha(ActionEvent event) {

	    }

	    @FXML
	    void eliminarPartidos(ActionEvent event) {

	    }

	    @FXML
	    void mostrarEquipos(ActionEvent event) {
	    	ObservableList<Equipo> equipos = FXCollections.observableList(combo.getValue().getEquipos());
	    	lista_equipos.setItems(equipos);
	    }
	    
	    

		
}
