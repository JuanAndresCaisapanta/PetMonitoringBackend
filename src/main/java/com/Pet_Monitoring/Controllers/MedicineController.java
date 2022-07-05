package com.Pet_Monitoring.Controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Pet_Monitoring.Dto.Message;
import com.Pet_Monitoring.Dto.FullName;
import com.Pet_Monitoring.Dto.MedicineDto;
import com.Pet_Monitoring.Entities.Medicine;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Services.MedicineService;
import com.Pet_Monitoring.Utils.Util;

@RestController
@RequestMapping("/medicine")
@CrossOrigin
public class MedicineController {

	@Autowired
	MedicineService medicineService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Medicine>> readAllMedicine() {
		List<Medicine> medicine = medicineService.readAllMedicine();
		return new ResponseEntity<>(medicine, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{medicine_id}")
	public ResponseEntity<Medicine> getByMedicineId(@PathVariable("medicine_id") Long medicine_id) {
		if (!medicineService.existsByMedicineId(medicine_id))
			return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
		Medicine medicine = medicineService.getOneMedicine(medicine_id).get();
		return new ResponseEntity<>(medicine, HttpStatus.OK);
	}

	@GetMapping("pet/{pet_id}")
	public ResponseEntity<?> getByPetId(@PathVariable("pet_id") Long pet_id) {
		List<Medicine> medicine = medicineService.findAllByPetId(pet_id);
		return new ResponseEntity<>(medicine, HttpStatus.OK);
	}

	@GetMapping("pets/user/{user_id}")
	public ResponseEntity<?> getMedicineFullNames(@PathVariable("user_id") Long user_id) {
		List<FullName> fullName = medicineService.getMedicineFullNames(user_id);
		return new ResponseEntity<>(fullName, HttpStatus.OK);
	}

	@GetMapping("pets/{medicineType_id}/{medicine_fullName}/{user_id}")
	public ResponseEntity<List<Pet>> getEstablishmentPets(@PathVariable("medicineType_id") Long medicineType_id,
			@PathVariable("medicine_fullName") String medicine_fullName, @PathVariable("user_id") Long user_id) {
		List<Pet> pets = medicineService.getMedicinePets(medicineType_id, medicine_fullName, user_id);
		if (pets.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pets);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> createMedicine(@Valid @ModelAttribute MedicineDto medicineDto, BindingResult bindingResult,
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
		medicine.setMedicineType(medicineDto.getMedicineType());
		medicine.setPet(medicineDto.getPet());
		medicineService.createMedicine(medicine);
		return new ResponseEntity<>(new Message("Medicina creada"), HttpStatus.OK);
	}

	@PutMapping("/{medicine_id}")
	public ResponseEntity<?> updateMedicine(@PathVariable("medicine_id") Long medicine_id,
			@ModelAttribute MedicineDto medicineDto,
			@RequestParam(required = false, value = "image") MultipartFile image) throws IOException {
		if (!medicineService.existsByMedicineId(medicine_id))
			return new ResponseEntity<>(new Message("no existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(medicineDto.getManufacturer()))
			return new ResponseEntity<>(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Medicine medicine = medicineService.getOneMedicine(medicine_id).get();
		medicine.setName(medicineDto.getName());
		medicine.setManufacturer(medicineDto.getManufacturer());
		medicine.setBatch(medicineDto.getBatch());
		medicine.setApplicator(medicineDto.getApplicator());
		medicine.setProduction_date(medicineDto.getProduction_date());
		medicine.setExpiration_date(medicineDto.getExpiration_date());
		medicine.setApplication_date(medicineDto.getApplication_date());
		if (image != null) {
			byte[] bytesImg = image.getBytes();
			medicine.setImage(bytesImg);
		}
		medicine.setUpdate_date(Util.dateNow());
		medicine.setMedicineType(medicineDto.getMedicineType());
		medicineService.updateMedicine(medicine);
		return new ResponseEntity<>(new Message("Medicina actualizada"), HttpStatus.OK);
	}

	@DeleteMapping("/{medicine_id}")
	public ResponseEntity<?> deleteMedicine(@PathVariable("medicine_id") Long medicine_id) {
		if (!medicineService.existsByMedicineId(medicine_id))
			return new ResponseEntity<>(new Message("no existe"), HttpStatus.NOT_FOUND);
		medicineService.deleteMedicine(medicine_id);
		return new ResponseEntity<>(new Message("Medicina borrada"), HttpStatus.OK);
	}
}
