package com.Pet_Monitoring.Security.Controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
import com.Pet_Monitoring.Utils.Util;

import antlr.Utils;

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
		jwtDto.setUser(userDetails.getUsername());
		jwtDto.setAuthorities(userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult,
			@RequestParam(required = false, value = "image") MultipartFile image) throws IOException {
		/*
		 * if (bindingResult.hasErrors()) return new ResponseEntity<>(new
		 * Message("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		 */
		if (userService.existsByUserEmail(userDto.getEmail()))
			return new ResponseEntity<>(new Message("ese email ya existe"), HttpStatus.BAD_REQUEST);
		Users user = new Users();

		if (image == null) {
			user.setImage(Util.extractBytes("src//main//resources//static//images//user.png"));
		} else {
			byte[] bytesImg = image.getBytes();
			user.setImage(bytesImg);
		}
		user.setName(userDto.getName());
		user.setLast_name(userDto.getLast_name());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setAddress(userDto.getAddress());
		user.setPhone(userDto.getPhone());
		user.setCreation_date(Util.dateNow());
		Collection<Role> role = new ArrayList<>();
		role.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
		// role.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
		user.setRole(role);
		userService.createUser(user);
		return new ResponseEntity<>(new Message("usuario guardado"), HttpStatus.CREATED);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/validate-token/{token}")
	public ResponseEntity<?> getValidateToken(@PathVariable("token") String token) {
		return new ResponseEntity(jwtProvider.validateToken(token), HttpStatus.OK);
	}

}
