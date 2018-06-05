package Launchers;

import java.util.List;

import dao.TorneoDaoMorphia;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Torneo;

public class Principal4 {

	public static void main(String[] args) {
		TorneoDaoMorphia daoT = new TorneoDaoMorphia();
		
		List<Torneo> torneos = daoT.getTorneos();
		
		for(Torneo t: torneos) {
			System.out.println("Nombre del Torneo: "+t.getNombre());
			System.out.println("-----------------------------------");
			for(Equipo e : t.getEquipos()) {
				System.out.println("Nombre Equipo: "+e.getNombre());
				for(Jugador j: e.getJugadores()) {
					System.out.print(j.getNombre()+" | ");
				}
				System.out.println("");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		}
	}

}
