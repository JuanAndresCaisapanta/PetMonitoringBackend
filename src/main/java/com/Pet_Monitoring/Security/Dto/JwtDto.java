package com.Pet_Monitoring.Security.Dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto {
	
	private String token;
    private String bearer;
    private String user;
    private Collection<? extends GrantedAuthority> authorities;
    
}
