package com.roland.syslog.model;
import java.util.*;
import java.util.regex.*;
import java.text.*;

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

		int pos = initTimeStamp();
		pos = initSeverity();

		initLogText(pos);
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

    private int initTimeStamp(){
		this.timeStamp = DateTimeHelper.getDataTime(this.originalText);
		this.fields.add(Fields.TimeStamp);
		return DateTimeHelper.getLastMatchedPosition();
    }

    private int initSeverity(){
		this.severity = SeverityHelper.getSeverity(this.originalText);
		this.fields.add(Fields.Severity);
		return SeverityHelper.getLastMatchedPosition();
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
 
    private void initLogText(int pos){
		pos = trimSpace(pos);
		logText = this.originalText.substring(pos);
		this.fields.add(Fields.Text);
    }
 
    private int trimSpace(int pos){
		while(pos < originalText.length() && originalText.charAt(pos++) == ' ');
		return --pos;
	}

}
