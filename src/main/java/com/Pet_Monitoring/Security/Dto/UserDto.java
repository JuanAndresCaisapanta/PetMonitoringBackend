package com.Pet_Monitoring.Security.Dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.Pet_Monitoring.Security.Entities.Role;

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
	
	private Collection<Role> role = new ArrayList<>();

}
