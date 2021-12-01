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

import com.MonitoreoMascotas.Dto.EspecialidadesDto;
import com.MonitoreoMascotas.Dto.Mensaje;
import com.MonitoreoMascotas.Entity.Especialidades;
import com.MonitoreoMascotas.Service.EspecialidadesService;

@RestController
@RequestMapping("/especialidades")
@CrossOrigin(origins = "http://localhost:3000")
public class EspecialidadesController {

	@Autowired
	EspecialidadesService especialidadesService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Especialidades>> lista() {
		List<Especialidades> list = especialidadesService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listaid/{id}")
	public ResponseEntity<Especialidades> getById(@PathVariable("id") int id) {
		if (!especialidadesService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Especialidades especialidad = especialidadesService.getOne(id).get();
		return new ResponseEntity<>(especialidad, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listanombre/{nombre}")
	public ResponseEntity<Especialidades> getByNombre(@PathVariable("nombre") String nombre) {
		if (!especialidadesService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Especialidades especialidad = especialidadesService.getByNombre(nombre).get();
		return new ResponseEntity<>(especialidad, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> guardar(@RequestBody @Validated EspecialidadesDto especialidadesDto) {
		if (StringUtils.isBlank(especialidadesDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (especialidadesService.existsByNombre(especialidadesDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Especialidades especialidad = new Especialidades(especialidadesDto.getNombre(),
				especialidadesDto.getProfesionales());
		especialidadesService.guardar(especialidad);
		return new ResponseEntity<>(new Mensaje("Especialidad creada"), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody EspecialidadesDto especialidadesDto) {
		if (!especialidadesService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		if (especialidadesService.existsByNombre(especialidadesDto.getNombre())
				&& especialidadesService.getByNombre(especialidadesDto.getNombre()).get().getId() != id)
			return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(especialidadesDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Especialidades especialidad = especialidadesService.getOne(id).get();
		especialidad.setNombre(especialidadesDto.getNombre());
		especialidadesService.actualizar(especialidad);
		return new ResponseEntity<>(new Mensaje("Especialidad actualizada"), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable("id") int id) {
		if (!especialidadesService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		especialidadesService.eliminar(id);
		return new ResponseEntity<>(new Mensaje("Especialidad borrada"), HttpStatus.OK);
	}

}
