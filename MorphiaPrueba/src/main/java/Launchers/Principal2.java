package Launchers;

import dao.JugadorDaoMorphia;
import modelo.Jugador;

public class Principal2 {
	public static void main(String []args) {
		JugadorDaoMorphia daoJ = new JugadorDaoMorphia();
		for(Jugador j: daoJ.getJugadores()) {
			System.out.println("Nombre: "+j.getNombre());
		}
	}
}
