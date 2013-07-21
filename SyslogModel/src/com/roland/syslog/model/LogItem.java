package com.roland.syslog.model;
import java.util.*;

import hirondelle.date4j.DateTime;

/**
 * @author DingLi
 * Class is used to record one line of syslog.
 */
public class LogItem {
	/**
	 * Used to indicates the log items contains how man fields.
	 */
	public enum Fields{
		TimeStamp, Severity, RU, PRB, Text
	}
	public LogItem(String plainText){
		this.originalText = plainText;
		fields = EnumSet.noneOf(Fields.class);

		initBasicLogItems();
		initIPALogItems();
	}

	public DateTime getTimeStamp(){
    	return timeStamp;
    }
	
	public Severity getSeverity(){
		return severity;
	}
	
	public String getLogText(){
		return logText;
	}
	
	public String getRU(){
		return ru;
	}
	
	public String getPRB(){
		return prb;
	}
	
    private String originalText;
    private DateTime timeStamp;
    private Severity severity;
    private String ru;
    private String prb;
    private EnumSet<Fields> fields;
    private String logText; //After all meaningful fields are chopped out, the pure text   

    private boolean initBasicLogItems(){
    	boolean result = BasicLogHelper.analyze(originalText);
    	if(result){
    		this.timeStamp = BasicLogHelper.getDataTime();
    		this.fields.add(Fields.TimeStamp);
    		
    		this.severity = BasicLogHelper.getSeverity();
    		this.fields.add(Fields.Severity);
    		
    		this.logText = BasicLogHelper.getLogText();
    		this.fields.add(Fields.Text);
    	}
    	return result;
    }
    
    private boolean initIPALogItems(){
    	boolean result = IPALogHelper.analyze(logText);
    	if(result){
    		this.ru = IPALogHelper.getRU();
    		this.fields.add(Fields.RU);
    		
    		this.prb = IPALogHelper.getPRB();
    		this.fields.add(Fields.PRB);
    		
    		this.logText = IPALogHelper.getLogText();
    	}
    	return result;
    }
}
