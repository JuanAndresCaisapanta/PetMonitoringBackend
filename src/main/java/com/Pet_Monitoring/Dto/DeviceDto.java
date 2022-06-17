package com.Pet_Monitoring.Dto;

import java.util.List;

import com.Pet_Monitoring.Entities.DetailData;
import com.Pet_Monitoring.Security.Entities.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {

	private String code;
	
	private String callback;
	
	private String time;

	private List<DetailData> detailData;

	private Users users;

}
