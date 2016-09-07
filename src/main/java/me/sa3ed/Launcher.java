package me.sa3ed;

import java.util.Map;
import java.util.Timer;

import me.sa3ed.notifications.NotificatoinHandler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

	
public static void main(String[] args) {
		
		
		ApplicationContext context = 
		          new ClassPathXmlApplicationContext("app-context.xml");
//		
//		ApplicationContext context = 
//		          new AnnotationConfigApplicationContext(Launcher.class);
		Map<String, NotificatoinHandler> handlers = context.getBeansOfType(NotificatoinHandler.class);
	     
		for (String string: handlers.keySet()) {
	      Timer timer = new Timer();
	      
	    
	      timer.schedule(handlers.get(string),(1000), (1000*60*15));  
	      
		}
		
	}
}


