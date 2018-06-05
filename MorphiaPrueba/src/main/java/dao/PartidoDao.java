package dao;

import java.util.List;

import org.bson.types.ObjectId;

import modelo.Partido;

public interface PartidoDao {
	public void agregarPartido(Partido p);
	
	public void eliminarPartido(Partido p);
	
	public void actualizarPartido(Partido p);
	
	public List<Partido> getPartidos();
	
	public Partido obtenerPartido(ObjectId id);
}
