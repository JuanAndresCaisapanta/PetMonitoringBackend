package com.MonitoreoMascotas.Dto;



import javax.validation.constraints.NotBlank;

import com.MonitoreoMascotas.Entity.Especialidades;
import com.MonitoreoMascotas.Entity.Mascotas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
}
