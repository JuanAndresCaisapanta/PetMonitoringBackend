package com.MonitoreoMascotas.Dto;



import javax.validation.constraints.NotBlank;

import com.MonitoreoMascotas.Entity.Especialidades;
import com.MonitoreoMascotas.Entity.Mascotas;

public class ProfesionalesDto {
	
	
	@NotBlank
	private String nombre;
	@NotBlank
	private String apellido;
	@NotBlank
	private String direccion;
	@NotBlank
	private String telefono;
	
	private Mascotas mascotas;
	
	private Especialidades especialidades;
	
	public ProfesionalesDto() {
	}

	public ProfesionalesDto(@NotBlank String nombre, @NotBlank String apellido, @NotBlank String direccion,
			@NotBlank String telefono,  Mascotas mascotas,  Especialidades especialidades) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mascotas = mascotas;
		this.especialidades = especialidades;
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
