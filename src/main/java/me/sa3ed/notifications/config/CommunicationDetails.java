package me.sa3ed.notifications.config;

import com.skilled.r.us.enums.Gender;
import com.skilled.r.us.model.Subscriber;

public class CommunicationDetails {
	private Integer id;
	private String name;
	private String email;
	private Gender gender;

	public CommunicationDetails(Subscriber subscriber) {
		super();
		this.id = subscriber.getId();
		try {
			this.name = subscriber.getFirstName() + " " + subscriber.getLastName();
		} catch (Exception e) {

			this.name = "Valued Customer";
		}
		this.email = subscriber.getEmail();
		this.gender = subscriber.getGender();
	}

	public CommunicationDetails(String name, String email, Gender gender) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
