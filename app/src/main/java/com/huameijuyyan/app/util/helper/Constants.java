package com.huameijuyyan.app.util.helper;

public class Constants {
    /*
    * It is for featured ,most recent and most popular video pages
    * */
    public interface SeeAllVideos{
        String FEATURED_VIDEO_PAGE="featured video page";
        String MOST_RECENT_VIDEO_PAGE="most recent video page";
        String MOST_POPULAR_VIDEO_PAGE="most popular video page";
        String LIVE_TV="live_tv";
        String CATEGORY_PAGE="category_page";
        int NOT_CATEGORY =-1;
    }

    public interface AddAppIDForad{
        String APP_ID="ca-app-pub-3940256099942544~3347511713";
        String API_TOKEN = "www";
    }

    public interface ServerUrl {
        String API_TOKEN = "www";
        String MAIN_URL = "https://theme1.w3engineers.com/vid/public/";
        String FULL_IMAGE_URL = MAIN_URL + "uploads/";
        String THUMB_IMAGE_URL = MAIN_URL + "uploads/thumb/";

    }

    public interface IntentKey {
        String PAYMENT_RESPONSE = "pay";
        String TOTAL_AMOUNT = "amount";
        String PAYMENT_METHOD = "payment_method";
        String FLAG_ORDER = "success";
        String FLAG_REVIEW_GIVEN="review_given";
        public String INTENT_SLIDER_ID = "category_id";
        String ITEM_ID = "item_id";
        String IS_ORDERED = "ordered";
    }

    public interface DefaultValue {
        int WELCOME_DELAY = 1000;
        String REMEMBER_ME = "yes";
        int DELAY_INTERVAL_VISIBILITY = 60000;
        int DELAY_INTERVAL = 1000;
        int MINIMUM_LENGTH_PASS = 6;
        int PIN_CODE_LIMIT = 4;
        int MAXIMUM_LENGTH = 20;
        long REFREASH_LOCATION = 300000;
        long DISTANCE_LOCATION = 500;
        int MINIMUM_LENGTH_TEXT = 2;
        int LOADER_DELAY = 500;
        String STATUS_ON = "1";
        float RATING_ZERO = 0;
        String CURRENCY_APPEND="1";
        String CURRENCY_PREPEND="2";
        String FEATURE="FEATURE";
        String RECENT="RECENT";
        String POPULAR="POPULAR";
    }

    public interface DateFormat {
        String GMT = "GMT";
        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        String DATE_FORMAT_VALIDITY = "dd MMM, yyyy";
        String DATE_WITH_MONTH_FIRST = "MMMM dd, yyyy";
        String DATE_FORMAT_TIME = "h:mm a, dd MMM, yyyy";
    }
}
