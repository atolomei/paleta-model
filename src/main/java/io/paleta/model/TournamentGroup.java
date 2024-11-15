package io.paleta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TournamentGroup extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String id;
	
	public String name;
	
	public List<Team> equipos;
	
	public TournamentGroup(String name) {
		this(name.toLowerCase().trim(), name, new ArrayList<Team>());
	}
	
	public TournamentGroup(String id, String name) {
		this(id, name, new ArrayList<Team>());
	}
	
	public TournamentGroup(String id, String name, List<Team> equipos) {
		this.id=id;
		this.name=name;
		this.equipos=equipos;
	}
	
	public String getName() {
		return name;
	}
	 
	@JsonIgnore
	public List<Team> getTeams() {
		if (equipos==null)
			equipos=new ArrayList<Team>();
		return equipos;
	}

	public void addTeam(Team team) {
		getTeams().add(team);
	}
	
}
