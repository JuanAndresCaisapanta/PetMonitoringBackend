package com.MonitoreoMascotas.Dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.MonitoreoMascotas.Entity.Profesionales;

public class EspecialidadesDto {

	@NotBlank
	private String nombre;
	
	private List<Profesionales> profesionales;

	public EspecialidadesDto() {
	}

	public EspecialidadesDto(@NotBlank String nombre, List<Profesionales> profesionales) {
		this.nombre = nombre;
		this.profesionales = profesionales;
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
