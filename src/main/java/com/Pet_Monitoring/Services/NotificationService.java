package com.Pet_Monitoring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entities.Notification;
import com.Pet_Monitoring.Repositories.NotificationRepository;

@Service
@Transactional
public class NotificationService {

	@Autowired
	NotificationRepository notificationRepository;
	
	public List<Notification> readAll() {
		return (List<Notification>) notificationRepository.findAll();
	}

	public void create(Notification notification) {
		notificationRepository.save(notification);
	}

	public void update(Notification notification) {
		notificationRepository.save(notification);
	}

	public void delete(Long id) {
		notificationRepository.deleteById(id);
	}

	public Optional<Notification> getOne(Long id) {
		return notificationRepository.findById(id);
	}
	
	public List<Notification> findAllByUsersId(Long id){
		return notificationRepository.findAllByUsersId(id);
	}
	
}
