package com.MonitoreoMascotas.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mascotas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMascota;
	private String nombre;
	private String color;
	private String raza;
	private Boolean esterilizacion;
	private Date fechaEsterilizacion;
	private Date fechaNacimiento;
	
	public Mascotas() {
	}

	public Mascotas(String nombre, String color, String raza, Boolean esterilizacion,
			Date fechaEsterilizacion, Date fechaNacimiento) {
		this.nombre = nombre;
		this.color = color;
		this.raza = raza;
		this.esterilizacion = esterilizacion;
		this.fechaEsterilizacion = fechaEsterilizacion;
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Boolean getEsterilizacion() {
		return esterilizacion;
	}

	public void setEsterilizacion(Boolean esterilizacion) {
		this.esterilizacion = esterilizacion;
	}

	public Date getFechaEsterilizacion() {
		return fechaEsterilizacion;
	}

	public void setFechaEsterilizacion(Date fechaEsterilizacion) {
		this.fechaEsterilizacion = fechaEsterilizacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
}

	