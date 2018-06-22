package controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import dao.PartidoDao;
import dao.PartidoDaoMorphia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import modelo.Partido;
import parsers.ExportadorPDF;

public class GenerarPlanillasController implements Initializable {
	
	@FXML
    private Button boton_generarPlanillaHorarios;

    @FXML
    private ComboBox<?> combo_sede;

    @FXML
    private Button boton_generarPlanillasPartidos;

    @FXML
    private DatePicker box_dia;
    
    @FXML
    private ListView<Partido> lista_partidos;
    
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

    @FXML
    void cargarPartidosDia(ActionEvent event) {
    	LocalDate diaSeleccionado = this.box_dia.getValue();
    	PartidoDao daoP = new PartidoDaoMorphia();
    	List<Partido> partidosTodos = daoP.getPartidos();
    	ObservableList<Partido> partidosDia = FXCollections.observableArrayList();
    	for(Partido p: partidosTodos) {
    		if(diaSeleccionado.isEqual(LocalDate.parse(p.getFecha()))) {    			
    			partidosDia.add(p);
    		}
    				
    	}
    	Collections.sort(partidosDia);
    	this.lista_partidos.setItems(partidosDia);
    }
    
    @FXML
    void generarPlanillasPartidos(ActionEvent event) {
    	try {
			ExportadorPDF.exportarPlanillasPDF(lista_partidos.getItems(),box_dia.getValue().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void generarPlanillaHorarios(ActionEvent event) {
    	try {
			ExportadorPDF.exportarHorariosPDF(lista_partidos.getItems(),box_dia.getValue().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	

}
