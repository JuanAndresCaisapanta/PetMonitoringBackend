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

	public List<TypeEstablishment> readAllTypeEstablishment() {
		return (List<TypeEstablishment>) typeEstablishmentRepository.findAll();
	}

	public void createTypeEstablishment(TypeEstablishment typeEstablishment) {
		typeEstablishmentRepository.save(typeEstablishment);
	}

	public void updateTypeEstablishment(TypeEstablishment typeEstablishment) {
		typeEstablishmentRepository.save(typeEstablishment);
	}

	public void deleteTypeEstablishment(Long typeEstablishmentId) {
		typeEstablishmentRepository.deleteById(typeEstablishmentId);
	}

	public Optional<TypeEstablishment> getOneTypeEstablishment(Long typeEstablishmentId) {
		return typeEstablishmentRepository.findById(typeEstablishmentId);
	}

	public boolean existsByTypeEstablishmentId(Long typeEstablishmentId) {
		return typeEstablishmentRepository.existsById(typeEstablishmentId);
	}
	public boolean existsByTypeEstablishmentName(String typeEstablishmentName) {
		return typeEstablishmentRepository.existsByName(typeEstablishmentName);
	}

	public Optional<TypeEstablishment> findByTypeEstablishmentName(String typeEstablishmentName) {
		return typeEstablishmentRepository.findByName(typeEstablishmentName);
	}
}
