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

import com.Pet_Monitoring.Dto.Mensaje;
import com.Pet_Monitoring.Dto.ProfesionalesDto;
import com.Pet_Monitoring.Entity.Establishment;
import com.Pet_Monitoring.Service.ProfesionalesService;

@RestController
@RequestMapping("/profesionales")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfesionalesControlador {

	@Autowired
	ProfesionalesService profesionalesService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Establishment>> lista() {
		List<Establishment> list = profesionalesService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listaid/{id}")
	public ResponseEntity<Establishment> getById(@PathVariable("id") int id) {
		if (!profesionalesService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Establishment profesionales = profesionalesService.getOne(id).get();
		return new ResponseEntity<>(profesionales, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listanombre/{nombre}")
	public ResponseEntity<Establishment> getByNombre(@PathVariable("nombre") String nombre) {
		if (!profesionalesService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Establishment profesionales = profesionalesService.getByNombre(nombre).get();
		return new ResponseEntity<>(profesionales, HttpStatus.OK);
	}

	/*
	 * @PostMapping(produces = "application/json") public ResponseEntity<?>
	 * crear(@RequestBody @Validated ProfesionalesDto profesionalesDto) { if
	 * (StringUtils.isBlank(profesionalesDto.getNombre())) return new
	 * ResponseEntity<>(new Mensaje("el nombre es obligatorio"),
	 * HttpStatus.BAD_REQUEST); if
	 * (profesionalesService.existsByNombre(profesionalesDto.getNombre())) return
	 * new ResponseEntity<>(new Mensaje("ese nombre ya existe"),
	 * HttpStatus.BAD_REQUEST); Establishment profesionales = new Establishment();
	 * profesionales.setNombre(profesionalesDto.getNombre());
	 * profesionales.setApellido(profesionalesDto.getApellido());
	 * profesionales.setDireccion(profesionalesDto.getDireccion());
	 * profesionales.setTelefono(profesionalesDto.getTelefono());
	 * profesionales.setMascotas(profesionalesDto.getMascotas());
	 * profesionales.setEspecialidades(profesionalesDto.getEspecialidades());
	 * profesionalesService.guardar(profesionales); return new ResponseEntity<>(new
	 * Mensaje("Profesional creado"), HttpStatus.OK); }
	 * 
	 * @PutMapping("{id}") public ResponseEntity<?> actualizar(@PathVariable("id")
	 * int id, @RequestBody ProfesionalesDto profesionalesDto) { if
	 * (!profesionalesService.existsById(id)) return new ResponseEntity<>(new
	 * Mensaje("no existe"), HttpStatus.NOT_FOUND); if
	 * (profesionalesService.existsByNombre(profesionalesDto.getNombre()) &&
	 * profesionalesService.getByNombre(profesionalesDto.getNombre()).get().getId()
	 * != id) return new ResponseEntity<>(new Mensaje("ese nombre ya existe"),
	 * HttpStatus.BAD_REQUEST); if
	 * (StringUtils.isBlank(profesionalesDto.getNombre())) return new
	 * ResponseEntity<>(new Mensaje("el nombre es obligatorio"),
	 * HttpStatus.BAD_REQUEST); Establishment profesionales =
	 * profesionalesService.getOne(id).get();
	 * profesionales.setNombre(profesionalesDto.getNombre());
	 * profesionales.setApellido(profesionalesDto.getApellido());
	 * profesionales.setDireccion(profesionalesDto.getDireccion());
	 * profesionales.setTelefono(profesionalesDto.getTelefono());
	 * profesionales.setMascotas(profesionalesDto.getMascotas());
	 * profesionales.setEspecialidades(profesionalesDto.getEspecialidades());
	 * profesionalesService.actualizar(profesionales); return new
	 * ResponseEntity<>(new Mensaje("Profesional actualizado"), HttpStatus.OK); }
	 */
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable("id") int id) {
		if (!profesionalesService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		profesionalesService.eliminar(id);
		return new ResponseEntity<>(new Mensaje("Profesional borrado"), HttpStatus.OK);
	}
}
