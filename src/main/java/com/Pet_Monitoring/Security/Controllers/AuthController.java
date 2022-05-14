package com.Pet_Monitoring.Security.Controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Security.Dto.JwtDto;
import com.Pet_Monitoring.Security.Dto.LoginDto;
import com.Pet_Monitoring.Security.Dto.UserDto;
import com.Pet_Monitoring.Security.Entities.Role;
import com.Pet_Monitoring.Security.Entities.Users;
import com.Pet_Monitoring.Security.Enums.RoleName;
import com.Pet_Monitoring.Security.Jwt.JwtProvider;
import com.Pet_Monitoring.Security.Services.RoleService;
import com.Pet_Monitoring.Security.Services.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	JwtProvider jwtProvider;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Message("campos mal puestos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto();
		jwtDto.setToken(jwt);
		jwtDto.setBearer("Bearer");
		jwtDto.setEmail(userDetails.getUsername());
		jwtDto.setAuthorities(userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		if (userService.existsByEmail(userDto.getEmail()))
			return new ResponseEntity<>(new Message("ese email ya existe"), HttpStatus.BAD_REQUEST);
		Users user = new Users();
		user.setName(userDto.getName());
		user.setLast_name(userDto.getLast_name());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setAddress(userDto.getAddress());
		user.setPhone(userDto.getPhone());
		user.setCreation_date(userDto.getCreation_date());
		Collection<Role> role = new ArrayList<>();
		role.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
		//role.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
		user.setRole(role);
		userService.create(user);
		return new ResponseEntity<>(new Message("usuario guardado"), HttpStatus.CREATED);
	}

}
