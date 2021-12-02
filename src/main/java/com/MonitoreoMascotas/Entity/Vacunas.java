package com.MonitoreoMascotas.Entity;




import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Vacunas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String fabricador;
	private String lote;
	private String aplicador;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaFabricacion;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaCaducidad;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaAplicacion;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaRevacuna;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mascotas_id")
	@JsonBackReference(value = "mascotas-vacunas")
	private Mascotas mascotas;

	public Vacunas() {
	}

	public Vacunas(String fabricador, String lote, String aplicador, Date fechaFabricacion, Date fechaCaducidad,
			Date fechaAplicacion, Date fechaRevacuna, Mascotas mascotas) {
		this.fabricador = fabricador;
		this.lote = lote;
		this.aplicador = aplicador;
		this.fechaFabricacion = fechaFabricacion;
		this.fechaCaducidad = fechaCaducidad;
		this.fechaAplicacion = fechaAplicacion;
		this.fechaRevacuna = fechaRevacuna;
		this.mascotas = mascotas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFabricador() {
		return fabricador;
	}

	public void setFabricador(String fabricador) {
		this.fabricador = fabricador;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getAplicador() {
		return aplicador;
	}

	public void setAplicador(String aplicador) {
		this.aplicador = aplicador;
	}

	public Date getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Date getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Date getFechaRevacuna() {
		return fechaRevacuna;
	}

	public void setFechaRevacuna(Date fechaRevacuna) {
		this.fechaRevacuna = fechaRevacuna;
	}

	public Mascotas getMascotas() {
		return mascotas;
	}

	public void setMascotas(Mascotas mascotas) {
		this.mascotas = mascotas;
	}

	
	
}
