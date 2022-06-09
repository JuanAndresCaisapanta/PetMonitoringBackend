package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.Medicine;
import com.Pet_Monitoring.Entities.Professional;
import com.Pet_Monitoring.Repositories.ProfessionalRepository;

@Service
@Transactional
public class ProfessionalService {

	@Autowired
	ProfessionalRepository professionalRepository;
	@Autowired
	JavaMailSender javaMailSender;

	public List<Professional> read() {
		return (List<Professional>) professionalRepository.findAll();
	}

	public void create(Professional professional) {
		professionalRepository.save(professional);
	}

	public void update(Professional profesionales) {
		professionalRepository.save(profesionales);
	}

	public void delete(int id) {
		professionalRepository.deleteById(id);
	}

	public Optional<Professional> getOne(int id) {
		return professionalRepository.findById(id);
	}

	public boolean existsById(int id) {
		return professionalRepository.existsById(id);
	}
	
	public List<Professional> findAllByPetId(int id) {
		return professionalRepository.findAllByPetId(id);
	}
	
	public void sendEmail(String fromEmail, String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setReplyTo(fromEmail);
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}

}
