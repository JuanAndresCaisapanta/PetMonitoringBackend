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

	public List<Pet> read() {
		return (List<Pet>) petRepository.findAll();
	}

	public void create(Pet pet) {
		petRepository.save(pet);
	}

	public void update(Pet pet) {
		petRepository.save(pet);
	}

	public void delete(int id) {
		petRepository.deleteById(id);
	}

	public Optional<Pet> getOne(int id) {
		return petRepository.findById(id);
	}

	public boolean existsById(int id) {
		return petRepository.existsById(id);
	}

}
