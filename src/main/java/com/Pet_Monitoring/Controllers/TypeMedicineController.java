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

import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Dto.TypeMedicineDto;
import com.Pet_Monitoring.Entities.TypeMedicine;
import com.Pet_Monitoring.Services.TypeMedicineService;

@RestController
@RequestMapping("/typeMedicine")
public class TypeMedicineController {

	@Autowired
	TypeMedicineService typeMedicineService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<TypeMedicine>> read() {

		List<TypeMedicine> typeMedicine = typeMedicineService.read();
		return new ResponseEntity<>(typeMedicine, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{id}")
	public ResponseEntity<TypeMedicine> getById(@PathVariable("id") int id) {

		if (!typeMedicineService.existsById(id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		TypeMedicine typeMedicine = typeMedicineService.getOne(id).get();
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
	public ResponseEntity<?> create(@RequestBody @Validated TypeMedicineDto typeMedicineDto) {

		if (StringUtils.isBlank(typeMedicineDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (typeMedicineService.existsByName(typeMedicineDto.getName()))
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		TypeMedicine typeMedicine = new TypeMedicine();
		typeMedicine.setName(typeMedicineDto.getName());
		typeMedicineService.create(typeMedicine);
		return new ResponseEntity<>(new Message("Tipo de medicina creada"), HttpStatus.OK);

	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody TypeMedicine typeMedicineDto) {

		if (!typeMedicineService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		if (typeMedicineService.existsByName(typeMedicineDto.getName())
				&& typeMedicineService.findByName(typeMedicineDto.getName()).get().getId() != id)
			return new ResponseEntity<>(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(typeMedicineDto.getName()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		TypeMedicine typeMedicine = typeMedicineService.getOne(id).get();
		typeMedicine.setName(typeMedicineDto.getName());
		typeMedicineService.update(typeMedicine);
		return new ResponseEntity<>(new Message("Tipo de medicina actualizada"), HttpStatus.OK);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!typeMedicineService.existsById(id))
			return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
		typeMedicineService.delete(id);
		return new ResponseEntity<>(new Message("Tipo de medicina borrada"), HttpStatus.OK);

	}
}
