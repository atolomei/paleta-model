package io.paleta.model.schedule;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.paleta.model.JsonObject;
import io.paleta.model.Match;


/*
 * for Round robin
 * 
 */
public class Schedule extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private String fileName;
	
	@JsonIgnore
	private List<Match> matchesClasificacion;
	
	@JsonIgnore
	private List<Match> matchesSemifinal;

	@JsonIgnore
	private Match matchFinal;

	
	public Schedule(String fileName, List<Match> matchesClasificacion) {
		this.fileName=fileName;
		this.matchesClasificacion=matchesClasificacion;
		
	}
	public Schedule() {
		this.matchesClasificacion=new ArrayList<Match>();
		 matchesSemifinal=null;
		 matchFinal=null;
	}

	public Match getMatchFinal() {
		return matchFinal;
	}

	public void setMatchFinal(Match matchFinal) {
		this.matchFinal = matchFinal;
	}

	public void addMatchClasificacion( Match match) {
		matchesClasificacion.add(match);
	}
	
	public void addMatchSemifinal( Match match) {
		if (matchesSemifinal==null)
			matchesSemifinal=new ArrayList<Match>();
		matchesSemifinal.add(match);
	}
	
	public List<Match> getMatchesClasificacion() {
		return matchesClasificacion;
	}

	public void setMatchesClasificacion(List<Match> matchesClasificacion) {
		this.matchesClasificacion = matchesClasificacion;
	}
	
	public List<Match> getMatchesSemifinal() {
		return matchesSemifinal;
	}

	public void setMatchesSemifinal(List<Match> matchesSemifinal) {
		this.matchesSemifinal = matchesSemifinal;
	}
		
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
