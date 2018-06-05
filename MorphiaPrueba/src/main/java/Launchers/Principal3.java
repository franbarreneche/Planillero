package Launchers;

import dao.EquipoDaoMorphia;
import dao.JugadorDaoMorphia;
import dao.TorneoDaoMorphia;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Torneo;

public class Principal3 {

	public static void main(String[] args) {
		
		Jugador j1 = new Jugador("Son Goku");
		Jugador j2 = new Jugador("Son Gohan");
		JugadorDaoMorphia daoJ = new JugadorDaoMorphia();
		daoJ.agregarJugador(j1);
		daoJ.agregarJugador(j2);
		
		Equipo e1 = new Equipo("DBZ");
		e1.agregarJugador(j1);
		e1.agregarJugador(j2);
		EquipoDaoMorphia daoE = new EquipoDaoMorphia();
		daoE.agregarEquipo(e1);
		
		Torneo t1 = new Torneo("Anime");
		t1.agregarEquipo(e1);
		TorneoDaoMorphia daoT = new TorneoDaoMorphia();
		daoT.agregarTorneo(t1);
		
	}

}
