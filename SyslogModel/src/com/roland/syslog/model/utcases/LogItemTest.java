package com.roland.syslog.model.utcases;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;

import com.roland.syslog.model.*;
import hirondelle.date4j.DateTime;

public class LogItemTest {
	@BeforeClass
	public static void testSetup() {
	}

    @Test
    public void testDateTimeHelper(){
    	assertEquals("01", DateTimeHelper.monthAbbrToNumber("Jan"));
    	assertEquals("02", DateTimeHelper.monthAbbrToNumber("Feb"));
    	assertEquals("03", DateTimeHelper.monthAbbrToNumber("Mar"));
    	assertEquals("04", DateTimeHelper.monthAbbrToNumber("Apr"));
    	assertEquals("05", DateTimeHelper.monthAbbrToNumber("May"));
    	assertEquals("06", DateTimeHelper.monthAbbrToNumber("Jun"));
    	assertEquals("07", DateTimeHelper.monthAbbrToNumber("Jul"));
    	assertEquals("08", DateTimeHelper.monthAbbrToNumber("Aug"));
    	assertEquals("09", DateTimeHelper.monthAbbrToNumber("Sep"));
    	assertEquals("10", DateTimeHelper.monthAbbrToNumber("Oct"));
    	assertEquals("11", DateTimeHelper.monthAbbrToNumber("Nov"));
    	assertEquals("12", DateTimeHelper.monthAbbrToNumber("Dec"));
    }
    
	@Test
	public void testLogItem() {
		String line = "Apr  9 14:47:30.034099 info CFPU-0 fmpprb: RU:OMU-0|PNAME:IL_Fmpprb|LPID:20121|PID:0x677 0x2 0x0|TIME:2013-04-09 14:47:30:033957|TEXT:data for pm9 is ready now|DATA:";
		LogItem item = new LogItem(line);
		DateTime dt = new DateTime("2012-04-09 14:47:30.034099");
		assertTrue(dt.equals(item.getTimeStamp()));
		assertEquals(item.getSeverity(), Severity.info);
		assertTrue(item.getRU().equals("CFPU-0"));
		assertTrue(item.getPRB().equals("fmpprb"));
		assertTrue(item.getLogText().equals("RU:OMU-0|PNAME:IL_Fmpprb|LPID:20121|PID:0x677 0x2 0x0|TIME:2013-04-09 14:47:30:033957|TEXT:data for pm9 is ready now|DATA:"));
	}

}
