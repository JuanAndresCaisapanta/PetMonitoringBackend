package com.Pet_Monitoring.Security.Controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Security.Dto.UserDto;
import com.Pet_Monitoring.Security.Entities.Users;
import com.Pet_Monitoring.Security.Services.RoleService;
import com.Pet_Monitoring.Security.Services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Users>> readAll() {
		List<Users> user = userService.read();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{email}")
	public ResponseEntity<Users> getByEmail(@PathVariable("email") String email) {
		// if(!usuariosService.existsById(id))
		// return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Users user = userService.getByEmail(email).get();
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/{id}")
	public ResponseEntity<Users> actualizar(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
		if (!userService.existsById(id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		if (userService.existsByEmail(userDto.getEmail())
				&& userService.getByEmail(userDto.getEmail()).get().getId() != id)
			return new ResponseEntity(new Message("El email no esta disponible"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(userDto.getEmail()))
			return new ResponseEntity(new Message("el email es obligatorio"), HttpStatus.BAD_REQUEST);
		Users usuario = userService.getById(id).get();
		usuario.setName(userDto.getName());
		usuario.setLast_name(userDto.getLast_name());
		usuario.setEmail(userDto.getEmail());
		usuario.setPassword(passwordEncoder.encode(userDto.getPassword()));
		usuario.setAddress(userDto.getAddress());
		usuario.setPhone(userDto.getPhone());
		userService.update(usuario);
		return new ResponseEntity(usuario, HttpStatus.OK);
	}
}