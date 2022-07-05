package com.Pet_Monitoring.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

	private String from_email;
	
	private String to_email;
	
	private String subject;
	
	private String body;

}
