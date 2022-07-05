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
import com.Pet_Monitoring.Dto.MedicineTypeDto;
import com.Pet_Monitoring.Entities.MedicineType;
import com.Pet_Monitoring.Services.MedicineTypeService;

@RestController
@RequestMapping("/medicineType")
@CrossOrigin
public class MedicineTypeController {

	@Autowired
	MedicineTypeService medicineTypeService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<MedicineType>> readAllMedicineType() {
		List<MedicineType> medicineType = medicineTypeService.readAllMedicineType();
		return new ResponseEntity<>(medicineType, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{medicineType_id}")
	public ResponseEntity<MedicineType> getByMedicineTypeId(@PathVariable("medicineType_id") Long medicineType_id) {
		if (!medicineTypeService.existsByMedicineTypeId(medicineType_id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		MedicineType medicineType = medicineTypeService.getOneMedicineType(medicineType_id).get();
		return new ResponseEntity<>(medicineType, HttpStatus.OK);
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
	public ResponseEntity<?> createMedicineType(@RequestBody @Validated MedicineTypeDto medicineTypeDto) {
		if (StringUtils.isBlank(medicineTypeDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (medicineTypeService.existsByMedicineTypeName(medicineTypeDto.getName()))
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		MedicineType medicineType = new MedicineType();
		medicineType.setName(medicineTypeDto.getName());
		medicineTypeService.createMedicineType(medicineType);
		return new ResponseEntity<>(new Message("Tipo de medicina creada"), HttpStatus.OK);
	}

	@PutMapping("{medicineType_id}")
	public ResponseEntity<?> updateMedicineType(@PathVariable("medicineType_id") Long medicineType_id,
			@RequestBody MedicineType typeMedicineDto) {
		if (!medicineTypeService.existsByMedicineTypeId(medicineType_id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (medicineTypeService.existsByMedicineTypeName(typeMedicineDto.getName()) && medicineTypeService
				.findByMedicineTypeName(typeMedicineDto.getName()).get().getId() != medicineType_id)
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(typeMedicineDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		MedicineType medicineType = medicineTypeService.getOneMedicineType(medicineType_id).get();
		medicineType.setName(typeMedicineDto.getName());
		medicineTypeService.updateMedicineType(medicineType);
		return new ResponseEntity<>(new Message("Tipo de medicina actualizada"), HttpStatus.OK);
	}

	@DeleteMapping("{medicineType_id}")
	public ResponseEntity<?> deleteMedicineType(@PathVariable("medicineType_id") Long medicineType_id) {
		if (!medicineTypeService.existsByMedicineTypeId(medicineType_id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		medicineTypeService.deleteMedicineType(medicineType_id);
		return new ResponseEntity<>(new Message("Tipo de medicina borrada"), HttpStatus.OK);
	}
}
