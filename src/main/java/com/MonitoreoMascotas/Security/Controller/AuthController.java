package com.MonitoreoMascotas.Security.Controller;

import java.util.HashSet;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MonitoreoMascotas.Dto.Mensaje;
import com.MonitoreoMascotas.Security.Dto.JwtDto;
import com.MonitoreoMascotas.Security.Dto.LoginDto;
import com.MonitoreoMascotas.Security.Dto.UsuariosDto;
import com.MonitoreoMascotas.Security.Entity.Roles;
import com.MonitoreoMascotas.Security.Entity.Usuarios;
import com.MonitoreoMascotas.Security.Enums.RolNombre;
import com.MonitoreoMascotas.Security.Jwt.ProveedorJwt;
import com.MonitoreoMascotas.Security.Service.RolesService;
import com.MonitoreoMascotas.Security.Service.UsuariosService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuariosService usuariosService;

	@Autowired
	RolesService rolesService;

	@Autowired
	ProveedorJwt proveedorJwt;

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody UsuariosDto usuariosDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		if (usuariosService.existsByEmail(usuariosDto.getEmail()))
			return new ResponseEntity<>(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
		Usuarios usuario = new Usuarios();
		usuario.setNombre(usuariosDto.getNombre());
		usuario.setApellido(usuariosDto.getApellido());
		usuario.setEmail(usuariosDto.getEmail());
		usuario.setPassword(passwordEncoder.encode(usuariosDto.getPassword()));
		usuario.setDireccion(usuariosDto.getDireccion());
		usuario.setTelefono(usuariosDto.getTelefono());
		Set<Roles> roles = new HashSet<>();
		roles.add(rolesService.getByRolNombre(RolNombre.ROLE_USER).get());

		// roles.add(rolesService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		usuariosService.guardar(usuario);
		return new ResponseEntity<>(new Mensaje("usuario guardado"), HttpStatus.CREATED);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = proveedorJwt.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto();
		jwtDto.setToken(jwt);
		jwtDto.setBearer("Bearer");
		jwtDto.setEmail(userDetails.getUsername());
		jwtDto.setAuthorities(userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listaid/{id}")
	public ResponseEntity<Usuarios> getById(@PathVariable("id") int id) {
		// if(!usuariosService.existsById(id))
		// return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Usuarios usuario = usuariosService.getOne(id).get();
		return new ResponseEntity(usuario, HttpStatus.OK);
	}

}
