package me.sa3ed.notifications.config;

import java.util.ArrayList;
import java.util.List;

import com.skilled.r.us.model.NetworkConnection;
import com.skilled.r.us.model.Subscriber;
import com.skilled.r.us.model.notification.Notification;
import com.skilled.r.us.model.notification.NotificationOnJob;
import com.skilled.r.us.model.notification.NotificationOnRecommendation;
import com.skilled.r.us.model.notification.NotificationOnSubscriber;

public class NotificationProxy {
	private Notification notification;

	public NotificationProxy(Notification notification) {
		this.notification = notification;
	}

	public CommunicationDetails getSubscriberConnectionDetails() {
		if (notification instanceof NotificationOnSubscriber) {
			
			return new CommunicationDetails(((NotificationOnSubscriber) notification).getSubscriber());

		} else {
			throw new RuntimeException("wrong association");
		}

	}

	public CommunicationDetails getTaskerConnectionDetails() {

		if (notification instanceof NotificationOnJob) {
			return new CommunicationDetails (((NotificationOnJob) notification).getTakser());
		} else {
			throw new RuntimeException("wrong association");
		}
	}

	public CommunicationDetails getRequesterConnectionDetails() {

		if (notification instanceof NotificationOnJob) {
			return new CommunicationDetails( ((NotificationOnJob) notification).getRequester());
		} else if (notification instanceof NotificationOnRecommendation) {
			return new CommunicationDetails( ((NotificationOnRecommendation) notification).getRequester());
		} else {
			throw new RuntimeException("wrong association");
		}
	}

	public CommunicationDetails getResponserConnectionDetails() {

		if (notification instanceof NotificationOnRecommendation) {
			return new CommunicationDetails( ((NotificationOnRecommendation) notification).getRequester());
		} else {
			throw new RuntimeException("wrong association");
		}
	}

	private List<CommunicationDetails> getFriendsEmail(Subscriber subscriber) {
		List<CommunicationDetails> emails = new ArrayList<CommunicationDetails>();
		for (NetworkConnection connection : subscriber.getConnectionList()) {
			emails.add(new CommunicationDetails(connection.getConnectedToSubscirber()));
		}
		return emails;
	}

	public List<CommunicationDetails> getTaskerFriendsEmails() {
		if (notification instanceof NotificationOnJob) {
			return getFriendsEmail(((NotificationOnJob) notification).getTakser());
		} else {
			throw new RuntimeException("wrong association");
		}
	}

	public List<CommunicationDetails> getRequesterFriendsEmails() {
		if (notification instanceof NotificationOnJob) {
			return getFriendsEmail(((NotificationOnJob) notification).getRequester());
		} else if (notification instanceof NotificationOnRecommendation) {
			return getFriendsEmail(((NotificationOnRecommendation) notification).getRequester());
		} else {
			throw new RuntimeException("wrong association");
		}
	}

	public List<CommunicationDetails> getSubscriberFriendsEmails() {
		if (notification instanceof NotificationOnSubscriber) {
			return getFriendsEmail(((NotificationOnSubscriber) notification).getSubscriber());
		} else {
			throw new RuntimeException("wrong association");
		}
	}

	public List<CommunicationDetails> getConnectionDetails() {
		switch (notification.getSubject()) {
		case registeration_verification:
			List<CommunicationDetails> list = new ArrayList<CommunicationDetails>();
			list.add(getSubscriberConnectionDetails());
			return list;
		case forgot_password:
			list = new ArrayList<CommunicationDetails>();
			list.add(getSubscriberConnectionDetails());
			return list;
		case updated_his_profile:
			list = new ArrayList<CommunicationDetails>();
			list.addAll(getFriendsEmail(((NotificationOnSubscriber)notification).getSubscriber()));
			return list;	

		default:
			break;
		}
		
		return null;
		
	}
	

}
