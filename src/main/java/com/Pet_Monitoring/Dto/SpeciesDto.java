package com.Pet_Monitoring.Dto;

import java.util.List;

import com.Pet_Monitoring.Entities.Pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesDto {

	private String nombre;

	private List<Pet> pet;

}
