package me.sa3ed.notifications.config.action.impl;

import java.util.List;

import me.sa3ed.notifications.channels.SendHTMLEmail;
import me.sa3ed.notifications.config.CommunicationDetails;
import me.sa3ed.notifications.config.NotificationProxy;
import me.sa3ed.notifications.config.action.NotificationAction;
import me.sa3ed.notifications.config.links.LinkCreator;
import me.sa3ed.notifications.model.visitors.ModelVisitor;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.skilled.r.us.model.notification.Notification;
import com.skilled.r.us.model.notification.enums.NotificationChannels;
import com.skilled.r.us.model.notification.enums.NotificationType;

public class EmailNotificationAction implements NotificationAction {

	private String templatePath;
	private String subject;
	private String from;
	private List<String> templateParams;
	private LinkCreator linkCreator;
	private TemplateEngine templateEngine;
	private SendHTMLEmail sender = new SendHTMLEmail();
	private ModelVisitor statusUpdate;
	

	public EmailNotificationAction(String templatePath, String subject, List<String> templateParams, LinkCreator linkCreator, String from, ModelVisitor statusUpdate) {
		super();
		this.templatePath = templatePath;
		this.subject = subject;
		this.templateParams = templateParams;
		this.linkCreator = linkCreator;
		this.from = from;
		this.statusUpdate = statusUpdate;
		

		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setTemplateMode("HTML5");
		resolver.setSuffix(".html");
		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(resolver);

	}

	public void createNotification(Notification notification) {

		final Context context = new Context();
		

		NotificationProxy proxy = new NotificationProxy(notification);
		
		
		for (CommunicationDetails details: proxy.getConnectionDetails()) {
//			for (int index = 0; index < templateParams.size(); index++) {
//				context.setVariable(templateParams.get(index), params[index + reference]);
//			}

			context.setVariable(templateParams.get(0), details.getName());
			if (linkCreator != null) {
				context.setVariable("link", linkCreator.createLink(notification));
			}

			final String html = templateEngine.process(templatePath, context);
			sender.sendEmail(details.getEmail(), from, subject, html);
		}
		
		
		statusUpdate.update(notification, this, NotificationChannels.email);
	}
	
	

}
