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

import com.Pet_Monitoring.Dto.BreedDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Breed;
import com.Pet_Monitoring.Services.BreedService;

@RestController
@RequestMapping("/breed")
@CrossOrigin
public class BreedController {

	@Autowired
	BreedService breedService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Breed>> read() {

		List<Breed> breed = breedService.read();
		return new ResponseEntity<>(breed, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{id}")
	public ResponseEntity<Breed> getById(@PathVariable("id") Long id) {
		if (!breedService.existsById(id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Breed breed = breedService.getOne(id).get();
		return new ResponseEntity<>(breed, HttpStatus.OK);
	}

	@GetMapping("species/{id}")
	public ResponseEntity<?> getBySpeciesId(@PathVariable("id") int id) {
		List<Breed> breed = breedService.findAllBySpeciesId(id);
		return new ResponseEntity<>(breed, HttpStatus.OK);
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
	public ResponseEntity<?> create(@RequestBody @Validated BreedDto breedDto) {

		if (StringUtils.isBlank(breedDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (breedService.existsByName(breedDto.getName()))
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Breed breed = new Breed();
		breed.setName(breedDto.getName());
		breed.setSpecies(breedDto.getSpecies());
		breedService.create(breed);
		return new ResponseEntity<>(new Message("Raza creada"), HttpStatus.OK);

	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BreedDto breedDto) {

		if (!breedService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (breedService.existsByName(breedDto.getName())
				&& breedService.findByName(breedDto.getName()).get().getId() != id)
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(breedDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Breed breed = breedService.getOne(id).get();
		breed.setName(breedDto.getName());
		breed.setSpecies(breedDto.getSpecies());
		breedService.update(breed);
		return new ResponseEntity<>(new Message("Raza actualizada"), HttpStatus.OK);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {

		if (!breedService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		breedService.delete(id);
		return new ResponseEntity<>(new Message("Raza borrada"), HttpStatus.OK);

	}

}
