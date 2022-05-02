package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.Pet_Monitoring.Entities.Profession;
import com.Pet_Monitoring.Repositories.ProfessionRepository;

@Service
@Transactional
public class ProfessionService {

	@Autowired
	ProfessionRepository professionRepository;

	public List<Profession> read() {
		return (List<Profession>) professionRepository.findAll();
	}

	public void create(@Valid @RequestBody Profession profession) {
		professionRepository.save(profession);
	}

	public void update(@Valid @RequestBody Profession profession) {
		professionRepository.save(profession);
	}

	public void delete(int id) {
		professionRepository.deleteById(id);
	}
	
	public Optional<Profession> getOne(int id) {
		return professionRepository.findById(id);
	}

	public boolean existsById(int id) {
		return professionRepository.existsById(id);
	}
	
	public boolean existsByName(String name) {
		return professionRepository.existsByName(name);
	}

	public Optional<Profession> findByName(String name) {
		return professionRepository.findByName(name);
	}
	
}
