package com.Pet_Monitoring.Controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import com.Pet_Monitoring.Dto.EmailDto;
import com.Pet_Monitoring.Dto.EstablishmentDto;
import com.Pet_Monitoring.Dto.FullName;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Establishment;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Services.EstablishmentService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/establishment")
@CrossOrigin
public class EstablishmentController {

	@Autowired 
	EstablishmentService establishmentService;

	@GetMapping
	public ResponseEntity<List<Establishment>> readallEstablishment() {

		List<Establishment> device = establishmentService.readAllEstablishment();
		if (device.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(device); 

	}

	@GetMapping("/{establishmentId}")
	public ResponseEntity<Establishment> getByEstablishmentId(@PathVariable("establishmentId") Long establishmentId) {

		Establishment establishment = establishmentService.getOneEstablishment(establishmentId).get();
		if (establishment == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(establishment);

	}
	
	@GetMapping("pets/user/{userId}")
	public ResponseEntity<?> getEstablishmentFullNames(@PathVariable("userId") Long userId) {
		List<FullName> fullName = establishmentService.getEstablishmentFullNames(userId);
		return new ResponseEntity<>(fullName, HttpStatus.OK);
	}

	@GetMapping("pets/{typeEstablishmentId}/{fullName}/{userId}")
	public ResponseEntity<List<Pet>> getEstablishmentPets(@PathVariable("typeEstablishmentId") Long typeEstablishmentId,
			@PathVariable("fullName") String fullName, @PathVariable("userId") Long userId) {
		List<Pet> pets = establishmentService.getEstablishmentPets(typeEstablishmentId, fullName, userId);
		if (pets.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pets);
	}
	

	@PostMapping
	public ResponseEntity<?> createEstablishment(@RequestBody @Validated EstablishmentDto establishmentDto,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(establishmentDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		// if (StringUtils.isBlank(deviceDto.getCode()))
		// return new ResponseEntity<>(new Message("El codigo es incorrecto"),
		// HttpStatus.BAD_REQUEST); 
		Establishment establishment = new Establishment();
		establishment.setName(establishmentDto.getName());
		establishment.setAddress(establishmentDto.getAddress());
		establishment.setEmail(establishmentDto.getEmail());
		establishment.setCell_phone(establishmentDto.getCell_phone());
		establishment.setPhone(establishmentDto.getPhone());
		establishment.setCreation_date(Util.dateNow());
		establishment.setTypeEstablishment(establishmentDto.getTypeEstablishment());
		establishment.setPet(establishmentDto.getPet());
		establishmentService.createEstablishment(establishment);
		return new ResponseEntity<>(new Message("Establecimiento creado"), HttpStatus.OK);

	}

	@PutMapping("/{establishmentId}")
	public ResponseEntity<?> update(@PathVariable("establishmentid") Long establishmentId, @RequestBody EstablishmentDto establishmentDto,
			BindingResult bindingResult) {
		if (!establishmentService.existsByEstablishmentId(establishmentId))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(establishmentDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Establishment establishment = establishmentService.getOneEstablishment(establishmentId).get();
		establishment.setName(establishmentDto.getName());
		establishment.setAddress(establishmentDto.getAddress());
		establishment.setEmail(establishmentDto.getEmail());
		establishment.setCell_phone(establishmentDto.getCell_phone());
		establishment.setPhone(establishmentDto.getPhone());
		establishment.setUpdate_date(Util.dateNow());
		establishmentService.updateEstablishment(establishment);
		return new ResponseEntity<>(new Message("Establecimiento actualizado"), HttpStatus.OK);

	}

	@DeleteMapping("/{establishmentId}")
	public ResponseEntity<?> delete(@PathVariable("establishmentId") Long establishmentId) {

		if (!establishmentService.existsByEstablishmentId(establishmentId))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		establishmentService.deleteEstablishment(establishmentId);
		return new ResponseEntity<>(new Message("Establecimiento borrado"), HttpStatus.OK);
 
	}


	@PostMapping("/emailEstablishment")
	public ResponseEntity<?> sendEmailEstablishment(@RequestBody @Validated EmailDto emailDto) {
	establishmentService.sendEmailEstablishment(emailDto.getFromEmail(), emailDto.getToEmail(), emailDto.getSubject(),
				emailDto.getBody());
		return new ResponseEntity<>(new Message("Email enviado"), HttpStatus.OK);

	}
	
}
