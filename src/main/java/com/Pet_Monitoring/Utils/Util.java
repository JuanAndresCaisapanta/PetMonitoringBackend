package com.Pet_Monitoring.Utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Util {
	
	public static Date dateNow () {
		LocalDateTime localDateTime = LocalDateTime.now();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}

}
