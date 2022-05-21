package com.Pet_Monitoring.Security.Dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.Pet_Monitoring.Security.Entities.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@NotBlank
	private String name;
	
	@NotNull
	private String last_name;
	
	@Email
	private String email;
	
	@NotNull
	private String password;

	private String address;

	private String phone;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creation_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date update_date;
	
	private Collection<Role> role = new ArrayList<>();

}
