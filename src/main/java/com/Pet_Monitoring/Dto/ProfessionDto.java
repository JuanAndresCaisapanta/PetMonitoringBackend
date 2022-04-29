package com.Pet_Monitoring.Dto;

import java.util.List;

import com.Pet_Monitoring.Entities.Professional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionDto {

	private String name;

	private List<Professional> professional;

}
