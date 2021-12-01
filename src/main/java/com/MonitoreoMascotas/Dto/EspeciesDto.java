package com.MonitoreoMascotas.Dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.MonitoreoMascotas.Entity.Mascotas;

public class EspeciesDto {
	
	@NotBlank
	private String nombre;
	private List<Mascotas> mascotas;
	
	public EspeciesDto() {
	}
	
	public EspeciesDto(@NotBlank String nombre, List<Mascotas> mascotas) {
		this.nombre = nombre;
		this.mascotas = mascotas;
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
