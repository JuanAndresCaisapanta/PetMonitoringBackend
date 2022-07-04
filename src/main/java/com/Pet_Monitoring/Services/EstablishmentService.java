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

	public void deleteEstablishment(Long establishmentId) {
		establishmentRepository.deleteById(establishmentId);
	}

	public Optional<Establishment> getOneEstablishment(Long establishmentId) {
		return establishmentRepository.findById(establishmentId);
	}

	public boolean existsByEstablishmentId(Long establishmentId) {
		return establishmentRepository.existsById(establishmentId);
	}
	
	public List<Pet> getEstablishmentPets(Long typeEstablishmentId,String fullName, Long userId){
		return establishmentRepository.getEstablishmentPets(typeEstablishmentId,fullName,userId);
	}
	
	public List<FullName> getEstablishmentFullNames(Long userId) {
		return establishmentRepository.getEstablishmentFullNames(userId);
	}

	public void sendEmailEstablishment(String fromEmail, String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setReplyTo(fromEmail);
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}
}
