package com.huameijuyyan.app.util.helper;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.FullScreenContentCallback;

import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.huameijuyyan.app.R;
import com.huameijuyyan.app.videon.data.local.apimodels.AdResponse;
import com.huameijuyyan.app.videon.data.remote.RemoteApiProvider;
import com.huameijuyyan.app.videon.data.remote.home.RemoteVideoApiInterface;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdHelper {
    static RemoteVideoApiInterface remoteVideoApiInterface;
    private static final String TAG = "MyActivity";

    private InterstitialAd interstitialAd;

    public static RemoteVideoApiInterface loadAdUnitsFromServer() {
        remoteVideoApiInterface = RemoteApiProvider.getInstance().getRemoteHomeVideoApi();
        return remoteVideoApiInterface;
    }

    public static void loadBannerAd(Context context, LinearLayout linearLayout) {

        remoteVideoApiInterface = loadAdUnitsFromServer();
        remoteVideoApiInterface.getAdUnits(ApiToken.GET_TOKEN(context)).enqueue(new Callback<AdResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<AdResponse> call, Response<AdResponse> response) {
                if (response.isSuccessful()) {

                    AdResponse adResponse = response.body();

                    ApplicationInfo ai = null;
                    try {
                        ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    ai.metaData.putString("com.google.android.gms.ads.APPLICATION_ID",adResponse.getData().getBannerId());


                    LinearLayout layout = linearLayout;
                    layout.setOrientation(LinearLayout.VERTICAL);

                    // Create a banner ad
                    AdView mAdView = new AdView(context);
                    mAdView.setAdSize(AdSize.SMART_BANNER);

                    // Create an ad request.
                    AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

                    // Optionally populate the ad request builder.
                    //adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);

                    // Add the AdView to the view hierarchy.
                    layout.addView(mAdView);



                    mAdView.setAdUnitId(adResponse.getData().getBannerUnitId());
                    // Start loading the ad.
                    mAdView.setBackgroundColor(context.getResources().getColor(R.color.white));
                    if(adResponse.getData().getBannerStatus().equals("1")) {
                        mAdView.loadAd(adRequestBuilder.build());
                    }
                }
            }

            @Override
            public void onFailure(Call<AdResponse> call, Throwable t) {
            }
        });

    }

    public void interstitialsAd(Context context, AdCloseListener adCloseListener) {
        remoteVideoApiInterface = loadAdUnitsFromServer();
        remoteVideoApiInterface.getAdUnits(ApiToken.GET_TOKEN(context)).enqueue(new Callback<AdResponse>() {
            @Override
            public void onResponse(Call<AdResponse> call, Response<AdResponse> response) {
                if (response.isSuccessful()) {
                    AdResponse adResponse = response.body();

                    ApplicationInfo ai = null;
                    try {
                        ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    ai.metaData.putString("com.google.android.gms.ads.APPLICATION_ID",adResponse.getData().getInterstitialId());

                    // Initialize the Mobile Ads SDK.

                    MobileAds.initialize(context, initializationStatus -> {});
                   /* MobileAds.initialize(context,
                            adResponse.getData().getInterstitialUnitId());*/

                    if (adResponse.getData().getInterstitialStatus().equals("1")) {
                        AdRequest adRequest = new AdRequest.Builder().build();
                        InterstitialAd.load(
                                context,
                                adResponse.getData().getInterstitialUnitId(),
                                adRequest,
                                new InterstitialAdLoadCallback() {
                                    @Override
                                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                        // The mInterstitialAd reference will be null until
                                        // an ad is loaded.
                                        AdHelper.this.interstitialAd = interstitialAd;
                                        Log.i(TAG, "onAdLoaded");
                                        //Toast.makeText(context, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                                        interstitialAd.setFullScreenContentCallback(
                                                new FullScreenContentCallback() {
                                                    @Override
                                                    public void onAdDismissedFullScreenContent() {
                                                        // Called when fullscreen content is dismissed.
                                                        // Make sure to set your reference to null so you don't
                                                        // show it a second time.
                                                        AdHelper.this.interstitialAd = null;
                                                        Log.d("TAG", "The ad was dismissed.");
                                                    }

                                                    @Override
                                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                                        // Called when fullscreen content failed to show.
                                                        // Make sure to set your reference to null so you don't
                                                        // show it a second time.
                                                        AdHelper.this.interstitialAd = null;
                                                        Log.d("TAG", "The ad failed to show.");
                                                    }

                                                    @Override
                                                    public void onAdShowedFullScreenContent() {
                                                        // Called when fullscreen content is shown.
                                                        Log.d("TAG", "The ad was shown.");
                                                    }
                                                });
                                    }

                                    @Override
                                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                        // Handle the error
                                        Log.i(TAG, loadAdError.getMessage());
                                        interstitialAd = null;

                                        String error =
                                                String.format(Locale.ROOT,
                                                        "domain: %s, code: %d, message: %s",
                                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                                        Toast.makeText(
                                                context, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                });
                    }



/*                    InterstitialAd mInterstitialAd = new InterstitialAd(context);
                    //      mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
                    mInterstitialAd.setAdUnitId(adResponse.getData().getInterstitialUnitId());
                    if (adResponse.getData().getInterstitialStatus().equals("1")) {
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                // Code to be executed when an ad finishes loading.
                                mInterstitialAd.show();
                            }

                            @Override
                            public void onAdFailedToLoad(int errorCode) {
                                // Code to be executed when an ad request fails.
                            }

                            @Override
                            public void onAdOpened() {
                                // Code to be executed when the ad is displayed.
                            }

                            @Override
                            public void onAdLeftApplication() {
                                // Code to be executed when the user has left the app.
                            }

                            @Override
                            public void onAdClosed() {
                                // Code to be executed when when the interstitial ad is closed.
                                adCloseListener.onAdClosed();
                            }
                        });
                    }*/
                }
            }

            @Override
            public void onFailure(Call<AdResponse> call, Throwable t) {

            }
        });

    }

    public interface AdCloseListener {
        void onAdClosed();
    }
}
