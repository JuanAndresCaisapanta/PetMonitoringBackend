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

	public List<Breed> read() {
		return (List<Breed>) breedRepository.findAll();
	}

	public void create(Breed breed) {
		breedRepository.save(breed);
	}

	public void update(Breed breed) {
		breedRepository.save(breed);
	}

	public void delete(Long id) {
		breedRepository.deleteById(id);
	}

	public Optional<Breed> getOne(Long id) {
		return breedRepository.findById(id);
	}

	public boolean existsById(Long id) {
		return breedRepository.existsById(id);
	}

	public boolean existsByName(String name) {
		return breedRepository.existsByName(name);
	}

	public Optional<Breed> findByName(String name) {
		return breedRepository.findByName(name);
	}

}
