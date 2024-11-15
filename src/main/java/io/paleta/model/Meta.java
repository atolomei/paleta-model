package io.paleta.model;

import java.io.Serializable;

public class Meta extends JsonObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String language;
	
	
	public String title;
	public String description;
	public String keywords;
	public String googleAnalytics;
	
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getGoogleAnalytics() {
		return googleAnalytics;
	}
	public void setGoogleAnalytics(String googleAnalytics) {
		this.googleAnalytics = googleAnalytics;
	}

	
}
