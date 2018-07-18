package dao;

import java.util.List;

import org.bson.types.ObjectId;

import modelo.Jugador;

public interface JugadorDao {
	public List<Jugador> getJugadores();
	
	public Jugador obtenerJugador(ObjectId id);
	
	public void actualizarJugador(Jugador j);
	
	public void agregarJugador(Jugador j);
	
	public void eliminarJugador(Jugador j);		
} 
