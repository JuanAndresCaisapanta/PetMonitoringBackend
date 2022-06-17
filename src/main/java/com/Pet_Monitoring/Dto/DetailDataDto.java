package com.Pet_Monitoring.Dto;

import com.Pet_Monitoring.Entities.MasterData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailDataDto {

	private Float latitude;

	private Float longitude;

	private Float temperature;

	private int battery;

	private MasterData masterData;

}
