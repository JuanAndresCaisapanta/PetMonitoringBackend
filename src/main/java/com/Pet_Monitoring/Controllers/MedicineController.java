package com.Pet_Monitoring.Controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Dto.PetDto;
import com.Pet_Monitoring.Dto.MedicineDto;
import com.Pet_Monitoring.Entities.Medicine;
import com.Pet_Monitoring.Security.Dto.UserDto;
import com.Pet_Monitoring.Services.MedicineService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/medicine")
@CrossOrigin
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("pet/{id}")
	public ResponseEntity<?> getByPetId(@PathVariable("id") int id) {
		List<Medicine> medicine = medicineService.findAllByPetId(id);
		return new ResponseEntity<>(medicine, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> create(@Valid @ModelAttribute MedicineDto medicineDto, BindingResult bindingResult,
			@RequestParam(required = false, value = "image") MultipartFile image) throws IOException {
		
		Medicine medicine = new Medicine();
		if (image == null) {
			medicine.setImage(Util.extractBytes("src//main//resources//static//images//medicine-profile.png"));
		} else {
			byte[] bytesImg = image.getBytes();
			medicine.setImage(bytesImg);
		}
		medicine.setName(medicineDto.getName());
		medicine.setManufacturer(medicineDto.getManufacturer());
		medicine.setBatch(medicineDto.getBatch());
		medicine.setApplicator(medicineDto.getApplicator());
		medicine.setDescription(medicineDto.getDescription());
		medicine.setProduction_date(medicineDto.getProduction_date());
		medicine.setExpiration_date(medicineDto.getExpiration_date());
		medicine.setApplication_date(medicineDto.getApplication_date());
		medicine.setCreate_date(Util.dateNow());
		medicine.setTypeMedicine(medicineDto.getTypeMedicine());
		medicine.setPet(medicineDto.getPet());
		medicineService.create(medicine);
		return new ResponseEntity<>(new Message("Medicina creada"), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @ModelAttribute MedicineDto medicineDto,
			@RequestParam(required=false,value="image") MultipartFile image)
			throws IOException {

		if (!medicineService.existsById(id))
			return new ResponseEntity<>(new Message("no existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(medicineDto.getManufacturer()))
			return new ResponseEntity<>(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Medicine medicine = medicineService.getOne(id).get();
		medicine.setName(medicineDto.getName());
		medicine.setManufacturer(medicineDto.getManufacturer());
		medicine.setBatch(medicineDto.getBatch());
		medicine.setApplicator(medicineDto.getApplicator());
		medicine.setProduction_date(medicineDto.getProduction_date());
		medicine.setExpiration_date(medicineDto.getExpiration_date());
		medicine.setApplication_date(medicineDto.getApplication_date());
		if(image!=null) {
			byte[] bytesImg = image.getBytes();
			medicine.setImage(bytesImg);
		}
		medicine.setUpdate_date(Util.dateNow());
		medicine.setTypeMedicine(medicineDto.getTypeMedicine());
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
}
