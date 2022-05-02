package com.Pet_Monitoring.Controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.SpeciesDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Species;
import com.Pet_Monitoring.Services.SpeciesService;

@RestController
@RequestMapping("/species")
public class SpeciesController {

	@Autowired
	SpeciesService speciesService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Species>> read() {

		List<Species> species = speciesService.read();
		return new ResponseEntity<>(species, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{id}")
	public ResponseEntity<Species> getById(@PathVariable("id") int id) {

		if (!speciesService.existsById(id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Species species = speciesService.getOne(id).get();
		return new ResponseEntity<>(species, HttpStatus.OK);

	}

	/*
	 * @SuppressWarnings({ "unchecked", "rawtypes" })
	 * 
	 * @GetMapping("/listanombre/{nombre}") public ResponseEntity<Species>
	 * getByNombre(@PathVariable("nombre") String nombre) { if
	 * (!especiesService.existsByNombre(nombre)) return new ResponseEntity(new
	 * Mensaje("no existe"), HttpStatus.NOT_FOUND); Species especie =
	 * especiesService.getByNombre(nombre).get(); return new
	 * ResponseEntity<>(especie, HttpStatus.OK); }
	 */

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> create(@RequestBody @Validated SpeciesDto speciesDto) {

		if (StringUtils.isBlank(speciesDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (speciesService.existsByName(speciesDto.getName()))
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Species specie = new Species();
		specie.setName(speciesDto.getName());
		speciesService.create(specie);
		return new ResponseEntity<>(new Message("Especie creada"), HttpStatus.OK);

	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody SpeciesDto speciesDto) {

		if (!speciesService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (speciesService.existsByName(speciesDto.getName())
				&& speciesService.findByName(speciesDto.getName()).get().getId() != id)
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(speciesDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Species species = speciesService.getOne(id).get();
		species.setName(speciesDto.getName());
		speciesService.update(species);
		return new ResponseEntity<>(new Message("Especie actualizada"), HttpStatus.OK);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!speciesService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		speciesService.delete(id);
		return new ResponseEntity<>(new Message("Especie borrada"), HttpStatus.OK);

	}

}
