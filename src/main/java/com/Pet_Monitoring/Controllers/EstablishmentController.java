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
	public ResponseEntity<List<Establishment>> readAllEstablishment() {
		List<Establishment> device = establishmentService.readAllEstablishment();
		if (device.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(device);
	}

	@GetMapping("/{establishment_id}")
	public ResponseEntity<Establishment> getByEstablishmentId(@PathVariable("establishment_id") Long establishment_id) {
		Establishment establishment = establishmentService.getOneEstablishment(establishment_id).get();
		if (establishment == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(establishment);
	}

	@GetMapping("pets/user/{user_id}")
	public ResponseEntity<?> getEstablishmentFullNames(@PathVariable("user_id") Long user_id) {
		List<FullName> fullName = establishmentService.getEstablishmentFullNames(user_id);
		return new ResponseEntity<>(fullName, HttpStatus.OK);
	}

	@GetMapping("pets/{establishmentType_id}/{establishment_fullName}/{user_id}")
	public ResponseEntity<List<Pet>> getEstablishmentPets(
			@PathVariable("establishmentType_id") Long establishmentType_id,
			@PathVariable("establishment_fullName") String establishment_fullName,
			@PathVariable("user_id") Long user_id) {
		List<Pet> pets = establishmentService.getEstablishmentPets(establishmentType_id, establishment_fullName,
				user_id);
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
			return new ResponseEntity<>(new Message("El nombre del establecimiento es obligatorio"),
					HttpStatus.BAD_REQUEST);
		Establishment establishment = new Establishment();
		establishment.setName(establishmentDto.getName());
		establishment.setAddress(establishmentDto.getAddress());
		establishment.setEmail(establishmentDto.getEmail());
		establishment.setCell_phone(establishmentDto.getCell_phone());
		establishment.setPhone(establishmentDto.getPhone());
		establishment.setCreation_date(Util.dateNow());
		establishment.setEstablishmentType(establishmentDto.getEstablishmentType());
		establishment.setPet(establishmentDto.getPet());
		establishmentService.createEstablishment(establishment);
		return new ResponseEntity<>(new Message("Establecimiento creado"), HttpStatus.OK);
	}

	@PutMapping("/{establishment_id}")
	public ResponseEntity<?> updateEstablishment(@PathVariable("establishment_id") Long establishment_id,
			@RequestBody EstablishmentDto establishmentDto, BindingResult bindingResult) {
		if (!establishmentService.existsByEstablishmentId(establishment_id))
			return new ResponseEntity<>(new Message("El establecimiento no existe"), HttpStatus.NOT_FOUND);
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(establishmentDto.getName()))
			return new ResponseEntity<>(new Message("El nombre del establecimiento es obligatorio"),
					HttpStatus.BAD_REQUEST);
		Establishment establishment = establishmentService.getOneEstablishment(establishment_id).get();
		establishment.setName(establishmentDto.getName());
		establishment.setAddress(establishmentDto.getAddress());
		establishment.setEmail(establishmentDto.getEmail());
		establishment.setCell_phone(establishmentDto.getCell_phone());
		establishment.setPhone(establishmentDto.getPhone());
		establishment.setUpdate_date(Util.dateNow());
		establishmentService.updateEstablishment(establishment);
		return new ResponseEntity<>(new Message("Establecimiento actualizado"), HttpStatus.OK);

	}

	@DeleteMapping("/{establishment_id}")
	public ResponseEntity<?> deleteEstablishment(@PathVariable("establishment_id") Long establishment_id) {
		if (!establishmentService.existsByEstablishmentId(establishment_id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		try {
			establishmentService.deleteEstablishment(establishment_id);
			return new ResponseEntity<>(new Message("Establecimiento borrado"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("Error al eliminar el establecimiento"), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/emailEstablishment")
	public ResponseEntity<?> sendEmailEstablishment(@RequestBody @Validated EmailDto emailDto) {
		establishmentService.sendEmailEstablishment(emailDto.getFrom_email(), emailDto.getTo_email(),
				emailDto.getSubject(), emailDto.getBody());
		return new ResponseEntity<>(new Message("Correo electrónico enviado"), HttpStatus.OK);
	}

}
