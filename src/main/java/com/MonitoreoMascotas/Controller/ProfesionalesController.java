package com.MonitoreoMascotas.Controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MonitoreoMascotas.Dto.Mensaje;
import com.MonitoreoMascotas.Dto.ProfesionalesDto;
import com.MonitoreoMascotas.Entity.Profesionales;
import com.MonitoreoMascotas.Service.ProfesionalesService;

@RestController
@RequestMapping("/profesionales")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfesionalesController {

	@Autowired
	ProfesionalesService profesionalesService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Profesionales>> list() {
		List<Profesionales> list = profesionalesService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listaid/{id}")
	public ResponseEntity<Profesionales> getById(@PathVariable("id") int id) {
		if (!profesionalesService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Profesionales profesional = profesionalesService.getOne(id).get();
		return new ResponseEntity<>(profesional, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listanombre/{nombre}")
	public ResponseEntity<Profesionales> getByNombre(@PathVariable("nombre") String nombre) {
		if (!profesionalesService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Profesionales profesional = profesionalesService.getByNombre(nombre).get();
		return new ResponseEntity<>(profesional, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> crear(@RequestBody @Validated ProfesionalesDto profesionalesDto) {
		if (StringUtils.isBlank(profesionalesDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (profesionalesService.existsByNombre(profesionalesDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Profesionales profesional = new Profesionales();
		profesional.setNombre(profesionalesDto.getNombre());
		profesional.setApellido(profesionalesDto.getApellido());
		profesional.setDireccion(profesionalesDto.getDireccion());
		profesional.setTelefono(profesionalesDto.getTelefono());
		profesional.setMascotas(profesionalesDto.getMascotas());
		profesional.setEspecialidades(profesionalesDto.getEspecialidades());
		profesionalesService.guardar(profesional);
		return new ResponseEntity<>(new Mensaje("Profesional creado"), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody ProfesionalesDto profesionalesDto) {
		if (!profesionalesService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		if (profesionalesService.existsByNombre(profesionalesDto.getNombre())
				&& profesionalesService.getByNombre(profesionalesDto.getNombre()).get().getId() != id)
			return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(profesionalesDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Profesionales profesional = profesionalesService.getOne(id).get();
		profesional.setNombre(profesionalesDto.getNombre());
		profesional.setApellido(profesionalesDto.getApellido());
		profesional.setDireccion(profesionalesDto.getDireccion());
		profesional.setTelefono(profesionalesDto.getTelefono());
		profesional.setMascotas(profesionalesDto.getMascotas());
		profesional.setEspecialidades(profesionalesDto.getEspecialidades());
		profesionalesService.actualizar(profesional);
		return new ResponseEntity<>(new Mensaje("Profesional actualizado"), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable("id") int id) {
		if (!profesionalesService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		profesionalesService.eliminar(id);
		return new ResponseEntity<>(new Mensaje("Profesional borrado"), HttpStatus.OK);
	}
}
