package com.Pet_Monitoring.Dto;

import java.util.Date;

import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Entities.TypeEstablishment;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDto {

	private String name;

	private String address;

	private String phone;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creation_date;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date update_date;

	private TypeEstablishment typeEstablishment;

	private Pet pet;
}
