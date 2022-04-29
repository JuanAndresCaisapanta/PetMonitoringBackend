package com.Pet_Monitoring.Dto;

import java.util.List;

import com.Pet_Monitoring.Entities.Establishment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeEstablishmentDto {
	
	private String name;

	private List<Establishment> establishment;
	
}
