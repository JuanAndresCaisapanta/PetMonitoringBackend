package com.MonitoreoMascotas.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.MonitoreoMascotas.Entity.Especies;
import com.MonitoreoMascotas.Repository.EspeciesRepository;

@Service
@Transactional
public class EspeciesService {
	
	@Autowired
	EspeciesRepository especiesRepository;

	public List<Especies> lista() {
		return (List<Especies>) especiesRepository.findAll();
	}

	public Optional<Especies> getOne(int id) {
		return especiesRepository.findById(id);
	}

	public Optional<Especies> getByNombre(String nombre) {
		return especiesRepository.findByNombre(nombre);
	}

	public void guardar(@Valid @RequestBody Especies especies) {
		especiesRepository.save(especies);
	}

	public void actualizar(@Valid @RequestBody Especies especies) {
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
