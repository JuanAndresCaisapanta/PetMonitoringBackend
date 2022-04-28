package com.Pet_Monitoring.Dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.Pet_Monitoring.Entity.Species;
import com.Pet_Monitoring.Security.Entity.Usuarios;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotasDto {
	@NotBlank
	private String nombre;
	@NotBlank
	private String color;
	@NotBlank
	private String raza;
	@NotBlank
	private String genero;
	@NotBlank
	private Boolean esterilizacion;
	@NotBlank
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaEsterilizacion;
	@NotBlank
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	private Species especies;
	private Usuarios usuarios;
	
}
