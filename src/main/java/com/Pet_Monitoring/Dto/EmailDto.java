package com.Pet_Monitoring.Dto;

import java.util.List;

import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Entities.Species;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

	private String fromEmail;
	
	private String toEmail;
	
	private String subject;
	
	private String body;

}
