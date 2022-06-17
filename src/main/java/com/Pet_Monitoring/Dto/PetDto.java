package com.Pet_Monitoring.Dto;

import java.util.Date;
import java.util.List;

import com.Pet_Monitoring.Entities.Breed;
import com.Pet_Monitoring.Entities.DetailData;
import com.Pet_Monitoring.Entities.Establishment;
import com.Pet_Monitoring.Entities.Medicine;
import com.Pet_Monitoring.Entities.Professional;
import com.Pet_Monitoring.Security.Entities.Users;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {

	private String name;

	private String color_main;
	
	private String color_secondary;

	private String race;

	private float weight;

	private String sex;

	private Boolean sterilization;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birth_date;

	private List<Establishment> establishment;

	private List<Medicine> medicine;

	private List<Professional> professional;

	private List<DetailData> detailData;

	private Breed breed;

	private Users users;

}
