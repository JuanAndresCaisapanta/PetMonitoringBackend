package com.Pet_Monitoring.Controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.PetDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Services.PetService;

@RestController
@RequestMapping("/pet")
@CrossOrigin
public class PetController {

	@Autowired
	PetService petService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Pet>> lista() {
		List<Pet> pet = petService.read();
		return new ResponseEntity<>(pet, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{id}")
	public ResponseEntity<Pet> getById(@PathVariable("id") int id) {

		if (!petService.existsById(id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Pet pet = petService.getOne(id).get();
		return new ResponseEntity(pet, HttpStatus.OK);

	}

	/*
	 * @SuppressWarnings({ "unchecked", "rawtypes" })
	 * 
	 * @GetMapping("/listanombre/{nombre}") public ResponseEntity<Pet>
	 * getByNombre(@PathVariable("nombre") String nombre) { if
	 * (!mascotasService.existsByNombre(nombre)) return new ResponseEntity(new
	 * Message("no existe"), HttpStatus.NOT_FOUND); Pet mascota =
	 * mascotasService.getByNombre(nombre).get(); return new ResponseEntity(mascota,
	 * HttpStatus.OK); }
	 */

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> guardar(@RequestBody PetDto petDto) {

		if (StringUtils.isBlank(petDto.getName()))
			return new ResponseEntity<>(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST); //
		// if (petService.existsByNombre(petDto.getName()))
		// return new ResponseEntity<>(new Message("ese nombre ya existe"),
		// HttpStatus.BAD_REQUEST);
		Pet pet = new Pet();
		pet.setName(petDto.getName());
		pet.setColor(petDto.getColor());
		pet.setRace(petDto.getRace());
		pet.setWeight(petDto.getWeight());
		pet.setGender(petDto.getGender());
		pet.setSterilization(petDto.getSterilization());
		pet.setBirth_date(petDto.getBirth_date());
		pet.setCreation_date(petDto.getCreation_date());
		pet.setUpdate_date(petDto.getUpdate_date());
		pet.setSpecies(petDto.getSpecies());
		pet.setUsuarios(petDto.getUsuarios());
		petService.create(pet);
		return new ResponseEntity<>(new Message("Mascota creada"), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody PetDto petDto) {

		if (!petService.existsById(id))
			return new ResponseEntity<>(new Message("no existe"), HttpStatus.NOT_FOUND);
//		if (petService.existsByNombre(mascotasDto.getNombre())
//				&& petService.getByNombre(mascotasDto.getNombre()).get().getId() != id)
//			return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(petDto.getName()))
			return new ResponseEntity<>(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Pet pet = petService.getOne(id).get();
		pet.setName(petDto.getName());
		pet.setColor(petDto.getColor());
		pet.setRace(petDto.getRace());
		pet.setWeight(petDto.getWeight());
		pet.setGender(petDto.getGender());
		pet.setSterilization(petDto.getSterilization());
		pet.setBirth_date(petDto.getBirth_date());
		pet.setUpdate_date(petDto.getUpdate_date());
		pet.setSpecies(petDto.getSpecies());
		petService.update(pet);
		return new ResponseEntity<>(new Message("Mascota actualizada"), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!petService.existsById(id))
			return new ResponseEntity<>(new Message("no existe"), HttpStatus.NOT_FOUND);
		petService.delete(id);
		return new ResponseEntity<>(new Message("Mascota borrada"), HttpStatus.OK);

	}

}
