package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.Medicine;
import com.Pet_Monitoring.Repositories.MedicineRepository;

@Service
@Transactional
public class MedicineService {

	@Autowired
	MedicineRepository medicineRepository;

	public List<Medicine> readAllMedicine() {
		return (List<Medicine>) medicineRepository.findAll();
	}

	public void createMedicine(Medicine vacunas) {
		medicineRepository.save(vacunas);
	}

	public void updateMedicine(Medicine vacunas) {
		medicineRepository.save(vacunas);
	}

	public void deleteMedicine(Long medicineId) {
		medicineRepository.deleteById(medicineId);
	}

	public List<Medicine> findAllByPetId(Long petId) {
		return medicineRepository.findAllByPetId(petId);
	}

	public Optional<Medicine> getOneMedicine(Long medicineId) {
		return medicineRepository.findById(medicineId);
	}

	public boolean existsByMedicineId(Long medicineId) {
		return medicineRepository.existsById(medicineId);
	}

}
