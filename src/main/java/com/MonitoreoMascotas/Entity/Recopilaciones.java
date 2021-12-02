package com.MonitoreoMascotas.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Recopilaciones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Float latitud;
	private Float longitud;
	private Float temperatura;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dispositivos_id")
	@JsonBackReference(value = "dispositivos-recopilaciones")
	private Dispositivos dispositivos;

	public Recopilaciones() {
	}

	public Recopilaciones(Float latitud, Float longitud, Float temperatura, Dispositivos dispositivos) {
		this.latitud = latitud;
		this.longitud = longitud;
		this.temperatura = temperatura;
		this.dispositivos = dispositivos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	public Float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Float temperatura) {
		this.temperatura = temperatura;
	}

	public Dispositivos getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(Dispositivos dispositivos) {
		this.dispositivos = dispositivos;
	}
	
	
	
	
	
}
