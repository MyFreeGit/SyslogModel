package com.roland.syslog.model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hirondelle.date4j.DateTime;

public class DateTimeHelper {
	/*Syslog isn't contains year information, so all year information is set to 2012 for simplicity*/
	public final static String defaultYear = "2012"; 
    public final static String DATETIME_PATTERN = "([a-zA-Z]+)\\s*([0-9]+)\\s*([0-9]+:[0-9]+:[0-9]+\\.[0-9]+)";

    public static DateTime getDataTime(String str){
    	lastMatchedPosition = 0;
    	Matcher matcher = dateTimePattern.matcher(str);
    	if(matcher.find()){
    		StringBuilder sb = new StringBuilder(64);
    		String month = DateTimeHelper.monthAbbrToNumber(matcher.group(1));
    		sb.append("2012-").append(month).append("-");
    		if(matcher.group(2).length() == 1){
    			sb.append("0");
    		}
    		sb.append(matcher.group(2)).append(" ").append(matcher.group(3));
    		lastMatchedPosition = matcher.end();
    		return new DateTime(sb.toString());
    	}
    	return null;
    }
    
    static public int getLastMatchedPosition(){
    	return lastMatchedPosition;
    }
    static private int lastMatchedPosition;
    static private Map<String, String> lookupTable;
	static private Pattern dateTimePattern;
	
	private static final DateTimeHelper instance = new DateTimeHelper();
	private DateTimeHelper(){
		lookupTable = new HashMap<String, String>();
		lookupTable.put("JAN", "01");
		lookupTable.put("FEB", "02");
		lookupTable.put("MAR", "03");
		lookupTable.put("APR", "04");
		lookupTable.put("MAY", "05");
		lookupTable.put("JUN", "06");
		lookupTable.put("JUL", "07");
		lookupTable.put("AUG", "08");
		lookupTable.put("SEP", "09");
		lookupTable.put("OCT", "10");
		lookupTable.put("NOV", "11");
		lookupTable.put("DEC", "12");

		dateTimePattern = Pattern.compile(DATETIME_PATTERN);
	}
    public static final String monthAbbrToNumber(String month){
    	return lookupTable.get(month.toUpperCase());
    }


}
