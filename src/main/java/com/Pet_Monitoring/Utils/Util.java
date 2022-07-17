package com.Pet_Monitoring.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class Util {

	public static Date dateNow() {
		LocalDateTime localDateTime = LocalDateTime.now();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}

	public static byte[] extractBytes(String ImagePath) throws IOException {
	    	URL url = new URL(ImagePath);
	        URLConnection conn = url.openConnection();
	        conn.setConnectTimeout(5000);
	        conn.setReadTimeout(5000);
	        conn.connect(); 

	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        IOUtils.copy(conn.getInputStream(), baos);

	        return baos.toByteArray();
	}

}
