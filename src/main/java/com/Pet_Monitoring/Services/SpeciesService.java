package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.Pet_Monitoring.Entities.Species;
import com.Pet_Monitoring.Repositories.SpeciesRepository;

@Service
@Transactional
public class SpeciesService {
	
	@Autowired
	SpeciesRepository speciesRepository;

	public List<Species> read() {
		return (List<Species>) speciesRepository.findAll();
	}
	
	public void create(@Valid @RequestBody Species species) {
		speciesRepository.save(species);
	}

	public void update(@Valid @RequestBody Species species) {
		speciesRepository.save(species);
	}

	public void delete(int id) {
		speciesRepository.deleteById(id);
	}

	public Optional<Species> getOne(int id) {
		return speciesRepository.findById(id);
	}

	public boolean existsById(int id) {
		return speciesRepository.existsById(id);
	}
	
	public boolean existsByName(String name) {
		return speciesRepository.existsByName(name);
	}

	public Optional<Species> findByName(String name) {
		return speciesRepository.findByName(name);
	}

}
