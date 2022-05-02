package com.Pet_Monitoring.Controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.ProfessionDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Profession;
import com.Pet_Monitoring.Services.ProfessionService;

@RestController
@RequestMapping("/profession")
public class ProfessionController {

	@Autowired
	ProfessionService professionService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Profession>> read() {

		List<Profession> profession = professionService.read();
		return new ResponseEntity<>(profession, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{id}")
	public ResponseEntity<Profession> getById(@PathVariable("id") int id) {

		if (!professionService.existsById(id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Profession profession = professionService.getOne(id).get();
		return new ResponseEntity<>(profession, HttpStatus.OK);

	}

	/*
	 * @SuppressWarnings({ "unchecked", "rawtypes" })
	 * 
	 * @GetMapping("/listanombre/{nombre}") public ResponseEntity<Professional>
	 * getByNombre(@PathVariable("nombre") String nombre) { if
	 * (!professionService.existsByNombre(nombre)) return new ResponseEntity(new
	 * Message("no existe"), HttpStatus.NOT_FOUND); Professional especialidad =
	 * professionService.getByNombre(nombre).get(); return new
	 * ResponseEntity<>(especialidad, HttpStatus.OK); }
	 */

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> create(@RequestBody @Validated ProfessionDto professionDto) {

		if (StringUtils.isBlank(professionDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (professionService.existsByName(professionDto.getName()))
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Profession profession = new Profession();
		profession.setName(professionDto.getName());
		professionService.create(profession);
		return new ResponseEntity<>(new Message("Profesión creada"), HttpStatus.OK);

	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProfessionDto professionDto) {

		if (!professionService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (professionService.existsByName(professionDto.getName())
				&& professionService.findByName(professionDto.getName()).get().getId() != id)
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(professionDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Profession profession = professionService.getOne(id).get();
		profession.setName(professionDto.getName());
		professionService.update(profession);
		return new ResponseEntity<>(new Message("Profesión actualizada"), HttpStatus.OK);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!professionService.existsById(id))
			return new ResponseEntity<>(new Message("no existe"), HttpStatus.NOT_FOUND);
		professionService.delete(id);
		return new ResponseEntity<>(new Message("Profesión borrada"), HttpStatus.OK);

	}

}
