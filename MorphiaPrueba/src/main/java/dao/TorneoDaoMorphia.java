package dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import dbconnection.MorphiaSingleton;
import modelo.Jugador;
import modelo.Torneo;

public class TorneoDaoMorphia implements TorneoDao {
	protected Datastore db;
	
	public TorneoDaoMorphia() {
		this.db = MorphiaSingleton.getInstance().getDatstore();
	}
	
	public void agregarTorneo(Torneo t) {
		db.save(t);
	}

	public void eliminarTorneo(Torneo t) {
		db.delete(t);
	}

	public void actualizarTorneo(Torneo t) {
		// TODO Auto-generated method stub

	}

	public List<Torneo> getTorneos() {
		final Query<Torneo> query = db.createQuery(Torneo.class);
		return query.asList();
	}

	public Torneo obtenerTorneo(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
