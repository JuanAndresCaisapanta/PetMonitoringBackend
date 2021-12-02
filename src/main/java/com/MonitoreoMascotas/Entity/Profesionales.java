package com.MonitoreoMascotas.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class Profesionales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;

	@ManyToOne(optional = false)
	@JoinColumn(name = "mascotas_id")
	@JsonIgnoreProperties("profesionales")
	private Mascotas mascotas;

	@ManyToOne(optional = false)
	@JoinColumn(name = "especialidades_id")
	@JsonIgnoreProperties("profesionales")
	private Especialidades especialidades;

	public Profesionales() {
	}

	public Profesionales(String nombre, String apellido, String direccion, String telefono, Mascotas mascotas,
			Especialidades especialidades) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mascotas = mascotas;
		this.especialidades = especialidades;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Mascotas getMascotas() {
		return mascotas;
	}

	public void setMascotas(Mascotas mascotas) {
		this.mascotas = mascotas;
	}

	public Especialidades getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Especialidades especialidades) {
		this.especialidades = especialidades;
	}

}
