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
		return (List<Breed>) breedRepository.findByOrderByIdAsc();
	}

	public void createBreed(Breed breed) {
		breedRepository.save(breed);
	}

	public void updateBreed(Breed breed) {
		breedRepository.save(breed);
	}

	public void deleteBreed(Long breed_id) {
		breedRepository.deleteById(breed_id);
	}

	public Optional<Breed> getOneBreed(Long breed_id) {
		return breedRepository.findById(breed_id);
	}

	public boolean existsByBreedId(Long breed_id) {
		return breedRepository.existsById(breed_id);
	}

	public boolean existsByBreedName(String breed_name) {
		return breedRepository.existsByName(breed_name);
	}

	public Optional<Breed> findByBreedName(String breed_name) {
		return breedRepository.findByName(breed_name);
	}

	public List<Breed> findAllBySpeciesId(Long speciesId) {
		return breedRepository.findAllBySpeciesId(speciesId);
	}

}
