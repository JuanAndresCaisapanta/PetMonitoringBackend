package com.Pet_Monitoring.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDto {
	
	private String Device_id;

	private String time;

}
