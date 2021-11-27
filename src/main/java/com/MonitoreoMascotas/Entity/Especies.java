package com.MonitoreoMascotas.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Especies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "especies")
	private List<Mascotas> mascotas;
	
	

	public Especies() {
	}



	public Especies(String nombre, List<Mascotas> mascotas) {
		this.nombre = nombre;
		this.mascotas = mascotas;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<Mascotas> getMascotas() {
		return mascotas;
	}



	public void setMascotas(List<Mascotas> mascotas) {
		this.mascotas = mascotas;
	}

	
}
