package com.MonitoreoMascotas.Dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;


import com.MonitoreoMascotas.Entity.Especies;
import com.MonitoreoMascotas.Security.Entity.Usuarios;
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
	private Boolean esterilizacion;
	@NotBlank
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaEsterilizacion;
	@NotBlank
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;
	private Especies especies;
	private Usuarios usuarios;
	
}
