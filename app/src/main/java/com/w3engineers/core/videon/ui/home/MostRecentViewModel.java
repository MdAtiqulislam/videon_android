package com.w3engineers.core.videon.ui.home;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.w3engineers.core.videon.data.local.commondatalistresponse.Datum;
import com.w3engineers.core.videon.data.remote.RemoteApiProvider;
import com.w3engineers.core.videon.data.remote.home.RemoteVideoApiInterface;
import com.w3engineers.ext.strom.application.ui.base.BaseRxViewModel;

public class MostRecentViewModel extends BaseRxViewModel {
    LiveData<PagedList<Datum>> dataList;

    public MostRecentViewModel() {
        init();
    }

    private void init() {
        RemoteVideoApiInterface service = RemoteApiProvider.getInstance().getRemoteHomeVideoApi();

        MostRecentDataFactory dataFactory = new MostRecentDataFactory(service);
        PagedList.Config paConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(5)
                .build();

        dataList = (new LivePagedListBuilder(dataFactory, paConfig)).build();
    }

    public LiveData<PagedList<Datum>> getRecentLiveData() {
        return dataList;
    }

}
