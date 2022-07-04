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
	
	public List<Notification> readAllNotification() {
		return (List<Notification>) notificationRepository.findAll();
	}

	public void createNotification(Notification notification) {
		notificationRepository.save(notification);
	}

	public void updateNotification(Notification notification) {
		notificationRepository.save(notification);
	}

	public void deleteNotification(Long notificationId) {
		notificationRepository.deleteById(notificationId);
	}

	public Optional<Notification> getOneNotification(Long notificationId) {
		return notificationRepository.findById(notificationId);
	}
	
	public List<Notification> findAllByUsersId(Long userId){
		return notificationRepository.findAllByUsersId(userId);
	}
	
}
