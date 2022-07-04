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
import com.Pet_Monitoring.Dto.TypeMedicineDto;
import com.Pet_Monitoring.Entities.TypeMedicine;
import com.Pet_Monitoring.Services.TypeMedicineService;

@RestController
@RequestMapping("/typeMedicine")
@CrossOrigin
public class TypeMedicineController {

	@Autowired
	TypeMedicineService typeMedicineService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<TypeMedicine>> readAllTypeMedicine() {

		List<TypeMedicine> typeMedicine = typeMedicineService.readAllTypeMedicine();
		return new ResponseEntity<>(typeMedicine, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{typeMedicineId}")
	public ResponseEntity<TypeMedicine> getByTypeMedicineId(@PathVariable("typeMedicineId") Long typeMedicineId) {

		if (!typeMedicineService.existsByTypeMedicineId(typeMedicineId))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		TypeMedicine typeMedicine = typeMedicineService.getOneTypeMedicine(typeMedicineId).get();
		return new ResponseEntity<>(typeMedicine, HttpStatus.OK);

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
	public ResponseEntity<?> createTypeMedicine(@RequestBody @Validated TypeMedicineDto typeMedicineDto) {

		if (StringUtils.isBlank(typeMedicineDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (typeMedicineService.existsByTypeMedicineName(typeMedicineDto.getName()))
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		TypeMedicine typeMedicine = new TypeMedicine();
		typeMedicine.setName(typeMedicineDto.getName());
		typeMedicineService.createTypeMedicine(typeMedicine);
		return new ResponseEntity<>(new Message("Tipo de medicina creada"), HttpStatus.OK);

	}

	@PutMapping("{typeMedicineId}")
	public ResponseEntity<?> updateTypeMedicine(@PathVariable("typeMedicineId") Long typeMedicineId, @RequestBody TypeMedicine typeMedicineDto) {

		if (!typeMedicineService.existsByTypeMedicineId(typeMedicineId))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (typeMedicineService.existsByTypeMedicineName(typeMedicineDto.getName())
				&& typeMedicineService.findByTypeMedicineName(typeMedicineDto.getName()).get().getId() != typeMedicineId)
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(typeMedicineDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		TypeMedicine typeMedicine = typeMedicineService.getOneTypeMedicine(typeMedicineId).get();
		typeMedicine.setName(typeMedicineDto.getName());
		typeMedicineService.updateTypeMedicine(typeMedicine);
		return new ResponseEntity<>(new Message("Tipo de medicina actualizada"), HttpStatus.OK);

	}

	@DeleteMapping("{typeMedicineId}")
	public ResponseEntity<?> delete(@PathVariable("typeMedicineId") Long typeMedicineId) {

		if (!typeMedicineService.existsByTypeMedicineId(typeMedicineId))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		typeMedicineService.deleteTypeMedicine(typeMedicineId);
		return new ResponseEntity<>(new Message("Tipo de medicina borrada"), HttpStatus.OK);

	}
}
