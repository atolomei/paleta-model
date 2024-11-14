package io.paleta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;



/*
 * for Round robin
 * 
 */
public class Schedule extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private List<Match> matchesClasificacion;
	
	private List<Match> matchesSemifinal;
	
	private Match matchFinal;
	
		
	
	
	
	

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
		
	

}
