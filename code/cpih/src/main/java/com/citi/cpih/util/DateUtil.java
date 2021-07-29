package com.citi.cpih.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class DateUtil { 
	
private DateUtil() {
		
	}
	
	public static String formatDate(Date date) {
		try {
			DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormatter.format(date);
		} catch (Exception exeption) {
			return null;
		}
	}
	
	public static String formatDateFull(Date date) {
		try {
			DateFormat dateFormatterFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			return dateFormatterFull.format(date);
		} catch (Exception exeption) {
			return null;
		}
	}
	
	public static Date formatDateFull(Date date, String formatComplement) {
		try {
			DateFormat dateFormatterFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String newDate = formatDate(date);
			newDate = newDate + formatComplement;
			return dateFormatterFull.parse(newDate);
		} catch (ParseException exeption) {
			return null;
		}
	}
	
	public static Date formatDateFull(String date, String formatComplement) {
		try {
			DateFormat dateFormatterFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			date = date + formatComplement;
			return dateFormatterFull.parse(date);
		} catch (ParseException exeption) {
			return null;
		}
	}
	
	public static String formatDateLog(Date creationDate) {
		DateFormat dateFormatterFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append(dateFormatterFull.format(creationDate)).append("|").append(dateFormatterFull.format(new Date()));
		return sb.toString();
	}
	
	public static String formatProductExpiration(String expiration) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			Date exp = sdf.parse(expiration);
			return formatDate(exp);
		} catch (ParseException exeption) {
			return formatDate(new Date());
		}
	}
	
}