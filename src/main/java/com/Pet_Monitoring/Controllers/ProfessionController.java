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
	@GetMapping("/{profession_id}")
	public ResponseEntity<Profession> getByProfessionId(@PathVariable("profession_id") Long profession_id) {
		if (!professionService.existsByProfessionId(profession_id))
			return new ResponseEntity(new Message("La profesión no existe"), HttpStatus.NOT_FOUND);
		Profession profession = professionService.getOneProfession(profession_id).get();
		return new ResponseEntity<>(profession, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> createProfession(@RequestBody @Validated ProfessionDto professionDto) {
		if (StringUtils.isBlank(professionDto.getName()))
			return new ResponseEntity<>(new Message("El nombre de la profesión es obligatorio"),
					HttpStatus.BAD_REQUEST);
		if (professionService.existsByProfessionName(professionDto.getName()))
			return new ResponseEntity<>(new Message("El nombre de la profesión ya existe"), HttpStatus.BAD_REQUEST);
		Profession profession = new Profession();
		profession.setName(professionDto.getName());
		professionService.createProfession(profession);
		return new ResponseEntity<>(new Message("Profesión creada"), HttpStatus.OK);
	}

	@PutMapping("{profession_id}")
	public ResponseEntity<?> updateProfession(@PathVariable("profession_id") Long profession_id,
			@RequestBody ProfessionDto professionDto) {
		if (!professionService.existsByProfessionId(profession_id))
			return new ResponseEntity<>(new Message("La profesión no existe"), HttpStatus.NOT_FOUND);
		if (professionService.existsByProfessionName(professionDto.getName())
				&& professionService.findByProfessionName(professionDto.getName()).get().getId() != profession_id)
			return new ResponseEntity<>(new Message("El nombre de la profesión ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(professionDto.getName()))
			return new ResponseEntity<>(new Message("El nombre de la profesión es obligatorio"),
					HttpStatus.BAD_REQUEST);
		Profession profession = professionService.getOneProfession(profession_id).get();
		profession.setName(professionDto.getName());
		professionService.updateProfession(profession);
		return new ResponseEntity<>(new Message("Profesión actualizada"), HttpStatus.OK);
	}

	@DeleteMapping("{profession_id}")
	public ResponseEntity<?> deleteProfession(@PathVariable("profession_id") Long profession_id) {
		if (!professionService.existsByProfessionId(profession_id))
			return new ResponseEntity<>(new Message("La profesión no existe"), HttpStatus.NOT_FOUND);
		try {
			professionService.deleteProfession(profession_id);
			return new ResponseEntity<>(new Message("Profesión borrada"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Message("Error al borrar la profesión"), HttpStatus.OK);
		}
	}

}
