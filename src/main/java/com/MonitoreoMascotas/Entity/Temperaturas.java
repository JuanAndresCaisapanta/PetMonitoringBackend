package com.MonitoreoMascotas.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Temperaturas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Float grados;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Dispositivos dispositivos;

	public Temperaturas() {
	}

	public Temperaturas(Float grados, Dispositivos dispositivos) {
		this.grados = grados;
		this.dispositivos = dispositivos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getGrados() {
		return grados;
	}

	public void setGrados(Float grados) {
		this.grados = grados;
	}

	public Dispositivos getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(Dispositivos dispositivos) {
		this.dispositivos = dispositivos;
	}

	
}
