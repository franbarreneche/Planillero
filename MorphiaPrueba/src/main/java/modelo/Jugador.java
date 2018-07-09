package modelo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity("jugadores")
@Indexes({
    @Index(fields = @Field("nombre"))
})
public class Jugador {
	@Id
	protected ObjectId id;
	protected String nombre;
	
	//constructores
	public Jugador() {
		
	}
	
	public Jugador(String nombre) {
		this.nombre = nombre;
	}
	
	public Jugador(ObjectId id, String nombre) {
		this.id =id;
		this.nombre = nombre;
	}
	
		
	//setters
	public void setNombre(String n) {
		this.nombre = n;
	}
	
	//getters
	public String getNombre() {
		return this.nombre;
	}
	
	//otros
	public String toString() {
		return this.getNombre();
	}
	
	@Override
	public boolean equals(Object p) {
		if(p!=null) {
		Jugador aux = (Jugador)p;
		return this.id.compareTo(aux.id) == 0;
		} else return false;
	}
	
	
}
