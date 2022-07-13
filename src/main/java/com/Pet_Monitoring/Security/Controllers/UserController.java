package com.Pet_Monitoring.Security.Controllers;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Security.Dto.UserDto;
import com.Pet_Monitoring.Security.Entities.Users;
import com.Pet_Monitoring.Security.Services.RoleService;
import com.Pet_Monitoring.Security.Services.UserService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/user")
@CrossOrigin(value="*")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Users>> readAllUser() {
		List<Users> user = userService.readAllUser();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{email}")
	public ResponseEntity<Users> getByUserEmail(@PathVariable("email") String email) {
		// if(!usuariosService.existsById(id))
		// return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Users user = userService.getByEmail(email).get();
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/{userId}")
	public ResponseEntity<Users> updateUser(@PathVariable("userId") Long userId, @ModelAttribute UserDto userDto,
			@RequestParam(required = false, value = "image") MultipartFile image) throws IOException {
		if (!userService.existsByUserId(userId))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		if (userService.existsByUserEmail(userDto.getEmail())
				&& userService.getByEmail(userDto.getEmail()).get().getId() != userId)
			return new ResponseEntity(new Message("El email no esta disponible"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(userDto.getEmail()))
			return new ResponseEntity(new Message("el email es obligatorio"), HttpStatus.BAD_REQUEST);
		Users usuario = userService.getByUserId(userId).get();
		usuario.setName(userDto.getName());
		usuario.setLast_name(userDto.getLast_name());
		usuario.setEmail(userDto.getEmail());
		usuario.setAddress(userDto.getAddress());
		usuario.setPhone(userDto.getPhone());
		usuario.setUpdate_date(Util.dateNow());
		if (image != null) {
			byte[] bytesImg = image.getBytes();
			usuario.setImage(bytesImg);
		}
		userService.updateUser(usuario);
		return new ResponseEntity(usuario, HttpStatus.OK);
	}
}
