package com.w3engineers.core.videon.ui;

import android.content.Intent;
import android.net.Uri;

import com.google.firebase.messaging.FirebaseMessaging;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;
import com.w3engineers.core.videon.ui.home.HomeActivity;
import com.w3engineers.ext.strom.App;

public class VideonApplication extends App {

    private static VideonApplication mInstance;

    public VideonApplication(){
        mInstance=this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().subscribeToTopic("message");
        mInstance=this;

        // OneSignal Initialization (Migrated to 4.x)
        OneSignal.initWithContext(this);
        OneSignal.setAppId("05a27b1c-919b-4c28-972c-4331b5aa2c33");
        OneSignal.setNotificationOpenedHandler(new NotificationOpenedHandler());

        ///OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.DEBUG);
    }

    public class NotificationOpenedHandler implements OneSignal.OSNotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenedResult result) {
           String launchUrl = result.getNotification().getLaunchURL();
           if (launchUrl != null && !launchUrl.isEmpty()){
               Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
               notificationIntent.setData(Uri.parse(launchUrl));
               notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(notificationIntent);
           } else {
               Intent intent = new Intent(VideonApplication.this, HomeActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
               startActivity(intent);
           }
        }
    }


    public static synchronized VideonApplication getInstance(){
        return mInstance;
    }
}
