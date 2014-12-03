package mx.skyguardian.controltower.util;

import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.util.PropertyPlaceholderHelper;

public class AppUtils {
	private static Logger log = Logger.getLogger(AppUtils.class);
	private static final int MAX_SECOND = 59;
	private static final int MAX_MINUTE = 59;
	private static final int MAX_HOUR = 23;
	
	private AppUtils(){}
	
	public static String getFormattedDate(String format, Long epoch){
		String date = new java.text.SimpleDateFormat(format).format(new java.util.Date (epoch*1000));
		return date;
	}
	
	public static String getTimeFrom(Integer timeTo, String interval){
		Long intervalSec = Long.valueOf(interval) * 3600;
				
		return String.valueOf(timeTo-intervalSec);
	}
	
	public static String getURL(String url, Map<String, String> propertiesMap) {
		PropertyPlaceholderHelper h = new PropertyPlaceholderHelper("$[", "]");
		Properties p = new Properties();

		for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
			p.setProperty(entry.getKey(), entry.getValue());
		}

		return h.replacePlaceholders(url, p);
	}
	
	public static Long getTimeFrom(String timeZone){
		TimeZone defaultTimeZone = TimeZone.getDefault();
		DateTime fromDate;
		long posixTime = 0L;
		
		if (!defaultTimeZone.getID().equalsIgnoreCase(timeZone)){
			Calendar datetimeWithTZ = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
 
		    int offset = datetimeWithTZ.getTimeZone().getOffset(datetimeWithTZ.getTimeInMillis());
		    fromDate =  new DateTime(DateTimeZone.UTC );
			fromDate = fromDate.plus(offset);
			fromDate = setTo0Hrs(fromDate);
			fromDate = fromDate.minus(offset);
			posixTime = fromDate.getMillis();
			
		} else {
			fromDate = new DateTime();
			fromDate = setTo0Hrs(fromDate);
			posixTime = fromDate.getMillis();
		}
		
		return posixTime/1000;
	}
	
	public static Long getTimeTo(String timeZone){
		TimeZone defaultTimeZone = TimeZone.getDefault();
		DateTime toDate;
		long posixTime = 0L;
		
		if (!defaultTimeZone.getID().equalsIgnoreCase(timeZone)){
			Calendar datetimeWithTZ = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
			 
		    int offset = datetimeWithTZ.getTimeZone().getOffset(datetimeWithTZ.getTimeInMillis());
		    toDate =  new DateTime(DateTimeZone.UTC );
		    toDate = toDate.plus(offset);
		    toDate = setTo023_59Hrs(toDate);
		    toDate = toDate.minus(offset);
			posixTime = toDate.getMillis();
			
		} else {
			toDate = new DateTime();
			toDate = setTo023_59Hrs(toDate);
			posixTime = toDate.getMillis();
		}
		
		return posixTime/1000;
	}
	
	private static DateTime setTo0Hrs(DateTime dateTime) {
		int sec = dateTime.getSecondOfMinute();
		int min = dateTime.getMinuteOfHour();
		int hr = dateTime.getHourOfDay();
		
		dateTime=dateTime.minusSeconds(sec);
		dateTime=dateTime.minusMinutes(min);
		dateTime=dateTime.minusHours(hr);
		
		return dateTime;
	}	
	
	private static DateTime setTo023_59Hrs(DateTime dateTime) {
		int sec = MAX_SECOND-dateTime.getSecondOfMinute();
		int min = MAX_MINUTE-dateTime.getMinuteOfHour();
		int hr = MAX_HOUR-dateTime.getHourOfDay();
				
		dateTime=dateTime.plusSeconds(sec);
		dateTime=dateTime.plusMinutes(min);
		dateTime=dateTime.plusHours(hr);
		
		return dateTime;
	}
	
		
	public static void main (String ... args){
		System.err.println(false);
	}

}
