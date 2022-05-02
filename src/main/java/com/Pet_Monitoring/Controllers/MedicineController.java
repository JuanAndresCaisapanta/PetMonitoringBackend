package com.Pet_Monitoring.Controllers;

import java.text.ParseException;
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
import com.Pet_Monitoring.Dto.MedicineDto;
import com.Pet_Monitoring.Entities.Medicine;
import com.Pet_Monitoring.Services.MedicineService;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	MedicineService medicineService;

	@GetMapping
	public ResponseEntity<List<Medicine>> read() {

		List<Medicine> medicine = medicineService.read();
		return new ResponseEntity<>(medicine, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{id}")
	public ResponseEntity<Medicine> getById(@PathVariable("id") int id) {

		if (!medicineService.existsById(id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Medicine medicine = medicineService.getOne(id).get();
		return new ResponseEntity<>(medicine, HttpStatus.OK);

	}

	/*
	 * @SuppressWarnings({ "unchecked", "rawtypes" })
	 * 
	 * @GetMapping("/listanombre/{nombre}") public ResponseEntity<Medicine>
	 * getByNombre(@PathVariable("nombre") String nombre) { if
	 * (!medicineService.existsByNombre(nombre)) return new ResponseEntity(new
	 * Message("no existe"), HttpStatus.NOT_FOUND); Medicine vacuna =
	 * medicineService.getByNombre(nombre).get(); return new
	 * ResponseEntity<>(vacuna, HttpStatus.OK); }
	 */

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> create(@RequestBody @Validated MedicineDto medicineDto) throws ParseException {

		if (StringUtils.isBlank(medicineDto.getManufacturer()))
			return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Medicine medicine = new Medicine();
		medicine.setManufacturer(medicineDto.getManufacturer());
		medicine.setBatch(medicineDto.getBatch());
		medicine.setApplicator(medicineDto.getApplicator());
		medicine.setProduction_date(medicineDto.getProduction_date());
		medicine.setExpiration_date(medicineDto.getExpiration_date());
		medicine.setApplication_date(medicineDto.getApplication_date());
		medicine.setCreate_date(medicineDto.getCreate_date());
		medicine.setUpdate_date(medicineDto.getUpdate_date());
		medicine.setTypeMedicine(medicineDto.getTypeMedicine());
		medicine.setPet(medicineDto.getPet());
		medicineService.create(medicine);
		return new ResponseEntity<>(new Message("Medicina creada"), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody MedicineDto medicineDto)
			throws ParseException {

		if (!medicineService.existsById(id))
			return new ResponseEntity<>(new Message("no existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(medicineDto.getManufacturer()))
			return new ResponseEntity<>(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Medicine medicine = medicineService.getOne(id).get();
		medicine.setManufacturer(medicineDto.getManufacturer());
		medicine.setBatch(medicineDto.getBatch());
		medicine.setApplicator(medicineDto.getApplicator());
		medicine.setProduction_date(medicineDto.getProduction_date());
		medicine.setExpiration_date(medicineDto.getExpiration_date());
		medicine.setApplication_date(medicineDto.getApplication_date());
		// medicine.setCreate_date(medicineDto.getCreate_date());
		medicine.setUpdate_date(medicineDto.getUpdate_date());
		medicine.setTypeMedicine(medicineDto.getTypeMedicine());
		medicine.setPet(medicineDto.getPet());
		medicineService.update(medicine);
		return new ResponseEntity<>(new Message("Medicina actualizada"), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		
		if (!medicineService.existsById(id))
			return new ResponseEntity<>(new Message("no existe"), HttpStatus.NOT_FOUND);
		medicineService.delete(id);
		return new ResponseEntity<>(new Message("Medicina borrada"), HttpStatus.OK);
		
	}

	/*
	 * public Date fecha(Date dia) throws ParseException { DateFormat formato = new
	 * SimpleDateFormat("dd/MM/yyyy"); Date diaFormateado =
	 * formato.parse(formato.format(dia)); return diaFormateado; }
	 */

}
