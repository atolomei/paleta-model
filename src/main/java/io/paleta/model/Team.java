package io.paleta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private List<String> players;
	
	
	public Team(String name) {
		this.name=name;
		this.players=new ArrayList<String>();
	}
	
	public Team(String name, List<String> list) {
		this.name=name;
		this.players=list;
	}

	public String getName() {
		return name;
	}
	
	
	public List<String> getPlayers() {
		return players;
	}
}
