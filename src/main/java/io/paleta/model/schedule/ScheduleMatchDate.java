package io.paleta.model.schedule;

import java.time.OffsetDateTime;

import io.paleta.model.Match;

public class ScheduleMatchDate {

	private long id;
	
	private OffsetDateTime date;
	
	private Match macth;
	
	public ScheduleMatchDate(long id, OffsetDateTime date) {
		this.date=date;
		this.id=id;
	}
	
	public ScheduleMatchDate(long id, OffsetDateTime date, Match match) {
		this.date=date;
		this.id=id;
		this.macth=match;
	}
	
	 
	public int getDayOfYear() 	{ return this.date.getDayOfYear(); 		}
	public int getYearr() 		{ return this.date.getYear();			}
	public int getHour() 		{ return this.date.getHour();			}
	public int getMinute() 		{  return this.date.getMinute();		}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OffsetDateTime getDate() {
		return date;
	}

	public void setDate(OffsetDateTime date) {
		this.date = date;
	}

	public Match getMacth() {
		return macth;
	}

	public void setMacth(Match macth) {
		this.macth = macth;
	}

	
}
