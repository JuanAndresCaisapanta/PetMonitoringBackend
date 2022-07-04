package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.Breed;
import com.Pet_Monitoring.Repositories.BreedRepository;

@Service
@Transactional
public class BreedService {

	@Autowired
	BreedRepository breedRepository;

	public List<Breed> readAllBreed() {
		return (List<Breed>) breedRepository.findAll();
	}

	public void createBreed(Breed breed) {
		breedRepository.save(breed);
	}

	public void updateBreed(Breed breed) {
		breedRepository.save(breed);
	}

	public void deleteBreed(Long breedId) {
		breedRepository.deleteById(breedId);
	}

	public Optional<Breed> getOneBreed(Long breedId) {
		return breedRepository.findById(breedId);
	}

	public boolean existsByBreedId(Long breedId) {
		return breedRepository.existsById(breedId);
	}

	public boolean existsByBreedName(String breedName) {
		return breedRepository.existsByName(breedName);
	}

	public Optional<Breed> findByBreedName(String breedName) {
		return breedRepository.findByName(breedName);
	}
	
	public List<Breed> findAllBySpeciesId(Long speciesId) {
		return breedRepository.findAllBySpeciesId(speciesId);
	}

}
