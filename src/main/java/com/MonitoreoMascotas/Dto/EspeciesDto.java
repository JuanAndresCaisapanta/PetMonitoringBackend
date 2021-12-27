package com.MonitoreoMascotas.Dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.MonitoreoMascotas.Entity.Mascotas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspeciesDto {
	
	@NotBlank
	private String nombre;
	private List<Mascotas> mascotas;
	
}
