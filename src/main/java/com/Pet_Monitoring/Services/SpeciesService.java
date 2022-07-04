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

	public List<Species> readAllSpecies() {
		return (List<Species>) speciesRepository.findAll();
	}
	
	public void createSpecies(@Valid @RequestBody Species species) {
		speciesRepository.save(species);
	}

	public void updateSpecies(@Valid @RequestBody Species species) {
		speciesRepository.save(species);
	}

	public void deleteSpecies(Long speciesId) {
		speciesRepository.deleteById(speciesId);
	}

	public Optional<Species> getOneSpecies(Long speciesId) {
		return speciesRepository.findById(speciesId);
	}

	public boolean existsBySpeciesId(Long speciesId) {
		return speciesRepository.existsById(speciesId);
	}
	
	public boolean existsBySpeciesName(String speciesName) {
		return speciesRepository.existsByName(speciesName);
	}

	public Optional<Species> findBySpeciesName(String speciesName) {
		return speciesRepository.findByName(speciesName);
	}

}
