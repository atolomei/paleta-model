package io.paleta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.paleta.util.Check;

public class Team extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private List<String> players;
	
	
	public Team(String name) {
		this.name=name;
		this.players=new ArrayList<String>();
	}
	
	public Team(String name, List<String> list) {
		Check.requireNonNullArgument(name, "name is null");
		Check.requireNonNullArgument(list, "list can not be null");
		this.name=name;
		this.players=new ArrayList<String>();
		list.forEach(item -> players.add(item.trim()));
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
	}

	public String getPlayersStr() {
		if (players==null)
			return "";
		return String.join(", ", players);
		
	}
	public String getName() {
		return name;
	}
	
	
	public List<String> getPlayers() {
		return players;
	}
}
