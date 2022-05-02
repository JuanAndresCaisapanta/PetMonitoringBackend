package com.Pet_Monitoring.Controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.EstablishmentDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Establishment;
import com.Pet_Monitoring.Services.EstablishmentService;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

	@Autowired
	EstablishmentService establishmentService;

	@GetMapping
	public ResponseEntity<List<Establishment>> read() {

		List<Establishment> device = establishmentService.read();
		if (device.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(device);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Establishment> getById(@PathVariable("id") int id) {

		Establishment establishment = establishmentService.getOne(id).get();
		if (establishment == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(establishment);

	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Validated EstablishmentDto establishmentDto,
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
		establishment.setPhone(establishmentDto.getPhone());
		establishment.setCreation_date(establishmentDto.getCreation_date());
		establishment.setUpdate_date(establishmentDto.getUpdate_date());
		establishment.setTypeEstablishment(establishmentDto.getTypeEstablishment());
		establishment.setPet(establishmentDto.getPet());
		establishmentService.create(establishment);
		return new ResponseEntity<>(new Message("Establecimiento creado"), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EstablishmentDto establishmentDto,
			BindingResult bindingResult) {

		
		if (!establishmentService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Message("Campos invalidos"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(establishmentDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Establishment establishment = establishmentService.getOne(id).get();
		establishment.setName(establishmentDto.getName());
		establishment.setAddress(establishmentDto.getAddress());
		establishment.setPhone(establishmentDto.getPhone());
		establishment.setUpdate_date(establishmentDto.getUpdate_date());
		establishmentService.update(establishment);
		return new ResponseEntity<>(new Message("Establecimiento actualizado"), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!establishmentService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		establishmentService.delete(id);
		return new ResponseEntity<>(new Message("Establecimiento borrado"), HttpStatus.OK);

	}

}
