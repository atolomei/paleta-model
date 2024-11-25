package io.paleta.model.club;

import java.io.Serializable;

import io.paleta.model.JsonObject;

public class Club extends JsonObject implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public String name;
	
	
	public Club(String name) {
		this.name=name;
	}
	
	
	
}
