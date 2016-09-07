package me.sa3ed.notifications.config.links;

import com.skilled.r.us.model.notification.Notification;


public interface LinkCreator {
	String createLink(Notification notification );
}
