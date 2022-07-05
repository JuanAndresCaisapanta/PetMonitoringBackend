package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Repositories.PetRepository;

@Service
@Transactional
public class PetService {

	@Autowired
	PetRepository petRepository;

	public List<Pet> readAllPet() {
		return (List<Pet>) petRepository.findAll();
	}

	public void createPet(Pet pet) {
		petRepository.save(pet);
	}

	public void updatePet(Pet pet) {
		petRepository.save(pet);
	}

	public void deletePet(Long pet_id) {
		petRepository.deleteById(pet_id);
	}

	public Optional<Pet> getOnePet(Long pet_id) {
		return petRepository.findById(pet_id);
	}

	public boolean existsByPetId(Long pet_id) {
		return petRepository.existsById(pet_id);
	}

}
