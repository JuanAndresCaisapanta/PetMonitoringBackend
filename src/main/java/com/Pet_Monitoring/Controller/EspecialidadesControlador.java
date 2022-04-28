package com.Pet_Monitoring.Controller;

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

import com.Pet_Monitoring.Dto.EspecialidadesDto;
import com.Pet_Monitoring.Dto.Mensaje;
import com.Pet_Monitoring.Entity.Professional;
import com.Pet_Monitoring.Service.EspecialidadesService;

@RestController
@RequestMapping("/especialidades")
@CrossOrigin(origins = "http://localhost:3000")
public class EspecialidadesControlador {

	@Autowired
	EspecialidadesService especialidadesService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Professional>> lista() {
		List<Professional> list = especialidadesService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listaid/{id}")
	public ResponseEntity<Professional> getById(@PathVariable("id") int id) {
		if (!especialidadesService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Professional especialidad = especialidadesService.getOne(id).get();
		return new ResponseEntity<>(especialidad, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listanombre/{nombre}")
	public ResponseEntity<Professional> getByNombre(@PathVariable("nombre") String nombre) {
		if (!especialidadesService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Professional especialidad = especialidadesService.getByNombre(nombre).get();
		return new ResponseEntity<>(especialidad, HttpStatus.OK);
	}

	/*
	 * @PostMapping(produces = "application/json") public ResponseEntity<?>
	 * guardar(@RequestBody @Validated EspecialidadesDto especialidadesDto) { if
	 * (StringUtils.isBlank(especialidadesDto.getNombre())) return new
	 * ResponseEntity<>(new Mensaje("el nombre es obligatorio"),
	 * HttpStatus.BAD_REQUEST); if
	 * (especialidadesService.existsByNombre(especialidadesDto.getNombre())) return
	 * new ResponseEntity<>(new Mensaje("ese nombre ya existe"),
	 * HttpStatus.BAD_REQUEST); Professional especialidad = new Professional();
	 * especialidad.setNombre(especialidadesDto.getNombre());
	 * especialidad.setProfesionales(especialidadesDto.getProfesionales());
	 * especialidadesService.guardar(especialidad); return new ResponseEntity<>(new
	 * Mensaje("Especialidad creada"), HttpStatus.OK); }
	 * 
	 * @PutMapping("{id}") public ResponseEntity<?> actualizar(@PathVariable("id")
	 * int id, @RequestBody EspecialidadesDto especialidadesDto) { if
	 * (!especialidadesService.existsById(id)) return new ResponseEntity<>(new
	 * Mensaje("no existe"), HttpStatus.NOT_FOUND); if
	 * (especialidadesService.existsByNombre(especialidadesDto.getNombre()) &&
	 * especialidadesService.getByNombre(especialidadesDto.getNombre()).get().getId(
	 * ) != id) return new ResponseEntity<>(new Mensaje("ese nombre ya existe"),
	 * HttpStatus.BAD_REQUEST); if
	 * (StringUtils.isBlank(especialidadesDto.getNombre())) return new
	 * ResponseEntity<>(new Mensaje("el nombre es obligatorio"),
	 * HttpStatus.BAD_REQUEST); Professional especialidad =
	 * especialidadesService.getOne(id).get();
	 * especialidad.setNombre(especialidadesDto.getNombre());
	 * especialidadesService.actualizar(especialidad); return new
	 * ResponseEntity<>(new Mensaje("Especialidad actualizada"), HttpStatus.OK); }
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable("id") int id) {
		if (!especialidadesService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		especialidadesService.eliminar(id);
		return new ResponseEntity<>(new Mensaje("Especialidad borrada"), HttpStatus.OK);
	}

}
