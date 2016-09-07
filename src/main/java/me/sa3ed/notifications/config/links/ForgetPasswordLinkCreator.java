package me.sa3ed.notifications.config.links;

import com.skilled.r.us.model.Subscriber;
import com.skilled.r.us.model.notification.Notification;
import com.skilled.r.us.model.notification.NotificationOnSubscriber;

public class ForgetPasswordLinkCreator implements LinkCreator {

	private String LINK = "http://sa3ed.me/resetPassword.jsf?";
	private String EMAIL = "email=";
	private String KEY = "key=";
	private String AMBERSAND = "&";
	private String PLUS_HTML = "%2B";
	private String PLUS_SIGN = "+";

	public String createLink(Notification notification) {

		Subscriber subscriber = ((NotificationOnSubscriber) notification).getSubscriber();
		String link = LINK + EMAIL + subscriber.getEmail() + AMBERSAND + KEY + subscriber.getChangePasswordDigestKey();// "http://www.tarabott.com/registration.jsf?key="
		link = link.replace(PLUS_SIGN, PLUS_HTML);																												// +
		return link;

	}

}
