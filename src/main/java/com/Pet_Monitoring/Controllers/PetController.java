package com.Pet_Monitoring.Controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Pet_Monitoring.Dto.PetDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Services.PetService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/pet")
@CrossOrigin
public class PetController {

	@Autowired
	PetService petService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Pet>> readAllPet() {
		List<Pet> pet = petService.readAllPet();
		return new ResponseEntity<>(pet, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{pet_id}")
	public ResponseEntity<Pet> getByPetId(@PathVariable("pet_id") Long pet_id) {
		if (!petService.existsByPetId(pet_id))
			return new ResponseEntity(new Message("La mascota no existe"), HttpStatus.NOT_FOUND);
		Pet pet = petService.getOnePet(pet_id).get();
		return new ResponseEntity(pet, HttpStatus.OK);
	}

	@GetMapping("users/{user_id}")
	public ResponseEntity<?> getByUsersId(@PathVariable("user_id") Long user_id) {
		List<Pet> pets = petService.findAllByUsersId(user_id);
		return new ResponseEntity<>(pets, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> createPet(@Valid @ModelAttribute PetDto petDto, BindingResult bindingResult,
			@RequestParam(required = false, value = "image") MultipartFile image) throws IOException {
		if (StringUtils.isBlank(petDto.getName()))
			return new ResponseEntity<>(new Message("El nombre de la mascota es obligatorio"), HttpStatus.BAD_REQUEST);
		Pet pet = new Pet();
		if (image == null) {
			pet.setImage(Util.extractBytes("https://i.ibb.co/3436j52/pet-profile.jpg"));
		} else {
			byte[] bytesImg = image.getBytes();
			pet.setImage(bytesImg);
		}
		pet.setName(petDto.getName());
		pet.setColor_main(petDto.getColor_main());
		pet.setColor_secondary(petDto.getColor_secondary());
		pet.setWeight(petDto.getWeight());
		pet.setSex(petDto.getSex());
		pet.setSterilization(petDto.getSterilization());
		pet.setBirth_date(petDto.getBirth_date());
		pet.setCreation_date(Util.dateNow());
		pet.setBreed(petDto.getBreed());
		pet.setUsers(petDto.getUsers());
		petService.createPet(pet);
		return new ResponseEntity<>(new Message("Mascota creada"), HttpStatus.OK);
	}

	@PutMapping("/{pet_id}")
	public ResponseEntity<?> updatePet(@PathVariable("pet_id") Long pet_id, @ModelAttribute PetDto petDto,
			@RequestParam(required = false, value = "image") MultipartFile image) throws IOException {
		if (!petService.existsByPetId(pet_id))
			return new ResponseEntity<>(new Message("La mascota no existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(petDto.getName()))
			return new ResponseEntity<>(new Message("El nombre de la mascota es obligatorio"), HttpStatus.BAD_REQUEST);
		Pet pet = petService.getOnePet(pet_id).get();
		pet.setName(petDto.getName());
		pet.setColor_main(petDto.getColor_main());
		pet.setColor_secondary(petDto.getColor_secondary());
		pet.setWeight(petDto.getWeight());
		pet.setSex(petDto.getSex());
		pet.setSterilization(petDto.getSterilization());
		pet.setBirth_date(petDto.getBirth_date());
		pet.setUpdate_date(Util.dateNow());
		pet.setBreed(petDto.getBreed());
		if (image != null) {
			byte[] bytesImg = image.getBytes();
			pet.setImage(bytesImg);
		}
		petService.updatePet(pet);
		return new ResponseEntity<>(new Message("Mascota actualizada"), HttpStatus.OK);
	}

	@DeleteMapping("/{pet_id}")
	public ResponseEntity<?> deletePet(@PathVariable("pet_id") Long pet_id) {
		if (!petService.existsByPetId(pet_id))
			return new ResponseEntity<>(new Message("La mascota no existe"), HttpStatus.NOT_FOUND);
		try {
			petService.deletePet(pet_id);
			return new ResponseEntity<>(new Message("Mascota borrada"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("Error al borrar la mascota"), HttpStatus.BAD_REQUEST);
		}
	}

}
