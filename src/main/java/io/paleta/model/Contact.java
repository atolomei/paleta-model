package io.paleta.model;

import java.io.Serializable;

public class Contact extends JsonObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String name;

	
	private String contactMethod;
	
	
	
	
	public Contact(String n, String cm) {
		setName(n);
		setContactMethod(cm);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactMethod() {
		return contactMethod;
	}
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	
	
	
	

}
