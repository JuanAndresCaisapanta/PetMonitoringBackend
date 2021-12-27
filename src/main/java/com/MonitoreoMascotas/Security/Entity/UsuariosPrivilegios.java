package com.MonitoreoMascotas.Security.Entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuariosPrivilegios implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String direccion;
	private String telefono;
	private Collection<? extends GrantedAuthority> authorities;

	public UsuariosPrivilegios(Integer id, String nombre, String apellido, String email, String password,
			String direccion, String telefono, Collection<? extends GrantedAuthority> authorities) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.direccion = direccion;
		this.telefono = telefono;
		this.authorities = authorities;
		this.id = id;
	}

	public static UsuariosPrivilegios build(Usuarios usuario) {
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(roles -> new SimpleGrantedAuthority(roles.getNombre().name())).collect(Collectors.toList());
		return new UsuariosPrivilegios(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(),
				usuario.getPassword(), usuario.getDireccion(), usuario.getTelefono(), authorities);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

}
