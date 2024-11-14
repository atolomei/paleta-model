package io.paleta.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class Alert extends JsonObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	OffsetDateTime startDate;
	OffsetDateTime endDate;
	String alertClass;
	String title;
	String text;
	
	
	public Alert() {
	}
	
	
	public OffsetDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}

	public OffsetDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	public String getAlertClass() {
		return alertClass;
	}

	public void setAlertClass(String clazz) {
		this.alertClass= clazz;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	
	
	
	
}
