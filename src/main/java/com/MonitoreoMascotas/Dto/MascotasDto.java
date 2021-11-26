package com.MonitoreoMascotas.Dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

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
	public MascotasDto() {
	}
	public MascotasDto(@NotBlank String nombre, @NotBlank String color, @NotBlank String raza,
			@NotBlank Boolean esterilizacion, @NotBlank Date fechaEsterilizacion, @NotBlank Date fechaNacimiento) {
		this.nombre = nombre;
		this.color = color;
		this.raza = raza;
		this.esterilizacion = esterilizacion;
		this.fechaEsterilizacion = fechaEsterilizacion;
		this.fechaNacimiento = fechaNacimiento;
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
