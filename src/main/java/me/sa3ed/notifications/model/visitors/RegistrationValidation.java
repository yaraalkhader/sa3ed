package me.sa3ed.notifications.model.visitors;

import java.util.Date;

import me.sa3ed.notifications.config.action.NotificationAction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.skilled.r.us.connection.DatabaseService;
import com.skilled.r.us.model.Subscriber;
import com.skilled.r.us.model.notification.Notification;
import com.skilled.r.us.model.notification.NotificationLog;
import com.skilled.r.us.model.notification.enums.NotificationChannels;

public class RegistrationValidation implements ModelVisitor {

	private Integer reference;

	

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public void update(Notification notification, NotificationAction action, NotificationChannels channel) {
		final Session session = DatabaseService.getSessionFactory().getCurrentSession();
		Transaction t = session.beginTransaction();
		
		notification.setDileveryCount(notification.getDileveryCount()+1);
		notification.setDeliveryDate(new Date());
		session.saveOrUpdate(notification);
		
		NotificationLog log = new NotificationLog();
		log.setNotification(notification);
		log.setChannel(channel);
		log.setNotificationDate(new Date());
		
		session.save(log);
		

		t.commit();
		
	}
	
	
}
