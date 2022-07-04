package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.TypeMedicine;
import com.Pet_Monitoring.Repositories.TypeMedicineRepository;

@Service
@Transactional
public class TypeMedicineService {
	
	@Autowired
	TypeMedicineRepository typeMedicineRepository;

	public List<TypeMedicine> readAllTypeMedicine() {
		return (List<TypeMedicine>) typeMedicineRepository.findAll();
	}

	public void createTypeMedicine(TypeMedicine typeMedicine) {
		typeMedicineRepository.save(typeMedicine);
	}

	public void updateTypeMedicine(TypeMedicine typeMedicine) {
		typeMedicineRepository.save(typeMedicine);
	}

	public void deleteTypeMedicine(Long typeMedicineId) {
		typeMedicineRepository.deleteById(typeMedicineId);
	}

	public Optional<TypeMedicine> getOneTypeMedicine(Long typeMedicineId) {
		return typeMedicineRepository.findById(typeMedicineId);
	}

	public boolean existsByTypeMedicineId(Long typeMedicineId) {
		return typeMedicineRepository.existsById(typeMedicineId);
	}
	
	public boolean existsByTypeMedicineName(String typeMedicineName) {
		return typeMedicineRepository.existsByName(typeMedicineName);
	}

	public Optional<TypeMedicine> findByTypeMedicineName(String typeMedicineName) {
		return typeMedicineRepository.findByName(typeMedicineName);
	}
}
