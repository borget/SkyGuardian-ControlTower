package mx.skyguardian.controltower.util;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.log4j.Logger;
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
	
	public static Long getFromDatetime(String serverTime){
		
		TimeZone defaultTimeZone = TimeZone.getDefault();
		
		GregorianCalendar fromDate;
		
		if (!defaultTimeZone.getID().equalsIgnoreCase("America/Mexico_City")){
			TimeZone mxTimeZone = TimeZone.getTimeZone("America/Mexico_City");
			fromDate = (GregorianCalendar)GregorianCalendar.getInstance(mxTimeZone);
					
			int offSet = mxTimeZone.getOffset(fromDate.getTimeInMillis());
			
			fromDate.add(GregorianCalendar.MILLISECOND, offSet);
			System.out.println("ForcedTimeZone:"+fromDate.getTime());
		} else {
			fromDate = (GregorianCalendar)GregorianCalendar.getInstance();
			System.out.println("Default:"+fromDate.getTime());
		}
		
		fromDate.add(GregorianCalendar.SECOND, -fromDate.get(GregorianCalendar.SECOND));
		fromDate.add(GregorianCalendar.MINUTE, -fromDate.get(GregorianCalendar.MINUTE));
		fromDate.add(GregorianCalendar.HOUR_OF_DAY, -fromDate.get(GregorianCalendar.HOUR_OF_DAY));
		
		System.out.println("Final:"+fromDate.getTime());
		
		return fromDate.getTimeInMillis()/1000;
		
	}
	
public static Long getToDatetime(String serverTime){
		
		TimeZone defaultTimeZone = TimeZone.getDefault();
		
		GregorianCalendar baseDate;
		
		if (!defaultTimeZone.getID().equalsIgnoreCase("America/Mexico_City")){
			TimeZone mxTimeZone = TimeZone.getTimeZone("America/Mexico_City");
			baseDate = (GregorianCalendar)GregorianCalendar.getInstance(mxTimeZone);
					
			int offSet = mxTimeZone.getOffset(baseDate.getTimeInMillis());
			
			baseDate.add(GregorianCalendar.MILLISECOND, offSet);
			System.out.println("ForcedTimeZone:"+baseDate.getTime());
		} else {
			baseDate = (GregorianCalendar)GregorianCalendar.getInstance();
			System.out.println("Default:"+baseDate.getTime());
		}
		
		int second = MAX_SECOND-baseDate.get(GregorianCalendar.SECOND);
		int minute = MAX_MINUTE-baseDate.get(GregorianCalendar.MINUTE);
		int hour = MAX_HOUR-baseDate.get(GregorianCalendar.HOUR_OF_DAY);
				
		baseDate.add(GregorianCalendar.SECOND, second);
		baseDate.add(GregorianCalendar.MINUTE, minute);
		baseDate.add(GregorianCalendar.HOUR_OF_DAY, hour);
		
		System.out.println("Final:"+baseDate.getTime());
		
		return baseDate.getTimeInMillis()/1000;
		
	}
	
	public static void main (String ... args){
		
		System.err.println(getToDatetime(String.valueOf(1397580661)));
	}

}
