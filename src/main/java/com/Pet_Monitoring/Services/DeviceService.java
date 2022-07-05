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

	public void deleteDevice(Long device_id) {
		deviceRepository.deleteById(device_id);
	}

	public Optional<Device> getOneDevice(Long device_id) {
		return deviceRepository.findById(device_id);
	}

	public boolean existsByDeviceId(Long device_id) {
		return deviceRepository.existsById(device_id);
	}

	public MessageDto getUserDevice(Long device_id) {
		return deviceRepository.getUserDevice(device_id);
	}

	public boolean existsByDeviceCode(String device_code) {
		return deviceRepository.existsByCode(device_code);
	}

	public void sendEmailDevice(String from_email, String to_email, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setReplyTo(from_email);
		message.setFrom(from_email);
		message.setTo(to_email);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}
}
