package me.sa3ed.notifications.model.visitors;

import java.util.Date;

import me.sa3ed.notifications.config.action.NotificationAction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.skilled.r.us.connection.DatabaseService;
import com.skilled.r.us.model.JobPost;
import com.skilled.r.us.model.Subscriber;
import com.skilled.r.us.model.notification.Notification;
import com.skilled.r.us.model.notification.enums.NotificationChannels;

public class JobPostUpdate implements ModelVisitor {

	private Integer reference;

	public void update(Object... objects) {

		final Session session = DatabaseService.getSessionFactory().getCurrentSession();
		Transaction t = session.beginTransaction();
		// final Query query = session.getNamedQuery(queryByName);
		final Criteria criteria = session.createCriteria(JobPost.class);
		criteria.add(Restrictions.eq("id", objects[reference]));
		Subscriber subscriber = (Subscriber) criteria.uniqueResult();
//		subscriber.setRegistrationNotification(true);
//		subscriber.setNotificationDate(new Date());
		session.save(subscriber);

		t.commit();
	}

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public void update(Notification notification, NotificationAction action, NotificationChannels channel) {
		// TODO Auto-generated method stub
		
	}

	

}
