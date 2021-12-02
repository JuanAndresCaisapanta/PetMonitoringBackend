package com.MonitoreoMascotas.Dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.MonitoreoMascotas.Entity.Especies;
import com.MonitoreoMascotas.Security.Entity.Usuarios;

public class MascotasDto {
	@NotBlank
	private String nombre;
	@NotBlank
	private String color;
	@NotBlank
	private String raza;
	@NotBlank
	private Boolean esterilizacion;
	@NotBlank
	private Date fechaEsterilizacion;
	@NotBlank
	private Date fechaNacimiento;
	private Especies especies;
	private Usuarios usuarios;
	
	public MascotasDto() {
	}

	public MascotasDto(@NotBlank String nombre, @NotBlank String color, @NotBlank String raza,
			@NotBlank Boolean esterilizacion, @NotBlank Date fechaEsterilizacion, @NotBlank Date fechaNacimiento,
			Especies especies, Usuarios usuarios) {
		this.nombre = nombre;
		this.color = color;
		this.raza = raza;
		this.esterilizacion = esterilizacion;
		this.fechaEsterilizacion = fechaEsterilizacion;
		this.fechaNacimiento = fechaNacimiento;
		this.especies = especies;
		this.usuarios = usuarios;
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

	public Especies getEspecies() {
		return especies;
	}

	public void setEspecies(Especies especies) {
		this.especies = especies;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
