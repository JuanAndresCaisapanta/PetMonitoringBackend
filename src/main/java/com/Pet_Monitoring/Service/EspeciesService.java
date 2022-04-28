package com.Pet_Monitoring.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.Pet_Monitoring.Entity.Species;
import com.Pet_Monitoring.Repository.EspeciesRepository;

@Service
@Transactional
public class EspeciesService {
	
	@Autowired
	EspeciesRepository especiesRepository;

	public List<Species> lista() {
		return (List<Species>) especiesRepository.findAll();
	}

	public Optional<Species> getOne(int id) {
		return especiesRepository.findById(id);
	}

	public Optional<Species> getByNombre(String nombre) {
		return especiesRepository.findByNombre(nombre);
	}

	public void guardar(@Valid @RequestBody Species especies) {
		especiesRepository.save(especies);
	}

	public void actualizar(@Valid @RequestBody Species especies) {
		especiesRepository.save(especies);
	}

	public void eliminar(int id) {
		especiesRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return especiesRepository.existsById(id);
	}

	public boolean existsByNombre(String nombre) {
		return especiesRepository.existsByNombre(nombre);
	}
}
