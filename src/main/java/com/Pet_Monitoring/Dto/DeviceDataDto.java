package com.Pet_Monitoring.Dto;

import java.util.Date;

import com.Pet_Monitoring.Entities.Device;
import com.Pet_Monitoring.Entities.Pet;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDataDto {

	private Float latitude;

	private Float longitude;

	private Float temperature;

	private int battery;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creation_date;

	private Device device;

	private Pet pet;

}
