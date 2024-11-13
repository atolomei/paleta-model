package io.paleta.model;

import java.io.Serializable;
import java.util.List;

public class GroupTable extends JsonObject implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	TournamentGroup zone;
	List<TablePosition> list;
	
	public GroupTable(TournamentGroup zone) {
		this.zone=zone;
	}
	
	public TournamentGroup getTournamentZone() {
		return zone;
	}
	

}
