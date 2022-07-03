package com.Pet_Monitoring.Dto;

import java.util.List;

import com.Pet_Monitoring.Entities.DeviceDetail;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Security.Entities.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
	
	private String code;
	
	private String callback_code;

	private Pet pet;

	private Users users;

	private List<DeviceDetail> deviceDetail;
}
