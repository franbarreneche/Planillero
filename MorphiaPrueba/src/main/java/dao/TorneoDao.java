package dao;

import java.util.List;

import org.bson.types.ObjectId;

import modelo.Torneo;

public interface TorneoDao {
	public void agregarTorneo(Torneo t);
	
	public void eliminarTorneo(Torneo t);
	
	public void actualizarTorneo(Torneo t);
	
	public List<Torneo> getTorneos();
	
	public Torneo obtenerTorneo(ObjectId id);
	
}
