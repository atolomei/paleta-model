package io.paleta.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Match extends JsonObject implements Serializable {
	
	
	public Integer getDaybreak() {
		return daybreak;
	}

	public void setDaybreak(Integer daybreak) {
		this.daybreak = daybreak;
	}

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private OffsetDateTime date;
	
	@JsonIgnore
	private boolean completed;
	
	@JsonIgnore
	public Team local;
	
	@JsonIgnore
	public Team visitor;
	
	@JsonIgnore
	public TournamentGroup group;
	
	@JsonIgnore
	public List<PaletaSet> sets;
	
	@JsonIgnore
	public MatchResult result;
	

	@JsonIgnore
	private String matchdate;
	
	
	@JsonIgnore
	private String matchhour;

	@JsonIgnore
	public Integer daybreak  = Integer.valueOf(-1);
	
	
	public String getSetStr() {
		
		if (sets==null)
			return "";
		
		StringBuilder str = new StringBuilder(); 
		for (PaletaSet set:sets) {
			boolean first = true;
			if (set.isCompletado()) {
				if (!first)
					str.append(", ");
				str.append(set.puntosLocal +"-" + set.puntosVisitante);
			}
			first=false;
		}
		
		return str.toString();
	}
	
	@Override
	public String toString() {
			StringBuilder str = new StringBuilder();
			str.append(this.getClass().getSimpleName());
			str.append(toJSON());
			return str.toString();
	}

	@Override
	public String toJSON() {
		StringBuilder str = new StringBuilder();
		
		str.append("{\"date\":\"" + matchdate+ "\"");
		str.append("{\"hour\":\"" + matchdate+ "\"");
		
		str.append("{\"groupe\":\"" + group.getName()+ "\"");
		str.append(", \"local\":\"" + local.getName()+ "\"");
		str.append(", \"visitor\":\"" + visitor.getName()+ "\"");
		
		str.append("{\"completed\":" + String.valueOf(completed) + "");
		
		if (result!=null) {
			str.append(", \"result\":\"" + result.getName() + "\"");
			str.append(", \"score\":\"" + sets.stream().map( (p) -> String.valueOf(p.puntosLocal) + "-" + String.valueOf(p.puntosVisitante)).collect(Collectors.joining(", "))  + "\"}");
		}
		
		return str.toString();
	}
	
	public void setDate(OffsetDateTime date) {
		this.date=date;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Team getLocal() {
		return local;
	}

	public void setLocal(Team local) {
		this.local = local;
	}

	public Team getVisitor() {
		return visitor;
	}

	public void setVisitor(Team visitor) {
		this.visitor = visitor;
	}

	public TournamentGroup getTournamentZone() {
		return group;
	}

	public void setTournamentZone(TournamentGroup zone) {
		this.group = zone;
	}

	public MatchResult getMatchResult() {
		return result;
	}

	public void setMatchResult(MatchResult result) {
		this.result = result;
	}

	public List<PaletaSet> getSets() {
		return sets;
	}

	public void setSets(List<PaletaSet> sets) {
		this.sets = sets;
	}

	public OffsetDateTime getDate() {
		return date;
	}

	public String getMatchDateStr() {
		return matchdate;
	}
	
	public String getMatchHourStr() {
		return matchhour;
	}
	
	public void setMatchDate(String date) {
		this.matchdate=date;
	}
	
	public void setMatchHour(String hour) {
		this.matchhour=hour;
	}

	public TournamentGroup getGroup() {
		return group;
	}

	public void setGroup(TournamentGroup group) {
		this.group = group;
	}

	public MatchResult getResult() {
		return result;
	}

	public void setResult(MatchResult result) {
		this.result = result;
	}

	public String getMatchdate() {
		return matchdate;
	}

	public void setMatchdate(String matchdate) {
		this.matchdate = matchdate;
	}

	public String getMatchhour() {
		return matchhour;
	}

	public void setMatchhour(String matchhour) {
		this.matchhour = matchhour;
	}

	
}
