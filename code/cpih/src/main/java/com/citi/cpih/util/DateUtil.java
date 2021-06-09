package com.citi.cpih.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class DateUtil { 
	
	private static DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat dateFormatterFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public static String formatDate(Date date) {
		try {
			return dateFormatter.format(date);
		} catch (Exception exeption) {
			return null;
		}
	}
	
	public static String formatDateFull(Date date) {
		try {
			return dateFormatterFull.format(date);
		} catch (Exception exeption) {
			return null;
		}
	}
	
	public static Date formatDateFull(Date date, String formatComplement) {
		try {
			String newDate = formatDate(date);
			newDate = newDate + formatComplement;
			return dateFormatterFull.parse(newDate);
		} catch (ParseException exeption) {
			return null;
		}
	}
	
	public static Date formatDateFull(String date, String formatComplement) {
		try {
			date = date + formatComplement;
			return dateFormatterFull.parse(date);
		} catch (ParseException exeption) {
			return null;
		}
	}
	
	
	
}