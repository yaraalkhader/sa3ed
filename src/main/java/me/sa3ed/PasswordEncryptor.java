package me.sa3ed;

import java.util.List;

import me.sa3ed.utilities.Encryption;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.skilled.r.us.connection.DatabaseService;
import com.skilled.r.us.dao.SubscriberDao;
import com.skilled.r.us.model.Subscriber;

public class PasswordEncryptor {

	public static void main(String[] args) {

		SessionFactory factory = DatabaseService.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		SubscriberDao dao = new SubscriberDao();
		List<Subscriber> list = dao.loadAllObjects(Subscriber.class);
		for (Subscriber subscriber : list) {
			if (subscriber.getRegistrationDigestKey() == null && subscriber.getPassword() != null)
				subscriber.setPassword(Encryption.encryptBase64(subscriber.getPassword()));
			dao.Save(subscriber);
		}

		session.getTransaction().commit();

	}
}
