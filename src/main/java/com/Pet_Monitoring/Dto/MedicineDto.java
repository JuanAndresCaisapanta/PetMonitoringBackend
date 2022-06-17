package com.Pet_Monitoring.Dto;

import java.util.Date;

import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Entities.TypeMedicine;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {

	private String name;

	private String manufacturer;

	private String batch;

	private String applicator;

	private String description;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date production_date;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date expiration_date;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date application_date;

	private TypeMedicine typeMedicine;

	private Pet pet;

}
