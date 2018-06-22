package modelo;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("partidos")
public class Partido implements Comparable<Partido>{
	@Id
	protected ObjectId id;
	@Reference(lazy=true)
	protected Equipo local;
	@Reference(lazy=true)
	protected Equipo visitante;
	protected String hora;
	protected String fecha;
	protected String matchday;
	
	
	//constructores
	public Partido() {
		this.local=null;
		this.visitante=null;
		this.hora="";
		this.fecha="";
		this.matchday="";
		
	}
	
	public Partido(Equipo equipo1,Equipo equipo2,String hora,String fecha, String matchday) {
		this.local = equipo1;
		this.visitante = equipo2;
		this.hora = hora;
		this.fecha = fecha;
		this.matchday = matchday;
	}
	
	//setters
	public void setLocal(Equipo equipo1) {
		this.local = equipo1;
	}
	
	public void setVisitante(Equipo equipo2) {
		this.visitante = equipo2;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public void setMatchday(String matchday) {
		this.matchday = matchday;
	}

	
	//getters
	public Equipo getLocal() {
		return this.local;
	}
	
	public Equipo getVisitante() {
		return this.visitante;
	}
	
	public String getHora() {
		return this.hora;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	
	public String getMatchday() {
		return this.matchday;
	}
		
	//otros
	public String toString() {
		if(this.visitante!=null) 
		return this.getLocal() + "  vs  " + 
				visitante + "  ||  Hora: " + 
				this.getHora() + "hs.  ||  Dia: " +
				this.getFecha() + "  ||  Fecha: " +
				this.getMatchday();
		else return "Libre: "+this.getLocal().getNombre()+ " || Dia: "+this.getFecha()+" || Fecha: "+this.getMatchday();
	}
	
	@Override
	public boolean equals(Object p) {
		if(p!=null) {
		Partido aux = (Partido)p;
		return this.id.compareTo(aux.id) == 0;
		} else return false;
	}
	
	public int compareTo(Partido p2) {
		
		LocalDateTime hora1 = LocalDateTime.parse(this.fecha+"T"+this.hora);
		LocalDateTime hora2 = LocalDateTime.parse(p2.fecha+"T"+p2.hora);
		return hora1.compareTo(hora2);
	}
	
}

