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

import com.Pet_Monitoring.Dto.ProfessionDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Entities.Profession;
import com.Pet_Monitoring.Services.ProfessionService;

@RestController
@RequestMapping("/profession")
@CrossOrigin
public class ProfessionController {

	@Autowired
	ProfessionService professionService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Profession>> readAllProfession() {

		List<Profession> profession = professionService.readAllProfession();
		return new ResponseEntity<>(profession, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{professionId}")
	public ResponseEntity<Profession> getByProfessionId(@PathVariable("professionId") Long professionId) {

		if (!professionService.existsByProfessionId(professionId))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Profession profession = professionService.getOneProfession(professionId).get();
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
	public ResponseEntity<?> createProfession(@RequestBody @Validated ProfessionDto professionDto) {

		if (StringUtils.isBlank(professionDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (professionService.existsByProfessionName(professionDto.getName()))
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Profession profession = new Profession();
		profession.setName(professionDto.getName());
		professionService.createProfession(profession);
		return new ResponseEntity<>(new Message("Profesión creada"), HttpStatus.OK);

	}

	@PutMapping("{professionId}")
	public ResponseEntity<?> updateProfession(@PathVariable("professionId") Long professionId, @RequestBody ProfessionDto professionDto) {

		if (!professionService.existsByProfessionId(professionId))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (professionService.existsByProfessionName(professionDto.getName())
				&& professionService.findByProfessionName(professionDto.getName()).get().getId() != professionId)
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(professionDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Profession profession = professionService.getOneProfession(professionId).get();
		profession.setName(professionDto.getName());
		professionService.updateProfession(profession);
		return new ResponseEntity<>(new Message("Profesión actualizada"), HttpStatus.OK);

	}

	@DeleteMapping("{professionId}")
	public ResponseEntity<?> deleteProfession(@PathVariable("professionId") Long professionId) {

		if (!professionService.existsByProfessionId(professionId))
			return new ResponseEntity<>(new Message("no existe"), HttpStatus.NOT_FOUND);
		professionService.deleteProfession(professionId);
		return new ResponseEntity<>(new Message("Profesión borrada"), HttpStatus.OK);

	}

}
