package me.sa3ed.notifications.config.rule;

import java.util.List;

import com.skilled.r.us.model.notification.Notification;

public interface NotificationRule {

	List<Notification> apply();
	
}
