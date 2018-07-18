package dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;


import dbconnection.MorphiaSingleton;
import modelo.Jugador;

public class JugadorDaoMorphia implements JugadorDao{
	protected Datastore db;
	
	public JugadorDaoMorphia() {
		this.db = MorphiaSingleton.getInstance().getDatstore();
	}

	public List<Jugador> getJugadores() {
		final Query<Jugador> query = db.createQuery(Jugador.class);
		return query.asList();
	}

	public Jugador obtenerJugador(ObjectId id) {
		return null;
	} 

	public void actualizarJugador(Jugador j) {
	
	}

	public void agregarJugador(Jugador j) {
		db.save(j);
	}

	public void eliminarJugador(Jugador j) {
		db.delete(j);
	}
}
