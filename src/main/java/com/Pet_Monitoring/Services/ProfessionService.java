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

	public List<Profession> readAllProfession() {
		return (List<Profession>) professionRepository.findAll();
	}

	public void createProfession(@Valid @RequestBody Profession profession) {
		professionRepository.save(profession);
	}

	public void updateProfession(@Valid @RequestBody Profession profession) {
		professionRepository.save(profession);
	}

	public void deleteProfession(Long professionId) {
		professionRepository.deleteById(professionId);
	}
	
	public Optional<Profession> getOneProfession(Long professionId) {
		return professionRepository.findById(professionId);
	}

	public boolean existsByProfessionId(Long professionId) {
		return professionRepository.existsById(professionId);
	}
	
	public boolean existsByProfessionName(String professionName) {
		return professionRepository.existsByName(professionName);
	}

	public Optional<Profession> findByProfessionName(String professionName) {
		return professionRepository.findByName(professionName);
	}
	
}
