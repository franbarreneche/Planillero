package modelo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("partidos")
public class Partido {
	@Id
	protected ObjectId id;
	@Reference
	protected Equipo local;
	@Reference
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
		return this.getLocal() + "  vs  " + 
				this.getVisitante() + "  ||  Hora: " + 
				this.getHora() + "hs.  ||  Dia: " +
				this.getFecha() + "  ||  Fecha: " +
				this.getMatchday();		
	}
	
}

