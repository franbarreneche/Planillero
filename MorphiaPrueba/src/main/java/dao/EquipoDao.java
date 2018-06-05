package dao;

import java.util.List;

import org.bson.types.ObjectId;

import modelo.Equipo;

public interface EquipoDao {
	public void agregarEquipo(Equipo e);
	
	public void eliminarEuqipo(Equipo e);
	
	public void actualizarEquipo(Equipo e);
	
	public List<Equipo> getEquipos();
	
	public Equipo obtenerEquipo(ObjectId id);
}
