package com.Pet_Monitoring.Dto;

import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Entities.EstablishmentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDto {

	private String name;

	private String address;
	
	private String email;
	
	private String cell_phone;

	private String phone;

	private EstablishmentType establishmentType;

	private Pet pet;
}
