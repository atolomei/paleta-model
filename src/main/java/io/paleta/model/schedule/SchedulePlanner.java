package io.paleta.model.schedule;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.paleta.logging.Logger;
import io.paleta.model.Match;
import io.paleta.util.Check;

public class SchedulePlanner {
			
	static private Logger logger = Logger.getLogger(SchedulePlanner .class.getName());

	static final public DateTimeFormatter full_spa = DateTimeFormatter.ofPattern("EEE dd MMM HH:mm", Locale.forLanguageTag("es"));

	
	private List<Match> matches;
	private List<ScheduleMatchDate> dates;
	private boolean done = false;
	
	private List<List<MD>> solutions;

	private Map<Long, Match> 				matchMap= new HashMap<Long, Match>();
	private Map<Long, ScheduleMatchDate> 	dateMap = new HashMap<Long, ScheduleMatchDate>();
	
	
	private class MD {
		
		private long schedulerDateID;
		private long matchID;
		
		public MD(long schedulerDateID, long matchID) {
			this.setSchedulerDateID(schedulerDateID);
			this.setMatchID(matchID);
		}
		
		public long getSchedulerDateID() {
			return schedulerDateID;
		}
		public void setSchedulerDateID(long schedulerDateID) {
			this.schedulerDateID = schedulerDateID;
		}

		public long getMatchID() {
			return matchID;
		}

		public void setMatchID(long matchID) {
			this.matchID = matchID;
		}

		public Match getMacth() {
			return matchMap.get(getMatchID());
		}

		public ScheduleMatchDate getScheduleMatchDate() {
			return dateMap.get(getSchedulerDateID());
		}
		
		public OffsetDateTime getDate() {
			return dateMap.get( getSchedulerDateID()).getDate();
		}
		
	}

	
	public SchedulePlanner(List<ScheduleMatchDate> dates, List<Match> matches) {
		
		this.matches=matches;
		this.matches.forEach( m -> matchMap.put(Long.valueOf(m.getId()), m));
		
		this.dates=dates;
		this.dates.forEach( m -> dateMap.put(Long.valueOf(m.getId()), m));
		
	}
	
	
	public List<ScheduleMatchDate> execute() {
	
		List<MD> dates = new ArrayList<MD>();
		//dates.forEach( n -> t_dates.add(n));
		
		solutions = new ArrayList<List<MD>>();
		
		solve(dates);

		solutions.forEach( s -> {
			
			logger.debug("Solution:"); 
			int index = 0;
			for (MD d: s) {
				
				logger.debug( ++index + " " + full_spa.format(d.getDate()) + "  " + d.getMacth().getLocal().getName() + " - " + d.getMacth().getVisitor().getName());
			}
			logger.debug("---------------------------------");
		});
		
		if (solutions==null)
			return null;
		
		List<ScheduleMatchDate> res = new ArrayList<ScheduleMatchDate>();
		solutions.get(0).forEach( n ->  {
			n.getScheduleMatchDate().setMacth(n.getMacth());
			res.add(n.getScheduleMatchDate());
		});
		
		return res;
		//return solutions;
	}
	
	
	

	private void solve(List<MD> list) {

		if (done)
			return;
		
		int current = list.size();
		
		//OffsetDateTime date=dates.get(current).getDate();
	 	
		if (list.size()==matches.size() || list.size()==dates.size()) {
			logger.debug("list.size()==matches.size() || list.size()==dates.size()");
			return;
		}

		Check.checkTrue(current<dates.size(), "there are no more dates to assign (current -> " + String.valueOf(current));
	

		logger.debug("start list.size -> " + list.size());
		
		for (int n=0; n<matches.size(); n++) {
		
			if (done)
				return;
			
			if (!contains(list, matches.get(n))) {
				
				if (checkConstraints(list, matches.get(n))) {
				
					List<MD> next = new ArrayList<MD>();
					list.forEach(i -> next.add(i));

					next.add(new MD(dates.get(current).getId(), matches.get(n).getId()));
					
					if (isSolution(next)) {
						
						solutions.add(next);
						logger.debug( "found solution " + String.valueOf(solutions.size()));
						
						//for (MD d: next) {
						//	logger.debug( full_spa.format(d.getDate()) + "  " + d.getMacth().getLocal().getName() + " - " + d.getMacth().getVisitor().getName());
						//}
						
						// + full_spa.format(aa) + " " 
						
						done = true; //(solutions.size()>4);
						return;
					}
					else {
						solve(next);
					}
				}
			}
		}
	}
	



	private boolean checkConstraints(List<MD> list, Match ma) {
		
		for (MD m: list) {
			
			Match match = m.getMacth();
			ScheduleMatchDate date= m.getScheduleMatchDate();
			
			if  (   match.getLocal().getName().toLowerCase().startsWith("cuba") || 
					match.getVisitor().getName().toLowerCase().startsWith("cuba"))  {
				
				if (date.getHour()==20) {
					logger.debug("constraint -> " + match.getId());
					return false;
				}
			}
		}
		
		if   ( ma.getLocal().getName().toLowerCase().startsWith("cuba") || 
			  ma.getVisitor().getName().toLowerCase().startsWith("cuba")
			  
			 ) {
			
			if (dates.get(list.size()).getHour()==20) {
				logger.debug("constraint -> " + ma.getId());
				return false;
			}
		}
		
		return true;
	}
	
	private boolean isSolution(List<MD> list) {
		return list.size()==matches.size() ||list.size()==20;
	}
	
	private boolean contains(List<MD> list, Match match) {
		
		for (MD i: list) {
			if (i.getMatchID()==match.getId())
				return true;
		}	
		return false;
	}


	
	
	private String getIDs(List<ScheduleMatchDate> next) {
		
		StringBuilder str = new StringBuilder();
		next.forEach( s-> 
		{	
			if (str.length()>0)
				str.append(", ");
			str.append( String.valueOf(s.getMacth().getId())); 
		}
				);
		return str.toString();
	}

	
}
