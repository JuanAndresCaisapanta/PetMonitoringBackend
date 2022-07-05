package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Dto.FullName;
import com.Pet_Monitoring.Entities.Establishment;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Repositories.EstablishmentRepository;

@Service
@Transactional
public class EstablishmentService {
	@Autowired
	EstablishmentRepository establishmentRepository;
	@Autowired
	JavaMailSender javaMailSender;

	public List<Establishment> readAllEstablishment() {
		return (List<Establishment>) establishmentRepository.findAll();
	}

	public void createEstablishment(Establishment establishment) {
		establishmentRepository.save(establishment);
	}

	public void updateEstablishment(Establishment establishment) {
		establishmentRepository.save(establishment);
	}

	public void deleteEstablishment(Long establishment_id) {
		establishmentRepository.deleteById(establishment_id);
	}

	public Optional<Establishment> getOneEstablishment(Long establishment_id) {
		return establishmentRepository.findById(establishment_id);
	}

	public boolean existsByEstablishmentId(Long establishment_id) {
		return establishmentRepository.existsById(establishment_id);
	}

	public List<Pet> getEstablishmentPets(Long establishmentType_id, String establishment_fullName, Long user_id) {
		return establishmentRepository.getEstablishmentPets(establishmentType_id, establishment_fullName, user_id);
	}

	public List<FullName> getEstablishmentFullNames(Long user_id) {
		return establishmentRepository.getEstablishmentFullNames(user_id);
	}

	public void sendEmailEstablishment(String from_email, String to_email, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setReplyTo(from_email);
		message.setFrom(from_email);
		message.setTo(to_email);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}
}
