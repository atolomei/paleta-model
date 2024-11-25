package io.paleta.model;

import java.util.ArrayList;
import java.util.List;

public enum MatchResult {
	
	LOCAL( 1, "L"),
	VISITOR(2, "V"),
	DRAW(3, "D");
	
	static List<MatchResult> results;
	
	private String name;
	private int code;
	
	
	
	public static MatchResult fromId(String id) {
		
		if (id==null)
			throw new IllegalArgumentException("id is null");
			
		try {
			return get(Integer.valueOf(id.trim()).intValue());
					
		} catch (IllegalArgumentException e) {
			throw (e);
		}	
		catch (Exception e) {
			throw new IllegalArgumentException("id not integer -> " + id);
		}
	}
	
	static MatchResult get(int v) {
		if (v==LOCAL.code) return LOCAL;
		if (v==VISITOR.code) return VISITOR;
		return DRAW;
	}

	public static List<MatchResult> getValues() {
		
		if (results!=null)
			return results;
		
		results = new ArrayList<MatchResult>();
		
		results.add( LOCAL );
		results.add( VISITOR); 
		results.add( DRAW );

		return results;
	}
	
	
	public String toJSON() {
		StringBuilder str = new StringBuilder();
		str.append("\"name\": \"" + name + "\"");
		str.append(", \"code\": " + code );
		return str.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.getClass().getSimpleName() +"{");
		str.append(toJSON());
		str.append("}");
		return str.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public int getCode() {
		return code;
	}


	private MatchResult(int code, String name) {
		this.code=code;
		this.name=name;
	}
	

}
