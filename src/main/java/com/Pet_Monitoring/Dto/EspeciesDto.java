package com.Pet_Monitoring.Dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.Pet_Monitoring.Entity.Pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspeciesDto {
	
	@NotBlank
	private String nombre;
	private List<Pet> mascotas;
	
}
