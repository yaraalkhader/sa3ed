package me.sa3ed.notifications.config.rule.impl;

import java.util.List;

import me.sa3ed.notifications.config.rule.NotificationRule;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.skilled.r.us.connection.DatabaseService;
import com.skilled.r.us.model.notification.Notification;

public class QueryNotificationRule implements NotificationRule {

	private String queryStr;

	public List<Notification> apply() {
		final Session session = DatabaseService.getSessionFactory().getCurrentSession();
		Transaction t = session.beginTransaction();
		
		final Query query = session.createQuery(queryStr);

		@SuppressWarnings("unchecked")
		List<Notification> list = (List<Notification>)query.list();
		t.commit();

		return list;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
	
	

}
