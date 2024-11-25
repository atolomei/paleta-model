package io.paleta.model.club;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.paleta.logging.Logger;
import io.paleta.model.Alert;
import io.paleta.model.Contact;
import io.paleta.model.JsonObject;
import io.paleta.model.Match;
import io.paleta.model.Meta;
import io.paleta.model.Team;
import io.paleta.model.TournamentGroup;
import io.paleta.model.TournamentGroupTable;
import io.paleta.model.schedule.Schedule;


public class ClubTournament extends JsonObject implements Serializable {
	
			
	static private Logger logger = Logger.getLogger(ClubTournament.class.getName());

	private static final long serialVersionUID = 1L;
	

	private String name;
	
	private String key;
	
	@JsonIgnore
	private Schedule schedule;
	
	@JsonIgnore
	private List<TournamentGroup> tournamentGroups;
	
	@JsonIgnore
	private List<Match> matches;

	@JsonIgnore
	private Map<TournamentGroup, TournamentGroupTable> groupTables = new HashMap<TournamentGroup, TournamentGroupTable>();

	@JsonIgnore
	private Team winner;
	
	@JsonIgnore
	private TournamentStatus tournamentStatus = TournamentStatus.NOT_STARTED;
	
	@JsonIgnore
	private OffsetDateTime startDate;
	
	@JsonIgnore
	private OffsetDateTime endDate;
	
	@JsonIgnore
	private Alert alert;
	
	@JsonIgnore
	private List<Contact> contacts;
	
	@JsonIgnore
	private String banner;
	
	@JsonIgnore
	private Meta meta;
	
	
	public ClubTournament() {
	}

	
	public ClubTournament(String name, String key) {
		this.name=name;
		this.key=key;
	}
	
	public OffsetDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}

	public OffsetDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	
	public void setTournamentGroups(List<TournamentGroup> list) {
		this.tournamentGroups=list;
	}

	
	public List<Match> getMatches() {
		return this.matches;
	}
	
	public void setMatches(List<Match> matches) {
		this.matches=matches;
	}
	public Team getWinner() {
		return this.winner;
	}

	
	public String getName() {
		return this.name;
	}
	
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public TournamentStatus getTournamentStatus() {
		return this.tournamentStatus;
	}
	
	public void setState(TournamentStatus state) {
		this.tournamentStatus = state;
	}
	
	public void setWinner(Team winner) {
		this.winner = winner;
	}

	public String getKey() {
		return this.key;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setKey(String key) {
		this.key=key;
	}
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public Schedule getSchedule() {
		return this.schedule;
	}
	
	public List<TournamentGroup> getTournamentGroups() {
		return this.tournamentGroups;
	}
	
	
	public Map<TournamentGroup, TournamentGroupTable> getGroupTables() {
		return groupTables;
	}

	public void setGroupTables(Map<TournamentGroup, TournamentGroupTable> groupTables) {
		this.groupTables = groupTables;
	}
	
	public List<TournamentGroupTable> getGroupTableList() {

		List<TournamentGroupTable> list = new ArrayList<TournamentGroupTable>();
		
		list.addAll(getGroupTables().values());
		list.sort(new Comparator<TournamentGroupTable>() {
			@Override
			public int compare(TournamentGroupTable o1, TournamentGroupTable o2) {
				try {
					return o1.getTournamentGroup().getName().compareToIgnoreCase(o2.getTournamentGroup().getName());
				} catch (Exception e) {
					logger.error(e);
					return 0;
				}
			}
		});
		
		return list;
	}

	public List<Team> getTeams() {

		List<Team> list = new ArrayList<Team>();
		
		if (getTournamentGroups()==null)
			return list;
		
		for (TournamentGroup g: getTournamentGroups()) {
			list.addAll(g.getTeams());
		}
		
		list.sort(new Comparator<Team>() {
			@Override
			public int compare(Team o1, Team o2) {
				try {
					return o1.getName().compareToIgnoreCase(o2.getName());
				} catch (Exception e) {
					logger.error(e);
				}
				return 0;
			}
		});
		return list;
	}

	public void calculateTables() {
		setGroupTables(new HashMap<TournamentGroup, TournamentGroupTable>());
		for (TournamentGroup group: this.getTournamentGroups()) {
			 TournamentGroupTable table = new TournamentGroupTable(group, getSchedule()); 
			 table.calculate();
			  getGroupTables().put(group, table);
		}
		
		logger.debug("tables done");
	}


}
