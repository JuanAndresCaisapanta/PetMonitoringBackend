package com.MonitoreoMascotas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MonitoreoMascotas.Entity.Dispositivos;
import com.MonitoreoMascotas.Repository.DispositivosRepository;

@Service
@Transactional
public class DispositivosService {
	
	@Autowired
	DispositivosRepository dispositivosRepository;

	public List<Dispositivos> lista() {
		return (List<Dispositivos>) dispositivosRepository.findAll();
	}

	public Optional<Dispositivos> getOne(int id) {
		return dispositivosRepository.findById(id);
	}

	public Optional<Dispositivos> getByNombre(String nombre) {
		return dispositivosRepository.findByNombre(nombre);
	}

	public void guardar(Dispositivos dispositivos) {
		dispositivosRepository.save(dispositivos);
	}

	public void actualizar(Dispositivos dispositivos) {
		dispositivosRepository.save(dispositivos);
	}

	public void eliminar(int id) {
		dispositivosRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return dispositivosRepository.existsById(id);
	}

	public boolean existsByNombre(String nombre) {
		return dispositivosRepository.existsByNombre(nombre);
		
		
	}

}
