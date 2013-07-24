package com.roland.syslog.model;

import java.io.*;

public class SyslogFileReader {
	public static LogItemsContainer read(String fileName) {
		FileReader file = null;
		LogItemsContainer allItems = new LogItemsContainer();

		try {
			file = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(file);
			String line = "";
			while ((line = reader.readLine()) != null) {
				LogItem item = new LogItem(line);
				allItems.add(item);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					// Ignore issues during closing
				}
			}
		}
		return allItems;
	}
	
	public static void main(String args[]){
		LogItem item = new LogItem("Sep  9 14:47:01.356376 notice CSCP-1 crond[11196]: (root) CMD (nice /opt/nokiasiemens/bin/timestamp.sh)");
		System.out.println(item.getLogText());
	}
}
