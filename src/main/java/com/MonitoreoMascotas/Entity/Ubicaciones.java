package com.MonitoreoMascotas.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ubicaciones {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Float Latitud;
	private Float Longitud;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dispositivos_id")
	private Dispositivos dispositivos;

	public Ubicaciones() {
	}

	public Ubicaciones(Float latitud, Float longitud, Dispositivos dispositivos) {
		Latitud = latitud;
		Longitud = longitud;
		this.dispositivos = dispositivos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getLatitud() {
		return Latitud;
	}

	public void setLatitud(Float latitud) {
		Latitud = latitud;
	}

	public Float getLongitud() {
		return Longitud;
	}

	public void setLongitud(Float longitud) {
		Longitud = longitud;
	}

	public Dispositivos getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(Dispositivos dispositivos) {
		this.dispositivos = dispositivos;
	}

	
}
