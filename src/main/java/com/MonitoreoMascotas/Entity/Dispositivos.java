package com.MonitoreoMascotas.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.MonitoreoMascotas.Security.Entity.Usuarios;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Dispositivos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String marca;
	private String fabricante;
	private String observacion;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "dispositivos")
	private List<Recopilaciones> recopilaciones;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="mascotas_id")
	@JsonIgnoreProperties({"dispositivos","profesionales","vacunas"})
	private Mascotas mascotas;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuarios_id")
	@JsonIgnoreProperties("dispositivos")
	private Usuarios usuarios;

	public Dispositivos() {
	}

	public Dispositivos(String nombre, String marca, String fabricante, String observacion,
			List<Recopilaciones> recopilaciones, Mascotas mascotas, Usuarios usuarios) {
		this.nombre = nombre;
		this.marca = marca;
		this.fabricante = fabricante;
		this.observacion = observacion;
		this.recopilaciones = recopilaciones;
		this.mascotas = mascotas;
		this.usuarios= usuarios;
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<Recopilaciones> getRecopilaciones() {
		return recopilaciones;
	}

	public void setRecopilaciones(List<Recopilaciones> recopilaciones) {
		this.recopilaciones = recopilaciones;
	}

	public Mascotas getMascotas() {
		return mascotas;
	}

	public void setMascotas(Mascotas mascotas) {
		this.mascotas = mascotas;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	
}
