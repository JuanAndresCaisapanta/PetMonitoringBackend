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
		return (List<Profession>) professionRepository.findByOrderByIdAsc();
	}

	public void createProfession(@Valid @RequestBody Profession profession) {
		professionRepository.save(profession);
	}

	public void updateProfession(@Valid @RequestBody Profession profession) {
		professionRepository.save(profession);
	}

	public void deleteProfession(Long profession_id) {
		professionRepository.deleteById(profession_id);
	}
	
	public Optional<Profession> getOneProfession(Long profession_id) {
		return professionRepository.findById(profession_id);
	}

	public boolean existsByProfessionId(Long profession_id) {
		return professionRepository.existsById(profession_id);
	}
	
	public boolean existsByProfessionName(String profession_name) {
		return professionRepository.existsByName(profession_name);
	}

	public Optional<Profession> findByProfessionName(String profession_name) {
		return professionRepository.findByName(profession_name);
	}
	
}
