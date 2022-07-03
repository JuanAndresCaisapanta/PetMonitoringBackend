package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Dto.MessageDto;
import com.Pet_Monitoring.Entities.Device;
import com.Pet_Monitoring.Repositories.DeviceRepository;


@Service
@Transactional
public class DeviceService {
	@Autowired
	DeviceRepository deviceRepository;
	@Autowired
	JavaMailSender javaMailSender;


	public List<Device> readAll() {
		return (List<Device>) deviceRepository.findAll();
	}

	public void create(Device device) {
		deviceRepository.save(device);
	}
	
	public void update(Device device) {
		deviceRepository.save(device);
	}
	
	public void delete(Long id) {
		deviceRepository.deleteById(id);
	}
	
	public Optional<Device> getOne(Long id) {
		return deviceRepository.findById(id);
	}
	
	public boolean existsById(Long id) {
		return deviceRepository.existsById(id);
	}
	
	public MessageDto getUser(Long id) {
		return deviceRepository.getUser(id);
	}
	
	public boolean existsByCode(String code) {
		return deviceRepository.existsByCode(code);
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
