package com.pavikumbhar.springboot.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.springframework.stereotype.Component;

/**
 *
 * @author pravinkumbhar
 */

@Component
public class TimestampConvertor {

    private String usingDateAndCalendar(long input) {
        Date date = new Date(input);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return (cal.get(Calendar.YEAR)
                + "/" + cal.get(Calendar.MONTH)
                + "/" + cal.get(Calendar.DATE)
                + " " + cal.get(Calendar.HOUR)
                + ":" + cal.get(Calendar.MINUTE)
                + ":" + cal.get(Calendar.SECOND)
                + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));

    }

    public String usingDateAndCalendarWithTimeZone(long input) {
        Date date = new Date(input);
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        cal.setTime(date);
        return (cal.get(Calendar.YEAR)
                + "/" + cal.get(Calendar.MONTH)
                + "/" + cal.get(Calendar.DATE)
                + " " + cal.get(Calendar.HOUR)
                + ":" + cal.get(Calendar.MINUTE)
                + ":" + cal.get(Calendar.SECOND)
                + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));

    }

    public String usingDateFormatter(long input) {
        Date date = new Date(input);
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd hh:mm:ss z");
        sdf.setCalendar(cal);
        cal.setTime(date);
        return sdf.format(date);

    }

    public String usingDateFormatterWithTimeZone(long input) {
        Date date = new Date(input);
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd hh:mm:ss z");
        sdf.setCalendar(cal);
        cal.setTime(date);
        return sdf.format(date);

    }
    
     public  String convertTimeWithTimeZome(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        cal.setTimeInMillis(time);
        return (cal.get(Calendar.YEAR) + " " + (cal.get(Calendar.MONTH) + 1) + " "
                + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + cal.get(Calendar.MINUTE));

    }

    public  String convertLongTimeSatmpToDate(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        return format.format(date);
    }

    public static void main(String[] args) {
        long input = 1478834160000L;
        TimestampConvertor tsc = new TimestampConvertor();
        //Get output in system timezone
        System.out.println("usingDateAndCalendar in local timezone: " + tsc.usingDateAndCalendar(input));
        //Get output in GMT
        System.out.println("usingDateAndCalendarWithTimeZone: " + tsc.usingDateAndCalendarWithTimeZone(input));
        //Get output in system timezone
        System.out.println("usingDateFormatter in local timezone: " + tsc.usingDateFormatter(input));
        //Get output in GMT
        System.out.println("usingDateFormatterWithTimeZone: " + tsc.usingDateFormatterWithTimeZone(input));
    }

}
