package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.TypeEstablishment;
import com.Pet_Monitoring.Repositories.TypeEstablishmentRepository;

@Service
@Transactional
public class TypeEstablishmentService {

	@Autowired
	TypeEstablishmentRepository typeEstablishmentRepository;

	public List<TypeEstablishment> read() {
		return (List<TypeEstablishment>) typeEstablishmentRepository.findAll();
	}

	public void create(TypeEstablishment typeEstablishment) {
		typeEstablishmentRepository.save(typeEstablishment);
	}

	public void update(TypeEstablishment typeEstablishment) {
		typeEstablishmentRepository.save(typeEstablishment);
	}

	public void delete(int id) {
		typeEstablishmentRepository.deleteById(id);
	}

	public Optional<TypeEstablishment> getOne(int id) {
		return typeEstablishmentRepository.findById(id);
	}

	public boolean existsById(int id) {
		return typeEstablishmentRepository.existsById(id);
	}
	public boolean existsByName(String name) {
		return typeEstablishmentRepository.existsByName(name);
	}

	public Optional<TypeEstablishment> findByName(String name) {
		return typeEstablishmentRepository.findByName(name);
	}
}
