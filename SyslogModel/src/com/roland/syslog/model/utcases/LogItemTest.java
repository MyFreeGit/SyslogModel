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
