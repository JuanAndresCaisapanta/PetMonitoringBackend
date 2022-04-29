package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.Pet_Monitoring.Entities.Professional;
import com.Pet_Monitoring.Repositories.ProfessionRepository;

@Service
@Transactional
public class ProfessionService {

	@Autowired
	ProfessionRepository especialidadesRepository;

	public List<Professional> lista() {
		return (List<Professional>) especialidadesRepository.findAll();
	}

	public Optional<Professional> getOne(int id) {
		return especialidadesRepository.findById(id);
	}

	public Optional<Professional> getByNombre(String nombre) {
		return especialidadesRepository.findByNombre(nombre);
	}

	public void guardar(@Valid @RequestBody Professional especialidades) {
		especialidadesRepository.save(especialidades);
	}

	public void actualizar(@Valid @RequestBody Professional especialidades) {
		especialidadesRepository.save(especialidades);
	}

	public void eliminar(int id) {
		especialidadesRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return especialidadesRepository.existsById(id);
	}

	public boolean existsByNombre(String nombre) {
		return especialidadesRepository.existsByNombre(nombre);
	}

}
