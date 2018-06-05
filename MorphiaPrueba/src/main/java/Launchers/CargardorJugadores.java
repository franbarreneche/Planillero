package Launchers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import dao.EquipoDao;
import dao.EquipoDaoMorphia;
import dao.JugadorDao;
import dao.JugadorDaoMorphia;
import dao.TorneoDao;
import dao.TorneoDaoMorphia;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Torneo;

public class CargardorJugadores {

	public static void main(String[] args) {
		List<Torneo> torneos = CargardorJugadores.cargarListaTorneos();
		for(Torneo t:torneos) {
			System.out.println("TORNEO: "+t.getNombre());
			for(Equipo e: t.getEquipos()) {
				System.out.println("Equipo: "+e.getNombre());
				for(Jugador j: e.getJugadores()) {
					System.out.print(j.getNombre()+ " | ");
				}
				System.out.println("");
				System.out.println("-----------------------");
			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
	}
	
	
	public static List<Torneo> cargarListaTorneos() {
		List<Torneo> resultado = new ArrayList<Torneo>();
		
		File[] files = new File("src/main/resources/torneos/").listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isDirectory()) {
		    	Torneo t = CargardorJugadores.cargarTorneo(file.getName());
		    	resultado.add(t);
		    	TorneoDao dao = new TorneoDaoMorphia();
		    	dao.agregarTorneo(t);
		    }
		}
		
		return resultado; 
	}
	
	
	public static Torneo cargarTorneo(String nombre) {
		Torneo torneo = new Torneo();
		torneo.setNombre(nombre);
		
		File[] files = new File("src/main/resources/torneos/"+nombre).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile()) {
		    	Equipo e = new Equipo(file.getName().replaceFirst("[.][^.]+$", ""));
		        torneo.agregarEquipo(e);
		        cargarJugadores(e,file);
		        EquipoDao dao = new EquipoDaoMorphia();
		        dao.agregarEquipo(e);
		    }
		}
		
		return torneo; 
	}
	
	
	
	
	public static void cargarJugadores(Equipo e, File f) {
		Reader in;
		try {
			in = new FileReader(f);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {
			    String nombre = record.get(0);
			    Jugador j = new Jugador(nombre);
			    JugadorDao dao = new JugadorDaoMorphia();
			    dao.agregarJugador(j);
			    e.agregarJugador(j);
			}
		} catch (FileNotFoundException e1) {e1.printStackTrace();} 
		catch (IOException e1) {e1.printStackTrace();}
		
	}

}
