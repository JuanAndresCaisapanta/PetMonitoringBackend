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

import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Dto.EstablishmentTypeDto;
import com.Pet_Monitoring.Entities.EstablishmentType;
import com.Pet_Monitoring.Services.EstablishmentTypeService;

@RestController
@RequestMapping("/establishmentType")
@CrossOrigin
public class EstablishmentTypeController {

	@Autowired
	EstablishmentTypeService establishmentTypeService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<EstablishmentType>> readAllEstablishmentType() {
		List<EstablishmentType> establishmentType = establishmentTypeService.readAllEstablishmentType();
		return new ResponseEntity<>(establishmentType, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{establishmentType_id}")
	public ResponseEntity<EstablishmentType> getByEstablishmentTypeId(
			@PathVariable("establishmentType_id") Long establishmentType_id) {
		if (!establishmentTypeService.existsByEstablishmentTypeId(establishmentType_id))
			return new ResponseEntity(new Message("El tipo de establecimiento no existe"), HttpStatus.NOT_FOUND);
		EstablishmentType establishmentType = establishmentTypeService.getOneEstablishmentType(establishmentType_id)
				.get();
		return new ResponseEntity<>(establishmentType, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> createEstablishmentType(
			@RequestBody @Validated EstablishmentTypeDto establishmentTypeDto) {
		if (StringUtils.isBlank(establishmentTypeDto.getName()))
			return new ResponseEntity<>(new Message("El nombre del tipo de establecimiento es obligatorio"),
					HttpStatus.BAD_REQUEST);
		if (establishmentTypeService.existsByEstablishmentTypeName(establishmentTypeDto.getName()))
			return new ResponseEntity<>(new Message("El nombre del tipo de establecimiento ya existe"),
					HttpStatus.BAD_REQUEST);
		EstablishmentType establishmentType = new EstablishmentType();
		establishmentType.setName(establishmentTypeDto.getName());
		establishmentTypeService.createEstablishmentType(establishmentType);
		return new ResponseEntity<>(new Message("Establecimiento creado"), HttpStatus.OK);
	}

	@PutMapping("/{establishmentType_id}")
	public ResponseEntity<?> updateEstablishmentType(@PathVariable("establishmentType_id") Long establishmentType_id,
			@RequestBody EstablishmentTypeDto establishmentTypeDto) {
		if (!establishmentTypeService.existsByEstablishmentTypeId(establishmentType_id))
			return new ResponseEntity<>(new Message("El tipo de establecimiento no existe"), HttpStatus.NOT_FOUND);
		if (establishmentTypeService.existsByEstablishmentTypeName(establishmentTypeDto.getName())
				&& establishmentTypeService.findByEstablishmentTypeName(establishmentTypeDto.getName()).get()
						.getId() != establishmentType_id)
			return new ResponseEntity<>(new Message("El nombre del tipo de establecimiento ya existe"),
					HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(establishmentTypeDto.getName()))
			return new ResponseEntity<>(new Message("El nombre del tipo de establecimiento es obligatorio"),
					HttpStatus.BAD_REQUEST);
		EstablishmentType establishmentType = establishmentTypeService.getOneEstablishmentType(establishmentType_id)
				.get();
		establishmentType.setName(establishmentTypeDto.getName());
		establishmentTypeService.updateEstablishmentType(establishmentType);
		return new ResponseEntity<>(new Message("Tipo de establecimiento actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("/{establishmentType_id}")
	public ResponseEntity<?> deleteEstablishmentType(@PathVariable("establishmentType_id") Long establishmentType_id) {
		if (!establishmentTypeService.existsByEstablishmentTypeId(establishmentType_id))
			return new ResponseEntity<>(new Message("El tipo de establecimiento no existe"), HttpStatus.NOT_FOUND);
		try {
			establishmentTypeService.deleteEstablishmentType(establishmentType_id);
			return new ResponseEntity<>(new Message("Tipo de establecimiento borrado"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("Error al borrar el tipo de establecimiento"),
					HttpStatus.BAD_REQUEST);
		}

	}

}
