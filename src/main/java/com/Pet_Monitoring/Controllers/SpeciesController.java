package com.Pet_Monitoring.Controllers;

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

import com.Pet_Monitoring.Dto.SpeciesDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Species;
import com.Pet_Monitoring.Services.SpeciesService;

@RestController
@RequestMapping("/species")
@CrossOrigin
public class SpeciesController {

	@Autowired
	SpeciesService speciesService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Species>> readAllSpecies() {

		List<Species> species = speciesService.readAllSpecies();
		return new ResponseEntity<>(species, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{speciesId}")
	public ResponseEntity<Species> getBySpeciesId(@PathVariable("speciesId") Long speciesId) {

		if (!speciesService.existsBySpeciesId(speciesId))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Species species = speciesService.getOneSpecies(speciesId).get();
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
	public ResponseEntity<?> createSpecies(@RequestBody @Validated SpeciesDto speciesDto) {

		if (StringUtils.isBlank(speciesDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (speciesService.existsBySpeciesName(speciesDto.getName()))
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Species species = new Species();
		species.setName(speciesDto.getName());
		speciesService.createSpecies(species);
		return new ResponseEntity<>(new Message("Especie creada"), HttpStatus.OK);

	}

	@PutMapping("{speciesId}")
	public ResponseEntity<?> updateSpecies(@PathVariable("speciesId") Long speciesId, @RequestBody SpeciesDto speciesDto) {

		if (!speciesService.existsBySpeciesId(speciesId))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (speciesService.existsBySpeciesName(speciesDto.getName())
				&& speciesService.findBySpeciesName(speciesDto.getName()).get().getId() != speciesId)
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(speciesDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Species species = speciesService.getOneSpecies(speciesId).get();
		species.setName(speciesDto.getName());
		speciesService.updateSpecies(species);
		return new ResponseEntity<>(new Message("Especie actualizada"), HttpStatus.OK);

	}

	@DeleteMapping("{speciesId}")
	public ResponseEntity<?> deleteSpecies(@PathVariable("speciesId") Long speciesId) {

		if (!speciesService.existsBySpeciesId(speciesId))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		speciesService.deleteSpecies(speciesId);
		return new ResponseEntity<>(new Message("Especie borrada"), HttpStatus.OK);

	}

}
