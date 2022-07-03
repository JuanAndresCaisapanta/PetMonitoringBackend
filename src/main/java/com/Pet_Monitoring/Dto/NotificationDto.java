package com.Pet_Monitoring.Dto;

import com.Pet_Monitoring.Security.Entities.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

	private String text;
	
	private Users users;
	
	private String subject;
	
}
