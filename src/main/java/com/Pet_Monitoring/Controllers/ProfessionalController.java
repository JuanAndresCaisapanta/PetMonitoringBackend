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
import com.Pet_Monitoring.Entities.Medicine;
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
	public ResponseEntity<List<Professional>> read() {

		List<Professional> professional = professionalService.read();
		return new ResponseEntity<>(professional, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{id}")
	public ResponseEntity<Professional> getById(@PathVariable("id") int id) {

		if (!professionalService.existsById(id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Professional professional = professionalService.getOne(id).get();
		return new ResponseEntity<>(professional, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("pet/{id}")
	public ResponseEntity<?> getByPetId(@PathVariable("id") int id) {
		List<Professional> professionals = professionalService.findAllByPetId(id);
		return new ResponseEntity<>(professionals, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> create(@RequestBody @Validated ProfessionalDto professionalDto) {

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
		professionalService.create(professional);
		return new ResponseEntity<>(new Message("Profesional creado"), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProfessionalDto professionalDto) {

		if (!professionalService.existsById(id))
			return new ResponseEntity<>(new Message("El profesional no existe"), HttpStatus.NOT_FOUND);
		// if (profesionalesService.existsByNombre(professionalDto.getNombre())
		// &&
		// profesionalesService.getByNombre(professionalDto.getNombre()).get().getId()
		// != id)
		// return new ResponseEntity<>(new Mensaje("ese nombre ya existe"),
		// HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(professionalDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Professional professional = professionalService.getOne(id).get();
		professional.setName(professionalDto.getName());
		professional.setLast_name(professionalDto.getLast_name());
		professional.setAddress(professionalDto.getAddress());
		professional.setEmail(professionalDto.getEmail());
		professional.setCell_phone(professionalDto.getCell_phone());
		professional.setUpdate_date(Util.dateNow());
		professional.setProfession(professionalDto.getProfession());
		professionalService.update(professional);
		return new ResponseEntity<>(new Message("Profesional actualizado"), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!professionalService.existsById(id))
			return new ResponseEntity<>(new Message("El profesional no existe"), HttpStatus.NOT_FOUND);
		professionalService.delete(id);
		return new ResponseEntity<>(new Message("Profesional borrado"), HttpStatus.OK);

	}

	@PostMapping("/email")
	public ResponseEntity<?> sendEmail(@RequestBody @Validated EmailDto emailDto) {
		professionalService.sendEmail(emailDto.getFromEmail(), emailDto.getToEmail(), emailDto.getSubject(),
				emailDto.getBody());
		return new ResponseEntity<>(new Message("Email enviado"), HttpStatus.OK);

	}

}
