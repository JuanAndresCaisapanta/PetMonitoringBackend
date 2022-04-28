package com.Pet_Monitoring.Dto;



import javax.validation.constraints.NotBlank;

import com.Pet_Monitoring.Entity.Pet;
import com.Pet_Monitoring.Entity.Professional;

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
	
	private Pet mascotas;
	
	private Professional especialidades;
	
}
