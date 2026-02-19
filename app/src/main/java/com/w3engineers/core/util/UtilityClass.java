package com.w3engineers.core.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.w3engineers.core.util.helper.Constants;
import com.w3engineers.ext.strom.util.helper.data.local.SharedPref;

import org.apache.commons.collections4.Get;

import java.lang.reflect.Type;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
