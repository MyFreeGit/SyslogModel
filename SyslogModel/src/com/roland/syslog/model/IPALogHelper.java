package com.roland.syslog.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPALogHelper {
	public final static String IPA_LOG_PATTERN = "(^[A-Za-z]+\\-[0-9]+)\\W*([A-Za-z]+)\\:\\W*(.*)";
	public static boolean analyze(String text){
		matcher = ipaPattern.matcher(text);
		return matcher.find();
	}
	
	public static String getRU(){
		return matcher.group(1);
	}
	
	public static String getPRB(){
		return matcher.group(2);
	}
	
	public static String getLogText(){
		return matcher.group(3);
	}
	
	private static Pattern ipaPattern;
	private static Matcher matcher;

	static {
		new IPALogHelper();
	}

	private IPALogHelper(){
		ipaPattern = Pattern.compile(IPA_LOG_PATTERN);
	}
	
}