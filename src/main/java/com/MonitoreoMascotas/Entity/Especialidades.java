package com.MonitoreoMascotas.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity

public class Especialidades {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;

	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "especialidades")
	@JsonManagedReference(value = "especialidades-profesionales")
	private List<Profesionales> profesionales;
	
	public Especialidades() {
	}

	

	public Especialidades(String nombre, List<Profesionales> profesionales) {
		super();
		this.nombre = nombre;
		this.profesionales = profesionales;
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

	public List<Profesionales> getProfesionales() {
		return profesionales;
	}

	public void setProfesionales(List<Profesionales> profesionales) {
		this.profesionales = profesionales;
	}

	
}
