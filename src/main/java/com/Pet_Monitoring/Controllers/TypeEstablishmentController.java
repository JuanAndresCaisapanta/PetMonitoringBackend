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
import com.Pet_Monitoring.Dto.TypeEstablishmentDto;
import com.Pet_Monitoring.Entities.TypeEstablishment;
import com.Pet_Monitoring.Services.TypeEstablishmentService;

@RestController
@RequestMapping("/typeEstablishment")
@CrossOrigin
public class TypeEstablishmentController {

	@Autowired
	TypeEstablishmentService typeEstablishmentService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<TypeEstablishment>> readAllTypeEstablishment() {

		List<TypeEstablishment> typeEstablishment = typeEstablishmentService.readAllTypeEstablishment();
		return new ResponseEntity<>(typeEstablishment, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{typeEstablishmentId}")
	public ResponseEntity<TypeEstablishment> getByTypeEstablishmentId(@PathVariable("typeEstablishmentId") Long typeEstablishmentId) {

		if (!typeEstablishmentService.existsByTypeEstablishmentId(typeEstablishmentId))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		TypeEstablishment typeEstablishment = typeEstablishmentService.getOneTypeEstablishment(typeEstablishmentId).get();
		return new ResponseEntity<>(typeEstablishment, HttpStatus.OK);

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
	public ResponseEntity<?> createTypeEstablishment(@RequestBody @Validated TypeEstablishmentDto typeEstablishmentDto) {

		if (StringUtils.isBlank(typeEstablishmentDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (typeEstablishmentService.existsByTypeEstablishmentName(typeEstablishmentDto.getName()))
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		TypeEstablishment typeEstablishment = new TypeEstablishment();
		typeEstablishment.setName(typeEstablishmentDto.getName());
		typeEstablishmentService.createTypeEstablishment(typeEstablishment);
		return new ResponseEntity<>(new Message("Establecimiento creado"), HttpStatus.OK);

	}

	@PutMapping("/{typeEstablishmentId}")
	public ResponseEntity<?> updateTypeEstablishment(@PathVariable("typeEstablishmentId") Long typeEstablishmentId,
			@RequestBody TypeEstablishmentDto typeEstablishmentDto) {

		if (!typeEstablishmentService.existsByTypeEstablishmentId(typeEstablishmentId))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (typeEstablishmentService.existsByTypeEstablishmentName(typeEstablishmentDto.getName())
				&& typeEstablishmentService.findByTypeEstablishmentName(typeEstablishmentDto.getName()).get().getId() != typeEstablishmentId)
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(typeEstablishmentDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		TypeEstablishment typeEstablishment = typeEstablishmentService.getOneTypeEstablishment(typeEstablishmentId).get();
		typeEstablishment.setName(typeEstablishmentDto.getName());
		typeEstablishmentService.updateTypeEstablishment(typeEstablishment);
		return new ResponseEntity<>(new Message("Tipo de establecimiento actualizado"), HttpStatus.OK);

	}

	@DeleteMapping("/{typeEstablishmentId}")
	public ResponseEntity<?> deleteTypeEstablishment(@PathVariable("typeEstablishmentId") Long typeEstablishmentId) {

		if (!typeEstablishmentService.existsByTypeEstablishmentId(typeEstablishmentId))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		typeEstablishmentService.deleteTypeEstablishment(typeEstablishmentId);
		return new ResponseEntity<>(new Message("Tipo de establecimiento borrado"), HttpStatus.OK);

	}
}
