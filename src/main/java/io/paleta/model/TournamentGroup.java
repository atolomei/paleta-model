package io.paleta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TournamentGroup extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	private List<Team> equipos;
	
	private List<Match> matches;
	
	public TournamentGroup(String name) {
		this(name.toLowerCase().trim(), name, new ArrayList<Team>());
	}
	
	public TournamentGroup(String id, String name) {
		this(id, name, new ArrayList<Team>());
	}
	
	public TournamentGroup(String id, String name, List<Team> equipos) {
		this.setId(id);
		this.name=name;
		this.equipos=equipos;
	}

	public List<Match> getMatches() {
		return this.matches;
	}
	
	public void setMatches(List<Match> matches) {
		this.matches=matches;
	}
	public void setName( String name) {
		this.name=name;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
