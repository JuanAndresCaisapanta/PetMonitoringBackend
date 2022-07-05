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

import com.Pet_Monitoring.Dto.EmailDto;
import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Dto.ProfessionalDto;
import com.Pet_Monitoring.Dto.FullName;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Entities.Professional;
import com.Pet_Monitoring.Services.ProfessionalService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/professional")
@CrossOrigin
public class ProfessionalController {

	@Autowired
	ProfessionalService professionalService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Professional>> readAllProfessional() {
		List<Professional> professional = professionalService.readAllProfessional();
		return new ResponseEntity<>(professional, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{professional_id}")
	public ResponseEntity<Professional> getByProfessionalId(@PathVariable("professional_id") Long professional_id) {
		if (!professionalService.existsByProfessionalId(professional_id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Professional professional = professionalService.getOneProfessional(professional_id).get();
		return new ResponseEntity<>(professional, HttpStatus.OK);
	}

	@GetMapping("pet/{pet_id}")
	public ResponseEntity<?> getByPetId(@PathVariable("pet_id") Long pet_id) {
		List<Professional> professionals = professionalService.findAllByPetId(pet_id);
		return new ResponseEntity<>(professionals, HttpStatus.OK);
	}

	@GetMapping("pets/user/{user_id}")
	public ResponseEntity<?> getProfessionalFullNames(@PathVariable("user_id") Long user_id) {
		List<FullName> fullName = professionalService.getProfessionalFullNames(user_id);
		return new ResponseEntity<>(fullName, HttpStatus.OK);
	}

	@GetMapping("pets/{profession_id}/{professional_fullName}/{user_id}")
	public ResponseEntity<List<Pet>> getProfessionalPets(@PathVariable("profession_id") Long profession_id,
			@PathVariable("professional_fullName") String professional_fullName,
			@PathVariable("user_id") Long user_id) {
		List<Pet> pets = professionalService.getProfessionalPets(profession_id, professional_fullName, user_id);
		if (pets.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pets);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> createProfessional(@RequestBody @Validated ProfessionalDto professionalDto) {
		if (StringUtils.isBlank(professionalDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		// if (professionalService.existsByNombre(professionalDto.getNombre()))
		// return new ResponseEntity<>(new Mensaje("ese nombre ya existe"),
		// HttpStatus.BAD_REQUEST);
		Professional professional = new Professional();
		professional.setName(professionalDto.getName());
		professional.setLast_name(professionalDto.getLast_name());
		professional.setAddress(professionalDto.getAddress());
		professional.setCell_phone(professionalDto.getCell_phone());
		professional.setEmail(professionalDto.getEmail());
		professional.setCreation_date(Util.dateNow());
		professional.setProfession(professionalDto.getProfession());
		professional.setPet(professionalDto.getPet());
		professionalService.createProfessional(professional);
		return new ResponseEntity<>(new Message("Profesional creado"), HttpStatus.OK);
	}

	@PutMapping("/{professional_id}")
	public ResponseEntity<?> updateProfessional(@PathVariable("professional_id") Long professional_id,
			@RequestBody ProfessionalDto professionalDto) {
		if (!professionalService.existsByProfessionalId(professional_id))
			return new ResponseEntity<>(new Message("El profesional no existe"), HttpStatus.NOT_FOUND);
		// if (profesionalesService.existsByNombre(professionalDto.getNombre())
		// &&
		// profesionalesService.getByNombre(professionalDto.getNombre()).get().getId()
		// != id)
		// return new ResponseEntity<>(new Mensaje("ese nombre ya existe"),
		// HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(professionalDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Professional professional = professionalService.getOneProfessional(professional_id).get();
		professional.setName(professionalDto.getName());
		professional.setLast_name(professionalDto.getLast_name());
		professional.setAddress(professionalDto.getAddress());
		professional.setEmail(professionalDto.getEmail());
		professional.setCell_phone(professionalDto.getCell_phone());
		professional.setUpdate_date(Util.dateNow());
		professional.setProfession(professionalDto.getProfession());
		professionalService.updateProfessional(professional);
		return new ResponseEntity<>(new Message("Profesional actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("/{professional_id}")
	public ResponseEntity<?> deleteProfessional(@PathVariable("professional_id") Long professional_id) {
		if (!professionalService.existsByProfessionalId(professional_id))
			return new ResponseEntity<>(new Message("El profesional no existe"), HttpStatus.NOT_FOUND);
		professionalService.deleteProfessional(professional_id);
		return new ResponseEntity<>(new Message("Profesional borrado"), HttpStatus.OK);
	}

	@PostMapping("/emailProfessional")
	public ResponseEntity<?> sendEmailProfessional(@RequestBody @Validated EmailDto emailDto) {
		professionalService.sendEmailProfessional(emailDto.getFrom_email(), emailDto.getTo_email(),
				emailDto.getSubject(), emailDto.getBody());
		return new ResponseEntity<>(new Message("Email enviado"), HttpStatus.OK);
	}

}
