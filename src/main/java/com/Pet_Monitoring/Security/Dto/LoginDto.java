package com.Pet_Monitoring.Security.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	
	@Email
    private String email;
    @NotBlank
    private String password;  
}
