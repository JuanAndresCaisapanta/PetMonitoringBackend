package com.Pet_Monitoring.Dto;

import java.util.List;

import com.Pet_Monitoring.Entities.Breed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesDto {

	private String name;

	private List<Breed> breed;

}
