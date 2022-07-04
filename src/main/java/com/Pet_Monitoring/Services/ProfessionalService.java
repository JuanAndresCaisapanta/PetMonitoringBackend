package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Dto.FullName;
import com.Pet_Monitoring.Entities.Pet;
import com.Pet_Monitoring.Entities.Professional;
import com.Pet_Monitoring.Repositories.ProfessionalRepository;

@Service
@Transactional
public class ProfessionalService {

	@Autowired
	ProfessionalRepository professionalRepository;
	@Autowired
	JavaMailSender javaMailSender;

	public List<Professional> readAllProfessional() {
		return (List<Professional>) professionalRepository.findAll();
	}

	public void createProfessional(Professional professional) {
		professionalRepository.save(professional);
	}

	public void updateProfessional(Professional profesionales) {
		professionalRepository.save(profesionales);
	}

	public void deleteProfessional(Long professionalId) {
		professionalRepository.deleteById(professionalId);
	}

	public Optional<Professional> getOneProfessional(Long professionalId) {
		return professionalRepository.findById(professionalId);
	}

	public boolean existsByProfessionalId(Long professionalId) {
		return professionalRepository.existsById(professionalId);
	}
	
	public List<Professional> findAllByPetId(Long petId) {
		return professionalRepository.findAllByPetId(petId);
	}
	
	public List<Pet> getProfessionalPets(Long professionId,String fullName, Long userId){
		return professionalRepository.getProfessionalPets(professionId,fullName,userId);
	}
	
	public List<FullName> getProfessionalFullNames(Long userId) {
		return professionalRepository.getProfessionalFullNames(userId);
	}
	
	public void sendEmailProfessional(String fromEmail, String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setReplyTo(fromEmail);
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}

}
