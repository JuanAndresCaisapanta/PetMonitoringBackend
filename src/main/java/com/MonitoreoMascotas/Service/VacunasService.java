package com.MonitoreoMascotas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MonitoreoMascotas.Entity.Vacunas;
import com.MonitoreoMascotas.Repository.VacunasRepository;

@Service
@Transactional
public class VacunasService {
	
	@Autowired
	VacunasRepository vacunasRepository;

	public List<Vacunas> lista() {
		return (List<Vacunas>) vacunasRepository.findAll();
	}

	public Optional<Vacunas> getOne(int id) {
		return vacunasRepository.findById(id);
	}

	public Optional<Vacunas> getByNombre(String nombre) {
		return vacunasRepository.findByFabricador(nombre);
	}

	public void guardar(Vacunas vacunas) {
		vacunasRepository.save(vacunas);
	}

	public void actualizar(Vacunas vacunas) {
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
