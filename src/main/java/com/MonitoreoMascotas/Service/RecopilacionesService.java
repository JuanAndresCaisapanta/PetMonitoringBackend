package com.MonitoreoMascotas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MonitoreoMascotas.Entity.Recopilaciones;
import com.MonitoreoMascotas.Repository.RecopilacionesRepository;

@Service
@Transactional
public class RecopilacionesService {

	@Autowired
	RecopilacionesRepository recopilacionesRepository;

	public List<Recopilaciones> lista() {
		return (List<Recopilaciones>) recopilacionesRepository.findAll();
	}

	public void guardar(Recopilaciones recopilaciones) {
		recopilacionesRepository.save(recopilaciones);
	}

}
