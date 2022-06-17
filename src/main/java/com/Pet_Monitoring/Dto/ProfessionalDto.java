package com.Pet_Monitoring.Dto;

import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Entities.Profession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDto {

	private String name;

	private String last_name;

	private String address;
	
	private String email;

	private String cell_phone;

	private Profession profession;

	private Pet pet;

}
