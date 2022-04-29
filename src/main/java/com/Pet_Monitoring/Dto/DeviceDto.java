package com.Pet_Monitoring.Dto;

import java.util.Date;
import java.util.List;

import com.Pet_Monitoring.Entities.DeviceData;
import com.Pet_Monitoring.Security.Entity.Usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {

	private String code;

	private Date creation_date;

	private Date update_date;

	private List<DeviceData> deviceData;

	private Usuarios usuarios;

}
