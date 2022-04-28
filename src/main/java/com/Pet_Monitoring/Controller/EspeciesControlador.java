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

import com.Pet_Monitoring.Dto.EspeciesDto;
import com.Pet_Monitoring.Dto.Mensaje;
import com.Pet_Monitoring.Entity.Species;
import com.Pet_Monitoring.Service.EspeciesService;

@RestController
@RequestMapping("/especies")
@CrossOrigin(origins = "http://localhost:3000")
public class EspeciesControlador {

	@Autowired
	EspeciesService especiesService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Species>> lista() {
		List<Species> list = especiesService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listaid/{id}")
	public ResponseEntity<Species> getById(@PathVariable("id") int id) {
		if (!especiesService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Species especie = especiesService.getOne(id).get();
		return new ResponseEntity<>(especie, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listanombre/{nombre}")
	public ResponseEntity<Species> getByNombre(@PathVariable("nombre") String nombre) {
		if (!especiesService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Species especie = especiesService.getByNombre(nombre).get();
		return new ResponseEntity<>(especie, HttpStatus.OK);
	}

	/*
	 * @PostMapping(produces = "application/json") public ResponseEntity<?>
	 * guardar(@RequestBody @Validated EspeciesDto especiesDto) { if
	 * (StringUtils.isBlank(especiesDto.getNombre())) return new
	 * ResponseEntity<>(new Mensaje("el nombre es obligatorio"),
	 * HttpStatus.BAD_REQUEST); if
	 * (especiesService.existsByNombre(especiesDto.getNombre())) return new
	 * ResponseEntity<>(new Mensaje("ese nombre ya existe"),
	 * HttpStatus.BAD_REQUEST); Species especie = new Species();
	 * especie.setNombre(especiesDto.getNombre());
	 * especie.setMascotas(especiesDto.getMascotas());
	 * especiesService.guardar(especie); return new ResponseEntity<>(new
	 * Mensaje("Especie creada"), HttpStatus.OK); }
	 * 
	 * @PutMapping("{id}") public ResponseEntity<?> actualizar(@PathVariable("id")
	 * int id, @RequestBody EspeciesDto especiesDto) { if
	 * (!especiesService.existsById(id)) return new ResponseEntity<>(new
	 * Mensaje("no existe"), HttpStatus.NOT_FOUND); if
	 * (especiesService.existsByNombre(especiesDto.getNombre()) &&
	 * especiesService.getByNombre(especiesDto.getNombre()).get().getId() != id)
	 * return new ResponseEntity<>(new Mensaje("ese nombre ya existe"),
	 * HttpStatus.BAD_REQUEST); if (StringUtils.isBlank(especiesDto.getNombre()))
	 * return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"),
	 * HttpStatus.BAD_REQUEST); Species especie = especiesService.getOne(id).get();
	 * especie.setNombre(especiesDto.getNombre());
	 * especiesService.actualizar(especie); return new ResponseEntity<>(new
	 * Mensaje("Especie actualizada"), HttpStatus.OK); }
	 */

	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable("id") int id) {
		if (!especiesService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		especiesService.eliminar(id);
		return new ResponseEntity<>(new Mensaje("Especie borrada"), HttpStatus.OK);
	}

}
