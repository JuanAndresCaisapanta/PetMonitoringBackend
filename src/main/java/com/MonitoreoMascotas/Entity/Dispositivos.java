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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Dispositivos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String marca;
	private String fabricante;
	private String observacion;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dispositivos")
	@JsonManagedReference(value = "dispositivos-temperaturas")
	private List<Temperaturas> temperaturas;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dispositivos")
	@JsonManagedReference(value = "dispositivos-ubicaciones")
	private List<Ubicaciones> ubicaciones;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mascotas_id")
	@JsonBackReference(value = "mascotas-dispositivos")
	private Mascotas mascotas;

	public Dispositivos() {
	}

	public Dispositivos(String nombre, String marca, String fabricante, String observacion,
			List<Temperaturas> temperaturas, List<Ubicaciones> ubicaciones, Mascotas mascotas) {
		this.nombre = nombre;
		this.marca = marca;
		this.fabricante = fabricante;
		this.observacion = observacion;
		this.temperaturas = temperaturas;
		this.ubicaciones = ubicaciones;
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

	public List<Temperaturas> getTemperaturas() {
		return temperaturas;
	}

	public void setTemperaturas(List<Temperaturas> temperaturas) {
		this.temperaturas = temperaturas;
	}

	public List<Ubicaciones> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicaciones> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public Mascotas getMascotas() {
		return mascotas;
	}

	public void setMascotas(Mascotas mascotas) {
		this.mascotas = mascotas;
	}

	
}
