package dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import dbconnection.MorphiaSingleton;
import modelo.Jugador;
import modelo.Partido;

public class PartidoDaoMorphia implements PartidoDao {
	protected Datastore db;
	
	public PartidoDaoMorphia() {
		this.db = MorphiaSingleton.getInstance().getDatstore();
	}

	public void agregarPartido(Partido p) {
		db.save(p);
	}

	public void eliminarPartido(Partido p) {
		db.delete(p);
	}

	public void actualizarPartido(Partido p) {
		// TODO Auto-generated method stub
		
	} 

	public List<Partido> getPartidos() {
		final Query<Partido> query = db.createQuery(Partido.class);
		return query.asList();
	}

	public Partido obtenerPartido(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
