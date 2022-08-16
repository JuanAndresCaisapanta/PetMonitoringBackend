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

	@GetMapping("/last_id")
	public ResponseEntity<Species> findTopByOrderBySpeciesIdDesc() {
		Species species = speciesService.findTopByOrderBySpeciesIdDesc().get();
		return new ResponseEntity<>(species, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{species_id}")
	public ResponseEntity<Species> getBySpeciesId(@PathVariable("species_id") Long species_id) {
		if (!speciesService.existsBySpeciesId(species_id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Species species = speciesService.getOneSpecies(species_id).get();
		return new ResponseEntity<>(species, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> createSpecies(@RequestBody @Validated SpeciesDto speciesDto) {
		if (StringUtils.isBlank(speciesDto.getName()))
			return new ResponseEntity<>(new Message("El nombre de la especie es obligatorio"), HttpStatus.BAD_REQUEST);
		if (speciesService.existsBySpeciesName(speciesDto.getName()))
			return new ResponseEntity<>(new Message("El nombre de la especie ya existe"), HttpStatus.BAD_REQUEST);
		Species species = new Species();
		species.setName(speciesDto.getName());
		speciesService.createSpecies(species);
		return new ResponseEntity<>(new Message("Especie creada"), HttpStatus.OK);
	}

	@PutMapping("{species_id}")
	public ResponseEntity<?> updateSpecies(@PathVariable("species_id") Long species_id,
			@RequestBody SpeciesDto speciesDto) {
		if (!speciesService.existsBySpeciesId(species_id))
			return new ResponseEntity<>(new Message("La especie no existe"), HttpStatus.NOT_FOUND);
		if (speciesService.existsBySpeciesName(speciesDto.getName())
				&& speciesService.findBySpeciesName(speciesDto.getName()).get().getId() != species_id)
			return new ResponseEntity<>(new Message("El nombre de la especie ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(speciesDto.getName()))
			return new ResponseEntity<>(new Message("El nombre de la especie es obligatorio"), HttpStatus.BAD_REQUEST);
		Species species = speciesService.getOneSpecies(species_id).get();
		species.setName(speciesDto.getName());
		speciesService.updateSpecies(species);
		return new ResponseEntity<>(new Message("Especie actualizada"), HttpStatus.OK);
	}

	@DeleteMapping("{species_id}")
	public ResponseEntity<?> deleteSpecies(@PathVariable("species_id") Long species_id) {
		if (!speciesService.existsBySpeciesId(species_id))
			return new ResponseEntity<>(new Message("La especie no existe"), HttpStatus.NOT_FOUND);
		try {
			speciesService.deleteSpecies(species_id);
			return new ResponseEntity<>(new Message("Especie borrada"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("Error al borrar la especie"), HttpStatus.BAD_REQUEST);
		}
	}

}
