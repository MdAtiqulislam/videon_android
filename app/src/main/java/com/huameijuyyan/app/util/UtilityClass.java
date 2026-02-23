package com.huameijuyyan.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class UtilityClass {

    public static String getDateStringFromDateValue(String formServerDateValue, String dateFormat) {
        String dateString = "";
        try {
            String pattern = "yyyy-MM-dd hh:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = simpleDateFormat.parse(formServerDateValue);


            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(dateFormat);
            dateString = simpleDateFormat2.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return dateString;//DateFormat.format(dateFormat, getTimestampInMili(dateValue)).toString();
    }

    /**
     * Convert time to local time
     *
     * @param dateFromServer date From Server
     * @return String local time
     */
    public static String convertTimeToLocalTime(String dateFromServer) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = null;
        try {
            date = df.parse(dateFromServer);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        df.setTimeZone(TimeZone.getDefault());
        return df.format(date);


    }




}
