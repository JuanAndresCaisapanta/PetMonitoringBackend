package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Dto.FullName;
import com.Pet_Monitoring.Entities.Medicine;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Repositories.MedicineRepository;

@Service
@Transactional
public class MedicineService {

	@Autowired
	MedicineRepository medicineRepository;

	public List<Medicine> readAllMedicine() {
		return (List<Medicine>) medicineRepository.findMedicineByOrderByIdAsc();
	}

	public void createMedicine(Medicine vacunas) {
		medicineRepository.save(vacunas);
	}

	public void updateMedicine(Medicine vacunas) {
		medicineRepository.save(vacunas);
	}

	public void deleteMedicine(Long medicine_id) {
		medicineRepository.deleteById(medicine_id);
	}

	public List<Medicine> findAllByPetId(Long pet_id) {
		return medicineRepository.findAllByPetId(pet_id);
	}

	public Optional<Medicine> getOneMedicine(Long medicine_id) {
		return medicineRepository.findById(medicine_id);
	}

	public boolean existsByMedicineId(Long medicine_id) {
		return medicineRepository.existsById(medicine_id);
	}
	
	public List<Pet> getMedicinePets(Long medicineType_id, String medicine_fullName, Long user_id) {
		return medicineRepository.getMedicinePets(medicineType_id, medicine_fullName, user_id);
	}

	public List<FullName> getMedicineFullNames(Long user_id) {
		return medicineRepository.getMedicineFullNames(user_id);
	}

}
