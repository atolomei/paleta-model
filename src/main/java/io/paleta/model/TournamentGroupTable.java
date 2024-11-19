package io.paleta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.paleta.logging.Logger;
import io.paleta.model.schedule.Schedule;


public class TournamentGroupTable extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;

	static private Logger logger = Logger.getLogger(TournamentGroupTable.class.getName());

	@JsonIgnore
	private TournamentGroup group;
	
	@JsonIgnore
	private Schedule schedule;
	
	@JsonIgnore
	private  Map<Team, TablePosition> map;
	
	@JsonIgnore
	private List<TablePosition> table;
	
	

	public String toJSON() {
	
	StringBuilder str = new StringBuilder();
		
	try {
		str.append("{\"group\":\"" + group.getName()+ "\"");
		if (table!=null) {
			str.append(", \"table\":[");
			boolean first= true;
				
			for (TablePosition tp: table) {
				if (!first)
					str.append(", ");
				
				str.append(tp.toJSON());
				first=false;
				}
			str.append("]");
		}
		else {
			str.append("{\"table\":null");
		}
		
	} catch (Exception e){
		logger.error(e);
		return "\"error\":\"" + e.getClass().getName()+ " | " + e.getMessage()+"\"";
	}
	
		return str.toString();
		
	}
	
	
	/**
	 * 
	 * @param group
	 * @param schedule
	 */
	public TournamentGroupTable(TournamentGroup group, Schedule schedule) {
		this.group=group;
		this.schedule=schedule;
		this.table=new ArrayList<TablePosition>();
	}
	
	
	
	public void calculate() {
		
		this.map = new HashMap<Team, TablePosition>();
		
		for (Match match: this.schedule.getMatchesClasificacion()) {
			
			if (match.getTournamentGroup().equals(getTournamentGroup())) {
				
				if (!this.map.containsKey(match.local))
					this.map.put(match.local, new TablePosition(match.local));
				
				if (!this.map.containsKey(match.visitor))
					this.map.put(match.visitor, new TablePosition(match.visitor));
				
				if (match.isCompleted()) {
					
					TablePosition local_pos = map.get(match.local);
					TablePosition visitor_pos = map.get(match.visitor);
					
					local_pos.partidosJugados +=1;
					visitor_pos.partidosJugados +=1;
					
					if (match.getResult()==MatchResult.LOCAL) {
				
						local_pos.partidosGanados+=1;
						visitor_pos.partidosPerdidos+=1;
						local_pos.puntos += 2;
						
					}
					else if (match.getResult()==MatchResult.VISITOR) {
						
						visitor_pos.partidosGanados+=1;
						local_pos.partidosPerdidos+=1;
						visitor_pos.puntos += 2;
						
					}
					
					
					for (PaletaSet set:match.getSets()) {
						
						local_pos.tantosFavor+=set.puntosLocal;
						local_pos.tantosContra+=set.puntosVisitante;
						
						if (set.isLocalWinner()) {
							local_pos.setGanados +=1;
							visitor_pos.setPerdidos +=1;
						
						}
						else if (set.isVisitorWinner()) {
							visitor_pos.setGanados +=1;
							local_pos.setPerdidos +=1;
						}
						
						visitor_pos.tantosFavor+=set.puntosVisitante;
						visitor_pos.tantosContra+=set.puntosLocal;
					}
					
					
					local_pos.difTantos=local_pos.tantosFavor-local_pos.tantosContra;
					visitor_pos.difTantos=visitor_pos.tantosFavor-visitor_pos.tantosContra;
					
					local_pos.difSets=local_pos.setGanados-local_pos.setPerdidos;
					visitor_pos.difSets=visitor_pos.setGanados-visitor_pos.setPerdidos;
					
				}
			}
		}
		
		for (Team team: this.map.keySet()) {
			this.table.add(this.map.get(team));
		}
		
		this.table.sort(new Comparator<TablePosition>() {

			@Override
			public int compare(TablePosition o1, TablePosition o2) {
				
				try {
					
					
					if (o1.getPuntos()>o2.getPuntos())
						return -1;
					
					if (o1.getDifSets()>o2.getDifSets())
						return -1;
					
					if (o1.getDifTantos()>o2.getDifTantos())
						return -1;

					if (o1.getSetGanados()>o2.getSetGanados())
						return -1;
					

					
					if (o1.getPuntos()<o2.getPuntos())
						return 1;
					
					if (o1.getDifSets()<o2.getDifSets())
						return 1;

					if (o1.getDifTantos()<o2.getDifTantos())
						return 1;

					
					
					// si todo esta igual sets ganados
					if (o1.getSetGanados()<o2.getSetGanados())
						return 1;
					
					if (o1.getSetGanados()>o2.getSetGanados())
						return -1;

					
					// si todo esta igual tantos a favor
					if (o1.getTantosFavor()>o2.getTantosFavor())
						return -1;

					if (o1.getTantosFavor()<o2.getTantosFavor())
						return 1;
					
					// si todo es igual. #partidos jugados
					// el que jugo menos va arriba
					if (o1.getPartidosJugados()<o2.getPartidosJugados())
						return -1;
					
					if (o1.getPartidosJugados()>o2.getPartidosJugados())
						return 1;
					
					// si todo es igual. alfabetico
					return (o1.team.getName().compareToIgnoreCase( o2.team.getName() ));
					
				} catch (Exception e) {
					logger.error(e);
					return 0;
				}
			}
		});
	}
	
	
	public TournamentGroup getTournamentGroup() {
		return group;
	}

	public void setTournamentGroup(TournamentGroup group) {
		this.group = group;
	}

	public List<TablePosition> getTable() {
		return table;
	}

	public void setTable(List<TablePosition> table) {
		this.table = table;
	}



}
