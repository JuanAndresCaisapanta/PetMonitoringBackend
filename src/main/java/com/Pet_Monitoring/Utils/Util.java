package com.Pet_Monitoring.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Util {

	public static Date dateNow() {
		LocalDateTime localDateTime = LocalDateTime.now();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}

	public static byte[] extractBytes(String ImagePath) throws IOException {
		File fi = new File(ImagePath);
		byte[] fileContent = Files.readAllBytes(fi.toPath());
		return fileContent;
	}

}
