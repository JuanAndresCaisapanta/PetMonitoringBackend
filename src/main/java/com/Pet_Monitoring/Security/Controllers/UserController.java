package com.Pet_Monitoring.Security.Controllers;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Dto.PasswordDto;
import com.Pet_Monitoring.Security.Dto.UserDto;
import com.Pet_Monitoring.Security.Entities.Users;
import com.Pet_Monitoring.Security.Services.RoleService;
import com.Pet_Monitoring.Security.Services.UserService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "*")
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
		if (!userService.existsByUserEmail(email))
			return new ResponseEntity(new Message("El correo electrónico no existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(email))
			return new ResponseEntity(new Message("El correo electrónico es obligatorio"), HttpStatus.BAD_REQUEST);
		Users user = userService.getByEmail(email).get();
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/id/{user_id}")
	public ResponseEntity<Users> getByUserId(@PathVariable("user_id") Long user_id) {
		if (!userService.existsByUserId(user_id))
			return new ResponseEntity(new Message("El usuario no existe"), HttpStatus.NOT_FOUND);
		Users user = userService.getByUserId(user_id).get();
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/{user_id}")
	public ResponseEntity<Users> updateUser(@PathVariable("user_id") Long user_id, @ModelAttribute UserDto userDto,
			@RequestParam(required = false, value = "image") MultipartFile image) throws IOException {
		if (!userService.existsByUserId(user_id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		if (userService.existsByUserEmail(userDto.getEmail())
				&& userService.getByEmail(userDto.getEmail()).get().getId() != user_id)
			return new ResponseEntity(new Message("El correo electrónico no esta disponible"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(userDto.getEmail()))
			return new ResponseEntity(new Message("El correo electrónico es obligatorio"), HttpStatus.BAD_REQUEST);
		Users user = userService.getByUserId(user_id).get();
		user.setName(userDto.getName());
		user.setLast_name(userDto.getLast_name());
		user.setEmail(userDto.getEmail());
		user.setAddress(userDto.getAddress());
		user.setPhone(userDto.getPhone());
		user.setUpdate_date(Util.dateNow());
		if (image != null) {
			byte[] bytesImg = image.getBytes();
			user.setImage(bytesImg);
		}
		userService.updateUser(user);
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/new-password/{user_id}")
	public ResponseEntity<Users> updatePassword(@PathVariable("user_id") Long user_id,
			@RequestBody PasswordDto passwordDto) {
		if (!userService.existsByUserId(user_id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Users user = userService.getByUserId(user_id).get();
		user.setPassword(passwordEncoder.encode(passwordDto.getNew_password()));
		userService.updateUser(user);
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@DeleteMapping("/{user_id}")
	public ResponseEntity<?> deleteUser(@PathVariable("user_id") Long user_id) {
		if (!userService.existsByUserId(user_id))
			return new ResponseEntity<>(new Message("no existe"), HttpStatus.NOT_FOUND);
		try {
		userService.deleteUser(user_id);
		return new ResponseEntity<>(new Message("Usuario borrado"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("Error al borrar el usuario"), HttpStatus.OK);
		}
	}
}
