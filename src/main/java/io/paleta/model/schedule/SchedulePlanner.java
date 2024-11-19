package io.paleta.model.schedule;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.paleta.logging.Logger;
import io.paleta.model.Match;
import io.paleta.model.Team;


public class SchedulePlanner {
			
	static private Logger logger = Logger.getLogger(SchedulePlanner .class.getName());

	static final private DateTimeFormatter df = DateTimeFormatter.ofPattern("EEE dd MMM HH:mm", Locale.forLanguageTag("es"));
	static final private NumberFormat nf_int = NumberFormat.getInstance(Locale.getDefault());
    
	static {
		nf_int.setMinimumFractionDigits(0);
        nf_int.setMaximumFractionDigits(0);
        nf_int.setRoundingMode(RoundingMode.HALF_UP);
	}
    
	private boolean done = false;
	
	private List<List<MD>> solutions;

	private List<Match> matches;
	private List<ScheduleMatchDate> dates;
							
	/** auxiliar */
	private final Map<String, Team> 			teamMap = new HashMap<String, Team>();
	private final Map<Long, Match> 				matchMap= new HashMap<Long, Match>();
	private final Map<Long, ScheduleMatchDate> 	dateMap = new HashMap<Long, ScheduleMatchDate>();

	private long cont = 0;
	
	private Map<String, List<String>> auxiliar = new HashMap<String, List<String>>();
	
	
	/**
	 * 
	 * 
	 */
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
			return getMatchMap().get(getMatchID());
		}

		public ScheduleMatchDate getScheduleMatchDate() {
			return getDateMap().get(getSchedulerDateID());
		}
		
		public OffsetDateTime getDate() {
			return getDateMap().get( getSchedulerDateID()).getDate();
		}
	}

	
	
	/**
	 * 
	 * 
	 * @param dates
	 * @param matches
	 */
	public SchedulePlanner(List<ScheduleMatchDate> dates, List<Match> matches) {
		
		this.matches=matches;
		this.matches.forEach(m -> matchMap.put(Long.valueOf(m.getId()), m));
		
		this.matches.forEach(m ->  {
			
			teamMap.put(m.getLocal().getId(), m.getLocal());
			teamMap.put(m.getVisitor().getId(), m.getVisitor());
		});
		
		this.dates=dates;
		this.dates.forEach(m -> dateMap.put(Long.valueOf(m.getId()), m));
		
	}
	
	
	private List<Match> getMatches() {
		return this.matches;
	}
	
	private List<ScheduleMatchDate> getDates() {
		return this.dates;
	}
	
	public List<ScheduleMatchDate> execute() {
		
		this.done=false;
		this.cont = 0;
		this.solutions = new ArrayList<List<MD>>();
		
		List<MD> worklist = new ArrayList<MD>();
		solve(worklist);

		if (this.solutions.isEmpty()) {
			logger.debug("There are no solutions given the constraint");
			return null;
		}

		this.solutions.forEach( s -> {
			logger.debug("Solution:"); 
			int index = 0;
			for (MD d: s) {
				logger.debug( ++index + " " + df.format(d.getDate()) + "  " + d.getMacth().getLocal().getName() + " - " + d.getMacth().getVisitor().getName());
			}
			logger.debug("---------------------------------");
		});
		
		List<ScheduleMatchDate> res = new ArrayList<ScheduleMatchDate>();
		this.solutions.get(0).forEach( n ->  {
			n.getScheduleMatchDate().setMacth(n.getMacth());
			res.add(n.getScheduleMatchDate());
		});
		
		return res;
	}
	
	
	
	/**
	 * 
	 * Batcktracking
	 * 
	 * @param list
	 */
	
	
	private void solve(List<MD> list) {

		
		if (cont%5000==0) {
			logger.debug("solve ->" +  nf_int.format(cont));
		}
		cont++;
		
		if (isDone())
			return;
		
		int current = list.size();
		
		// Check.checkTrue(current< getDates().size(), "there are no more dates to assign (current -> " + String.valueOf(current));
	
		for (int index=0; index< getMatches().size(); index++) {
		
			if (isDone())
				return;
			
			Match candidate = getMatches().get(index);
			
			if (!contains(list, candidate)) {
				
				if (checkConstraints(list, current, candidate)) {
				
					List<MD> next = new ArrayList<MD>();
					list.forEach(i -> next.add(i));

					next.add(new MD(getDates().get(current).getId(), candidate.getId()));
					
					if (isSolution(next)) {
						solutions.add(next);
						logger.debug( "found solution " + String.valueOf(solutions.size()));
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
	
	/**
	 * 
	 * @param list
	 * @param ma
	 * @return
	 */
	private boolean checkConstraints(List<MD> list, int current, Match ma) {
							
		this.auxiliar.clear();
		
		for (MD m: list) {

			String key=String.format("%4d %02d %02d", m.getDate().getYear(), m.getDate().getMonthValue(), m.getDate().getDayOfMonth());
			
			if (!this.auxiliar.containsKey(key))
				this.auxiliar.put(key, new ArrayList<String>());

			this.auxiliar.get(key).add(m.getMacth().getLocal().getId());
			this.auxiliar.get(key).add(m.getMacth().getVisitor().getId());
		}
		
		//d_index = 0;
		//logger.debug("LIST (" + list.size()+")");
		//list.forEach( d -> logger.debug(d_index++ +". " + df.format(d.getDate()) + ": " + d.getMacth().getLocal().getName() + "-" + d.getMacth().getVisitor().getName()));
		//logger.debug("");
		//logger.debug("TD (" + td.size()+")");
		/**
		this.td.entrySet().forEach( s -> { 
			
				StringBuilder str = new StringBuilder();
			
				
				s.getValue().forEach( k -> { 
					if (str.length()>0)
						str.append(", ");
					str.append( teamMap.get(k).getName());
				});
				logger.debug(s.getKey() + ": " + str.toString());
				
		});
		**/
		//logger.debug("");
		
		String key=String.format("%4d %02d %02d", dates.get(current).getDate().getYear(), dates.get(current).getDate().getMonthValue(), dates.get(current).getDate().getDayOfMonth());
	
		
		
		
		
		if (!this.auxiliar.containsKey(key))
			return true;
			
		if (this.auxiliar.get(key).contains(ma.getLocal().getId())) {
			//logger.debug(teamMap.get(ma.getLocal().getId()).getName() +" is already on -> " + key);
			//logger.debug("constraint -> " + key + "  local: " + ma.getLocal().getName());
			return false;
		}
					
		if (this.auxiliar.get(key).contains(ma.getVisitor().getId())) {
			//logger.debug(teamMap.get(ma.getVisitor().getId()).getName() +" is already on -> " + key);
			//logger.debug("constraint -> " + key + " visitor: " + ma.getVisitor().getName());
			return false;
		}
		return true;
	}
	
	private boolean isSolution(List<MD> list) {
		return list.size()==getMatches().size() || list.size()==getDates().size();
	}
	
	private boolean contains(List<MD> list, Match match) {
		for (MD i: list) {
			if (i.getMatchID()==match.getId())
				return true;
		}	
		return false;
	}

	private boolean isDone() {
		return this.done;
	}
	
	private final Map<Long, Match> getMatchMap() {
		return this.matchMap;
	}
	
	private final Map<Long, ScheduleMatchDate> getDateMap() {
		return this.dateMap;
	}
	
	
	/**
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
	}*/
	


}
