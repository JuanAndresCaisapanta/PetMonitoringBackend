package com.MonitoreoMascotas.Security.Dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.MonitoreoMascotas.Security.Entity.Roles;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UsuariosDto {
	@NotBlank
    private String nombre;
	@NotBlank
    private String apellido;
	@Email
    private String email;
    private String password;
    private String direccion;
    private String telefono;
    
    private Set<Roles> roles = new HashSet<>();

	
    
}
