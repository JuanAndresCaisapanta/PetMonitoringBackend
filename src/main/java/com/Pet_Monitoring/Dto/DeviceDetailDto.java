package com.Pet_Monitoring.Dto;

import com.Pet_Monitoring.Entities.Device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDetailDto {

	private Float latitude;

	private Float longitude;

	private Float temperature;

	private int battery;

	private Device device;

}
