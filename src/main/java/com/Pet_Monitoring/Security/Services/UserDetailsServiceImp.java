package com.Pet_Monitoring.Security.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Pet_Monitoring.Security.Entities.Users;
import com.Pet_Monitoring.Security.Entities.UserPrincipalDetails;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userService.getByEmail(email).get();
		return UserPrincipalDetails.build(user);
	}

}
