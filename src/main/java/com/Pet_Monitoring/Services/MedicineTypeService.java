package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.MedicineType;
import com.Pet_Monitoring.Repositories.MedicineTypeRepository;

@Service
@Transactional
public class MedicineTypeService {
	
	@Autowired
	MedicineTypeRepository medicineTypeRepository;

	public List<MedicineType> readAllMedicineType() {
		return (List<MedicineType>) medicineTypeRepository.findByOrderByIdAsc();
	}

	public void createMedicineType(MedicineType medicineType) {
		medicineTypeRepository.save(medicineType);
	}

	public void updateMedicineType(MedicineType medicineType) {
		medicineTypeRepository.save(medicineType);
	}

	public void deleteMedicineType(Long medicineType_id) {
		medicineTypeRepository.deleteById(medicineType_id);
	}

	public Optional<MedicineType> getOneMedicineType(Long medicineType_id) {
		return medicineTypeRepository.findById(medicineType_id);
	}

	public boolean existsByMedicineTypeId(Long medicineType_id) {
		return medicineTypeRepository.existsById(medicineType_id);
	}
	
	public boolean existsByMedicineTypeName(String medicineType_name) {
		return medicineTypeRepository.existsByName(medicineType_name);
	}

	public Optional<MedicineType> findByMedicineTypeName(String medicineType_name) {
		return medicineTypeRepository.findByName(medicineType_name);
	}
}
