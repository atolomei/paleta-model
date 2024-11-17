package io.paleta.model.schedule;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paleta.logging.Logger;
import io.paleta.model.Match;
import io.paleta.model.Team;
import io.paleta.model.TournamentGroup;
import io.paleta.util.Check;


public class RoundRobinGenerator {

	static private Logger logger = Logger.getLogger(RoundRobinGenerator.class.getName());

	static final public DateTimeFormatter full_spa = DateTimeFormatter.ofPattern("EEE dd MMM HH:mm", Locale.forLanguageTag("es"));
	

	private List<Match> matches;
	
	private List<ScheduleMatchDate> dates;
	
	private List<TournamentGroup> groups;

	
	public RoundRobinGenerator(List<TournamentGroup> tournamentGroups) {
			this.groups=tournamentGroups;
	}


	public List<ScheduleMatchDate> execute() {

		Check.requireNonNull(groups);
		
		matches = new ArrayList<Match>();
		
		for (TournamentGroup group: groups) {
			List<Match> list = process( group );
			list.forEach(m -> logger.debug(m.getLocal().getName() + " - " + m.getVisitor().getName()));
			
			logger.debug("Total -> "+ list.size());
			list.forEach(m -> matches.add(m));
		}
		
		dates = new ArrayList<ScheduleMatchDate>();
		
		int yr  []   = {2024};
		
		int mth []   = {11, 11, 11, 11, 
						11, 11, 11, 
						11, 11, 11, 11, 
						12, 12 , 12,
						12, 12, 12,
						12, 12, 12};
		
		int dom []   = {22, 22, 22, 22, 
						27, 27, 27, 
						29, 29, 29, 29, 
						4, 4 , 4,
						6, 6, 6,
						11, 11, 11};
		
		int hours [] = {19, 19, 20, 20, 
						19, 19, 20, 
						19, 19, 20, 20, 
						19, 19, 20,
						19, 19, 20,
						19, 19, 20};
		
		int min [] 	 = {0, 30, 0, 30, 
						0, 30, 0, 
						0, 30, 0, 30, 
						0, 30, 0,
						0, 30, 0,
						0, 30, 0};
		
		int index    = 0;
		
		int d_index  = 0;
		int h_index  = 0;
		int m_index  = 0;
		
		for (Match match: matches) {
			
			//d_index = (int) Math.abs(Math.floor((double) index / (double) 3));
			//h_index = index % hours.length;
			//m_index = index % min.length;
			
			
			
			OffsetDateTime md =
					
					OffsetDateTime.of (
							yr	  [d_index % yr.length ], 
							mth   [d_index % mth.length ], 
							dom   [d_index % dom.length ],
							hours [d_index % hours.length ], 
							min   [d_index % min.length ],
							0, 
							0, 
							ZoneOffset.of("-03:00"));

			
			d_index++;
			
			//m_index = d_index + index % 3;
			dates.add(new ScheduleMatchDate(index, md));
			
			index++;
		}
		
		dates.forEach(d -> logger.debug( full_spa.format(d.getDate())));
		
		logger.debug("Total -> " + dates.size());
		
		logger.debug("done");
		
		return dates;
		
		
		
	}
	
	long matchId = 0;
	
	private List<Match> process(TournamentGroup group) {
		
		List<Match> list = new ArrayList<Match>();
		
		
		int local_index = 0;
		for (Team local: group.getTeams()) {
			
			
			int visitor_index = 0;
			for (Team visitor:group.getTeams()) {
				if (visitor_index > local_index) {
					Match match = new Match(matchId++, local, visitor);
					match.setGroup(group);
					list.add(match);
				}
				visitor_index++;
			}
			local_index++;
		}
		
		return list;
	}


	public List<Match> getMatches() {
		return matches;
	}


	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}


	public List<TournamentGroup> getGroups() {
		return groups;
	}


	public void setGroups(List<TournamentGroup> groups) {
		this.groups = groups;
	}
}
