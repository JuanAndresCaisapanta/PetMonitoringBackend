package com.Pet_Monitoring.Security.Entities;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPrincipalDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String last_name;
	private String email;
	private String password;
	private String address;
	private String phone;
	private Collection<? extends GrantedAuthority> authorities;

	public static UserPrincipalDetails build(Users user) {
		List<GrantedAuthority> authorities = user.getRole().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new UserPrincipalDetails(user.getId(), user.getName(), user.getLast_name(), user.getEmail(),
				user.getPassword(), user.getAddress(), user.getPhone(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
