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

	public List<TypeMedicine> read() {
		return (List<TypeMedicine>) typeMedicineRepository.findAll();
	}

	public void create(TypeMedicine typeMedicine) {
		typeMedicineRepository.save(typeMedicine);
	}

	public void update(TypeMedicine typeMedicine) {
		typeMedicineRepository.save(typeMedicine);
	}

	public void delete(int id) {
		typeMedicineRepository.deleteById(id);
	}

	public Optional<TypeMedicine> getOne(int id) {
		return typeMedicineRepository.findById(id);
	}

	public boolean existsById(int id) {
		return typeMedicineRepository.existsById(id);
	}
}
