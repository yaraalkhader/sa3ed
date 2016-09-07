package me.sa3ed.notifications.model.visitors;

import java.security.acl.NotOwnerException;

import me.sa3ed.notifications.config.action.NotificationAction;

import com.skilled.r.us.model.notification.Notification;
import com.skilled.r.us.model.notification.enums.NotificationChannels;

public interface ModelVisitor {
	void update(Notification notification, NotificationAction action, NotificationChannels channel);
}
