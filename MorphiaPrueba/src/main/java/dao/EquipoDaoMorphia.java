package dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import dbconnection.MorphiaSingleton;
import modelo.Equipo;

public class EquipoDaoMorphia implements EquipoDao{
	protected Datastore db;
	
	public EquipoDaoMorphia() {
		this.db = MorphiaSingleton.getInstance().getDatstore();
	}

	public void agregarEquipo(Equipo e) {
		db.save(e);
	}

	public void eliminarEuqipo(Equipo e) {
		db.delete(e);
	}

	public void actualizarEquipo(Equipo e) {
		// TODO Auto-generated method stub
		
	}

	public List<Equipo> getEquipos() {
		final Query<Equipo> query = db.createQuery(Equipo.class);
		return query.asList();
	}

	public Equipo obtenerEquipo(ObjectId id) {
		return null;		
	}

}
