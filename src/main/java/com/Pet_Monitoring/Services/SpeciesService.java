package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
		return (List<Species>) speciesRepository.findByOrderByIdDesc();
	}
	
	public void createSpecies(@Valid @RequestBody Species species) {
		speciesRepository.save(species);
	}

	public void updateSpecies(@Valid @RequestBody Species species) {
		speciesRepository.save(species);
	}

	public void deleteSpecies(Long species_id) {
		speciesRepository.deleteById(species_id);
	}

	public Optional<Species> getOneSpecies(Long species_id) {
		return speciesRepository.findById(species_id);
	}

	public boolean existsBySpeciesId(Long species_id) {
		return speciesRepository.existsById(species_id);
	}
	
	public boolean existsBySpeciesName(String species_name) {
		return speciesRepository.existsByName(species_name);
	}

	public Optional<Species> findBySpeciesName(String species_name) {
		return speciesRepository.findByName(species_name);
	}
	
	public Optional<Species>findTopByOrderBySpeciesIdDesc(){
		return speciesRepository.findTopByOrderByIdDesc();
	}

}
