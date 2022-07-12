package com.Pet_Monitoring.Security.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Security.Services.RoleService;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = {"http://localhost:8080","https://moniopet.vercel.app"})
public class RoleController {

	RoleService roleService;

	/*
	 * @PostMapping(produces = "application/json") public ResponseEntity<?>
	 * create(@Valid @RequestBody RoleDto roleDto, BindingResult bindingResult) {
	 * 
	 * if (bindingResult.hasErrors()) return ResponseEntity<?>(new
	 * Message("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST); if
	 * (userService.existsByEmail(userDto.getEmail())) return ResponseEntity<?>(new
	 * Message("ese email ya existe"), HttpStatus.BAD_REQUEST);
	 * 
	 * Role role = new Role(); role.setName(roleDto.getName());
	 * roleService.create(role); return new ResponseEntity<>(new
	 * Message("usuario guardado"), HttpStatus.CREATED); }
	 */

	/*
	 * @PostMapping("/addtouser") public ResponseEntity<?>
	 * addRoleToUser(@Valid @RequestBody RoleToUserForm roleToUserForm,
	 * BindingResult bindingResult) {
	 * 
	 * if (bindingResult.hasErrors()) return ResponseEntity<?>(new
	 * Message("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST); if
	 * (userService.existsByEmail(userDto.getEmail())) return ResponseEntity<?>(new
	 * Message("ese email ya existe"), HttpStatus.BAD_REQUEST);
	 * 
	 * roleService.addRoleToUser(roleToUserForm.getUser_email(),
	 * roleToUserForm.getRole_name()); return ResponseEntity.ok().build(); }
	 */

}
