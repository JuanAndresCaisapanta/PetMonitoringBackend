package com.Pet_Monitoring.Security.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = "*")
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
		if (userService.existsByUserEmail(userDto.getEmail()))
			return new ResponseEntity<>(new Message("El correo electrónico ya existe"), HttpStatus.BAD_REQUEST);
		Users user = new Users();
		if (image == null) {
			user.setImage(Util.extractBytes("https://i.ibb.co/6DX9405/user.png"));
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
		role.add(roleService.getByRoleName(RoleName.User).get());
		// role.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
		user.setRole(role);
		userService.createUser(user);
		return new ResponseEntity<>(new Message("usuario guardado"), HttpStatus.CREATED);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/forget-password/{email}")
	public ResponseEntity<?> forgetPassword(@PathVariable("email") String email) {
		if (!userService.existsByUserEmail(email))
			return new ResponseEntity(new Message("El correo electrónico no existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(email))
			return new ResponseEntity(new Message("El correo electrónico es obligatorio"), HttpStatus.BAD_REQUEST);
		String password = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
		Users user = userService.getByEmail(email).get();
		user.setPassword(passwordEncoder.encode(password));
		userService.updateUser(user);
		userService.sendEmailUser("server.moniopet@gmail.com", user.getEmail(), "Contraseña Olvidada",
				"Su nueva contraseña es: " + password);
		return new ResponseEntity<>(new Message("Contraseña enviada"), HttpStatus.CREATED);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/validate-token/{token}")
	public ResponseEntity<?> getValidateToken(@PathVariable("token") String token) {
		return new ResponseEntity(jwtProvider.validateToken(token), HttpStatus.OK);
	}

}
