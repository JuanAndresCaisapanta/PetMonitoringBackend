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
	MedicineRepository vacunasRepository;

	public List<Medicine> lista() {
		return (List<Medicine>) vacunasRepository.findAll();
	}

	public Optional<Medicine> getOne(int id) {
		return vacunasRepository.findById(id);
	}

	public Optional<Medicine> getByNombre(String nombre) {
		return vacunasRepository.findByFabricador(nombre);
	}

	public void guardar(Medicine vacunas) {
		vacunasRepository.save(vacunas);
	}

	public void actualizar(Medicine vacunas) {
		vacunasRepository.save(vacunas);
	}

	public void eliminar(int id) {
		vacunasRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return vacunasRepository.existsById(id);
	}

	public boolean existsByNombre(String nombre) {
		return vacunasRepository.existsByFabricador(nombre);
	}

}
