package com.huameijuyyan.app.videon.ui.home;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.huameijuyyan.app.videon.data.local.commondatalistresponse.Datum;
import com.huameijuyyan.app.videon.data.remote.home.RemoteVideoApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MostRecentDataSource extends PageKeyedDataSource<Integer, Datum> {
    RemoteVideoApiInterface getDataService;
    //  int totalNoPage;
    //the size of a page that we want
    int PAGE_SIZE;

    //we will start from the first page which is 1
    int FIRST_PAGE = 1;

    MostRecentDataSource(RemoteVideoApiInterface getDataService) {
        this.getDataService = getDataService;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Datum> callback) {
        Call<Datum> result = getDataService.getRecentVideo("www", FIRST_PAGE);

        result.enqueue(new Callback<Datum>() {
            @Override
            public void onResponse(Call<Datum> call, Response<Datum> response) {

                PAGE_SIZE = response.body().getTotalPageNo();

                if (HomeActivity.sHomeActivity != null) {
                    HomeActivity.sHomeActivity.mBinding.progressBarMostRecent.setVisibility(View.GONE);
                  //  HomeActivity.sHomeActivity.mBinding.swipeRefresh.setRefreshing(false);
                }

                callback.onResult(response.body().getData(),
                        null, PAGE_SIZE > FIRST_PAGE ? FIRST_PAGE + 1 : null);

            }

            @Override
            public void onFailure(Call<Datum> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {
        Call<Datum> result = getDataService.getRecentVideo("www",
                params.key);

        result.enqueue(new Callback<Datum>() {
            @Override
            public void onResponse(Call<Datum> call, Response<Datum> response) {
//                Log.e("response_merchant", " " + response.body());
//                Log.e("response_merchant", "url " + response.raw().request().url());

                //   PAGE_SIZE = response.body().getTotalPageNo();
                //    callback.onResult(response.body().getMerchantEntityList(), null,  FIRST_PAGE + 1);


                //if the current page is greater than one
                //we are decrementing the page number
                //else there is no previous page
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.body() != null) {

                    //passing the loaded data
                    //and the previous page key
                    callback.onResult(response.body().getData(), adjacentKey);
                }

            }

            @Override
            public void onFailure(Call<Datum> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {

        Call<Datum> result = getDataService.getRecentVideo("www",
                params.key);

        result.enqueue(new Callback<Datum>() {
            @Override
            public void onResponse(Call<Datum> call, Response<Datum> response) {
//                Log.e("response_merchant", " " + params.key);
//                Log.e("response_merchant", "url " + PAGE_SIZE);

                //   PAGE_SIZE = response.body().getTotalPageNo();
                //    callback.onResult(response.body().getMerchantEntityList(), null,  FIRST_PAGE + 1);

                if (response.body() != null) {
                    //if the response has next page
                    //incrementing the next page number
                    Integer key = PAGE_SIZE == params.key ? null : params.key + 1;

                    //passing the loaded data and next page value
                    callback.onResult(response.body().getData(), key);
                }

            }

            @Override
            public void onFailure(Call<Datum> call, Throwable t) {

            }
        });

    }
}
