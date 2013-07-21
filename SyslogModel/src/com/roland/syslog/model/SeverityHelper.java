package com.roland.syslog.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeverityHelper {
	public final static String SEVERITY_PATTERN = "((?i)emerg|alert|crit|err|warn|notice|info|debug)";
	public static Severity getSeverity(String string){
		lastMatchedPosition = 0;
		Matcher matcher = severityPattern.matcher(string);
		if(matcher.find()){
			lastMatchedPosition = matcher.end();
			return Severity.fromString(matcher.group());
		}else{
			return null;
		}
	}

    static public int getLastMatchedPosition(){
    	return lastMatchedPosition;
    }

    static private int lastMatchedPosition;
	static private Pattern severityPattern;
	private static final SeverityHelper instance = new SeverityHelper();
	
	private SeverityHelper(){
		severityPattern = Pattern.compile(SEVERITY_PATTERN);
	}
	
}
