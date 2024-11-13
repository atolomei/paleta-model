package io.paleta.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TournamentGroup extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String name;
	
	public List<Team> equipos;
	
	public TournamentGroup(String name, List<Team> equipos) {
		this.name=name;
		this.equipos=equipos;
	}
	
	public String getName() {
		return name;
	}
	
	 
	@JsonIgnore
	public List<Team> getTeams() {
		return equipos;
	}
	
}
