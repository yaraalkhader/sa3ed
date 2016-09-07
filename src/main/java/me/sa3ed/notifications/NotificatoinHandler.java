package me.sa3ed.notifications;

import java.util.List;
import java.util.TimerTask;

import me.sa3ed.notifications.config.action.NotificationAction;
import me.sa3ed.notifications.config.rule.NotificationRule;

import org.springframework.stereotype.Component;

import com.skilled.r.us.model.notification.Notification;

@Component
public class NotificatoinHandler extends TimerTask {
	private NotificationRule rule;
	private NotificationAction action;
	
	public void run() {
		List<Notification> list = rule.apply();
		for (Notification notification: list) {
			try{
			action.createNotification(notification);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public NotificationRule getRule() {
		return rule;
	}

	public void setRule(NotificationRule rule) {
		this.rule = rule;
	}

	public NotificationAction getAction() {
		return action;
	}

	public void setAction(NotificationAction action) {
		this.action = action;
	}
	
	
	
	
}
