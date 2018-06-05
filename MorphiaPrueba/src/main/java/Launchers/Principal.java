package Launchers;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;
import modelo.Torneo;

public class Principal {
	
	public static void main(String args[]) {
		final Morphia morphia = new Morphia();

		// tell Morphia where to find your classes
		// can be called multiple times with different packages or classes
		morphia.mapPackage("modelo");

		// create the Datastore connecting to the default port on the local host
		MongoClientURI uri = new MongoClientURI(
			    "mongodb://franbarreneche:xander@indiana01-shard-00-00-srzvi.mongodb.net:27017,indiana01-shard-00-01-srzvi.mongodb.net:27017,indiana01-shard-00-02-srzvi.mongodb.net:27017/test?ssl=true&replicaSet=Indiana01-shard-0&authSource=admin&retryWrites=true");
		final Datastore datastore = morphia.createDatastore(new MongoClient(uri), "morphia");
		datastore.ensureIndexes();
		
		Jugador j1 = new Jugador("Juan Ayape");datastore.save(j1);
		Jugador j2 = new Jugador("Diego Ainciondo"); datastore.save(j2);
		Jugador j3 = new Jugador("Edudardo Segui"); datastore.save(j3);
		Jugador j4 = new Jugador("Francisco Barreneche"); datastore.save(j4);
		Jugador j5 = new Jugador("Sebastian Frank"); datastore.save(j5);
		Jugador j6 = new Jugador("Abel Menchi"); datastore.save(j6);
		
		Equipo equipo1 = new Equipo("Los Guerreros Magicos");
		equipo1.agregarJugador(j1);
		equipo1.agregarJugador(j2);
		datastore.save(equipo1);
		
		Equipo equipo2 = new Equipo("Las Divinas");
		equipo2.agregarJugador(j3);
		equipo2.agregarJugador(j4);
		datastore.save(equipo2);
		
		Equipo equipo3 = new Equipo("Los Narizones");
		equipo3.agregarJugador(j5);
		equipo3.agregarJugador(j6);
		datastore.save(equipo3);
		
		Partido p1 = new Partido(equipo1,equipo2,"20:40","02/06/2018","6");datastore.save(p1);
		Partido p2 = new Partido(equipo2,equipo3,"22:40","02/06/2018","6");datastore.save(p2);
		Partido p3 = new Partido(equipo3,equipo1,"23:40","02/06/2018","6");datastore.save(p3);

		Torneo t = new Torneo("Futbol 5 Loco");
		t.agregarEquipo(equipo1);
		t.agregarEquipo(equipo2);
		t.agregarEquipo(equipo3);
		t.agregarPartido(p1);
		t.agregarPartido(p2);
		t.agregarPartido(p3);
		datastore.save(t);
		
		
		
		final Query<Torneo> query = datastore.createQuery(Torneo.class).field("nombre").equal("Futbol 5 Loco");
		Torneo t1 = query.get();
		System.out.println("Torneo: "+t1.getNombre());
		
		for(Partido p:t1.getPartidos()) {
			System.out.println("Local: "+p.getLocal().getNombre());
			for(Jugador j: p.getLocal().getJugadores()) {
				System.out.print(j.getNombre()+ " | ");
			}
			System.out.println("Local: "+p.getVisitante().getNombre());
			for(Jugador j: p.getVisitante().getJugadores()) {
				System.out.print(j.getNombre()+ " | ");
			}
			System.out.println("-----------------------------------"	);
		}
		
		
		
	}
	
}
