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


	public List<Device> readAllDevice() {
		return (List<Device>) deviceRepository.findAll();
	}

	public void createDevice(Device device) {
		deviceRepository.save(device);
	}
	
	public void updateDevice(Device device) {
		deviceRepository.save(device);
	}
	
	public void deleteDevice(Long deviceId) {
		deviceRepository.deleteById(deviceId);
	}
	
	public Optional<Device> getOneDevice(Long deviceId) {
		return deviceRepository.findById(deviceId);
	}
	
	public boolean existsByDeviceId(Long deviceId) {
		return deviceRepository.existsById(deviceId);
	}
	
	public MessageDto getUserDevice(Long deviceId) {
		return deviceRepository.getUserDevice(deviceId);
	}
	
	public boolean existsByDeviceCode(String deviceCode) {
		return deviceRepository.existsByCode(deviceCode);
	}


	public void sendEmailDevice(String fromEmail, String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setReplyTo(fromEmail);
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}
}
