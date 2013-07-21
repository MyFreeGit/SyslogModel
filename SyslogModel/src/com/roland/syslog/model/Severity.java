package com.roland.syslog.model;

import java.util.HashMap;
import java.util.Map;

public enum Severity{
	emerg(0), alert(1), crit(2), err(3), warn(4), notice(5), info(6), debug(7);
	private int value;
	private Severity(int val){
		this.value = val;
	}
	
	public int getServity(){
		return value;
	}
	
	public static Severity fromString(String str){
		return lookupTable.get(str.toLowerCase());
	}

	static private Map<String, Severity> lookupTable = new HashMap<String, Severity>();
	static {
		for(Severity item: Severity.values()){
			lookupTable.put(item.name(), item);
		}		
	}
	
}
