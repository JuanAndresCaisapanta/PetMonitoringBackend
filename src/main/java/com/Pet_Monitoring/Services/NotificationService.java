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

	public void deleteNotification(Long notification_id) {
		notificationRepository.deleteById(notification_id);
	}

	public Optional<Notification> getOneNotification(Long notification_id) {
		return notificationRepository.findById(notification_id);
	}
	
	public List<Notification> findAllByUsersId(Long user_id){
		return notificationRepository.findAllByUsersId(user_id);
	}
	
}
