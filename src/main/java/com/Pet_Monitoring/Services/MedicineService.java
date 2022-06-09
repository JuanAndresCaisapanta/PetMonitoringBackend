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

	public List<Medicine> read() {
		return (List<Medicine>) medicineRepository.findAll();
	}

	public void create(Medicine vacunas) {
		medicineRepository.save(vacunas);
	}

	public void update(Medicine vacunas) {
		medicineRepository.save(vacunas);
	}

	public void delete(int id) {
		medicineRepository.deleteById(id);
	}

	public List<Medicine> findAllByPetId(int id) {
		return medicineRepository.findAllByPetId(id);
	}

	public Optional<Medicine> getOne(int id) {
		return medicineRepository.findById(id);
	}

	public boolean existsById(int id) {
		return medicineRepository.existsById(id);
	}

}
