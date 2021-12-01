package com.MonitoreoMascotas.Controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MonitoreoMascotas.Dto.Mensaje;
import com.MonitoreoMascotas.Dto.ProfesionalesDto;
import com.MonitoreoMascotas.Entity.Profesionales;
import com.MonitoreoMascotas.Repository.EspecialidadesRepository;
import com.MonitoreoMascotas.Repository.MascotasRepository;
import com.MonitoreoMascotas.Service.ProfesionalesService;

@RestController
@RequestMapping("/profesionales")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfesionalesController {

	@Autowired
	ProfesionalesService profesionalesService;
	MascotasRepository mascotasRepository;
	EspecialidadesRepository especialidadesRepository;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Profesionales>> list() {
		List<Profesionales> list = profesionalesService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> crear(@RequestBody @Validated ProfesionalesDto profesionalesDto) {
		if (StringUtils.isBlank(profesionalesDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (profesionalesService.existsByNombre(profesionalesDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		// Mascotas mascota =
		// mascotasRepository.getById(profesionalesDto.getMascotas().getId());
		// System.out.println(mascota);
		// Especialidades especialidad =
		// especialidadesRepository.getById(profesionalesDto.getMascotas().getId());
		Profesionales profesional = new Profesionales();
		profesional.setNombre(profesionalesDto.getNombre());
		profesional.setApellido(profesionalesDto.getApellido());
		profesional.setDireccion(profesionalesDto.getDireccion());
		profesional.setTelefono(profesionalesDto.getTelefono());
		profesional.setMascotas(profesionalesDto.getMascotas());
		profesional.setEspecialidades(profesionalesDto.getEspecialidades());
		profesionalesService.guardar(profesional);
		return new ResponseEntity<>(new Mensaje("Especialidad creada"), HttpStatus.OK);
	}
}
