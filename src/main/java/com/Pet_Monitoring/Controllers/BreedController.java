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
	public ResponseEntity<List<Breed>> readAllBreed() {
		List<Breed> breed = breedService.readAllBreed();
		return new ResponseEntity<>(breed, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{breed_id}")
	public ResponseEntity<Breed> getByBreedId(@PathVariable("breed_id") Long breed_id) {
		if (!breedService.existsByBreedId(breed_id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Breed breed = breedService.getOneBreed(breed_id).get();
		return new ResponseEntity<>(breed, HttpStatus.OK);
	}

	@GetMapping("species/{species_id}")
	public ResponseEntity<?> getBySpeciesId(@PathVariable("species_id") Long species_id) {
		List<Breed> breed = breedService.findAllBySpeciesId(species_id);
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
	public ResponseEntity<?> createBreed(@RequestBody @Validated BreedDto breedDto) {
		if (StringUtils.isBlank(breedDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (breedService.existsByBreedName(breedDto.getName()))
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Breed breed = new Breed();
		breed.setName(breedDto.getName());
		breed.setSpecies(breedDto.getSpecies());
		breedService.createBreed(breed);
		return new ResponseEntity<>(new Message("Raza creada"), HttpStatus.OK);
	}

	@PutMapping("{breed_id}")
	public ResponseEntity<?> updateBreed(@PathVariable("breed_id") Long breed_id, @RequestBody BreedDto breedDto) {
		if (!breedService.existsByBreedId(breed_id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (breedService.existsByBreedName(breedDto.getName())
				&& breedService.findByBreedName(breedDto.getName()).get().getId() != breed_id)
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(breedDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Breed breed = breedService.getOneBreed(breed_id).get();
		breed.setName(breedDto.getName());
		breed.setSpecies(breedDto.getSpecies());
		breedService.updateBreed(breed);
		return new ResponseEntity<>(new Message("Raza actualizada"), HttpStatus.OK);

	}

	@DeleteMapping("{breed_id}")
	public ResponseEntity<?> deleteBreed(@PathVariable("breed_id") Long breed_id) {
		if (!breedService.existsByBreedId(breed_id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		breedService.deleteBreed(breed_id);
		return new ResponseEntity<>(new Message("Raza borrada"), HttpStatus.OK);
	}
	
}
