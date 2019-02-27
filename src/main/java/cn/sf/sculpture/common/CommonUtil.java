package cn.sf.sculpture.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


/**
 * @author sq mail:feifanhyx@foxmail.com
 * @date 2018/08/28
 */
public class CommonUtil {

    
	public static String getNow() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public static LocalDateTime parserTime(String time) {
	    return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	

    public static LocalDate parserDate(String time) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
	
	public static String formatter(LocalDateTime date ) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return date.format(formatter);
	}
	
	public static String formatter(LocalDate date ) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return date.format(formatter);
	}
	

   public static String convertWeek(String time) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date date = null;
        try {
            date = f.parse(time);
        } catch (ParseException e) {
            return null;
        }
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	
   
   
   public static String convertHours(Double hours){
       int day =  (int)Math.floor( hours / 8 );
       int hour = (int)(hours % 8);
       
       return day + "天" + hour + "小时";
   }

   public static String convertFm(String time) {
       ZoneId zone = ZoneId.systemDefault();
       Instant instant = CommonUtil.parserTime(time).atZone(zone).toInstant();
       Date date = Date.from(instant);
      
       SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
       return dateFm.format(date);
   }

}
