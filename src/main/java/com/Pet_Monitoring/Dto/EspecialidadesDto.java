package com.Pet_Monitoring.Dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.Pet_Monitoring.Entity.Establishment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecialidadesDto {

	@NotBlank
	private String nombre;
	
	private List<Establishment> profesionales;

}
