package modelo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("torneos")
public class Torneo {
	@Id
	protected ObjectId id;
	protected String nombre;
	@Reference(lazy=true)
	protected List<Partido> partidos;
	@Reference(lazy=true)
	protected List<Equipo> equipos;
	protected String sede;
	
	//constructores
	public Torneo() {
		this.partidos = new ArrayList<Partido>();
		this.equipos = new ArrayList<Equipo>();
		this.nombre = "";
		this.sede = "";
	}
	
	public Torneo(String nombre) {
		this.id = new ObjectId();
		this.nombre = nombre;
		this.partidos = new ArrayList<Partido>();
		this.equipos = new ArrayList<Equipo>();
		this.sede = "";
	}
	
	//setters
	public void agregarPartido(Partido p) {
		this.partidos.add(p);
	}
	
	public void agregarEquipo(Equipo e) {
		this.equipos.add(e);
	}
	
	public void setPartidos(List<Partido> p) {
		this.partidos = p;
	}
	
	public void setEquipos(List<Equipo> e) {
		this.equipos = e;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setSede(String s) {
		this.sede = s;
	}
	
	//getters
	public Partido getPartido(int pos) {
		return this.partidos.get(pos);
	}
	
	public Equipo getEquipo(int pos) {
		return this.equipos.get(pos);
	}
	
	public List<Partido> getPartidos() {
		return this.partidos;
	}
	
	public List<Equipo> getEquipos() {
		return this.equipos;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getSede() {
		return this.sede;
	}
	
	public List<Jugador> getJugadoresEquipo(String equipo) {
		for(Equipo e: this.equipos) {
			if(e.getNombre().equals(equipo)) return e.getJugadores();
		}
		return null;
	}
	
	///otros
	public String toString() {
		return this.nombre;
	}
	
	
}
