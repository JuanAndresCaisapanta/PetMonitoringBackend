package com.Pet_Monitoring.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetEstablishmentDto {
	
	private Long pet_id;
	private String pet_name;
	private String breed_name;
	private String species_name;
	private String pet_sex;

}
