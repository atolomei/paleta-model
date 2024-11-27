package io.paleta.model;



import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.paleta.logging.Logger;

public class Match extends JsonObject implements Serializable {
				
	@SuppressWarnings("unused")
	static private Logger logger = Logger.getLogger(Match.class.getName());

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private final long id;
	
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
	public Integer daybreak  = Integer.valueOf(-1);

	
	public Match(long id) {
		this.id=id;
	}
	
	@Override 
	public boolean equals(Object m) {
		
		 if (m == this) {
		     return true;
		 }

		 
		if (!(m instanceof Match))
			return false;
		
		if ( ((Match) m).getId()==getId())
			return true;
		
		return false;
	}
	
	public long getId() {
		return id;
	}

	public Match(long id, Team local, Team visitor) {
		this.id=id;
		this.local=local;
		this.visitor=visitor;
	}

	public String getSetStr() {
		
		if (getSets()==null)
			return "";
		
		StringBuilder str = new StringBuilder(); 
		for (PaletaSet set: getSets()) {
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
		
		str.append("{\"id\":" + String.valueOf(id));
		str.append(", \"group\":\"" + (group!=null?group.getName():"null") + "\"");
		str.append(", \"local\":\"" +(local!=null?local.getName():"null")+ "\"");
		str.append(", \"visitor\":\"" + (visitor!=null?visitor.getName():"null")+ "\"");
		
		str.append(", \"completed\":" + String.valueOf(completed) + "");
		
		if (result!=null) {
			str.append(", \"result\":\"" + result.getName() + "\"");
			if (sets!=null)
				str.append(", \"score\":\"" + sets.stream().map( (p) -> String.valueOf(p.puntosLocal) + "-" + String.valueOf(p.puntosVisitante)).collect(Collectors.joining(", "))  + "\"");
		}
		
		str.append("}");
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

	
	public void setTournamentGroup(TournamentGroup group) {
		this.group = group;
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

	
	 // static final private int TIMESTAMP_LEN = "2019-08-21T00:00:00-03:00".length();
    
    	
	/**
    private void calculateDate() {
		
	
		
		String t_date = (this.matchdate==null) ? "10/12" : this.matchdate;
		String t_hour =  (this.matchhour==null) ? "19:00" : this.matchhour;
		
		String s_year, s_month, s_day;
		Integer year, month, day;
		
		{
			String arr[] = t_date.replaceAll("/", "-").split("-");
			
			if (arr.length<3)
				s_year = "2024";
			else
				s_year = arr[2];
			
			if (s_year.length()<4)
				s_year="20"+s_year;
			
			
			if (arr.length<2)
				s_month = "12";
			else
				s_month=arr[1];
			
			if (s_month.length()<2)
				s_month="0"+s_month;
			
			if (arr.length<1)
				s_day = "1";
			else
				s_day=arr[0];
			
			if (s_day.length()<2)
				s_day="0"+s_day;
		
			
			try {
				year = Integer.valueOf(s_year);
			} catch (Exception e) {
				logger.error(e);
				year=2024;
			}
			
			try {
				month = Integer.valueOf(s_month);
			} catch (Exception e) {
				logger.error(e);
				month=12;
			}
			
			try {
				day = Integer.valueOf(s_day);
			} catch (Exception e) {
				logger.error(e);
				day=1;
			}
		
		}
		

		String s_hour, s_min, s_sec;
		Integer hour, min, sec;

		String arr[] = t_hour.replaceAll("-", ":").split(":");
		
		if (arr.length<3)
			s_sec = "00";
		else
			s_sec = arr[2];
		
		if (s_sec.length()<2)
			s_sec="0"+s_sec;
		
		
		
		if (arr.length<2)
			s_min = "00";
		else
			s_min=arr[1];
		
		if (s_min.length()<2)
			s_min="0"+s_min;
		
		
		if (arr.length<1)
			s_hour = "19";
		else
			s_hour=arr[0];
		
		if (s_hour.length()<2)
			s_hour="0"+s_hour;

		
		2024-11-22T 19:00:00-03:00
				
		{
		
			try {
				hour = Integer.valueOf(s_hour);
			} catch (Exception e) {
				logger.error(e);
				hour=19;
			}
			
			try {
				min = Integer.valueOf(s_min);
			} catch (Exception e) {
				logger.error(e);
				min=0;
			}
			
			try {
				sec = Integer.valueOf(s_sec);
			} catch (Exception e) {
				logger.error(e);
				sec=0;
			}		
		}
		

		
		String str_date = s_year +"-" + s_month + "-" + s_day + "T" + s_hour+":" + s_min + ":" + s_sec + "-03:00";
		
		//logger.debug(str_date);
	
		try {
			this.date = OffsetDateTime.parse(str_date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		} catch (Exception e) {
			logger.error(e, " | Tried timestamp without GMT. " + str_date);
		}
		
		
		
	}
*/
	

	public TournamentGroup getTournamentGroup() {
		return group;
	}
	

	public String getResultCode() {
		if (getResult()==null)
			return null;
		return getResult().getName();
	}
	
	public MatchResult getResult() {
		return result;
	}

	public void setResult(MatchResult result) {
		this.result = result;
	}
	
	public String getMatchDateStr() {
		if (getDate()==null)
			return null;
			return  String.valueOf(getDate().getDayOfMonth())+"/"+String.valueOf(getDate().getMonthValue());
	 }
	
	public String getMatchHourStr() {
		if (getDate()==null)
			return null;
			return  String.valueOf(getDate().getHour());
	}

	public String getMatchMinStr() {
		if (getDate()==null)
			return null;
			return  String.format("%02d", getDate().getMinute());
	}
	
	public Integer getDaybreak() {
		return daybreak;
	}

	public void setDaybreak(Integer daybreak) {
		this.daybreak = daybreak;
	}

	


	
}
