package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.EstablishmentType;
import com.Pet_Monitoring.Repositories.EstablishmentTypeRepository;

@Service
@Transactional
public class EstablishmentTypeService {

	@Autowired
	EstablishmentTypeRepository establishmentTypeRepository;

	public List<EstablishmentType> readAllEstablishmentType() {
		return (List<EstablishmentType>) establishmentTypeRepository.findAll();
	}

	public void createEstablishmentType(EstablishmentType establishmentType) {
		establishmentTypeRepository.save(establishmentType);
	}

	public void updateEstablishmentType(EstablishmentType establishmentType) {
		establishmentTypeRepository.save(establishmentType);
	}

	public void deleteEstablishmentType(Long establishmentType_id) {
		establishmentTypeRepository.deleteById(establishmentType_id);
	}

	public Optional<EstablishmentType> getOneEstablishmentType(Long establishmentType_id) {
		return establishmentTypeRepository.findById(establishmentType_id);
	}

	public boolean existsByEstablishmentTypeId(Long establishmentType_id) {
		return establishmentTypeRepository.existsById(establishmentType_id);
	}
	public boolean existsByEstablishmentTypeName(String establishmentType_name) {
		return establishmentTypeRepository.existsByName(establishmentType_name);
	}

	public Optional<EstablishmentType> findByEstablishmentTypeName(String establishmentType_name) {
		return establishmentTypeRepository.findByName(establishmentType_name);
	}
}
