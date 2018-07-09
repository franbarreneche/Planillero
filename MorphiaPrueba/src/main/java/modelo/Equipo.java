package modelo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

@Entity("equipos")
@Indexes({
    @Index(fields = @Field("nombre"))
})
public class Equipo {
	@Id
	protected ObjectId id;
	protected String nombre;
	@Reference(lazy=true)
	protected List<Jugador> jugadores;
	
	//constructores
	public Equipo () {
		nombre = "";
		jugadores = new ArrayList<Jugador>();
	}
	
	public Equipo(String nombre) {
		this.id = new ObjectId();
		this.nombre = nombre;
		this.jugadores = new ArrayList<Jugador>();
	}
	
	//setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public void agregarJugador(Jugador j) {
		this.jugadores.add(j);
	}
	
	
	//getters
	public String getNombre() {
		return this.nombre;
	}
	
	public List<Jugador> getJugadores(){
		return  this.jugadores;
	}
		
	//otros
	public String toString() {
		return this.nombre;
	}
	
	@Override
	public boolean equals(Object p) {
		if(p!=null) {
		Equipo aux = (Equipo)p;
		return this.id.compareTo(aux.id) == 0;
		} else return false;
	}
	
	
}
