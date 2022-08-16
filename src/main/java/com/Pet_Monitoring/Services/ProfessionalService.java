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
		return (List<Professional>) professionalRepository.findProfessionalByOrderByIdAsc();
	}

	public void createProfessional(Professional professional) {
		professionalRepository.save(professional);
	}

	public void updateProfessional(Professional profesionales) {
		professionalRepository.save(profesionales);
	}

	public void deleteProfessional(Long professional_id) {
		professionalRepository.deleteById(professional_id);
	}

	public Optional<Professional> getOneProfessional(Long professional_id) {
		return professionalRepository.findById(professional_id);
	}

	public boolean existsByProfessionalId(Long professional_id) {
		return professionalRepository.existsById(professional_id);
	}
	
	
	public List<Pet> getProfessionalPets(Long profession_id,String professional_fullName, Long user_id){
		return professionalRepository.getProfessionalPets(profession_id,professional_fullName,user_id);
	}
	
	public List<FullName> getProfessionalFullNames(Long user_id) {
		return professionalRepository.getProfessionalFullNames(user_id);
	}
	
	public void sendEmailProfessional(String from_email, String to_email, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setReplyTo(from_email);
		message.setFrom(from_email);
		message.setTo(to_email);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}

}
