package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.Establishment;
import com.Pet_Monitoring.Repositories.ProfessionalRepository;

@Service
@Transactional
public class ProfessionalService {

	@Autowired
	ProfessionalRepository profesionalesRepository;

	public List<Establishment> lista() {
		return (List<Establishment>) profesionalesRepository.findAll();
	}

	public Optional<Establishment> getOne(int id) {
		return profesionalesRepository.findById(id);
	}

	public Optional<Establishment> getByNombre(String nombre) {
		return profesionalesRepository.findByNombre(nombre);
	}

	public void guardar(Establishment profesionales) {
		profesionalesRepository.save(profesionales);
	}

	public void actualizar(Establishment profesionales) {
		profesionalesRepository.save(profesionales);
	}

	public void eliminar(int id) {
		profesionalesRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return profesionalesRepository.existsById(id);
	}

	public boolean existsByNombre(String nombre) {
		return profesionalesRepository.existsByNombre(nombre);
		
		
	}
	
	
	
	
}
