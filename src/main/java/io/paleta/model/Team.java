package io.paleta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.paleta.util.Check;

public class Team extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private List<String> players;
	
	
	public Team(String name) {
		Check.requireNonNullArgument(name, "name is null");
		this.id=name.toLowerCase().trim();
		this.name=name;
	}
	
	
	public Team(String id, String name) {
		this.id=id;
		this.name=name;
		this.players=new ArrayList<String>();
	}
	
	public Team(String name, List<String> list) {
		Check.requireNonNullArgument(id, "id is null");	
		Check.requireNonNullArgument(name, "name is null");
		Check.requireNonNullArgument(list, "list can not be null");
		this.name=name;
		this.id=name.toLowerCase().trim();
		init(list);
	}
	
	public Team(String id, String name, List<String> list) { 
		Check.requireNonNullArgument(id, "id is null");	
		Check.requireNonNullArgument(name, "name is null");
		Check.requireNonNullArgument(list, "list can not be null");
		this.name=name;
		this.id=id;
		init(list);
	}
	
	public String getId() {
		return id;
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
	
	private void init(List<String> list) {
		this.players=new ArrayList<String>();
		list.forEach(item -> players.add(item.trim()));
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});		
	}


}
